package leetcode;

import java.util.LinkedList;

/*
USER：LQY
DATE：2020/8/20
TIME：8:47
*/
public class leetcode_529 {

    public static void main(String []args){
        char [][]board = {
//                {'B', '1', 'E', '1', 'B'},
//                {'B', '1', 'M', '1', 'B'},
//                {'B', '1', '1', '1', 'B'},
//                {'B', 'B', 'B', 'B', 'B'}
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int []click = {3, 0};
        char [][]nboard = new leetcode_529().updateBoard1(board, click);

        for(int i = 0;i < nboard.length;i++){
            for(int j = 0;j < nboard[0].length;j++){
                System.out.print(nboard[i][j]+" ");
            }
            System.out.println();
        }

    }
    int [][]dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}}; //八个方向
    int r,c;
//    DFS可以AC
    public char[][] updateBoard1(char[][] board, int[] click){
        r = board.length;
        if(r == 0) return board;

        c = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int [][]book = new int[r][c];
        int x = click[0];
        int y = click[1];
        book[x][y] = 1;
        dfs(board, x, y, book);

        return board;
    }
    void dfs(char [][]board, int x, int y, int [][]book){

//        book[x][y] = 1;
        int count = 0;
        for(int i = 0;i < 8;i++){
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
            if(board[nx][ny] == 'M'){
                count++;

            }
        }

        if(count > 0){
            board[x][y] = (char)(count+'0');
//            return;
        }else{
            board[x][y] = 'B';
            for(int i = 0;i < 8;i++){
                int nr = x + dir[i][0];
                int nc = y + dir[i][1];
                if(nr<0 || nr>=r || nc<0 || nc>=c) continue;
                if(book[nr][nc] == 1) continue;
                book[nr][nc] = 1;
                dfs(board, nr, nc, book);
            }
        }

    }


//    bfs超时
    public char[][] updateBoard(char[][] board, int[] click) {
        r = board.length;
        if(r == 0) return board;

        c = board[0].length;


//        直接点到地雷上
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
//        广搜策略：遇到M停止
        LinkedList<int[]> que = new LinkedList<>();
        int [][]book = new int[r][c];

        que.addLast(click);
        while(!que.isEmpty()){
            int []p = que.removeFirst();
            book[p[0]][p[1]] = 1;
//            先更新其本身：
//            如果是‘E’，而且不与地雷相邻，则改为'B'；如果与地雷相邻，则统计八个方向上的地雷数count，将其改为count
//            先统计看看有没有地雷周围（!!!!同时可以将周围的加入队列!!!
//              不行！！！：如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
//               所以很清楚，只有当前的是'E'才将其相邻的块加入队列递归。  所以需要两遍 ）
//            统计：
            int count = 0;
            for(int i = 0;i < 8;i++){
                int nr = p[0] + dir[i][0];
                int nc = p[1] + dir[i][1];
                if(nr<0 || nr>=r || nc<0 || nc>=c) continue;
                if(board[nr][nc] == 'M'){
                    count++;
//                    book[nr][nc] = 1;
                    continue;
                }
//                if(book[nr][nc]==1) continue;
//                加入队列
//                que.add(new int[]{nr,nc});

            }
            if(count > 0){
                board[p[0]][p[1]] = (char)(count+'0');
                continue;  //直接下一遍
            }else{
                board[p[0]][p[1]] = 'B';
            }
//            加入
            for(int i = 0;i < 8;i++){
                int nr = p[0] + dir[i][0];
                int nc = p[1] + dir[i][1];
                if(nr<0 || nr>=r || nc<0 || nc>=c) continue;
                if(book[nr][nc] == 1) continue;
                que.add(new int[]{nr, nc});
            }


         }



        return board;

    }
}
