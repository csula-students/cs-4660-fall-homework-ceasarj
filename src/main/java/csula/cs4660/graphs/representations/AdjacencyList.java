package csula.cs4660.graphs.representations;

import com.google.common.collect.Lists;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.util.*;

/**
 * Adjacency list is probably the most common implementation to store the unknown
 * loose graph
 *
 * TODO: please implement the method body
 */
public class AdjacencyList implements Representation {
    private Map<Node, Collection<Edge>> adjacencyList;

    public AdjacencyList(File file) {

    }

    public AdjacencyList() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
        Iterator<Edge> it = adjacencyList.get(x).iterator();
        while(it.hasNext()) {
            if(it.next().getTo().equals(y))
                return true;
        }
        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
        List<Node> neighbors = Lists.newArrayList();
        for(Edge edge: (Edge[])adjacencyList.get(x).toArray()){
            neighbors.add(edge.getTo());
        }
        return neighbors;
    }

    @Override
    public boolean addNode(Node x) {
        if(!adjacencyList.containsKey(x)){
            adjacencyList.put(x, new ArrayList<Edge>());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
        return false;
    }


    /**TODO:
     * **/
    @Override
    public boolean addEdge(Edge x) {
        Collection<Edge> edges = adjacencyList.get(x.getFrom());
        Collection<Edge> edgesTo = adjacencyList.get(x.getTo());
        if(!edges.contains(x.getFrom())) {
            edges.add(x);
            adjacencyList.put(x.getFrom(), edges );
            adjacencyList.put(x.getTo(), edgesTo );
        }
        /**TODO: deal with if the edges do not contain the list but need to remove the edge with different length
         * **/
        return true;
    }

    @Override
    public boolean removeEdge(Edge x) {
        Collection<Edge> edges = adjacencyList.get(x);
        if(edges.contains(x)) {
            adjacencyList.get(x).remove(x);
        }
        return false;
    }

    @Override
    public int distance(Node from, Node to) {
        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }

}
