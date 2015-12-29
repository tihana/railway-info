package org.tgereci.railway.operation;

import java.io.IOException;
import org.tgereci.railway.graph.GraphSearch;
import org.tgereci.railway.graph.TerminationType;
import org.tgereci.railway.model.Graph;

/**
 * Operation for calculating the number of trips between two towns with maximum or exact number of stops.
 */
public class TripsOperation extends Operation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tgereci.railway.operation.Operation#execute(org.tgereci.railway.model.Graph2)
	 */
	@Override
	public void execute(Graph graph) throws IOException {
		System.out.println();
		System.out.println("Find the number of trips between two towns with maximum or exact number of stops");
		System.out.println("---");
		System.out.print("Enter start town: ");
		String from = getInput();
		System.out.print("Enter end town: ");
		String to = getInput();
		int stops = 0;
		while (stops < 1) {
			System.out.print("Enter number of stops: ");
			try {
				stops = Integer.parseInt(getInput());
			} catch (NumberFormatException e) {
			}
			if (stops < 1) {
				System.err.println("Input value must be a number greater than zero");
			}
		}
		TerminationType type = null;
		while (type == null) {
			System.out.print("Enter (maximum or exactly) whether you wish to find route for maximum or exact number of stops: ");
			type = TerminationType.getByCode(getInput());
			if (type == null) {
				System.err.println("Input value must be maximum or exaclty");
			}
		}
		int result = GraphSearch.forGraph(graph).findNumberOfTrips(from, to, type, stops);
		System.out.println();
		System.out.println("Result: " + result);
	}

}
