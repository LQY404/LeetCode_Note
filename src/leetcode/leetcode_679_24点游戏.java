package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/*
USER：LQY
DATE：2020/8/22
TIME：9:32
*/
public class leetcode_679_24点游戏 {

    public static void main(String []args){
        int []nums = {1, 2, 3, 4};
        System.out.println(new leetcode_679_24点游戏().judgePoint24(nums));
    }

    public boolean judgePoint24(int[] nums) {

        boolean ans = false;
        LinkedList<Double> list = new LinkedList<>();
        for(int i: nums){
            list.addLast((double)i);
        }

        return solve2(list, Math.pow(10, -6));
    }
//    每次取出两个数，分别做+-*/。做完后放入列表，并将做运算的两个数从列表弹出。
//    可以一直递归，知道列表中仅剩下1个数。
//    考虑到实数除法，所以当结果与24相差很小（这里使用ep来衡量）时即可认为两数相等。
    boolean solve2(LinkedList<Double> list, Double ep){
        if(list.size() == 0) return false;
        if(list.size() == 1){
//            if(Math.abs(list.get(0)-24) <= ep) return true;
            return Math.abs(list.get(0)-24) <= ep;
        }
        boolean ans = false;
        int len = list.size();
        for(int i = 0;i < len;i++){
            for(int j = 0;j < len;j++){
                if(i == j) continue;
                double n1 = list.get(i);
                double n2 = list.get(j);

                LinkedList<Double> list2 = new LinkedList<>();
//                使用list2而不是直接在list上remove
                for(int k = 0;k < len;k++){
                    if(k==i || k==j) continue;
                    list2.add(list.get(k));
                }
//                list2.add(n);

//                +-*/
                for(int k = 0;k < 4;k++){
                    switch(k){
                        case 0:
                            list2.addLast(n1+n2);
                            break;
                        case 1:
                            list2.addLast(n1-n2);
                            break;
                        case 2:
                            list2.addLast(n1*n2);
                            break;
                        default:
                            if(Math.abs(n2-0) <= ep) continue;
                            list2.addLast(n1/n2);
                    }
                    // if(k == 0){
                    //     //+
                    //     list2.addLast(n1+n2);
                    // }else if(k == 1){
                    //     list2.addLast(n1-n2);
                    // }else if(k == 2){
                    //     list2.addLast(n1*n2);
                    // }else{
                    //     if(Math.abs(n2-0) <= ep) continue;
                    //     list2.addLast(n1/n2);
                    // }
                    if(solve2(list2, ep)) return true;

                    list2.removeLast();

                }
            }
        }

        return ans;

    }
    boolean solve(int []nums, int []book, char op){
//        num.addLast(nums[index]);
        boolean ans = false;
        if(num.size() == 4){
            if(cacu() == 24) return true;
            return false;
        }

        for(int i = 0;i < 4;i++){
            if(book[i] == 1) continue;
            num.addLast(nums[i]);
            oper.addLast(op);
            book[i] = 1;
            ans = ans || solve(nums, book, '+');
            book[i] = 0;
            oper.removeLast();
            num.removeLast();
        }

        for(int i = 0;i < 4;i++){
            if(book[i] == 1) continue;
            num.addLast(nums[i]);
            oper.addLast(op);
            book[i] = 1;
            ans = ans || solve(nums, book, '-');
            book[i] = 0;
            oper.removeLast();
            num.removeLast();
        }
        for(int i = 0;i < 4;i++){
            if(book[i] == 1) continue;
            num.addLast(nums[i]);
            oper.addLast(op);
            book[i] = 1;
            ans = ans || solve(nums, book, '*');
            book[i] = 0;
            oper.removeLast();
            num.removeLast();
        }
        for(int i = 0;i < 4;i++){
            if(book[i] == 1) continue;
            num.addLast(nums[i]);
            oper.addLast(op);
            book[i] = 1;
            ans = ans || solve(nums, book, '/');
            book[i] = 0;
            oper.removeLast();
            num.removeLast();
        }
        return ans;



    }

    int cacu(){
        String ans = "";
//        System.out.println("num:"+num.size()+",oper:"+oper.size());
        int index = 0;
        while(index < 3){
            System.out.print(num.get(index)+""+oper.get(index++));
//            ans += num.removeFirst() + oper.removeFirst();
        }
//        ans += num.removeLast();
        System.out.println(num.get(index));
        return 0;
    }
    LinkedList<Integer> num = new LinkedList<>();
    LinkedList<Character> oper = new LinkedList<>();



}
