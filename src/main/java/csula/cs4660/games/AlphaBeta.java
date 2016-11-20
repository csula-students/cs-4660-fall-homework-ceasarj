package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.AlphaBetaNode;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import csula.cs4660.utils.SearchUtil;

import java.util.List;

public class AlphaBeta {
    public static Node getBestMove(Graph graph, Node source, Integer depth, Integer alpha, Integer beta, Boolean max) {
        getBestMoveHelper(graph, source, depth, alpha, beta, max);
        return SearchUtil.getMaxMove(graph, source);
    }

    public static Node getBestMoveHelper(Graph graph, Node source,
                                         Integer depth, Integer alpha, Integer beta, Boolean max) {
        source = graph.getNode(source).get();
        List<Node> neighboors = graph.neighbors(source);
        MiniMaxState rootState = (MiniMaxState)source.getData();

        if(neighboors.size() != 0 && depth != 0){ // check if there is a neighboor to update minimax value
            int i = 0;
            if(max){ // max turn
                int maxVal = Integer.MIN_VALUE;

                while(i < neighboors.size() && alpha < beta){
                    // get the minimaxstate of next node
                    MiniMaxState newStae = (MiniMaxState)getBestMoveHelper(graph,
                            neighboors.get(i), depth - 1, alpha, beta, !max).getData();

                    maxVal = Math.max( maxVal, newStae.getValue());
                    alpha = Math.max(alpha, maxVal);

                    rootState.set(maxVal);

                    i++;
                }

            } else { // min turn
                int minVal = Integer.MAX_VALUE;

                while(i < neighboors.size() && alpha < beta) {
                    // get the minimaxstate of next node
                    MiniMaxState newState = (MiniMaxState)getBestMoveHelper(graph,
                            neighboors.get(i), depth - 1, alpha, beta, !max).getData();

                    minVal = Math.min(newState.getValue(), minVal);
                    beta = Math.min(beta, minVal);

                    rootState.set(minVal);

                    i++;
                }
            }


        }
        return source;
    }
}
