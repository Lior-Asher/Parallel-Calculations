package multiThread_calc;

public class Expression1 extends Expression {
    /**
     * Constructor
     * @param nElements - Number of elements to calculate.
     */
    public Expression1(int nElements) {
        super(1,  0, nElements , 0);
    }

    @Override
    public String toString() {
        String str = String.format("Expr. type (1.1), n = %s: < %s >", _nSum, _result.getResult());
        return str;
    }

    @Override
    // True if all calculations are done.
    public boolean isDone() {
        return (_nSum + 1 == _multiplicands);
    }
}
