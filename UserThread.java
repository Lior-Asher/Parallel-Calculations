package multiThread_calc;

import java.util.ArrayList;
import java.util.List;

public class UserThread extends Thread {
    private static ArrayList<Expression> _expr1ToString, _expr2ToString;
    private Feeder _feeder;
    private PoolManager _pm;

    /**
     * Constructor
     * @param k - The number of expressions of the form (1.1) that have to be evaluated.
     * @param r - The number of expressions of the form (1.2) that have to be evaluated.
     * @param n_list - The k values of n for each expression (1.1) that have to be evaluated.
     * @param l_list - The r values of ` for each expression (1.2) that have to be evaluated.
     * @param m_list - The r values of m for each expression (1.2) that have to be evaluated.
     * @param t - The size of the task data structure of the PoolManager.
     * @param s - The number of summands that a single PoolThread can carry out.
     * @param m - The number of multiplicands that a single PoolThread can carry out.
     * @param p - Number of available threads.
     */
    public UserThread(int k , int r , List<Integer> n_list , List<Integer> l_list , List<Integer> m_list , int t , int s , int m , int p) {
        _expr1ToString = new ArrayList<Expression>();
        _expr2ToString = new ArrayList<Expression>();
        _pm = PoolManager.createPoolManager(p, t, s, m);
        _feeder = new Feeder(_pm);
        _feeder.executeTasks(n_list, l_list, m_list);
    }

    /**
     * Fill expression lists for printing.
     * @param expr - An expression to add to the printing list.
     */
    public static void fillPrintLists(Expression expr){
        if(expr instanceof Expression1)
            _expr1ToString.add(expr);
        else
            _expr2ToString.add(expr);
    }

    @Override
    public void run() {
        try {
            while(!_feeder.isDone()){
                sleep(500); // Wait 0.5 a second.
            }
            for (Expression expr : _expr1ToString) {
                System.out.println("_expr1ToString");//DEBUG

                System.out.println(expr);
            }
            System.out.println();
            for (Expression expr : _expr2ToString) {
                System.out.println("_expr2ToString");//DEBUG

                System.out.println(expr);
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR!\n");
            e.printStackTrace();
        }
    }
}
