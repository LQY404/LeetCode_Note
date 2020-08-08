package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
USER：LQY
DATE：2020/8/8
TIME：8:50
*/
public class leetcode_99 {

    public static void main(String []args){

    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> queue = new ArrayList<>();
        LinkedList<TreeNode> index = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode q = queue.remove(0);
            index.addFirst(q);
            if(q == null) continue;
//            if(q.left==null && q.right==null) break;
            queue.add(q.left);
            queue.add(q.right);
        }

//        queue.add(root);
        int i = 0;
        while(i < index.size()){
            TreeNode p = index.get(i++);
            if(p == null) continue;
            if(p.left==null && p.right==null) continue; //叶子节点

            solve(p, p.left, -1);
            solve(p, p.right, 1);

            if(f == 1) i = 0;  //有交换的就复原，从最后一层非叶子节点重新开始。缺点在于这种方法比较耗时。。。
            // lf = rf = 0;
            f = 0;

        }

    }
    // int lf = 0;
    // int rf = 0;
    int f = 0;
    void solve(TreeNode root, TreeNode now, int flag){
        if(now == null) return;
        if(flag == -1){
            if(now.val > root.val){
                f = 1;
                // if(flag == -1) lf = 1;
                // else rf = 1;
                int tem = root.val;
                root.val = now.val;
                now.val = tem;
                // return;
            }
        }else if(flag == 1){
            if(now.val < root.val){
                f = 1;
                // if(flag == -1) lf = 1;
                // else rf = 1;
                int tem = root.val;
                root.val = now.val;
                now.val = tem;
                // return;
            }
        }
        solve(root, now.left, flag);
        solve(root, now.right, flag);
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v){val = v;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
