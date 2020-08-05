package leetcode;

import com.sun.org.glassfish.gmbal.ParameterNames;

import java.util.*;

/*
USER：LQY
DATE：2020/7/29
TIME：8:59
*/
public class LCR_13_important {

    public static void main(String []args){

    }
    int row;
    int col;
    public int minimalSteps(String[] maze) {
        row = maze.length;
        col = maze[0].length();
        int ans = 0;
//        存放M
        List<int []> ms = new ArrayList<>();
//        存放O
        List<int []> os = new ArrayList<>();
//        存放起点
        int sr = 0;
        int sc = 0;
//        存放终点
        int er = row;
        int ec = col;
//        初始化
        for(int i = 0;i < row;i++){
            for(int j = 0;j < col;j++){
                char nowc = maze[i].charAt(j);
                if(nowc=='#' || nowc=='.') continue;
                else if(nowc == 'M'){
                    ms.add(new int[]{i, j});
                }else if(nowc == 'O'){
                    os.add(new int[]{i, j});
                }else if(nowc == 'S'){
                    sr = i;
                    sc = j;
                }else if(nowc == 'T'){
                    er = i;
                    ec = j;
                }
            }
        }
//        初始化起点到M、O以及终点的最短距离
        int [][]dic = dfs(maze, sr, sc);



        return ans;
    }

    int [][]dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};  //上、右、下、左
    public int[][] dfs(String []maze, int sr, int sc){

        int [][]ans = new int[row][col];
        for(int i = 0;i < row;i++)
            Arrays.fill(ans[i], -1);
        ans[sr][sc] = 0;
        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});

        while(!queue.isEmpty()){
            int []p = queue.poll();
            int nx = p[0];
            int ny = p[1];
            for(int i = 0;i < 4;i++){
                int nextx = nx + dir[i][0];
                int nexty = ny + dir[i][1];
//                ①不越界。②可以走。③没有走过
                if(!isOutRange(nextx, nexty) && maze[nextx].charAt(nexty)!='#' && ans[nextx][nexty]==-1){
                    ans[nextx][nexty] = ans[nx][ny] + 1;
                    queue.offer(new int[]{nextx, nexty});
                }
            }
        }
        return ans;
    }
//    是否越界
    public boolean isOutRange(int nx, int ny){
        if(nx<0 || nx>=row || ny<0 || ny>=col) return true;
        return false;
    }
}
