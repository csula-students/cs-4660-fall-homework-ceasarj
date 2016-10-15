package csula.cs4660.graphs;

import java.util.Comparator;

/**
 * Created by ceejay562 on 10/14/2016.
 */
public class NodeComparator implements Comparator<NodeWeight> {
    @Override
    public int compare(NodeWeight o1, NodeWeight o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
