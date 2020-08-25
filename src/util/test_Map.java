package util;

import java.util.*;

/*
USER：LQY
DATE：2020/8/25
TIME：10:46
*/
public class test_Map {

    public static void main(String []args){
        Map<List<Integer>, Integer> an = new HashMap<>();
        LinkedList<Integer> l1 = new LinkedList(Arrays.asList(1, 2, 3));
        an.put(l1,1);
        LinkedList<Integer> l2 = new LinkedList(Arrays.asList(1, 2, 3,3));
        System.out.println(an.containsKey(l2));

    }


}
