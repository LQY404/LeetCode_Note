package leetcode;

/*
USER：LQY
DATE：2020/8/15
TIME：10:27
*/
public class leetcode_546_important {

    public static void main(String []args){
        int []boxes = {1,3,2,2,2,3,4,3,1};
        new leetcode_546_important().removeBoxes(boxes);
    }
    int n;
    public int removeBoxes(int[] boxes) {

        n = boxes.length;
        if(n <= 1) return n;
        int [][][]dp = new int [101][101][101]; //dp[i][j][k]：[j->n)之间还有k个与boxes[j]相同的数。
        // 并且消除这k个boxes以及[i->j]这一段可以获得的最高分保存到dp[i][j][k]中

        return solve(boxes, dp, 0, n-1, 0);
    }

    int solve(int []boxes, int [][][]dp, int l, int r, int k){
        if(l >= r) return 0;
        if(dp[l][r][k] != 0) return dp[l][r][k];

        while(l<r && boxes[r-1]==boxes[r]){
            k++;
            r--;
        }
//        r`代表r--之后的r
//        直接消去r`->r之间的k个与boxes[r]相同的数
//        如[6,3,6,5,6,6,6,6]。此时就直接将[5->8]的末尾4个6消去。
//        所以递归的时候，传进去 r-1 。
        dp[l][r][k] = solve(boxes, dp, l, r-1, 0) + (k+1)*(k+1);
//      除了上面的策略，因为[1->4]之间还有6存在，所以可能有更大的消去得分方法。

        for(int i = l;i < r;i++){
            if(boxes[i] == boxes[r]){
//                遍历[1->4]之间，找到其间的值为6的下标处，
//                max中第一个就是上面的方法：直接消去当前连续的6
//                max中的第二个就是考虑还有离散的6分布，所以就通过消除离散的6之间的数来使其连续，以达到最大得分。
//                假设此时的 i = 3。
                dp[l][r][k] = Math.max(
                        dp[l][r][k],
//                        前面一个是：消去从[1->3]之间的数以及[3->末尾的k+1]个6所能够得到的最大分。
//                        这里使用k+1而不是k是因为：i = 3也是6，而原本r->末尾处就已经有k个6
//                        后面一个是：直接消去[4->4]之间的数（这里只有一个5）
                        solve(boxes, dp, l, i, k+1)+solve(boxes, dp, i+1, r-1,0)
                );
            }

        }
        return dp[l][r][0];

    }

}
