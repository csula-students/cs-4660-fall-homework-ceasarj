package csula.cs4660.graphs.searches;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;

/**
 * Depth first search
 */
public class DFS implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        Map<Node, Edge> parent = Maps.newHashMap();
        Stack<Node> s = new Stack<>();
        Set<Node> v = Sets.newHashSet();
        List<Edge> path = Lists.newArrayList();

        s.push(source);
        v.add(source);

        Node current = source;

        while(!current.equals(dist)){
            List<Node> n = graph.neighbors(current);
            boolean noNeighbors = true;
            for(Node neighbor: n){
                if(!v.contains(neighbor)){
                    s.add(neighbor);
                    v.add(neighbor);
                    parent.put(
                            neighbor,
                            new Edge(current, neighbor, graph.distance(current, neighbor))
                    );
                    current = neighbor;
                    noNeighbors = false;
                    break;
                }
            }
            if(noNeighbors) current = s.pop();
        }

        // backtrack
        Node curr = dist;
        do{
            Edge e = parent.get(curr);
            path.add(e);
            curr = e.getFrom();
        }while(!curr.equals(source));
        Collections.reverse(path);
        return path;
    }
}
