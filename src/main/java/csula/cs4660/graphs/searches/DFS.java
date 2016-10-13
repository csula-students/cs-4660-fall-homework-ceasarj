package csula.cs4660.graphs.searches;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.SearchUtil;

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

        s.push(source);
        v.add(source);

        Node current = source;

        while(!current.equals(dist)){
            boolean noNeighbors = true;
            for(Node neighbor: graph.neighbors(current)){
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

        return SearchUtil.getPath(parent, source, dist);
    }
}
