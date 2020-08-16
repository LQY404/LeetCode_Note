package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/*
USER：LQY
DATE：2020/8/16
TIME：8:49
*/
public class leetcode_733 {

    public static void main(String []args){
        int [][]image = {{0,0,1},{0,1,1}};
        int sr = 1;
        int sc = 1;
        int nec = 1;
        new leetcode_733().floodFill1(image, sr, sc, nec);
    }
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        int r = image.length;
        if(r == 0) return image;
        int c = image[0].length;
        LinkedList<Integer[]> que = new LinkedList<>();
        int [][]book = new int[r][c];
        int [][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int i = 0;i < r;i++) Arrays.fill(book[i],-1);
        que.addLast(new Integer[]{sr,sc});
        int color = image[sr][sc];

        while(!que.isEmpty()){
            Integer []p = que.removeFirst();
            book[p[0]][p[1]] = newColor;

            for(int i = 0;i < 4;i++){
                int nr = p[0] + dir[i][0];
                int nc = p[1] + dir[i][1];
                if(nr<0 || nr>=r || nc<0 || nc>=c) continue;
                if(book[nr][nc]!=-1 || image[nr][nc]!=color) continue;
                que.addLast(new Integer[]{nr,nc});

            }
        }

        for(int i = 0;i < r;i++){
            for(int j = 0;j < c;j++){
                if(book[i][j] != -1) continue;
                book[i][j] = image[i][j];
            }
        }
        return book;
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int r = image.length;
        if(r == 0) return image;
        int c = image[0].length;
        LinkedList<Integer[]> que = new LinkedList<>();
        int [][]book = new int[r][c];
        int [][]dir = {{1,0},{0,1},{-1,0},{0,-1}};
        que.addLast(new Integer[]{sr,sc});
//        book[sr][sc] = 1;

        int color = image[sr][sc];

        while(!que.isEmpty()){
            Integer []p = que.removeFirst();
            image[p[0]][p[1]] = newColor;
            book[p[0]][p[1]] = 1;
            for(int i = 0;i < 4;i++){
                int nr = p[0] + dir[i][0];
                int nc = p[1] + dir[i][1];

                if(nr<0 || nr>=r || nc<0 || nc>=c) continue;
                if(book[nr][nc]==1 || image[nr][nc]!=color) continue;
//                if(image[nr][nc] != color) continue;
                que.addLast(new Integer[]{nr,nc});
                System.out.println("add:"+nr+","+nc);

            }
        }

        return image;
    }
}
