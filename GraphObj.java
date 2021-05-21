package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Iterator;


/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Ho Jong Kang
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _edges = new ArrayList<ArrayList<Integer>>();
        _vertices = new ArrayList<>();
        _removed = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return _vertices.size();
    }

    @Override
    public int maxVertex() {
        try {
            return Collections.max(_vertices);
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    @Override
    public int edgeSize() {
        return _edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        int counter = 0;
        Iterator<Integer> temp = successors(v);
        while (temp.hasNext()) {
            temp.next();
            counter += 1;
        }
        return counter;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _vertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        List<Integer> temp1 = new ArrayList<>();
        temp1.add(u);
        temp1.add(v);
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(v);
        temp2.add(u);
        if (isDirected()) {
            for (ArrayList<Integer> elem : _edges) {
                if (elem.equals(temp1)) {
                    return true;
                }
            }
        } else {
            for (ArrayList<Integer> elem : _edges) {
                if (elem.equals(temp1) || elem.equals(temp2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int add() {
        if (_removed.isEmpty()) {
            int temp = maxVertex() + 1;
            _vertices.add(temp);
            return temp;
        } else {
            int adding = Collections.min(_removed);
            _vertices.add(adding);
            _removed.remove(Integer.valueOf(adding));
            return adding;
        }
    }

    @Override
    public int add(int u, int v) {
        if (!contains(u, v)) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(u);
            temp.add(v);
            _edges.add(temp);
        }
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        List<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        temp.addAll(_edges);
        if (contains(v)) {
            _vertices.remove(Integer.valueOf(v));
            _removed.add(v);
            for (ArrayList<Integer> elem : temp) {
                if (elem.contains(v)) {
                    _edges.remove(elem);
                }
            }
        }
    }

    @Override
    public void remove(int u, int v) {
        List<Integer> temp1 = new ArrayList<>();
        temp1.add(u);
        temp1.add(v);
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(v);
        temp2.add(u);
        List<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        temp.addAll(_edges);
        if (contains(u, v)) {
            if (isDirected()) {
                _edges.remove(temp1);
            } else {
                _edges.remove(temp1);
                _edges.remove(temp2);
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_vertices);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> elem : _edges) {
            if (elem.get(0).equals(v)) {
                result.add(elem.get(1));
            }
            if (!isDirected()) {
                if (elem.get(1).equals(v)) {
                    if (!result.contains(elem.get(0))) {
                        result.add(elem.get(0));
                    }
                }
            }
        }
        return Iteration.iteration(result);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        List<int[]> result = new ArrayList<>();
        for (ArrayList<Integer> elem : _edges) {
            int[] temp = new int[2];
            temp[0] = elem.get(0);
            temp[1] = elem.get(1);
            result.add(temp);
        }
        return Iteration.iteration(result);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("vertex not from Graph");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected() && (v > u)) {
            return ((v + u) * (v + u + 1)) / 2 + u;
        } else {
            return ((u + v) * (u + v + 1)) / 2 + v;
        }
    }

    /** List of vertices. */
    private List<Integer> _vertices;

    /** List of edges. */
    protected List<ArrayList<Integer>> _edges;

    /** Removed. */
    private List<Integer> _removed;

}
