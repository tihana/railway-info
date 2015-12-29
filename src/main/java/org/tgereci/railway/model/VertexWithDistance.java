package org.tgereci.railway.model;

/**
 * Vertex with distance.
 */
public class VertexWithDistance implements Comparable<VertexWithDistance> {

	/** The name. */
	private final String name;

	/** The distance. */
	private int distance;

	/**
	 * Instantiate a new vertex with distance.
	 *
	 * @param name
	 *            the name
	 */
	public VertexWithDistance(String name) {
		super();
		this.name = name;
		this.distance = Integer.MAX_VALUE;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the distance.
	 *
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Sets the distance.
	 *
	 * @param distance
	 *            the new distance
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(VertexWithDistance o) {
		return Integer.compare(this.distance, o.distance);
	}

}
