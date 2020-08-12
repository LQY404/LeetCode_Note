package leetcode;

import java.util.Arrays;

/*
USER：LQY
DATE：2020/8/11
TIME：10:52
*/
public class leetcode_130 {

    public static void main(String []args){
        char [][]board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
//                {'O','O','0','O','X','X'},
//                {'O','O','O','O','O','O'},
//                {'O','X','O','X','O','O'},
//                {'O','X','O','O','X','O'},
//                {'O','X','O','X','O','O'},
//                {'O','X','O','O','O','O'}
        };
        new leetcode_130().solve1(board);
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[0].length;j++)
                System.out.print(board[i][j]+" ");
            System.out.println();
        }
    }
    int r,c;

    public void solve1(char [][]board){
        r = board.length;
        if(r == 0) return;
        c = board[0].length;
        int [][]book = new int[r][c];

        int flag = 1;
        for(int i = 0;i < c;i++){
            if(board[0][i] == 'X') continue;
            if(book[0][i] != 0) continue;  //已经被标记过
            book[0][i] = (flag++);
            merge(board, book, book[0][i], 0, i);

        }
        for(int i = 1;i < r;i++){
            if(board[i][0] == 'X') continue;
            if(book[i][0] != 0) continue;  //已经被标记过
            book[i][0] = (flag++);
            merge(board, book, book[i][0], i, 0);

        }
        for(int i = 1;i < c;i++){
            if(board[r-1][i] == 'X') continue;
            if(book[r-1][i] != 0) continue;  //已经被标记过
            book[r-1][i] = (flag++);
            merge(board, book, book[r-1][i], r-1, i);

        }
        for(int i = 1;i < r-1;i++){
            if(board[i][c-1] == 'X') continue;
            if(book[i][c-1] != 0) continue;  //已经被标记过
            book[i][c-1] = (flag++);
            merge(board, book, book[i][c-1], i, c-1);

        }
        for(int i = 0;i < r;i++){
            for(int j = 0;j < c;j++){
                if(book[i][j]==0 && board[i][j]=='O') board[i][j] = 'X';
            }
//            System.out.println();
        }

    }
    void merge(char [][]board, int [][]book, int flag, int nr, int nc){
        if(nr>=r || nr<0 || nc>=c || nc<0) return;
        if(board[nr][nc] == 'X') return;

        book[nr][nc] = flag;

        for(int i = 0;i < 4;i++){
            int rr = nr + dir[i][0];
            int rc = nc + dir[i][1];
            if(rr>=r || rr<0 || rc>=c || rc<0) continue;

            if(book[rr][rc] != 0) continue;
            if(board[rr][rc] == 'X') continue;

            merge(board, book, flag, rr, rc);
        }
    }

    public void solve(char[][] board) {
        r = board.length;
        if(r == 0) return;
        c = board[0].length;
        int [][]book = new int[r][c];

        for(int i = 1;i < r;i++){
            for(int k = 0;k < r;k++)
                Arrays.fill(book[k], 0);

            for(int j = 1;j < c;j++){
                if(board[i][j] == 'X') continue;
                book[i][j] = 1;
                if(find(board, i, j, book)){
                    System.out.println("find at ("+i+","+j+")");
                    board[i][j] = 'X';
                }
            }
        }
    }
    int [][]dir = {{-1,0},{0,1},{1,0},{0,-1}};
    public boolean find(char [][]board, int nr, int nc, int [][]book){
        if(nr>=r || nr<0 || nc>=c || nc<0) return false;
//        if(board[nr][nc]=='X' && (nr>0 && nr<r-1 && nc>0 && nc<c-1)) return true;
        if((nr==r-1 || nr==0 || nc==c-1 || nc==0) && board[nr][nc]=='O') return false;

        boolean ans = true;
        for(int i = 0;i < 4;i++){
            int rr = nr + dir[i][0];
            int rc = nc + dir[i][1];
            if(rr>=r || rr<0 || rc>=c || rc<0) continue;
            if(board[rr][rc] == 'X') continue;
            if(book[rr][rc] == 1) continue;

            book[rr][rc] = 1;
            ans = ans && find(board, rr, rc, book);
            book[rr][rc] = 0;
        }
        return ans;
    }

}
