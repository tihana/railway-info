package org.tgereci.railway;

import java.io.IOException;
import org.tgereci.railway.graph.GraphBuilder;
import org.tgereci.railway.model.Graph;
import org.tgereci.railway.operation.Operation;
import org.tgereci.railway.operation.OperationFactory;
import org.tgereci.railway.operation.Option;

/**
 * Railway info.
 */
public class Railway {

	/**
	 * Run railway info app.
	 */
	public void run() {
		System.out.println("Welcome to Railway Information About The Routes!");
		System.out.println("To exit the app enter \"exit\" anytime.");

		Graph graph = null;
		while (graph == null) {
			try {
				System.out.println();
				System.out.println("Enter graph (list of distances between two towns separated by commas, each item in the list consists of"
						+ " start town, end town and distance, for example \"AB5, BC7, CD3\"): ");
				graph = GraphBuilder.create(Operation.getInput()).build();
				System.out.println();
				System.out.println("Graph entered:");
				System.out.println(graph);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		while (true) {
			try {
				Option option = chooseOption();
				Operation operation = OperationFactory.getOperation(option);
				if (operation == null) {
					System.err.println("Invalid input option, allowed options are 1, 2, 3, 4 or exit");
					continue;
				}
				operation.execute(graph);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Choose option.
	 *
	 * @return the selected option
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private Option chooseOption() throws IOException {
		System.out.println();
		System.out.println("What kind of information do you need?");
		System.out.println("1 - the distance of the route");
		System.out.println("2 - the number of trips between two towns with maximum or exact number of stops");
		System.out.println("3 - the length of the shortest route (in terms of distance to travel) between two towns");
		System.out.println("4 - the number of different routes between two towns");
		System.out.println("exit - exit the app");
		System.out.print("Select option (1-4): ");
		return Option.getByCode(Operation.getInput());
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Railway railway = new Railway();
		railway.run();
	}

}
