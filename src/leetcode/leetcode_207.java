package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
USER：LQY
DATE：2020/8/4
TIME：8:20
*/
public class leetcode_207 {

    public static void main(String []args){
        int n = 2;
        int [][] pre = {
//                {1,0}
//                {0,1}
//                {1,0},{2,6},{1,7},{5,1},{6,4},{7,0},{0,5}
                {1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}
        };
        System.out.println(new leetcode_207().canFinish1(7, pre));
    }
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        int []enterd = new int[numCourses];

        //记录每个点的入度
        for(int i = 0;i < n;i++)
            enterd[prerequisites[i][0]]++;
        //将入度为0的点加入队列
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0;i < numCourses;i++){
            if(enterd[i] == 0) queue.add(i);
        }
        //bfs
        int count = 0;
        while(!queue.isEmpty()){
            int sp = queue.removeFirst();
//            enterd[sp]--;
            count++;  //遍历的点统计
            for(int i = 0;i < n;i++){
                if(prerequisites[i][1] != sp) continue;  //此边的起点不是当前的点时可以直接continue。
                enterd[prerequisites[i][0]]--;   //此时的边i一定是以sp为起点的边，所以将终点的入度减一
                if(enterd[prerequisites[i][0]] == 0)  //如果入度以及减为0，则满足入队条件，可以入队。
                    queue.addLast(prerequisites[i][0]);
            }

        }
        //如果统计的遍历过的节点数与想要学习的课程数目一样则返回true
        return count==numCourses;


    }
//    int n;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;

        int []dot = new int[numCourses];
        int []book = new int[numCourses];

        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> looked = new LinkedList<>();
        //找出入度为0的点
        for(int i = 0;i < n;i++){
            dot[prerequisites[i][0]]++;

        }
        //如果没有入度为0的点，false
//        int f = 1;
//        for(int i = 0;i < numCourses;i++){
//            if(dot[i] == 0){
//                f = 0;
//                System.out.println("点"+i+"的入度为0");
//            }
//
//        }

//        if(f == 1) return false;

        for(int i = 0;i < numCourses;i++){
            if(dot[i] != 0) continue;
//            if(looked.contains(i)) continue;
            //bfs
            queue.clear();
            Arrays.fill(book, 0);
            queue.add(i);
//            book[i] = i + 1;
            while(!queue.isEmpty()){
                int sp = queue.removeFirst();

                for(int j = 0;j < n;j++){

                    if(prerequisites[j][1] != sp) continue;
                    if(book[prerequisites[j][0]]==2 && book[sp]==2) return false;
                    System.out.println(sp+"->"+prerequisites[j][0]);
                    queue.addLast(prerequisites[j][0]);
                }
                //推迟到现在标记
                if(!looked.contains(sp)) looked.add(sp);
                book[sp]++;
            }

        }
        System.out.println("走过："+looked.size());
        for(int i : looked){
            System.out.print(i+" ");
        }
        if(looked.size() != numCourses) return false;
        //最后判断：如果有哪一个book[i] == 2，则说明有环。

        return true;
    }

}
