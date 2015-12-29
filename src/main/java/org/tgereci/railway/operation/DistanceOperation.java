package org.tgereci.railway.operation;

import java.io.IOException;
import org.tgereci.railway.graph.GraphSearch;
import org.tgereci.railway.model.Graph;

/**
 * Operation for calculating the distance of the route.
 */
public class DistanceOperation extends Operation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tgereci.railway.operation.Operation#execute(org.tgereci.railway.model.Graph2)
	 */
	@Override
	public void execute(Graph graph) throws IOException {
		System.out.println();
		System.out.println("Find the distance of the route");
		System.out.println("---");
		System.out.print("Enter route (towns separated by dashes, for example \"A-B-C\"): ");
		int result = GraphSearch.forGraph(graph).findRouteDistance(getInput());
		System.out.println();
		System.out.print("Result: ");
		System.out.println(result == -1 ? "NO SUCH ROUTE" : result);
	}

}
