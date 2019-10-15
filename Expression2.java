package multiThread_calc;

public class Expression2 extends Expression{
    /**
     * Constructor
     * @param nMul - Number of multiplicands.
     * @param nSum - Number of summands (not typo, this is the proper math term).
     */
    public Expression2(int nMul , int nSum) {
        super(3, 1, nMul, nSum);
    }

    @Override
    public String toString() {
        String str = String.format("Expr. type (1.2), nMul = %s nSum = %s: < %s >", _nMul, _nSum, _result.getResult());
        return str;
    }

    @Override
    public boolean isDone() {
        return (_nMul + 1 == _multiplicands && _nSum + 1 == _summands);
    }
}
