package multiThread_calc;

import java.util.ArrayList;
import java.util.List;

public class Feeder {

    private ArrayList<Expression> _tasks; // Expressions List.
    private PoolManager _pm; // PoolManager object reference.

    /**
     * Constructor
     * @param pm - PoolManager object.
     */
    public Feeder(PoolManager pm) {
        _tasks = new ArrayList<Expression>();
        _pm = pm;
    }

    /**
     * @param n_list - List of elements for expression 1.1
     * @param l_list - List of elements for expression 1.2
     * @param m_list - List of elements for expression 1.2
     */
    public void executeTasks(List<Integer> n_list , List<Integer> l_list , List<Integer> m_list) {
        for (int num : n_list)
            _tasks.add(new Expression1(num));

        for (int i = 0; i < l_list.size(); i++)
            _tasks.add(new Expression2(l_list.get(i) , m_list.get(i)));

        _pm.isTaskAdded(_tasks.remove(0));

        loadTasks();
    }

    /**
     * @return - true if the PoolManger has completed his work.
     */
    public boolean isDone() {
        return !_pm.isAlive();
    }

    private void loadTasks() {
        try {
            _pm.start();
            while(!_tasks.isEmpty()){
                Expression expr = _tasks.get(0);
                if(_pm.isTaskAdded(expr)) {
                    System.out.println("loadTasks");//DEBUG

                    _tasks.remove(0);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR!\n");
            e.printStackTrace();
        }
    }
}
