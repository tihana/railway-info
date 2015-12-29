package org.tgereci.railway.model;

/**
 * Edge.
 */
public class Edge {

	/** The vertex. */
	private final String vertex;

	/** The weight. */
	private final int weight;

	/**
	 * Instantiate a new edge.
	 *
	 * @param vertex
	 *            the vertex
	 * @param weight
	 *            the weight
	 */
	public Edge(String vertex, int weight) {
		super();
		this.vertex = vertex;
		this.weight = weight;
	}

	/**
	 * Gets the vertex.
	 *
	 * @return the vertex
	 */
	public String getVertex() {
		return vertex;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + vertex + ", weight = " + weight + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
		result = prime * result + weight;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

}
