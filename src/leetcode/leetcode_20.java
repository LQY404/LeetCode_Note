package leetcode;

import java.util.LinkedList;

/*
USER：LQY
DATE：2020/8/14
TIME：8:27
*/
public class leetcode_20 {

    public static void main(String []args){

    }
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        int n = s.length();
        for(int i = 0;i < n;i++){
            char ar = s.charAt(i);
//            if(ar=='(' || ar=='{' || ar=='['){
//                stack.addLast(ar);
//                continue;
//            }

            switch(ar){
                default:
                    stack.addLast(ar);
                    break;
                case ')':
                    if(!stack.isEmpty() && stack.getLast() == '(') stack.removeLast();
                    else return false;
                    break;
                case ']':
                    if(!stack.isEmpty() && stack.getLast() == '[') stack.removeLast();
                    else return false;
                    break;
                case '}':
                    if(!stack.isEmpty() && stack.getLast() == '{') stack.removeLast();
                    else return false;
                    break;

            }

        }
        return stack.isEmpty();
    }
}
