package org.tgereci.railway.graph;

/**
 * Termination type.
 */
public enum TerminationType {

	/** Exact number of stops. */
	EXACTLY("exactly"),

	/** Maximum number of stops. */
	MAXIMUM("maximum");

	/** The code. */
	private final String code;

	/**
	 * Instantiate a new termination type.
	 *
	 * @param code
	 *            the code
	 */
	private TerminationType(String code) {
		this.code = code;
	}

	/**
	 * Get termination type by code.
	 *
	 * @param code
	 *            the code
	 * @return the termination type by code
	 */
	public static TerminationType getByCode(String code) {
		for (TerminationType value : TerminationType.values()) {
			if (code != null && value.code.toLowerCase().equals(code.toLowerCase())) {
				return value;
			}
		}
		return null;
	}
}
