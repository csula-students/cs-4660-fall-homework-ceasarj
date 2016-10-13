package csula.cs4660.graphs.searches;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.*;

/**
 * Breadth first search
 */
public class BFS implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        Queue<Node> q = new LinkedList<>();
        Set<Node> v = Sets.newHashSet();
        Map<Node, Edge> parent = Maps.newHashMap();
        List<Edge> path = Lists.newArrayList();

        // source equals destination
        if(source.equals(dist)) return path;

        q.add(source);
        v.add(source);

        boolean found = false;

        while(!q.isEmpty() && !found) {
            Node curr = q.poll();
            List<Node> n = graph.neighbors(curr);

            for(Node node: n){ // destination found
                if(node.equals(dist)) {
                    parent.put(
                            node,
                            new Edge(curr, node, graph.distance(curr, node))
                    );
                    found = true;
                    break;
                } else if(!v.contains(node)) { // if not visited then visit node
                    q.add(node);
                    v.add(node);
                    parent.put(
                            node,
                            new Edge(curr, node, graph.distance(curr, node))
                    );
                }
            }

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
