package csula.cs4660.graphs.representations;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.ParserUtil;

import javax.swing.text.html.HTMLDocument;
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
        adjacencyList = Maps.newHashMap();
        List<List<Integer>> numbers = ParserUtil.readFile(file);
        convertList(numbers);
    }

    public AdjacencyList() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
        if(x == null || y == null) return false;
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
        if(adjacencyList.get(x) == null) return neighbors;
        adjacencyList.get(x).forEach(edge ->{
            neighbors.add(edge.getTo());
        });
        return neighbors;
    }

    @Override
    public boolean addNode(Node x) {
        if(!adjacencyList.containsKey(x)){
            adjacencyList.put(x, Lists.newArrayList());
            return true;
        }
        return false;
    }

    /***
     * TODO:CLean THis up. Looks too ugly.
     * **/
    @Override
    public boolean removeNode(Node x) {
        Node nodeTest = null;
        Edge edgeTest = null;
        if(adjacencyList.get(x) != null){
            for(Node node: adjacencyList.keySet()) {
                    Iterator<Edge> it = adjacencyList.get(node).iterator();
                    while (it.hasNext()) {
                        Edge edge = it.next();
                        if (edge.getTo().equals(x)) {
                            edgeTest = edge;
                            nodeTest = node;
                        }
                    }

            }
            adjacencyList.get(nodeTest).remove(edgeTest);
            adjacencyList.remove(x);
            return true;
        }
        return false;
    }


    /**TODO:
     * **/
    @Override
    public boolean addEdge(Edge x) {
        Collection<Edge> edges = adjacencyList.get(x.getFrom());
        if(!(edges.contains(new Edge(x.getFrom(), x.getTo(), x.getValue())))) {
            edges.add(x);
            adjacencyList.put(x.getFrom(), edges );
            return true;
        }
        /**TODO: deal with if the edges do not contain the list but need to remove the edge with different length
         * **/
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
        if(x == null) return false;
        Collection<Edge> edges = adjacencyList.get(x.getFrom());
        if(edges != null) {
            if (edges.contains(x)) {
                edges.remove(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public int distance(Node from, Node to) {
        Collection<Edge> edges = adjacencyList.get(from);
        for(Edge edge: edges){
            if(to.equals(edge.getTo())){
                return edge.getValue();
            }
        }
        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }

    public void convertList(List<List<Integer>> numbers){
        for(List<Integer> edge: numbers){
            if(edge.size() > 1) {
                Node fromNode = new Node(edge.get(0));
                Node toNode = new Node(edge.get(1));
                Collection<Edge> edges = this.adjacencyList.get(fromNode);
                if (edges == null) {
                    edges = Lists.newArrayList();
                    edges.add(new Edge(fromNode, toNode, edge.get(2)));
                    adjacencyList.putIfAbsent(fromNode, edges);
                } else {
                    edges.add(new Edge(fromNode, toNode, edge.get(2)));
                }
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        adjacencyList.forEach( (k,edges)->{
            sb.append(k.getData() + "->");
            edges.forEach(edge ->{
                sb.append("    " + edge.getTo().getData());
            });
            sb.append("\n");
        });
        return sb.toString();
    }
}
