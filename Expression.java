package multiThread_calc;

// Most basic task/calculation to perform.
public abstract class Expression {
    protected Result _result;
    protected int _pow, _nMul, _nSum, _summands, _multiplicands;
    protected int _const;

    /**
     * Constructor
     * @param nThreads - Number of threads.
     * @param constVar - Constant variable for the calculation.
     * @param nMul - Number of multiplicands.
     * @param nSum - Number of summands.
     */
    public Expression(int nThreads, int constVar, int nMul, int nSum) {
        _result = new Result();
        _pow = nThreads;
        _const = constVar;
        _nMul = nMul;
        _nSum = nSum;
        _summands = 1;
        _multiplicands = 1;
    }

    // Public methods.
    public void incrementSummands() {
        _summands++;
    }

    public int getNumOfSummands() {
        return _summands;
    }

    public void incrementMultiplicands() {
        _multiplicands++;
    }

    public int getNumOfMultiplicands() {
        return _multiplicands;
    }

    /**
     * For printing purposes.
     */
    public abstract String toString();

    /**
     *
     * @return - true if calculation is complete, otherwise - false.
     */
    public abstract boolean isDone();
}
