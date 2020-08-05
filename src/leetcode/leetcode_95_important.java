package leetcode;

import java.util.LinkedList;
import java.util.List;

/*
USER：LQY
DATE：2020/7/21
TIME：8:39
*/
public class leetcode_95_important {

    public static void main(String []args){
        new leetcode_95_important().generateTrees(3);
    }
    public List<TreeNode> generateTrees(int n){
        List<TreeNode> ans = new LinkedList<>();
        if(n == 0) return ans;

        ans = solve(1, n);
        for(TreeNode r : ans){
            printTree(r);
            System.out.println();
        }
        System.out.println("\n"+ans.size());
        return ans;

    }
    public void v1(List<TreeNode> ans, TreeNode root, int start, int end){

        if(start>end || start<=0) {
            root = null;
            return;
        }
//        if(start == end){
//            root = new TreeNode(start);
//            return;
//        }

        for(int i = start;i <= end;i++){
            root.val = i;
            root.left = new TreeNode(-1);
            root.right = new TreeNode(-1);
            v1(ans, root.left, start, i-1);
            v1(ans, root.right, i+1, end);
            ans.add(root);
        }
    }
    public void printTree(TreeNode root){
        if(root != null){
            System.out.print(root.val+" ");
            printTree(root.left);
            printTree(root.right);
        }
    }
    public List<TreeNode> solve(int start, int end){
        List<TreeNode>ans = new LinkedList<>();
        if(start > end){
            ans.add(null);  //it's very important.
            return ans;
        }

        for(int i = start; i <= end;i++){
            List<TreeNode> leftTree = solve(start, i-1);
            List<TreeNode> rightTree = solve(i+1, end);

            for(TreeNode t: leftTree){
                for(TreeNode r: rightTree){
                    TreeNode c = new TreeNode(i);
                    c.left = t;
                    c.right = r;
                    System.out.println("add tree");
                    printTree(c);
                    ans.add(c);
                }
            }
        }
        return ans;


    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){};
        TreeNode(int val){this.val = val;};
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
