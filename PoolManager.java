package multiThread_calc;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PoolManager extends Thread {
    private static PoolManager _instance = null;
    private ArrayList<Expression> _tasks;
    private ArrayList<ThreadWorker> _pool;
    private boolean _isLocked;
    private int _tasksCount;
    private int _poolSize;
    private int _summands;
    private int _multiplicands;
    private ReentrantLock _reentrantLock;
    private Condition _condition;


    /**
     * Constructor
     * @param t - Number of tasks.
     * @param p - Number of available threads.
     * @param s - Number of summands.
     * @param m - Number of multiplicands.
     */
    public PoolManager(int t, int p, int s, int m) {
        _tasksCount = t;
        _poolSize = p;
        _summands = s;
        _multiplicands = m ;
        _reentrantLock = new ReentrantLock();
        _condition = _reentrantLock.newCondition();
        _tasks = new ArrayList<Expression>(_tasksCount);
        _pool = new ArrayList<ThreadWorker> (_poolSize);

        for (int i = 0; i < _poolSize; i++)
            _pool.add(new ThreadWorker(_summands, _multiplicands));
    }

    // Singleton design pattern.
    public static PoolManager createPoolManager(int p , int t , int s , int m){
        if(_instance == null){
            _instance = new PoolManager(p , t , s , m);
        }
        return _instance;
    }

    /**
     *
     * @param expr - Expression to evaluate.
     * @return - true if the expression has been added to the tasks list.
     * 			 else - false
     */
    public boolean isTaskAdded(Expression expr){
        if(!_isLocked){
            try{
                _reentrantLock.lock();
                _tasks.add(expr);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally{
                _condition.signalAll();
                _reentrantLock.unlock();
            }
            return true;
        }
        if(_tasks.size() == _tasksCount){
            _isLocked = true;
        }
        return false;
    }

    /**
     * Removes task from the list.
     * @param i - Index to remove.
     */
    public void removeTask(int i){
        try{
            _reentrantLock.lock();
            _tasks.remove(i);
            _isLocked = false;
        }
        catch(Exception e) {
            System.out.println("ERROR!\n");
            e.printStackTrace();
        }
        finally{
            _condition.signalAll();
            _reentrantLock.unlock();
        }
    }

    /**
     *
     * @return - true if the CS is busy.
     */
    public boolean isLocked(){
        return _isLocked;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < _poolSize; i++) {
                _pool.get(i).setTask(_tasks.get(i));
                _tasks.get(i)._result.updateProgress(_pool.get(i).call()); // Report partial results.
            }
            while(!_tasks.isEmpty()){
                for (int i = 0; i < _tasks.size(); i++) {
                    for (int j = 0; j < _poolSize; j++) {
                        if(_pool.get(j).isAvailable()){
                            ThreadWorker thread = _pool.get(j);
                            Expression expr = _tasks.get(i);

                            thread.setTask(expr);
                            expr._result.updateProgress(thread.call()); // Report partial results.
                        }
                    }
                    if(_tasks.get(i).isDone()){
                        UserThread.fillPrintLists(_tasks.get(i));
                        removeTask(i);
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("ERROR!\n");
            e.printStackTrace();
        }
    }
}
