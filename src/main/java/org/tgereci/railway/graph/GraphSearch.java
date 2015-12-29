package org.tgereci.railway.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import org.tgereci.railway.model.Edge;
import org.tgereci.railway.model.Graph;
import org.tgereci.railway.model.VertexWithDistance;

/**
 * Graph search.
 */
public class GraphSearch {

	/** The graph. */
	private Graph graph;

	/**
	 * Instantiate a new graph search.
	 *
	 * @param graph
	 *            the graph
	 */
	private GraphSearch(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Instantiate a new graph search.
	 *
	 * @param graph
	 *            the input graph
	 * @return the graph search
	 */
	public static GraphSearch forGraph(Graph graph) {
		return new GraphSearch(graph);
	}

	/**
	 * Find the distance of the route.
	 *
	 * @param route
	 *            the input route
	 * @return the distance
	 */
	public int findRouteDistance(String route) {
		if (route == null || route.isEmpty()) {
			return -1;
		}
		int distance = 0;
		String[] stops = route.replaceAll(" ", "").split("-");
		if (stops.length < 2) {
			return distance;
		}
		for (int i = 1; i < stops.length; i++) {
			int weight = getWeight(stops[i - 1], stops[i]);
			if (weight < 0) {
				return -1;
			}
			distance += weight;
		}
		return distance;
	}

	/**
	 * Return weight between two vertices.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @return the weight
	 */
	private int getWeight(String from, String to) {
		if (!graph.getVertices().containsKey(from)) {
			return -1;
		}
		Optional<Edge> edge = graph.getVertices().get(from).stream().filter(x -> x.getVertex().equals(to)).findFirst();
		return edge.isPresent() ? edge.get().getWeight() : -1;
	}

	/**
	 * Find the number of trips with specified number of stops.
	 *
	 * @param from
	 *            the from vertex
	 * @param to
	 *            the to vertex
	 * @param type
	 *            the termination type for stops (maximum or exactly)
	 * @param stops
	 *            the number of stops
	 * @return the number of trips
	 */
	public int findNumberOfTrips(String from, String to, TerminationType type, int stops) {
		if (!graph.getVertices().containsKey(from) || !graph.getVertices().containsKey(to)) {
			return 0;
		}
		return findNumberOfTrips(from, to, type, stops, 0);
	}

	/**
	 * Internal method to find the number of trips with specified number of stops.
	 *
	 * @param from
	 *            the from vertex
	 * @param to
	 *            the to vertex
	 * @param type
	 *            the termination type for stops (maximum or exactly)
	 * @param targetStops
	 *            the target number of stops
	 * @param currentStops
	 *            the current number of stops
	 * @return the number of trips
	 */
	private int findNumberOfTrips(String from, String to, TerminationType type, int targetStops, int currentStops) {
		if (currentStops > targetStops) {
			return 0;
		}
		if (from.equals(to) && currentStops > 0 && (type == TerminationType.MAXIMUM || (type == TerminationType.EXACTLY && targetStops == currentStops))) {
			return 1;
		}
		int trips = 0;
		for (Edge edge : graph.getVertices().get(from)) {
			trips += findNumberOfTrips(edge.getVertex(), to, type, targetStops, currentStops + 1);
		}
		return trips;
	}

	/**
	 * Find the length of the shortest route.
	 *
	 * @param from
	 *            the from vertex
	 * @param to
	 *            the to vertex
	 * @return the shortest route
	 */
	public int findShortestRoute(String from, String to) {
		if (!graph.getVertices().containsKey(from) || !graph.getVertices().containsKey(to)) {
			return -1;
		}
		int length = dijkstra(from).get(to);
		if (length == Integer.MAX_VALUE) {
			return -1;
		}
		return length;
	}

	/**
	 * Dijkstra's algorithm.
	 *
	 * @param source
	 *            the source vertex
	 * @return the map of all vertices reachable from source with minimum distance
	 */
	private Map<String, Integer> dijkstra(String source) {
		Map<String, Integer> visited = new HashMap<>();
		Map<String, VertexWithDistance> verticesWithDistance = graph.getVertices().keySet().stream().map(VertexWithDistance::new)
				.collect(Collectors.toMap(VertexWithDistance::getName, vertex -> vertex));
		verticesWithDistance.get(source).setDistance(0);
		PriorityQueue<VertexWithDistance> queue = new PriorityQueue<>(verticesWithDistance.values());
		boolean firstPass = true;
		while (!queue.isEmpty()) {
			VertexWithDistance vertex = queue.poll();
			visited.put(vertex.getName(), vertex.getDistance());
			for (Edge edge : graph.getVertices().get(vertex.getName())) {
				if (visited.containsKey(edge.getVertex())) {
					continue;
				}
				int distance = vertex.getDistance() + edge.getWeight();
				VertexWithDistance adjacentVertex = verticesWithDistance.get(edge.getVertex());
				if (distance < adjacentVertex.getDistance()) {
					queue.remove(adjacentVertex);
					adjacentVertex.setDistance(distance);
					queue.offer(adjacentVertex);

				}
			}
			if (firstPass) {
				firstPass = false;
				vertex.setDistance(Integer.MAX_VALUE);
				visited.remove(vertex.getName());
				queue.offer(vertex);
			}
		}
		return visited;
	}

	/**
	 * Find the number of different routes.
	 *
	 * @param from
	 *            the from vertex
	 * @param to
	 *            the to vertex
	 * @param maxDistance
	 *            the maximum allowed distance (inclusive)
	 * @return the number of different routes
	 */
	public int findNumberOfDifferentRoutes(String from, String to, int maxDistance) {
		if (!graph.getVertices().containsKey(from) || !graph.getVertices().containsKey(to)) {
			return 0;
		}
		Set<String> routes = new HashSet<>();
		findNumberOfDifferentRoutes(from, to, maxDistance, 0, new StringBuilder(from), routes);
		return routes.size();
	}

	/**
	 * Internal method to find the number of different routes.
	 *
	 * @param from
	 *            the from vertex
	 * @param to
	 *            the to vertex
	 * @param targetDistance
	 *            the target distance
	 * @param currentDistance
	 *            the current distance
	 * @param currentRoute
	 *            the current route
	 * @param routes
	 *            the routes
	 */
	private void findNumberOfDifferentRoutes(String from, String to, int targetDistance, int currentDistance, StringBuilder currentRoute, Set<String> routes) {
		if (currentDistance > targetDistance) {
			return;
		}
		if (from.equals(to) && currentDistance > 0) {
			routes.add(currentRoute.toString());
		}
		for (Edge edge : graph.getVertices().get(from)) {
			currentRoute.append(edge.getVertex());
			findNumberOfDifferentRoutes(edge.getVertex(), to, targetDistance, currentDistance + edge.getWeight(), currentRoute, routes);
		}
	}

}
