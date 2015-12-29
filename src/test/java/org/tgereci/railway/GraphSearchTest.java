package org.tgereci.railway;

import org.tgereci.railway.graph.GraphBuilder;
import org.tgereci.railway.graph.GraphSearch;
import org.tgereci.railway.graph.TerminationType;
import org.tgereci.railway.model.Graph;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for graph search.
 */
public class GraphSearchTest extends TestCase {

	/** The search. */
	private GraphSearch search;

	/**
	 * Create the test case.
	 *
	 * @param testName
	 *            the name of the test case
	 */
	public GraphSearchTest(String testName) {
		super(testName);
	}

	/**
	 * Create the test suite.
	 *
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(GraphSearchTest.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		Graph graph = GraphBuilder.create("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7").build();
		search = GraphSearch.forGraph(graph);
	}

	/**
	 * Question #1: Find the distance of the route A-B-C.
	 */
	public void testQuestion1() {
		assertEquals("Distance of route A-B-C", 9, search.findRouteDistance("A-B-C"));
	}

	/**
	 * Question #2: Find the distance of the route A-D.
	 */
	public void testQuestion2() {
		assertEquals("Distance of route A-D", 5, search.findRouteDistance("A-D"));
	}

	/**
	 * Question #3: Find the distance of the route A-D-C.
	 */
	public void testQuestion3() {
		assertEquals("Distance of route A-D-C", 13, search.findRouteDistance("A-D-C"));
	}

	/**
	 * Question #4: Find the distance of the route A-E-B-C-D.
	 */
	public void testQuestion4() {
		assertEquals("Distance of route A-E-B-C-D", 22, search.findRouteDistance("A-E-B-C-D"));
	}

	/**
	 * Question #5: Find the distance of the route A-E-D.
	 */
	public void testQuestion5() {
		assertEquals("Distance of route A-E-D", -1, search.findRouteDistance("A-E-D"));
	}

	/**
	 * Question #6: Find the number of trips starting at C and ending at C with a maximum of 3 stops.
	 */
	public void testQuestion6() {
		assertEquals("Number of trips from C to C with max 3 stops", 2, search.findNumberOfTrips("C", "C", TerminationType.MAXIMUM, 3));
	}

	/**
	 * Question #7: Find the number of trips starting at A and ending at C with exactly 4 stops.
	 */
	public void testQuestion7() {
		assertEquals("Number of trips from A to C with exactly 4 stops", 3, search.findNumberOfTrips("A", "C", TerminationType.EXACTLY, 4));
	}

	/**
	 * Question #8: Find the length of the shortest route (in terms of distance to travel) from A to C.
	 */
	public void testQuestion8() {
		assertEquals("Length of shortest route from A to C", 9, search.findShortestRoute("A", "C"));
	}

	/**
	 * Question #9: Find the length of the shortest route (in terms of distance to travel) from B to B.
	 */
	public void testQuestion9() {
		assertEquals("Length of shortest route from B to B", 9, search.findShortestRoute("B", "B"));
	}

	/**
	 * Question #10: Find the number of different routes from C to C with a distance of less than 30.
	 */
	public void testQuestion10() {
		assertEquals("Number of different routes from C to C with distance less than 30", 7, search.findNumberOfDifferentRoutes("C", "C", 29));
	}
}
