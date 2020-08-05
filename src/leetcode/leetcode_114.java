package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

/*
USER：LQY
DATE：2020/8/2
TIME：9:25
*/
public class leetcode_114 {

//    public static void main(String []args){
//        TreeNode root = new TreeNode(1);
//
//    }

    public void flatten(TreeNode root) {
        solve(root);
    }
    public void solve(TreeNode root){
        if(root == null) return;
        if(root.left==null && root.right==null) return;

        solve(root.left);
        solve(root.right);

        //转换
        // 情况1
        if(root.left!=null && root.right!=null){
            // 因为是递归，所以此时root的左右子树必定都是右单边树（即题目要求的链表树）。
            // 在左右子树都存在的情况下，采用：
            // ① 先将右子树挂载到左子树的最右下无右子树的节点（其实就是左子树“链表”的最后一个节点）上：
            // 先找到最后一个节点
            TreeNode tem = root.left;
            while(tem.right != null){
                tem = tem.right;
            }
            // 找到后把右子树挂上去
            tem.right = root.right;
            // ② 此时root没有右子树，只有一条左子树（左链表）。
            // 要满足题目要求，则需要再将左子树转换为右子树。
            root.right = root.left;
            // 转换后再将左子树置为null
            root.left = null;
        }
        // 情况2
        else if(root.left!=null && root.right==null){
            // 和情况1一样，因为递归处理，所以此时root的左右子树（不管有没有）必定都是一个右单子树（链表）。
            // 所以在情况2下，直接将左子树转为右子树即可，不需要其他处理。
            root.right = root.left;
            root.left = null;
        }
        // 至于root.left==null && root.right!=null的情况
        // 就不需要处理了。。。
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}
        public TreeNode(int v){
            val = v;
        }
        public TreeNode(int v, TreeNode l, TreeNode r){
            val = v;
            left = l;
            right = r;
        }
    }
}
