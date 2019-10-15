package multiThread_calc;

public class Result {
    private double _res1, _res2;

    /**
     * Constructor
     */
    public Result() {
        _res1 = 1;
        _res2 = 0;
    }

    /**
     * Updates calculations' progress.
     * @param partialResults - Array of results.
     */
    public void updateProgress(double partialResults[]){
        _res1 *= partialResults[0];
        _res2 += partialResults[1];
    }

    /**
     * @return calculation result.
     */
    public double getResult(){
        return _res1 + _res2;
    }
}
