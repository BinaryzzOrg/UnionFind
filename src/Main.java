import java.util.Scanner;

public class Main {

	// === FIELD VARIABLES === //
	private static UnionFind uf;
	static Scanner sc;
	static Node[] universal;

	// === MAIN METHOD === //
	public static void main(String[] args) {

		System.out.print(":: What is the size of the set?\nUserInput%> ");
		int inputSize = checkUserInputMenu("What is the size of the set?\nUserInput%> ");
		universal = new Node[inputSize];

		System.out.print("\nEnter " + inputSize + " numbers to be added to the set.");
		for (int i = 0; i < inputSize; i++) {
			System.out.print("\nUserInput%> ");
			universal[i] = new Node(checkUserInput("\nUserInput%> "));

		} // end for
		uf = new UnionFind(universal.length, universal);
		printUniversalSet();

		Menu();
	}// end class

	/*
	 * The PrintMenuChoices method returns a formatted string for MainMenu. This
	 * formatted string is displayed to the user when they run the code for the
	 * first time. The method is also passed as a parameter to other methods that
	 * use the 'prompt' String.
	 */
	public static String printMenuChoices() {
		//@formatter:off
		String MenuChoicesAsString = "\n" 
				+ "  {Union Find MainMenu}\n" 
				+ "-=======================-\n"
				+ "| (1) : Union	 \n" 
				+ "| (2) : Find \n" 
				+ "| (3) : Exit \n" 
				+ "-=======================-\n"
				+ "Select an operation> ";
		//@formatter:on
		return MenuChoicesAsString;
	}// end method

	/*
	 * The MenuChoices method contains the following operations: Search, Insert,
	 * Delete, Display, and Exit. This method calls the PrintMenuChoices that prints
	 * out the choices for modifying the HashTable that is chosen by the user.
	 * MenuChoices method also handles miss inputs of the user and loops if it
	 * detects one.
	 */
	public static void Menu() {
		System.out.print(printMenuChoices());

		switch (checkUserInput(printMenuChoices())) {
		case 1: {// Union
			int A = checkElement();
			int B = checkElement();

			uf.union(A, B);
			System.out.println("\n" + A + " and " + B + " are now connected!");
			break;
		}

		case 2: {// Find
			int A = checkElement();
			int B = checkElement();

			//@formatter:off
			System.out.println((uf.find(A, B)) ? 
					  "\n" + A + " and " + B + " belong to the same subset"
					: "\n" + A + " and " + B + " don't belong to the same subset");
			//@formatter:on
			break;
		}

		case 3: {// Delete
			System.out.println(":: Exiting now...");
			System.exit(0);
			break;
		}

		default:
			// @formatter:off
			System.out.println("\n" 
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" 
					+ "┇ Error: \n"
					+ "┇ Input is not a valid Menu choice. \n"
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" 
					+ "┇ Msg: \n"
					+ "┇ \033[3mPlease enter only 1 to 3 as input\033[0m \n"
					+ "⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃");
			// @formatter:on
			break;
		}// end method

		Menu();
	}// end method

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing, and the 'isNotAllowed' parameter is
	 * for the setSize method. If 'isNotAllowed' is passed a 'true', then the
	 * checkUserMenu method would not accept any value that is less than 15.
	 */
	// TLDR - METHOD FOR DEALING WITH INTEGER INPUT
	public static int checkUserInput(String prompt) {
		sc = new Scanner(System.in);

		if (sc.hasNextInt()) {
			int value = sc.nextInt();

			return value;
		} // end if

		System.out.println(printCustomError("integer"));

		System.out.print(prompt);
		return checkUserInput(prompt);
	}// end if

	/*
	 * The CheckUserInputMenu method scans the user's input and checks if it is an
	 * integer. If the input is an integer, it is stored in the 'value' variable and
	 * returns it. If the input is not an integer, an error message is displayed,
	 * and the user is prompted to enter an integer value. The 'prompt' parameter is
	 * used for different scenarios of printing, and the 'isNotAllowed' parameter is
	 * for the setSize method. If 'isNotAllowed' is passed a 'true', then the
	 * checkUserMenu method would not accept any value that is less than 15.
	 */
	// TLDR - METHOD FOR DEALING WITH NEGATIVE INTEGERS
	public static int checkUserInputMenu(String prompt) {
		sc = new Scanner(System.in);

		if (sc.hasNextInt()) {
			int value = sc.nextInt();

			if (value < 1) {
				// @formatter:off
				System.out.println("\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Warning: Cannot accept integer < 1 input. \n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Notice: \033[3mPlease input another number.\033[0m\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
				// @formatter:on
				System.out.print(prompt);
				return checkUserInputMenu(prompt);
			}
			return value;
		} // end if

		System.out.println(printCustomError("integer"));

		System.out.print(prompt);
		return checkUserInput(prompt);
	}// end if

	public static String printCustomError(String type) {
		// @formatter:off
			return "\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Warning: Input is not a "+ type +" value. \n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Notice: \033[3mPlease only enter a "+ type +" value.\033[0m\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n";
		// @formatter:on
	}// end method

	public static int checkElement() {
		System.out.print("\n:: Select one element from set.\nUserInput%> ");
		int set = checkUserInput("\n:: Select one element from set.\nUserInput%> ");
		boolean setExist = false;
		for (Node node : universal) {
			if (node.getData() == set) {
				setExist = true;
				break;
			}
		} // end for

		if (setExist == false) {

			// @formatter:off
			System.out.print("\n" +
					"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
					"┇ Warning: "+ set +" does not exist in the universal set. \n" +
					"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
					"┇ Notice: \033[3mPlease input another number.\033[0m\n" +
					"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			// @formatter:on
			checkElement();
		} // end if

		return set;
	}// end class

	public static void printUniversalSet() {
		System.out.print("\nS = { ");
		for (Node node : universal) {
			System.out.print(node.getData() + " ");
		} // end for
		System.out.print("}\n");
	}// end method

}// end class
