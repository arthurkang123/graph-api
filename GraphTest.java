package graph;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Unit tests for the Graph class.
 *  @author Ho Jong Kang
 */
public class GraphTest {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void addAndRemoveVertex() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        assertEquals("Number of vertex after "
                + "adding first one", 1, temp.vertexSize());
        temp.remove(1);
        assertEquals("Number of vertex after "
                + "removing one", 0, temp.vertexSize());
    }

    @Test
    public void addAndRemoveEdge() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        assertEquals("Number of vertex after adding 2", 2, temp.vertexSize());
        assertEquals("Number of edge after adding 1 edge", 1, temp.edgeSize());
        temp.remove(1);
        assertEquals("Number of vertex after "
                + "removing vertex 1", 1, temp.vertexSize());
        assertEquals("Number of edge after "
                + "removing vertex 1", 0, temp.edgeSize());
    }

    @Test
    public void addAndRemoveEdgeOnly() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        assertEquals("Number of vertex after adding 2", 2, temp.vertexSize());
        assertEquals("Number of edge after adding 1 edge", 1, temp.edgeSize());
        temp.remove(1, 2);
        assertEquals("Number of vertex after "
                + "removing edge", 2, temp.vertexSize());
        assertEquals("Number of edge after "
                + "removing vertex 1", 0, temp.edgeSize());
    }

    @Test
    public void addAndRemoveEdgeOnlyDirected() {
        DirectedGraph temp = new DirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        assertEquals("Number of vertex after adding 2", 2, temp.vertexSize());
        assertEquals("Number of edge after adding 1 edge", 1, temp.edgeSize());
        temp.remove(1, 2);
        assertEquals("Number of vertex after "
                + "removing edge", 2, temp.vertexSize());
        assertEquals("Number of edge after "
                + "removing vertex 1", 0, temp.edgeSize());
    }

    @Test
    public void addAndRemoveEdgeOnlyDirectedTwo() {
        DirectedGraph temp = new DirectedGraph();
        temp.add();
        temp.add();
        temp.add(2, 1);
        assertEquals("Number of vertex after adding 2", 2, temp.vertexSize());
        assertEquals("Number of edge after adding 1 edge", 1, temp.edgeSize());
        temp.remove(1, 2);
        assertEquals("Number of vertex after "
                + "removing edge", 2, temp.vertexSize());
        assertEquals("Number of edge after removing vertex "
                + "of wrong direction", 1, temp.edgeSize());
    }

    @Test
    public void addAndRemoveEdgeOnlyUndirected() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(2, 1);
        assertEquals("Number of vertex after adding 2", 2, temp.vertexSize());
        assertEquals("Number of edge after adding 1 edge", 1, temp.edgeSize());
        temp.remove(1, 2);
        assertEquals("Number of vertex after removing edge",
                2, temp.vertexSize());
        assertEquals("Number of edge after removing vertex of other direction",
                0, temp.edgeSize());
    }

    @Test
    public void edgeTest() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        Iteration<int[]> edgeIter = temp.edges();
        int[] result = edgeIter.next();
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        int[] resultSecond = edgeIter.next();
        assertEquals(3, resultSecond[0]);
        assertEquals(4, resultSecond[1]);
    }

    @Test
    public void successorsTestUndirected() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        Iterator<Integer> succIter = temp.successors(1);
        int result = succIter.next();
        assertEquals(2, result);
        int result2 = succIter.next();
        assertEquals(3, result2);

        Iterator<Integer> succIter1 = temp.successors(2);
        int result3 = succIter1.next();
        assertEquals(1, result3);
    }

    @Test
    public void inDegreeTestUndirected() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        int result = temp.inDegree(1);
        assertEquals(2, result);
    }

    @Test
    public void predecessorsTestUndirected() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        Iterator<Integer> succIter = temp.predecessors(1);
        int result = succIter.next();
        assertEquals(2, result);
        int result2 = succIter.next();
        assertEquals(3, result2);

        Iterator<Integer> succIter1 = temp.predecessors(2);
        int result3 = succIter1.next();
        assertEquals(1, result3);
    }

    @Test(expected = NoSuchElementException.class)
    public void successorsTestDirected() {
        DirectedGraph temp = new DirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        Iterator<Integer> succIter = temp.successors(1);
        int result = succIter.next();
        assertEquals(2, result);
        int result2 = succIter.next();
        assertEquals(3, result2);

        Iterator<Integer> succIter1 = temp.successors(2);
        int result3 = succIter1.next();
        assertEquals(1, result3);
    }

    @Test
    public void inDegreeTestDirected() {
        DirectedGraph temp = new DirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        int result = temp.inDegree(1);
        assertEquals(0, result);
        int result1 = temp.inDegree(3);
        assertEquals(1, result1);
    }

    @Test(expected = NoSuchElementException.class)
    public void predecessorsTestDirected() {
        DirectedGraph temp = new DirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add();
        temp.add();
        temp.add(3, 4);
        temp.add(1, 3);

        Iterator<Integer> succIter = temp.predecessors(3);
        int result = succIter.next();
        assertEquals(1, result);

        Iterator<Integer> succIter1 = temp.predecessors(1);
        int result3 = succIter1.next();
        assertEquals(1, result3);
    }

    @Test(expected = NoSuchElementException.class)
    public void undirectedSelf() {
        UndirectedGraph temp = new UndirectedGraph();
        temp.add();
        temp.add();
        temp.add(1, 2);
        temp.add(1, 1);

        Iterator<Integer> succIter = temp.successors(1);
        int result = succIter.next();
        assertEquals(2, result);
        int result2 = succIter.next();
        assertEquals(1, result2);
        int result3 = succIter.next();
        assertEquals(1, result3);
    }
}
