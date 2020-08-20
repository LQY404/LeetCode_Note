class Solution {
public:
    int r,c;
    int dir[8][2] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};

    void dfs(vector<vector<char>>& board, int x, int y, int book[][50]){
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

    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        r = board.size();
        // if(r == 0){
        //     return board;
        // }
        c = board[0].size();
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        int book[50][50];
        for(int i = 0;i < 50;i++)
            for(int j = 0;j < 50;j++)
                book[i][j] = 0;
        book[x][y] = 1;
        dfs(board, x, y, book);
        return board;
    }
};