package org.tgereci.railway;

import java.util.List;
import java.util.Map;
import org.tgereci.railway.graph.GraphBuilder;
import org.tgereci.railway.model.Edge;
import org.tgereci.railway.model.Graph;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for graph builder.
 */
public class GraphBuilderTest extends TestCase {

	/**
	 * Create the test case.
	 *
	 * @param testName
	 *            the name of the test case
	 */
	public GraphBuilderTest(String testName) {
		super(testName);
	}

	/**
	 * Create the test suite.
	 *
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(GraphBuilderTest.class);
	}

	/**
	 * Parse input graph.
	 */
	public void testGraph() {
		Graph graph = GraphBuilder.create("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7").build();

		Map<String, List<Edge>> vertices = graph.getVertices();
		assertEquals("Graph must contain 5 vertices", 5, vertices.size());

		assertTrue("Graph must contain vertex A", vertices.containsKey("A"));
		List<Edge> edges = vertices.get("A");
		assertEquals("Number of edges for vertex", 3, edges.size());
		assertTrue("Vertex A must contain edge B with weight 5", edges.contains(new Edge("B", 5)));
		assertTrue("Vertex A must contain edge D with weight 5", edges.contains(new Edge("D", 5)));
		assertTrue("Vertex A must contain edge E with weight 7", edges.contains(new Edge("E", 7)));

		assertTrue("Graph must contain vertex B", vertices.containsKey("B"));
		edges = vertices.get("B");
		assertEquals("Number of edges for vertex B", 1, edges.size());
		assertTrue("Vertex B must contain edge C with weight 4", edges.contains(new Edge("C", 4)));

		assertTrue("Graph must contain vertex C", vertices.containsKey("C"));
		edges = vertices.get("C");
		assertEquals("Number of edges for vertex C", 2, edges.size());
		assertTrue("Vertex C must contain edge D with weight 8", edges.contains(new Edge("D", 8)));
		assertTrue("Vertex C must contain edge E with weight 2", edges.contains(new Edge("E", 2)));

		assertTrue("Graph must contain vertex D", vertices.containsKey("D"));
		edges = vertices.get("D");
		assertEquals("Number of edges for vertex D", 2, edges.size());
		assertTrue("Vertex D must contain edge C with weight 8", edges.contains(new Edge("C", 8)));
		assertTrue("Vertex D must contain edge E with weight 6", edges.contains(new Edge("E", 6)));

		assertTrue("Graph must contain vertex E", vertices.containsKey("E"));
		edges = vertices.get("E");
		assertEquals("Number of edges for vertex E", 1, edges.size());
		assertTrue("Vertex E must contain edge B with weight 3", edges.contains(new Edge("B", 3)));
	}
}
