package csula.cs4660.graphs;

/**
 * Created by ceejay562 on 10/14/2016.
 */
public class NodeWeight {

    Edge edge;
    int weight;

    public NodeWeight(Edge edge, int weight){
        this.edge = edge;
        this.weight = weight;
    }

    public Edge getEdge(){
        return edge;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public String toString(){
        return "{" + edge + " weight = " + weight + "}";
    }
}
