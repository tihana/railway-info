package org.tgereci.railway.graph;

import java.util.LinkedList;
import java.util.List;
import org.tgereci.railway.model.Graph;

/**
 * Graph builder.
 */
public class GraphBuilder {

	/** The connections. */
	private List<Connection> connections;

	/**
	 * Instantiate a new graph builder.
	 */
	private GraphBuilder() {
		connections = new LinkedList<>();
	}

	/**
	 * Create graph builder.
	 *
	 * @return the graph builder
	 */
	public static GraphBuilder create() {
		return new GraphBuilder();
	}

	/**
	 * Create graph builder.
	 *
	 * @param input
	 *            the input
	 * @return the graph builder
	 */
	public static GraphBuilder create(String input) {
		GraphBuilder builder = create();
		if (input == null || input.isEmpty()) {
			return builder;
		}
		for (String edge : input.replaceAll(" ", "").split(",")) {
			if (!edge.matches("[A-Z][A-Z][0-9]")) {
				throw new IllegalArgumentException("Invalid edge: " + edge);
			}
			String from = String.valueOf(edge.charAt(0));
			String to = String.valueOf(edge.charAt(1));
			int weight = edge.charAt(2) - '0';
			builder = builder.add(from, to, weight);
		}
		return builder;
	}

	/**
	 * Add weight between two vertices.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param weight
	 *            the edge
	 * @return the graph builder
	 */
	public GraphBuilder add(String from, String to, int weight) {
		connections.add(new Connection(from, to, weight));
		return this;
	}

	/**
	 * Build the graph.
	 *
	 * @return the graph
	 */
	public Graph build() {
		Graph graph = new Graph();
		for (Connection connection : connections) {
			graph.connect(connection.from, connection.to, connection.weight);
		}
		return graph;
	}

	/**
	 * Connection.
	 */
	private class Connection {

		/** The from. */
		private String from;

		/** The to. */
		private String to;

		/** The weight. */
		private int weight;

		/**
		 * Instantiate a new connection.
		 *
		 * @param from
		 *            the from
		 * @param to
		 *            the to
		 * @param weight
		 *            the weight
		 */
		public Connection(String from, String to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}
}
