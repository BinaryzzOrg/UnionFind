
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
	 * check if the root is null, therefore set the first element of the subset as
	 * the root then if both nodes exist in the given set perform the following if
	 * conditions if the the first given node already exist in the list of connected
	 * nodes connect the second node at the end of the list else if condition is the
	 * opposite of the first one where the second node already exist in the
	 * connected nodes else the given two nodes are not in the list and just connect
	 * them to each other as next and previous the for loop after the if condition
	 * is for keeping track of all the subsets given to ensure that if a pair
	 * already exist just connect that pair to the already connected nodes
	 */
	public void union(int A, int B) {
		Node firstNode = new Node(A);
		Node secondNode = new Node(B);

		if (rootContains(firstNode) && rootContains(secondNode)) {
			//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇\033[3m Elements " + A + " and " + B + " are ALREADY CONNECTED to each other!\033[0m\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on
			return;
		} else if (rootContains(firstNode) == rootContains(secondNode)) {
			//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇\033[3mCan't Union the SAME elements.\033[0m\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on
			return;
		}

		if (firstNode != null && secondNode != null) {
			Node temp = root;
			// root contains the first node input and its connected is not equal null
			// traverse to end
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
						"┇\033[3m Elements " + A + " and " + B + " are now connected!\033[0m\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
			//@formatter:on

			for (int index = 0; index < subsets.length; index++) {
				if (subsets[index] == null) {
					subsets[index] = firstNode;
//					System.out.println(subsets[index].getData() + " " + subsets[index].getNext().getData());

					if (root == null) {
						root = subsets[0];
					}
					return;
				}
			} // end for
		} // end if
	}// end method

	/*
	 * find returns true if both the given nodes have the same root
	 */
	public boolean find(int A, int B) {
		return rootContains(new Node(A)) && rootContains(new Node(B));
	}// end method

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
