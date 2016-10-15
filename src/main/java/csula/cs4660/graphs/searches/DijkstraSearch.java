package csula.cs4660.graphs.searches;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import csula.cs4660.graphs.*;
import csula.cs4660.utils.SearchUtil;

import java.util.*;

/**
 * As name, dijkstra search using graph structure
 */
public class DijkstraSearch implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        Queue<Node> q = new LinkedList<>();
        Map<Node, NodeWeight> parents = Maps.newHashMap();
        Set<Node> visited = Sets.newHashSet();

        q.add(source);
        parents.put(source, new NodeWeight(new Edge(source, source, 0), 0));

        while(!q.isEmpty()){
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
                }
            }
        }
        return SearchUtil.getPathDijkstra(parents, source, dist);
    }
}
