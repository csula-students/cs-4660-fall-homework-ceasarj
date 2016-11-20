package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.SearchUtil;

import java.util.List;

public class MiniMax {



    public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
        createMiniMaxStates(graph, root, depth, max).getData();
        return SearchUtil.getMaxMove(graph, root);
    }

    private static Node createMiniMaxStates(Graph graph, Node root, Integer depth, Boolean max){
        root = graph.getNode(root).get();
        List<Node> neighboors = graph.neighbors(root);
        MiniMaxState rootState = (MiniMaxState)root.getData();

        if(neighboors.size() != 0 && depth != 0){ // check if there is a neighboor to update minimax value

            if(max){ // max turn
                int maxVal = Integer.MIN_VALUE;

                for(Node n: neighboors){
                    // get the minimaxstate of next node
                    MiniMaxState newStae = (MiniMaxState)createMiniMaxStates(graph, n, depth - 1, !max)
                            .getData();

                    //
                    maxVal = Math.max( maxVal, newStae.getValue());
                    rootState.set(maxVal);
                }

            } else { // min turn
                int minVal = Integer.MAX_VALUE;

                for(Node n: neighboors){
                    // get the minimaxstate of next node
                    MiniMaxState newState = (MiniMaxState)createMiniMaxStates(graph, n, depth - 1, !max)
                            .getData();

                    minVal = Math.min(newState.getValue(), minVal);
                    rootState.set(minVal);
                }
            }
        }
        return root;
    }
}
