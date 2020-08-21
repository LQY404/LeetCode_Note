package leetcode;

/*
USER：LQY
DATE：2020/8/21
TIME：9:53
*/
public class leetcode_111 {

    public int minDepth_simple(TreeNode root){
        if(root == null) return 0;

        int left = minDepth_simple(root.left);
        int right = minDepth_simple(root.right);

//      左右都不为空，很明显取最小的那个
        if(root.left!=null && root.right!=null){
            return Math.min(left, right) + 1;
        }else{//至少有一个为空时，left/right中至少有一个为0，所以返回最大的那个
            return Math.max(left, right) + 1;
        }
//        return (root.left==null || root.right==null) ? left+right+1 : Math.min(left, right) + 1;
    }

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        solve(root, 1);
        return ans;
    }
    int ans = Integer.MAX_VALUE;

    void solve(TreeNode root, int k){
        if(root==null || (root.left==null&&root.right==null)){
            ans = Math.min(ans, k);
            return;
        }
        if(root.left == null){
            solve(root.right, k+1);
        }else if(root.right == null){
            solve(root.left, k+1);
        }else{
            solve(root.left, k+1);
            solve(root.right, k+1);
       }


    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v){val = v;}
    }
}
