package leetcode;

/*
USER：LQY
DATE：2020/7/15
TIME：15:30
*/
public class leetcode_96 {
    public static void main(){


    }

//    这个题的思路很重要。
//    定义：dp[i]表示以1->i的i个节点组成的二叉搜索树的个数。
//    很显然，按照搜索树的定义：当前的节点值比左子树中的任意节点值都大，比右子树中的任意节点值都小。
//    所以，当总节点个数为n时，若当前根节点的值为i时，则左子树还有i-1个节点可以安排，即还有dp[i-1]种情况；
//    右子树还有n-i-1个节点可以安排，即还有dp[n-i-1]种情况。所以，以值为i为根节点这种情况时的二叉搜索树有dp[i-1]*dp[n-i-1]种。
//    而i可以从1到n取值，所以，当总结点个数为n个时，可以构成的二叉搜索树的总个数就是：
//    从i=1到i=n的一个累加。
//    实现则如下
    public int numTrees(int n) {
        int []dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1;i <= n;i++){

            for(int j = 0;j < i;j++){
                dp[i] += dp[j]*dp[i-j-1];
            }
        }

        return dp[n];
    }
}
