package csula.cs4660.games.models.Tron;

/**
 * Created by ceejay562 on 12/3/2016.
 */
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

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        String dir = "RIGHT";

        boolean[][] grid = new boolean[MAX_Y + 1][MAX_X + 1];
        // game loop
        while (true) {
            int n = in.nextInt(); // total number of players (2 to 4).
            int p = in.nextInt(); // your player number (0 to 3).
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
                    System.err.println(x + " " + y);
                }
                grid[y1][x1] = true;
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            dir = getGreedyScore(grid, dir, y, x);
            System.out.println(dir);
        }
    }

    public static boolean bounds(String dir,int  y, int x){
        //System.err.println()
        if(dir.equals("LEFT") && x <= MIN_X) return true;
        if(dir.equals("RIGHT") && x >= MAX_X) return true;
        if(dir.equals("DOWN") && y >= MAX_Y) return true;
        if(dir.equals("UP") && y <= MIN_Y ) return true;

        return false;
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
            score2 = getLeftScore(grid, y, x);

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
            System.err.println("Left Score = " + score);
            //if(score == 6) return score;
        }
        return score;
    }

    public static int getRightScore(boolean[][] grid, int y, int x){
        int score = 1;

        while(!bounds("RIGHT", y , x+ score) && !grid[y][x+ score]){
            System.err.println("RIght Score" + score + "," + y);
            score++;
            //if(score == 6) return score;
        }
        return score;
    }

    public static int getUpScore(boolean[][] grid, int y, int x){
        int score = 1;

        while(!grid[y - score][x] && !bounds("UP", y - score, x)){
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
}