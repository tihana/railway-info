package org.tgereci.railway.operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.tgereci.railway.model.Graph;

/**
 * Operation.
 */
public abstract class Operation {

	/**
	 * Execute.
	 *
	 * @param graph
	 *            the graph
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public abstract void execute(Graph graph) throws IOException;

	/**
	 * Get user input. Exit the app for input "exit".
	 *
	 * @return the input
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		if (Option.isExit(input)) {
			System.out.println("Goodbye!");
			System.exit(0);
		}
		return input;
	}
}
