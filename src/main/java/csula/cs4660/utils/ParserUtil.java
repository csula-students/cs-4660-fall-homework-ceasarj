package csula.cs4660.utils;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by ceejay562 on 9/8/2016.
 */
public class ParserUtil {

    public static List<List<Integer>> readFile(File file){
        List<List<Integer>> numbers = Lists.newArrayList();
        try(Stream<String> lines = Files.lines(file.toPath())){
            lines.forEach(line ->{
                List<Integer> lineList = Lists.newArrayList();
                for(String lineNumber: line.split(":")){
                    lineList.add(Integer.parseInt(lineNumber));
                }
                numbers.add(lineList);
            });
        } catch(Exception e){
            e.printStackTrace();
        }
        return numbers;
    }

    public static void main(String[] arg){
        ClassLoader classLoader = (ParserUtil.class).getClassLoader();
        if(classLoader == null)
            System.out.println("Nuklk");
        if(classLoader.getResource("homework-1/graph-1.txt").getFile() == null)
            System.out.println("Resource nukl");
        File file1 = new File(classLoader.getResource("homework-1/graph-1.txt").getFile());
        File file2 = new File((ParserUtil.class).getResource("homework-1/graph-2.txt").getFile());

        readFile(file1);
    }

}
