package leetcode;

import java.util.*;

/*
USER：LQY
DATE：2020/8/27
TIME：8:16
*/
public class leetcode_332_重新安排行程 {

    public static void main(String []args){
        List<List<String>> tickles = new LinkedList<>();
//        tickles.add(Arrays.asList("MUC","LHR"));
//        tickles.add(Arrays.asList("JFK","MUC"));
//        tickles.add(Arrays.asList("SFO","SJC"));
//        tickles.add(Arrays.asList("LHR","SFO"));
        tickles.add(Arrays.asList("JFK","AAA"));
        tickles.add(Arrays.asList("AAA","JFK"));
        tickles.add(Arrays.asList("JFK","BBB"));
        tickles.add(Arrays.asList("JFK","CCC"));
        tickles.add(Arrays.asList("CCC","JFK"));
        new leetcode_332_重新安排行程().findItinerary1(tickles);
    }

    Map<String, PriorityQueue<String>> graph = new HashMap<>();  //连接表。使用优先队列解决字母序的问题

    public List<String> findItinerary1(List<List<String>> tickets) {
        if(tickets==null || tickets.size()==0) return ans;


        //建立邻接表
        for(List l: tickets){
            String res = (String)l.get(0);
            String des = (String)l.get(1);
            if(!graph.containsKey(res)) graph.put(res, new PriorityQueue<>());

            graph.get(res).offer(des);
        }
        dfs1("JFK");
        for(String s: ans){
            System.out.print(s+" ");
        }
        Collections.reverse(ans);  //因为是逆序处理的，所以最终结果需要调转。
        return ans;
    }
    void dfs1(String res){
        while(graph.containsKey(res) && graph.get(res).size()>0){  //先处理当前点的边
            String des = graph.get(res).poll();
            dfs1(des);
        }
//        最后再处理当前点
        ans.add(res);
    }






    List<String> ans = new LinkedList<>();
    LinkedList<String> tem = new LinkedList<>();   //这里一定要使用linkedlist，
    // 因为如果只是使用list的话，在remove指定元素的时候，我们是想要移除最后一个元素（因为这个要想移除的指定元素
//    一定在队尾。而list.remove(object)在list中有重复元素的时候就不一定移除队尾元素了！！！！
    int []book;
    int len;
    public List<String> findItinerary(List<List<String>> tickets) {

        len = tickets.size();
        book = new int[len];
        dfs(tickets, 1, 0);
        System.out.println("\nthe answer is :");
        for(String s: ans){
            System.out.print(s+" ");
        }
        return ans;
    }

//    复杂度太高，无法AC
    void dfs(List<List<String>> tickets, int flag, int index){

        if(flag == 1){

            for(int i = 0;i < len;i++){
                Arrays.fill(book, 0);
                tem.clear();
                flag = 0;
                if(!tickets.get(i).get(0).equals("JFK")) continue;
                //开始查找
                System.out.println("start at "+tickets.get(i));
                book[i] = 1;
                tem.addLast("JFK");
                dfs(tickets, flag, i);
                flag = 1;

            }
        }else{
//            System.out.println(tickets.get(index).get(0)+"->"+tickets.get(index).get(1));
//            非起点
            if(isok()){
                tem.addLast(tickets.get(index).get(1));
                System.out.println("find a load:");
                for(String s: tem){
                    System.out.print(s+" ");
                }
                System.out.println();
//                if(ans.isEmpty()) ans = new LinkedList<>(tem);
                ans = new LinkedList<>(tem);
//                else{
//                    for(int i = 0;i <= len;i++){
//                        if(tem.get(i).compareTo(ans.get(i)) > 0) break;
//                        if(tem.get(i).compareTo(ans.get(i)) < 0){
//                            System.out.println(tem.get(i)+" better than "+ans.get(i));
//                            ans = new LinkedList<>(tem);
//                            break;
//                        }
//                    }
//                }
                tem.removeLast();
                return;
            }
            for(int i = 0;i < len;i++){
                if(book[i]==1 ||
                        !tickets.get(i).get(0).equals(tickets.get(index).get(1))
                ) continue;

                book[i] = 1;
                tem.addLast(tickets.get(index).get(1));
                if(ans.isEmpty() || tem.getLast().compareTo(ans.get(ans.size()-1))<0) dfs(tickets, flag, i);
                tem.removeLast();
                book[i] = 0;

            }
        }

    }
    boolean isok(){
        for(int i = 0;i < len;i++){
            if(book[i] == 0) return false;

        }
        return true;
    }

}
