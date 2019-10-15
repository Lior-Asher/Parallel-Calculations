package multiThread_calc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        int p = 3 , t = 5 , s = 2 , m = 3;
//        List<Integer> a = new ArrayList<Integer>();	a.add(1); a.add(6); a.add(120);a.add(10); a.add(105); a.add(38);
//        List<Integer> b = new ArrayList<Integer>();	b.add(10); b.add(56); b.add(26); b.add(40);	b.add(80); b.add(796); b.add(2996); b.add(404);
//        List<Integer> c = new ArrayList<Integer>(); c.add(100); c.add(9); c.add(37); c.add(14);c.add(1010); c.add(968); c.add(370); c.add(2114);
//        UserThread ut = new UserThread(3 , 4 , a , b , c , t , s , m , p);

        int p = 3 , t = 3 , s = 2 , m = 3;
        List<Integer> a = new ArrayList<Integer>();	a.add(1); a.add(6);
        List<Integer> b = new ArrayList<Integer>();	b.add(10); b.add(56);
        List<Integer> c = new ArrayList<Integer>(); c.add(100); c.add(9);
        UserThread ut = new UserThread(3 , 4 , a , b , c , t , s , m , p);

        ut.start();
    }
}
