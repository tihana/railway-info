package org.tgereci.railway.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph.
 */
public class Graph {

	/** The vertices. */
	private final Map<String, List<Edge>> vertices;

	/**
	 * Instantiate a new graph.
	 */
	public Graph() {
		this.vertices = new HashMap<>();
	}

	/**
	 * Gets the vertices.
	 *
	 * @return the vertices
	 */
	public Map<String, List<Edge>> getVertices() {
		return vertices;
	}

	/**
	 * Connect new edge to graph.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param weight
	 *            the weight
	 */
	public void connect(String from, String to, int weight) {
		if (!vertices.containsKey(from)) {
			vertices.put(from, new ArrayList<>());
		}
		if (!vertices.containsKey(to)) {
			vertices.put(to, new ArrayList<>());
		}
		vertices.get(from).add(new Edge(to, weight));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		vertices.keySet().forEach(vertex -> sb.append("\n" + vertex + " -> " + vertices.get(vertex)));
		return sb.toString().trim();
	}

}
