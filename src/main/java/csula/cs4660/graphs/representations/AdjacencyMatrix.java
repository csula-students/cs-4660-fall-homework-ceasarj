package csula.cs4660.graphs.representations;

import com.google.common.collect.Lists;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.ParserUtil;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Adjacency matrix in a sense store the nodes in two dimensional array
 *
 * TODO: please fill the method body of this class
 */
public class AdjacencyMatrix implements Representation {
    private Node[] nodes;
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(File file) {
<<<<<<< HEAD
=======

>>>>>>> master
        List<List<Integer>> matrixList = ParserUtil.readFile(file);
        int numOfNodes = matrixList.get(0).get(0);
        nodes = new Node[numOfNodes];
        adjacencyMatrix = new int[numOfNodes][numOfNodes];
        convertList(matrixList);
<<<<<<< HEAD
=======

>>>>>>> master
    }

    public AdjacencyMatrix() {
        nodes = new Node[5];
        adjacencyMatrix = new int[5][5];
        for(int i=0; i<adjacencyMatrix.length; i++)
            nodes[i] = new Node(i);
    }

    @Override
    public boolean adjacent(Node x, Node y) {
<<<<<<< HEAD
        return adjacencyMatrix[(Integer)x.getData()][(Integer)y.getData()] > 0;
=======
        Integer xValue = (Integer)x.getData();
        Integer yValue = (Integer)y.getData();
        return adjacencyMatrix[xValue][yValue] > 0;
>>>>>>> master
    }

    @Override
    public List<Node> neighbors(Node x) {
<<<<<<< HEAD
        Integer xValue = (Integer)x.getData();
        List<Node> neighbors = Lists.newArrayList();
        for(int yValue=0; yValue<adjacencyMatrix[0].length; yValue++) {
=======
        System.out.println(toString() + "\n" + x.getData());
        Integer xValue = (Integer)x.getData();
        List<Node> neighbors = Lists.newArrayList();
        for(int yValue=0; yValue<adjacencyMatrix[xValue].length; yValue++) {
>>>>>>> master
            if(adjacencyMatrix[xValue][yValue] > 0) {
                neighbors.add(nodes[yValue]);
            }
        }
        return neighbors;
    }

    @Override
    public boolean addNode(Node x) {
        Integer value = (Integer)x.getData();
<<<<<<< HEAD
        if(value >= nodes.length) { //out of bounds
            resizeNodes(x);
            resizeMatrix(x);
        }
        else if(nodes[value] == null ) {
            nodes[value] = x;
        }
        else {
            return false;
        }
        return true;
=======
        if(value >= nodes.length){ //out of bounds
            resizeNodes(x);
            resizeMatrix(x);

            return true;
        }
        if(nodes[value] == null ){
            nodes[value] = x;

            return true;
        }

        return false;
>>>>>>> master
    }

    @Override
    public boolean removeNode(Node x) {
        Integer value = (Integer)x.getData();
        if(value >= nodes.length || value < 0){
            return false;
        }
        else if(nodes[value] != null){
            nodes[value] = null;
            removeNeighbors(value);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addEdge(Edge x) {
        Integer fromValue = (Integer)(x.getFrom().getData());
        Integer toValue = (Integer)(x.getTo().getData());

        if(fromValue >= adjacencyMatrix.length || fromValue < 0){ // out of bounds
            return false;
        }
        if(toValue >= adjacencyMatrix.length || toValue < 0){ // out of bounds
            return false;
        }
        if(adjacencyMatrix[fromValue][toValue] != x.getValue()){ // edge is not already there
            adjacencyMatrix[fromValue][toValue] = x.getValue();
<<<<<<< HEAD
=======
            adjacencyMatrix[toValue][fromValue] = x.getValue();
>>>>>>> master
            return true;
        }

        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
        Integer fromValue = (Integer)(x.getFrom().getData());
        Integer toValue = (Integer)(x.getTo().getData());
        if(fromValue >= adjacencyMatrix.length || fromValue < 0){ // out of bounds
            return false;
        }
        if(toValue >= adjacencyMatrix.length || toValue < 0){ // out of bounds
            return false;
        }
        if(adjacencyMatrix[fromValue][toValue] > 0){ // edge is already there
            adjacencyMatrix[fromValue][toValue] = 0;
<<<<<<< HEAD
            return true;
        }
=======
            adjacencyMatrix[toValue][fromValue] = 0;
            return true;
        }

>>>>>>> master
        return false;
    }

    public int distance(Node from, Node to) {
<<<<<<< HEAD
        return adjacencyMatrix[(Integer)from.getData()][(Integer)to.getData()];
=======
        Integer fromValue = (Integer)from.getData();
        Integer toValue = (Integer)to.getData();
        return adjacencyMatrix[fromValue][toValue];
>>>>>>> master
    }

    @Override
    public Optional<Node> getNode(int index) {
        return Optional.ofNullable(nodes[index]);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
<<<<<<< HEAD
=======

>>>>>>> master
        for(int i = 0; i<adjacencyMatrix.length; i++){
            for(int j = 0; j<adjacencyMatrix.length; j++){
                sb.append(adjacencyMatrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
<<<<<<< HEAD
=======

>>>>>>> master
        return sb.toString();
    }

    private void removeNeighbors(int val){
        for(int i=0; i<adjacencyMatrix.length; i++){
            adjacencyMatrix[val][i] = 0;
            adjacencyMatrix[i][val] = 0;
        }
    }

    private void resizeMatrix(Node node){
        Integer value = (Integer) node.getData();
        int[][] newAdjacencyMatrix = new int[value + 1][value + 1];

        // copy adacency matrix to new adjacency matrix
<<<<<<< HEAD
        for(int row=0; row<adjacencyMatrix.length; row++)
            for(int col=0; col<adjacencyMatrix[row].length; col++)
                newAdjacencyMatrix[row][col] = adjacencyMatrix[row][col];

        adjacencyMatrix = newAdjacencyMatrix;
    }

    private void resizeNodes(Node node){
        Integer value = (Integer) node.getData();
        Node[] newNodes = new Node[value + 1];
        newNodes[value] = node;
        for(int i=0; i<nodes.length; i++){
            newNodes[i] = nodes[i];
        }
    }

    private void convertList(List<List<Integer>> numbers){
        numbers.forEach(line ->{
            // skip the first line because it is the size of matrix nxn
            if(line.size() > 1){
                int nodeFromData = line.get(0);
                int nodeToData = line.get(1);

                adjacencyMatrix[nodeFromData][nodeToData] = line.get(2);

                if(nodes[nodeFromData] == null)
                    nodes[nodeFromData] = new Node(nodeFromData);
            }

        });
=======
        for(int row=0; row<adjacencyMatrix.length; row++){
            for(int col=0; col<adjacencyMatrix[row].length; col++){
                newAdjacencyMatrix[row][col] = adjacencyMatrix[row][col];
            }
        }

        adjacencyMatrix = newAdjacencyMatrix;
>>>>>>> master
    }

    private void resizeNodes(Node node){
        Integer value = (Integer) node.getData();
        Node[] newNodes = new Node[value + 1];
        newNodes[value] = node;
        for(int i=0; i<nodes.length; i++){
            newNodes[i] = nodes[i];
        }
    }

    private void convertList(List<List<Integer>> numbers){
        numbers.forEach(line ->{
            // skip the first line because it is the size of matrix nxn
            if(line.size() > 1){
                int nodeFromData = line.get(0);
                int nodeToData = line.get(1);

                adjacencyMatrix[nodeFromData][nodeToData] = line.get(2);
                adjacencyMatrix[nodeToData][nodeFromData] = line.get(2);

                if(nodes[nodeFromData] == null)
                    nodes[nodeFromData] = new Node(nodeFromData);

                if(nodes[nodeToData] == null)
                    nodes[nodeToData] = new Node(nodeToData);
            }

        });
    }

    public static void main(String[] args){
        AdjacencyMatrix mat = new AdjacencyMatrix();
        System.out.print(mat);
        mat.addNode(new Node(10));
        System.out.println(mat);
        System.out.println(mat.neighbors(new Node(10)));
        mat.addEdge(new Edge(new Node(1), new Node(4), 4));
        System.out.println(mat);
        mat.addEdge(new Edge(new Node(1), new Node(2), 2));
        System.out.println(mat);
        mat.addEdge(new Edge(new Node(1), new Node(5), 5));
        System.out.println(mat);
        mat.removeEdge(new Edge(new Node(1), new Node(5), 5));
        if(mat.removeNode(new Node(1)))
            System.out.println("wtf is wrong???" + mat);

    }

}
