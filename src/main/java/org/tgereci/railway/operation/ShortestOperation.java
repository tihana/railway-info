package org.tgereci.railway.operation;

import java.io.IOException;
import org.tgereci.railway.graph.GraphSearch;
import org.tgereci.railway.model.Graph;

/**
 * Operation for calculating the length of the shortest route (in terms of distance to travel) between two towns.
 */
public class ShortestOperation extends Operation {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tgereci.railway.operation.Operation#execute(org.tgereci.railway.model.Graph2)
	 */
	@Override
	public void execute(Graph graph) throws IOException {
		System.out.println();
		System.out.println("Find the length of the shortest route (in terms of distance to travel) between two towns");
		System.out.println("---");
		System.out.print("Enter start town: ");
		String from = getInput();
		System.out.print("Enter end town: ");
		String to = getInput();
		int result = GraphSearch.forGraph(graph).findShortestRoute(from, to);
		System.out.println();
		System.out.print("Result: ");
		System.out.println(result == -1 ? "NO SUCH ROUTE" : result);
	}

}
