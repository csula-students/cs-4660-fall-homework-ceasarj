package csula.cs4660.graphs.searches;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.graphs.NodeWeight;
import csula.cs4660.utils.SearchUtil;

import java.util.*;

/**
 * Created by ceejay562 on 10/16/2016.
 */
public class DijkstraSearchQuiz implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        Queue<Node> q = new LinkedList<>();
        Map<Node, NodeWeight> parents = Maps.newHashMap();
        Set<Node> visited = Sets.newHashSet();

        q.add(source);
        parents.put(source, new NodeWeight(new Edge(source, source, 0), 0));

        boolean found = false;
        while(!q.isEmpty() && !found){
            Node curr = q.poll();
            for(Node neighboor: graph.neighbors(curr)){
                if(!parents.containsKey(neighboor)){
                    NodeWeight currWeight = parents.get(curr);
                    int currWeightValue = currWeight.getWeight() + graph.distance(curr, neighboor);
                    int l = graph.distance(curr, neighboor);
                    parents.put(
                            neighboor,
                            new NodeWeight(new Edge(curr, neighboor, l), currWeightValue)
                    );
                    q.add(neighboor);
                } else {
                    NodeWeight neighboorWeight = parents.get(neighboor);
                    NodeWeight currWeight = parents.get(curr);
                    int currWeightValue = currWeight.getWeight() + graph.distance(curr, neighboor);
                    if(currWeightValue < neighboorWeight.getWeight()) {
                        parents.replace(
                                neighboor,
                                new NodeWeight(
                                        new Edge(curr, neighboor, graph.distance(curr, neighboor)),
                                        currWeightValue
                                )
                        );

                    }
                    if(neighboor.equals(dist)) found = true;
                }
            }
        }
        return SearchUtil.getPathDijkstra(parents, source, dist);
    }
}
