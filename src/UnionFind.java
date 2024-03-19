
public class UnionFind {
	// == FIELD VARIABLES == //
	private Node[] universal;
	private Node[] subsets;
	private Node root;

	// == CONSTRUCTOR == //
	public UnionFind(int size, Node[] universal) {
		this.setUniversal(universal);
		this.subsets = new Node[size];
		this.root = null;
	}// end CONSTRUCTOR

	// == GETTER SETTERS == //
	public Node[] getUniversal() {
		return universal;
	}// end method

	public void setUniversal(Node[] universal) {
		this.universal = universal;
	}// end method

	/*
	 * Union method is for connecting specified two nodes given by the user first
	 * First check if both the nodes given already exist in the root, if yes print
	 * that they are already connected and end the method, else if they are trying
	 * to connect the node to itself end the method also. If no condition were
	 * satisified in the first if structure proceed to the second where if the first
	 * node exist in the connected nodes, add the second node to the end, else if
	 * the second node exist add the first node in the end. Lastly, if both the
	 * provided node does not exist yet just connect them to each other. The loop
	 * after the ifs is for keeping track of the connected nodes that are not
	 * connected yet to the root but connected to each other so when union executes
	 * it connects both the nodes in the connected roots. Additionally, if the root
	 * is null just set the first subset as the root.
	 */
	public void union(int A, int B) {
		Node firstNode = find(new Node(A));
		Node secondNode = find(new Node(B));

		if (rootContains(firstNode) && rootContains(secondNode)) {
			//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Elements " + A + " and " + B + " are ALREADY CONNECTED to each other!\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on
			return;
		} else if (firstNode.getData() == secondNode.getData()) {
			//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Can't Union the SAME elements.\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on
			return;
		}

		if (firstNode != null && secondNode != null) {
			Node temp = root;
			if (rootContains(firstNode)) {
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(secondNode);
			} else if (rootContains(secondNode)) {
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(firstNode);
			} else {
				firstNode.setNext(secondNode);
				secondNode.setPrev(firstNode);
			}

			//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Elements " + A + " and " + B + " are now connected!\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on

			for (int index = 0; index < subsets.length; index++) {
				if (subsets[index] == null) {
					subsets[index] = firstNode;

					if (root == null) {
						root = subsets[0];
					}
					return;
				}
			} // end for
		} // end if
	}// end method

	/*
	 * find method returns true if both the given nodes have the same root
	 */
	public boolean find(int A, int B) {
		return rootContains(new Node(A)) && rootContains(new Node(B));
	}// end method

	/*
	 * This overloaded find node method is for getting the element from the user
	 * given set to keep references of connected nodes that are connected to a node
	 * in the user set
	 */
	private Node find(Node node) {
		for (int index = 0; index < universal.length; index++) {
			if (node.getData() == universal[index].getData()) {
				return universal[index];
			}
		}
		return null;
	}

	/*
	 * rootContains is for checking the main structure of the connected nodes to
	 * check if a specified node already exist that is connected to the root
	 */
	private boolean rootContains(Node node) {
		Node temp = root;
		while (temp != null) {
			if (temp.getData() == node.getData()) {
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}// end method
}// end class
