class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        dirs = [[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]]
        r = len(board)
        if r == 0:
            return board
        c = len(board[0])

        x, y = click[0], click[1]
        if board[x][y] == 'M':
            board[x][y] = 'X'
            return board

        book = [[0 for _ in range(c)] for _ in range(r)]

        def dfs(board: List[List[str]], x: int, y: int, book: List[List[int]], r: int, c: int, dirs:List[List[int]]):

            count = 0
            for i in range(8):
                nx = x + dirs[i][0]
                ny = y + dirs[i][1]
                if nx<0 or nx>=r or ny<0 or ny>=c:
                    continue
                if board[nx][ny] == 'M':
                    count += 1

            if count > 0:
                board[x][y] = str(count)
                # board[x][y] += ""
            else:
                board[x][y] = 'B'

                for i in range(8):
                    nx = x + dirs[i][0]
                    ny = y + dirs[i][1]
                    if nx<0 or nx>=r or ny<0 or ny>=c:
                        continue
                    if book[nx][ny] == 1:
                        continue
                    book[nx][ny] = 1
                    dfs(board, nx, ny, book, r, c, dirs)

        dfs(board, x, y, book, r, c, dirs)

        return board