package org.tgereci.railway.operation;

import java.io.IOException;
import org.tgereci.railway.graph.GraphSearch;
import org.tgereci.railway.model.Graph;

/**
 * Operation for calculating the number of different routes between two towns.
 */
public class RoutesOperation extends Operation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tgereci.railway.operation.Operation#execute(org.tgereci.railway.model.Graph2)
	 */
	@Override
	public void execute(Graph graph) throws IOException {
		System.out.println();
		System.out.println("Find the number of different routes between two towns");
		System.out.println("---");
		System.out.print("Enter start town: ");
		String from = getInput();
		System.out.print("Enter end town: ");
		String to = getInput();
		int maxDistance = 0;
		while (maxDistance < 1) {
			System.out.print("Enter maximum allowed distance: ");
			try {
				maxDistance = Integer.parseInt(getInput());
			} catch (NumberFormatException e) {
			}
			if (maxDistance < 1) {
				System.err.println("Input value must be a number greater than zero");
			}
		}
		int result = GraphSearch.forGraph(graph).findNumberOfDifferentRoutes(from, to, maxDistance);
		System.out.println();
		System.out.println("Result: " + result);
	}

}
