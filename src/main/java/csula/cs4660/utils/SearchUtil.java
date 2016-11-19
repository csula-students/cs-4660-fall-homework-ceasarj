package csula.cs4660.utils;

import com.google.common.collect.Lists;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import csula.cs4660.graphs.NodeWeight;

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

    public static List<Edge> getPathDijkstra(Map<Node, NodeWeight> parents, Node source, Node dist){
        List<Edge> path = Lists.newArrayList();

        Node curr = dist;
        do{
            Edge e = parents.get(curr).getEdge();
            path.add(e);
            System.out.println(path.size());
            curr = e.getFrom();
        }while(!curr.equals(source));
        Collections.reverse(path);
        return path;
    }
}