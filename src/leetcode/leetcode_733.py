class Solution:
    def floodFill(self, image: List[List[int]], sr: int, sc: int, newColor: int) -> List[List[int]]:
        s = len(image)
        if s == 0:
            return image
        c = len(image[0])

        que = []
        book = [[0 for i in range(c)] for j in range(s)]

        dir = [[-1,0],[0,1],[1,0],[0,-1]]

        que.append([sr,sc])
        # print(len(que))
        col = image[sr][sc]

        while len(que)!=0:
            x,y = que[0][0], que[0][1]
            que.pop(0)
            book[x][y] = 1
            image[x][y] = newColor
            for i in range(4):
                nr = dir[i][0] + x
                nc = dir[i][1] + y

                if nr<0 or nr>=s or nc<0 or nc>=c:
                    continue
                if book[nr][nc]==1 or image[nr][nc]!=col:
                    continue

                que.append([nr,nc])


        return image