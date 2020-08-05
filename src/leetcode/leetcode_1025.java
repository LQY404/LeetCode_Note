package leetcode;

/*
USER：LQY
DATE：2020/7/24
TIME：8:04
*/
public class leetcode_1025 {

    public static void main(String []args){
        new leetcode_1025().divisorGame(3);
    }
    public boolean divisorGame(int N) {

        int []book = new int[N+1];

        boolean []dp = new boolean[N+1];
//       使用非精简版时需要如下初始化 。否则不需要初始化，保持全零即可。
        dp[1] = false;
        dp[2] = true;
        book[1] = 1;
        book[2] = 2;
        boolean res = solve(N, 1, book, dp);
        System.out.println(res);
        return res;
    }
//    非精简版
    public boolean solve(int N, int flag, int []book, boolean []dp){
        if(N <= 2) return dp[N] ^ (flag==1);
        if(book[N] == 1) return dp[N];
        book[N] = 1;
        boolean res = false;
        if(N%2 != 0){
            for(int i = N-2;i >= 1;i -= 2){
                if(N%i != 0) continue;
                res = res || solve(N-i, -flag, book, dp);
            }
        }else{
            res = res || solve(N-1, -flag, book, dp);
        }

        return dp[N] = res;

    }
//    精简版。省去了dp数组，节省了内存空间
//    使用book数组来记忆化。空间换时间。
//    book[N] == 0表示：数N时的博弈结果还未知。
//    book[N] == 1表示在前面的博弈过程中，数N已经出现过，此时必定已经记录了数字为N时的结果。直接返回即可。
//    同时，book[N] == 1也表示数字为N时，爱丽丝失败。
//    book[N] == 2表示：数字为N时，爱丽丝取胜。
    public boolean solve(int N, int flag, int []book){
        if(N <= 2) return (book[N]==2) ^ (flag==1);

        if(book[N] == 1) return book[N]==2;  //此时直接返回，因为已经记录了结果，不需要重复计算。
        //没有记录过数N时的结果。
        book[N] = 1;  //因为当前就是要来计算数N时的博弈结果的，所以将book[N]标记为1（而不是2！！！）
        boolean res = false;  //数为N时的博弈结果暂存变量。初始肯定为false。
//        有两种取数策略：
//        ①如果当前的N为奇数。那么“我”只能在（0，N）之间取出一个奇数x才能满足：N%x == 0 的条件。
        if(N%2 != 0){
//            所以使用一个for循环遍历，类似查找。
            for(int i = N-2;i >= 1;i -= 2){
                if(N%i != 0) continue;
                res = res || solve(N-i, -flag, book);
            }
        }
//        ②如果当前的N为偶数，那么“我”可以直接就取出一个1就可以了
//        当然，如果想取出其他可以整除N的偶数也可，但是不符合博弈心理：“我”要想方设法赢。
//        所以，该题并不是简单的遍历。。。
        else{
            res = res || solve(N-1, -flag, book);
        }
        book[N] = res ? 2 : 1;  //如果res为真，设book[N]=2来记录。
        return res;

    }
}
