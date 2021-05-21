package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Ho Jong Kang
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int counter = 0;
        Iterator<Integer> temp = predecessors(v);
        while (temp.hasNext()) {
            temp.next();
            counter += 1;
        }
        return counter;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> elem : _edges) {
            if (elem.get(1).equals(v)) {
                result.add(elem.get(0));
            }
        }
        return Iteration.iteration(result);
    }

}
