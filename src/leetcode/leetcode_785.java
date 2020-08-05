package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
USER：LQY
DATE：2020/7/16
TIME：8:34
*/
public class leetcode_785 {

    public static void main(String []args){
        int [][]graph = {
                {},
                {2,4,6},
                {1,4,8,9},
                {7,8},
                {1,2,8,9},
                {6,9},
                {1,5,7,8,9},
                {3,6,9},
                {2,3,4,6,9},
                {2,4,5,6,7,8}
        };
        System.out.println("\n"+new leetcode_785().isBipartite(graph));
    }
    public boolean isBipartite(int[][] graph) {

        int lens = graph.length;

        int []paint = new int[lens];
        int []book = new int[lens];
//        DFS
//        for(int i = 0;i < lens;i++){
//            if(book[i] == 1) continue;
//            book[i] = 1;
//            dfs(graph, paint, book, i);
//        }
        //BFS
//        LinkedList<Integer> queen = new LinkedList<>();
//        for(int k = 0;k < lens;k++){
//            if(book[k] == 1) continue;
//            queen.addLast(k);
//            while(!queen.isEmpty()){
//                int nv = queen.removeFirst();
////            book[nv] = 1;
//                //添加邻边的同时染色
//                int f1 = 0;
//                int f2 = 0;
////            int f3 = 0;
//                for(int i = 0;i < graph[nv].length;i++){
//                    int next = graph[nv][i];
//                    if(paint[next] == 1) f1 = 1;
//                    if(paint[next] == 2) f2 = 2;
////                if(paint[i] == 3) f3 = 3;
//                    if(book[next] == 1) continue;
//                    queen.addLast(next);
//                    book[next] = 1;
//
//                }
//                //染色
//                if(f1 == 0){
//                    paint[nv] = 1;
//                }else if(f2 == 0){
//                    paint[nv] = 2;
//                }else{
//                    paint[nv] = 3;
//                }
//                System.out.println("点"+nv+"被染成："+paint[nv]);
//            }
//        }
        for(int i : paint){
            System.out.print(i+" ");
        }
        for(int i = 0;i < lens;i++){
            if(paint[i] == 3){
                return false;
            }
        }
        return true;
    }
    public void dfs(int [][]graph, int []paint, int[]book, int nv) {
        if(paint[nv] != 0) return;
        //先判断该点处是否染色
        int f1 = 0;
        int f2 = 0;
//            int f3 = 0;
        //统计邻边染色情况
        for(int i = 0;i < graph[nv].length;i++){
            int next = graph[nv][i];
            if(paint[next] == 1) f1 = 1;
            if(paint[next] == 2) f2 = 2;
//                if(paint[i] == 3) f3 = 3;
        }
        //染色
        if(f1 == 0){
            paint[nv] = 1;
        }else if(f2 == 0){
            paint[nv] = 2;
        }else{
            paint[nv] = 3;
        }
        System.out.println("点"+nv+"被染成："+paint[nv]);

        //深搜
        for(int i = 0;i < graph[nv].length;i++){
            int next = graph[nv][i];
            if(book[next] == 1) continue;
            book[next] = 1;
            dfs(graph, paint, book, next);
        }
    }
}
