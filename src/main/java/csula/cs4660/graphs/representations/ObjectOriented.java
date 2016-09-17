package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.ParserUtil;

import java.io.File;
import java.util.*;

/**
 * Object oriented representation of graph is using OOP approach to store nodes
 * and edges
 *
 * TODO: Please fill the body of methods in this class
 */
public class ObjectOriented implements Representation {
    private Collection<Node> nodes;
    private Collection<Edge> edges;

    public ObjectOriented(File file) {
        nodes = new HashSet<>();
        edges = new ArrayList<>();

        List<List<Integer>> numbers = ParserUtil.readFile(file);
        convertList(numbers);
    }

    public ObjectOriented() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {

        Iterator<Edge> it = edges.iterator();

        while(it.hasNext()) {
            Edge edge = it.next();
            Node from = edge.getFrom();
            Node to = edge.getTo();
            if(x.equals(from) && y.equals(to)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
        List<Node> neighbors = new ArrayList<>();
        edges.forEach(edge ->{
            if(edge.getFrom().equals(x)) neighbors.add(edge.getTo());
        });
        return neighbors;
    }

    @Override
    public boolean addNode(Node x) {
        if(nodes.contains(x)) return false;

        nodes.add(x);
        return true;
    }

    @Override
    public boolean removeNode(Node x) {
        if(!nodes.contains(x)) return false;

        nodes.remove(x);
        ArrayList<Edge> edgeList = new ArrayList<>();

        edges.forEach(edge->{
            if(edge.getTo().equals(x))
                edgeList.add(edge);
        });

        edgeList.forEach(edge->{
            edges.remove(edge);
        });
        return true;
    }

    @Override
    public boolean addEdge(Edge x) {
        if(edges.contains(x)) return false;

        edges.add(x);
        return true;
    }

    @Override
    public boolean removeEdge(Edge x) {
        if(!edges.contains(x))return false;

        edges.remove(x);
        return true;
    }

    @Override
    public int distance(Node from, Node to) {
        int dis = -1;

        Iterator<Edge> it = edges.iterator();
        while(it.hasNext()){
            Edge edge = it.next();
            if(edge.getFrom().equals(from) && edge.getTo().equals(to)) {
                dis = edge.getValue();
                break;
            }
        }

        return dis;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return Optional.ofNullable(new Node(index));
    }

    private void convertList(List<List<Integer>> numbers){
        numbers.forEach(line ->{
            if(line.size() > 1){
                Node from = new Node(line.get(0));
                Node to = new Node(line.get(1));

                edges.add(new Edge(from, to, line.get(2)));

                if(!nodes.contains(from)) nodes.add(from);
                if(!nodes.contains(to)) nodes.add(to);
            }
        });
    }
}
