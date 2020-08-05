package leetcode;

/*
USER：LQY
DATE：2020/8/5
TIME：9:47
*/
public class leetcode_337 {

    public static void main(String []args){
//        leetcode_337 ins = new leetcode_337();
//        System.out.println(ins.rob())
    }
    public int rob(TreeNode root) {
        if(root == null) return 0;

//        int vl = solve(root.left);
//        int vr = solve(root.right);

        int rv = solve(root);

        return rv;
//        return Math.max(vl+vr+root.val, rv);
    }

//   参数root：表示这次偷节点root。
//    总体思路为：root.val + （在左子树可以偷到的总和） + （在右子树可以偷到的总和）。
//    唯一需要注意的地方：对于任意的节点root，有两种选择：偷与不偷。
//    如果偷，则可以获得的总和为：
//    root.val + （在左子树可以偷到的总和） + （在右子树可以偷到的总和）
//    如果不偷，则在计算总和时，直接跳过root即可，但是总体的计算公式还是一样。
//    这两种情况体现在下面solve函数的return语句中：
//    max(lv + rv + root.val, solve(root.left)+solve(root.right))。
//
    public int solve(TreeNode root){
        //此时root为null，没有什么可以偷的。直接返回0。
        if(root == null) return 0;
//      此时可以偷root（返回root.val）。但是发现root后面没有东西可以偷了。所以直接返回root.val即可。
        if(root.left==null && root.right==null) return root.val;

        // int leftv = root.val + Math.max(solve(root.left.left), solve(root.left.right)) ;
        // int rightv = root.val + Math.max(solve(root.right.left), solve(root.right.right));
        int lv = 0;  //存放左子树剩余的那边可以偷到的数目。
        int rv = 0;  //存放右子树
//        如果左右都还有东西可以偷，则使用DFS更新lv与rv。
        if(root.left!=null && root.right!=null){
            lv = ((solve(root.left.left) + solve(root.left.right)));
            rv = (solve(root.right.left) + solve(root.right.right));
        }else if(root.left == null){    //此时只有右边还可以偷，所以只需要且只能更新rv。
            rv = (solve(root.right.left) + solve(root.right.right));
        }else{                         //此时只有左边还可以偷，所以只需要且只能更新lv。
            lv = (solve(root.left.left) + solve(root.left.right));
        }
        //最后返回时取一个max：
//        前面的指的是偷当前节点root与其左右子树时可以获得的最大数目。
//        后面的指的是不偷当前的节点root，而转向偷其左右子树时可以获得的最大数目。
        return Math.max(lv + rv + root.val, solve(root.left)+solve(root.right));
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v){val = v;}
    }
}
