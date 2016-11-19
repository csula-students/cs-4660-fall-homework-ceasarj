package csula.cs4660.quizes;

import com.google.common.collect.Sets;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.graphs.representations.Representation;
import csula.cs4660.graphs.searches.BFS;
import csula.cs4660.graphs.searches.DFS;
import csula.cs4660.graphs.searches.DijkstraSearchQuiz;
import csula.cs4660.graphs.searches.SearchStrategy;
import csula.cs4660.quizes.models.State;
import csula.cs4660.quizes.models.DTO;

import java.util.*;

/**
 * Here is your quiz entry point and your app
 */
public class App {
    public enum SEARCH_STRATEGY {
        BFS,
        DIJKSTRA
    }

    public static void main(String[] args) {
        // to get a state, you can simply call `Client.getState with the id`
        State initialState = Client.getState("10a5461773e8fd60940a56d2e9ef7bf4").get();
        State finalState = Client.getState("e577aa79473673f6158cc73e0e5dc122").get();

        Graph graph = buildGraph(initialState);

        Node initialNode = new Node(initialState.getId());
        Node finialNode = new Node(finalState.getId());

        List<Edge> path = graph.search(new BFS(), initialNode, finialNode);
        System.out.println("BFS:");
        printPath(path);


        path = graph.search(new DijkstraSearchQuiz(), initialNode, finialNode);
        System.out.println("\n\nDijkstra:");
        printPath(path);

        List<Edge> test = graph.search(new DFS(), initialNode, finialNode);
        System.out.println("Dijkstra:");
        printPath(test);

    }

    public static Graph buildGraph(State source){
        //classLoader.getResource("homework-1/graph-1.txt").getFile()
        Queue<String> frontier = new LinkedList<>();
        Set<String> exploredSet = Sets.newHashSet();
        Graph graph = new Graph(Representation.of(Representation.STRATEGY.ADJACENCY_LIST));

        frontier.add(source.getId());
        exploredSet.add(source.getId());

        int edgeCounter =0;
        System.out.println();
        while(!frontier.isEmpty()){
            String curr = frontier.poll();
            Node currNode = new Node(curr);
            graph.addNode(currNode);
            for(State neighbor: Client.getState(curr).get().getNeighbors()){
                if(!exploredSet.contains(neighbor.getId())) {
                    frontier.add(neighbor.getId());

                }
                    Node neighborNode = new Node(neighbor.getId());
                    int weight = Client.stateTransition(curr, neighbor.getId())
                            .get()
                            .getEvent()
                            .getEffect();
                    graph.addNode(neighborNode);
                    graph.addEdge(new Edge(currNode, neighborNode, weight));
                    exploredSet.add(neighbor.getId());
                //System.out.println(++edgeCounter);
            }
            //System.out.println();
        }
        return graph;
    }

    public static void printPath(List<Edge> path){
        path.forEach(edge->{
            Node from = edge.getFrom();
            Node to = edge.getTo();
            int weight = edge.getValue();

            State fromState =  Client.getState((String)from.getData()).get();
            State toState =  Client.getState((String)to.getData()).get();

            StringBuilder sb = new StringBuilder();
            sb.append(fromState.getLocation().getName())
                    .append(":")
                    .append(toState.getLocation().getName())
                    .append(":")
                    .append(weight);

            System.out.println(sb.toString());
        });
    }

    public static Map<State, Integer> constructPath(Map<State, State> parents, Map<State, Integer> distances, State current) {
        State c = current;
        Map<State, Integer> result = new LinkedHashMap<>();

        while (parents.get(c) != null) {
            result.put(c, distances.get(c));
            c = parents.get(c);
        }
        result.put(c, distances.get(c));

        return result;
    }

    public static class StateComparator implements Comparator<State> {
        private final Map<State, Integer> distances;
        public StateComparator(Map<State, Integer> distances) {
            this.distances = distances;
        }

        @Override
        public int compare(State a, State b) {
            return distances.getOrDefault(b, Integer.MAX_VALUE)
                .compareTo(distances.getOrDefault(a, Integer.MAX_VALUE));
        }
    }
}
