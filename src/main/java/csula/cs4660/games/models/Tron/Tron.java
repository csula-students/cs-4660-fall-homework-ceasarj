import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Tron {
    public static final int MIN_X = 0;
    public static final int MAX_X = 29;
    public static final int MIN_Y = 0;
    public static final int MAX_Y = 19;

    public static final String[] DIR = {"LEFT", "UP" , "RIGHT", "DOWN"};

    static int area0 = 0;
    static int area1 = 0;
    static int area2 = 0;
    static int area3 = 0;
    static int area4 = 0;
    static int area5 = 0;

    static Coordinate riders[];

    public static class Coordinate {
        int x;
        int y;

        public Coordinate(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        String dir = "RIGHT";

        boolean[][] grid = new boolean[MAX_Y + 1][MAX_X + 1];


        int counter = 0;
        // game loop
        while (true) {
            int n = in.nextInt(); // total number of players (2 to 4).
            int p = in.nextInt(); // your player number (0 to 3).
            riders = new Coordinate[n - 1];
            int x = 0;
            int y = 0;
            for (int i = 0; i < n; i++) {
                int x0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int x1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                if(p == i){
                    x = x1;
                    y = y1;
                } else {
                    if(i < p) riders[i] = new Coordinate(y1, x1) ;
                    if(i > p) riders[i - 1] = new Coordinate(y1, x1) ;
                }
                grid[y1][x1] = true;
                grid[y0][x0] = true;
            }
            if(counter % 10 ==0){
                findOpenArea(grid);
                printAreas();
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            dir = getDirection(grid, dir, y, x);

            System.out.println(dir);

            counter++;
        }
    }

    static void printAreas(){
        System.err.println(area0);
        System.err.println(area1);
        System.err.println(area2);
        System.err.println(area3);
        System.err.println(area4);
        System.err.println(area5);
    }

    public static boolean bounds(String dir,int  y, int x){
        //System.err.println()
        if(dir.equals("LEFT") && x <= MIN_X) return true;
        if(dir.equals("RIGHT") && x >= MAX_X) return true;
        if(dir.equals("DOWN") && y >= MAX_Y) return true;
        if(dir.equals("UP") && y <= MIN_Y ) return true;

        return false;
    }

    public static String determineTurn(String dir, int y, int x){
        return null;
    }

    /**
     * Searching Depth for best move
     * */
    public static String getGreedyScore(boolean[][] grid, String dir,int y,int x){
        int score1 = 0;
        int score2 = 0;
        int score3 = 0;

        if(dir.equals("LEFT")){
            score1 = getLeftScore(grid, y, x);
            score2 = getUpScore(grid, y, x);
            score3 = getDownScore(grid, y, x);

            return getBestDirection(score1, "LEFT", score2, "UP", score3, "DOWN");
        } else if(dir.equals("RIGHT")){
            score1 = getRightScore(grid, y, x);
            score2 = getUpScore(grid, y, x);
            score3 = getDownScore(grid, y, x);

            return getBestDirection(score1, "RIGHT", score2, "UP", score3, "DOWN");
        } else if(dir.equals("UP")){
            score1 = getRightScore(grid, y, x);
            score2 = getUpScore(grid, y, x);
            score3 = getLeftScore(grid, y, x);

            return getBestDirection(score1, "RIGHT", score2, "UP", score3, "LEFT");
        } else {
            score1 = getRightScore(grid, y, x);
            score2 = getDownScore(grid, y, x);
            score3 = getLeftScore(grid, y, x);

            return getBestDirection(score1, "RIGHT", score2, "DOWN", score3, "LEFT");
        }
    }

    public static String getBestDirection(int s1, String d1, int s2, String d2, int s3, String d3){
        if(s1 > s2) {
            if(s1 > s3) return d1;
            else return d3;
        } else {
            if(s2 > s3) return d2;
            else return d3;
        }
    }

    public static int getLeftScore(boolean[][] grid, int y, int x){
        int score = 1;
        while(!bounds("LEFT", y, x - score) && !grid[y][x- score]){
            score++;
            //if(score == 6) return score;
        }
        return score;
    }

    public static int getRightScore(boolean[][] grid, int y, int x){
        int score = 1;

        while(!bounds("RIGHT", y , x+ score) && !grid[y][x+ score]){
            score++;
            //if(score == 6) return score;
        }
        return score;
    }

    public static int getUpScore(boolean[][] grid, int y, int x){
        int score = 1;

        while(!bounds("UP", y - score, x) && !grid[y - score][x]){
            score++;
            //System.err.println(x + score);
            //if(score == 6) return score;
        }

        return score;
    }

    public static int getDownScore(boolean[][] grid, int y, int x){
        int score = 1;

        while( !bounds("DOWN", y+ score, x) && !grid[y + score][x]){
            score++;
            //if(score == 6) return score;
        }

        return score;
    }

    public static void findOpenArea(boolean[][] grid){
        int x0 = 0;
        int x1 = 10;
        int x2 = 20;
        int y0 = 0;
        int y1 = 10;

        area0 = 0;
        area1 = 0;
        area2 = 0;
        area3 = 0;
        area4 = 0;
        area5 = 0;

        for(int x = 0; x < 10; x++){
            for(int y=0; y<10; y++){
                if(!grid[y0 + y][x0 + x]) area0 += 1;
                if(!grid[y0 + y][x1 + x]) area1 += 1;
                if(!grid[y0 + y][x2 + x]) area2 += 1;
                if(!grid[y1 + y][x0 + x]) area3 += 1;
                if(!grid[y1 + y][x1 + x]) area4 += 1;
                if(!grid[y1 + y][x2 + x]) area5 += 1;
            }
        }

        for(int i=0; i<riders.length; i++){
            balanceArea(riders[i].x, riders[i].y);
        }
    }

    public static void balanceArea(int y, int x){
        switch(riderGrid(y, x)){
            case 0:
                area0 -= 10;
                break;
            case 1:
                area1 -= 10;
                break;
            case 2:
                area2 -= 10;
                break;
            case 3:
                area3 -= 10;
                break;
            case 4:
                area4 -= 10;
                break;
            case 5:
                area5 -= 10;
                break;
        }
    }

    public static String getDirection(boolean grid[][], String dir, int y, int x){
        if(dir.equals("LEFT")){
            if(x != 0 && !grid[y][x - 1]) return "LEFT";
        } else if(dir.equals("RIGHT")){
            if(x != MAX_X && !grid[y][x + 1]) return "RIGHT";
        } else if(dir.equals("UP")){
            if(y != 0 && grid[y - 1][x]) return "UP";
        } else {
            if(y != MAX_Y && !grid[y + 1][x]) return "DOWN";
        }
        return getGreedyScore(grid, dir, y, x);
    }

    /***
     * Determine where the riders are at.
     */
    public static int riderGrid(int y, int x){
        // top cells
        if(y < 10){
            if(x < 10) return 0;
            if(x < 20) return 1;
            return 2;
        } else {
            if(x < 10) return 3;
            if(x < 20) return 4;
            return 5;
        }
    }
}