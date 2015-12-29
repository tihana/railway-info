package org.tgereci.railway.operation;

/**
 * Option.
 */
public enum Option {

	/** Distance of the route. */
	DISTANCE("1"),

	/** Number of trips between two towns. */
	TRIPS("2"),

	/** Length of the shortest route. */
	SHORTEST("3"),

	/** Number of different routes between two towns. */
	ROUTES("4"),

	/** Exit the app. */
	EXIT("exit");

	/** The code. */
	private final String code;

	/**
	 * 
	 * Instantiate a new option.
	 *
	 * @param code
	 *            the code
	 */
	private Option(String code) {
		this.code = code;
	}

	/**
	 * Get input option by code.
	 *
	 * @param code
	 *            the code
	 * @return the input option by code
	 */
	public static Option getByCode(String code) {
		for (Option value : Option.values()) {
			if (code != null && value.code.toLowerCase().equals(code.toLowerCase())) {
				return value;
			}
		}
		return null;
	}

	/**
	 * Check for exit code.
	 *
	 * @param code
	 *            the code
	 * @return true, if code is exit
	 */
	public static boolean isExit(String code) {
		return EXIT.code.toLowerCase().equals(code.toLowerCase());
	}
}
