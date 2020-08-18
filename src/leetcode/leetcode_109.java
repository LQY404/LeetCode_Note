package leetcode;

import java.util.LinkedList;

/*
USER：LQY
DATE：2020/8/18
TIME：8:43
*/
public class leetcode_109 {

    public static void main(String[] args){

    }
//    建立完全二叉树。二分法
//    每次都找到List[s:e)的中间位置 p 作为当前树的根，
//    然后同样的方式递归处理List[s:p)和List[p.next:e)。
//    不是很难
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        int size = 0;
        ListNode p = head;

//        先处理第一个根，特殊处理
//        计数

        while(p != null){
            size++;
            p = p.next;
        }
        if(size == 1){
            return new TreeNode(head.val);
        }
        int index = 0;
        p = head;
//      找到中间值
        while(index<size/2 && p!=null){
            index++;
            p = p.next;
        }
        TreeNode root = new TreeNode(p.val);
//        再处理[s,p)与[p.next, e)。
//        处理一样，只是要递归处理所以另外开辟一个函数。
        solve(root, head, p, -1);
        solve(root, p.next, null, 1);

        return root;
    }
//    flag = 1表示找到的 p 放到当前root的右子树。
//    flag = -1则放到root的左子树
//  这个函数还可以改进一下：增加一个每一段长度的参数，就可以免除下面的对[s, e)的计数。
    void solve(TreeNode root, ListNode s, ListNode e, int flag){
        if(root==null || s==null || s==e) return;
        int size = 0;
        ListNode p = s;
        while(p != e){
            size++;
            p = p.next;
        }
//        if(size == 0) return;

        p = s;
        int index = 0;
        while(index<size/2 && p!=null){
            index++;
            p = p.next;
        }
        switch(flag){
            case -1:
                root.left = new TreeNode(p.val);
                solve(root.left, s, p, -1);
                solve(root.left, p.next, e, 1);
                break;
            default:
                root.right = new TreeNode(p.val);
                solve(root.right, s, p, -1);
                solve(root.right, p.next, e, 1);
        }

    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
