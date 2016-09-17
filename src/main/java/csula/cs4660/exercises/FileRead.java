package csula.cs4660.exercises;

import java.io.*;

/**
 * Introduction Java exercise to read file
 */
public class FileRead {
    private int[][] numbers;
    /**
     * Read the file and store the content to 2d array of int
     * @param file read file
     */
    public FileRead(File file) {
        numbers = new int[5][8];
        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        try {
            int row = 0;
            while((line = br.readLine()) != null){
                String[] lineInts = line.split(" ");
                for(int i= 0; i<lineInts.length; i++){
                    numbers[row][i] = Integer.parseInt(lineInts[i]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the file assuming following by the format of split by space and next
     * line. Display the sum for each line and tell me
     * which line has the highest mean.
     *
     * lineNumber starts with 0 (programming friendly!)
     */
    public int mean(int lineNumber) {
        int mean = 0;
        int currMean = 0;
        for(int col=0; col <numbers[lineNumber].length; col++){
            currMean += numbers[lineNumber][col];
        }
        currMean /= numbers[lineNumber].length;
        mean = (currMean > mean)?currMean:mean;

        return mean;
    }

    public int max(int lineNumber) {
        int max = 0;
        for(int num: numbers[lineNumber]){
            max = (max < num)?num:max;
        }
        return max;
    }

    public int min(int lineNumber) {
        int max = 0;
        for(int num: numbers[lineNumber]){
            max = (max > num)?num:max;
        }
        return max;
    }

    public int sum(int lineNumber) {
        int max = 0;
        for(int num: numbers[lineNumber]){
            max += num;
        }
        return max;
    }
}