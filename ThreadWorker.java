package multiThread_calc;

import java.util.concurrent.Callable;

/**
 * Worker class.
 * Implements Callable interface, instead of Runnable,  because we need to return calculations results.
 * Implements call() method instead of run().
 */
public class ThreadWorker implements Callable<double[]> {

    private int _summands, _multiplicands;
    private Expression _expr;
    private boolean _isAvailable;

    /**
     * Constructor
     * @param summands - Number of elements to sum.
     * @param multiplicands - Number of elements to multiply.
     */
    public ThreadWorker(int summands, int multiplicands) {
        _summands = summands;
        _multiplicands = multiplicands;
        _isAvailable = true;
    }

    /**
     *
     * @param expr - Set the expression for work.
     */
    public synchronized void setTask(Expression expr) {
        _isAvailable = false;
        _expr = expr;
        _summands = expr.getNumOfSummands();
        _multiplicands = expr.getNumOfMultiplicands();
    }

    @Override
    public double[] call() throws Exception {
        double res1 = 1 , res2 = 0;
        int count = 0;
        // TODO: Change to "while" loop
        for (; _multiplicands <= _expr._nMul; _multiplicands++) {
            res1 *= Math.pow(-1, _expr._pow * _multiplicands) / (2 * _multiplicands + _expr._const);
            _expr.incrementMultiplicands();
            count++;
            if(count == _multiplicands){
                break;
            }
        }
        count = 0;
        // TODO: Change to "while" loop
        for (; _summands <= _expr._nSum; _summands++) {
            res2 += _summands / ((2 * Math.pow(_summands, 2)) + 1);
            _expr.incrementSummands();
            count++;
            if(count == _summands){
                break;
            }
        }

        double arr[] = {res1, res2};
        _isAvailable = true;
        return arr;
    }

    public boolean isAvailable() {
        return _isAvailable;
    }
}
