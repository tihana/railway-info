package org.tgereci.railway.operation;

/**
 * A factory for creating Operation objects.
 */
public class OperationFactory {

	/**
	 * Create new operation.
	 *
	 * @param option
	 *            the option
	 * @return the operation
	 */
	public static Operation getOperation(Option option) {
		if (option == Option.DISTANCE) {
			return new DistanceOperation();
		} else if (option == Option.TRIPS) {
			return new TripsOperation();
		} else if (option == Option.SHORTEST) {
			return new ShortestOperation();
		} else if (option == Option.ROUTES) {
			return new RoutesOperation();
		}
		return null;
	}
}
