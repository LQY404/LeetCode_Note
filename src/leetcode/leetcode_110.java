package leetcode;

/*
USER：LQY
DATE：2020/8/17
TIME：8:37
*/
public class leetcode_110 {

    public static void main(String []args){

    }
    public boolean isBalanced(TreeNode root) {
        solve(root);
        return ans;
    }
    boolean ans = true;
    int solve(TreeNode root){
        if(root == null) return 0;


        int leftd = solve(root.left) + 1;
        int rightd = solve(root.right) + 1;

        ans = ans && (Math.abs(leftd-rightd)<=1);

        return Math.max(leftd, rightd);
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v){val = v;}
    }
}
