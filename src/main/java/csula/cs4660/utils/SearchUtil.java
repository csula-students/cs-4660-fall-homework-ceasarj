package csula.cs4660.utils;

import com.google.common.collect.Lists;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ceejay562 on 10/12/2016.
 */
public class SearchUtil {

    public static List<Edge> getPath(Map<Node, Edge> parents, Node source, Node dist){
        List<Edge> path = Lists.newArrayList();

        Node curr = dist;
        do{
            Edge e = parents.get(curr);
            path.add(e);
            curr = e.getFrom();
        }while(!curr.equals(source));
        Collections.reverse(path);
        return path;
    }

}
