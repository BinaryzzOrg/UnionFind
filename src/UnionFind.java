
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
	}//end CONSTRUCTOR

	// == GETTER SETTERS == //
	public Node[] getUniversal() {
		return universal;
	}//end method
	
	public void setUniversal(Node[] universal) {
		this.universal = universal;
	}//end method
	
	/*
	 * Union method is for connecting specified two nodes given by the user
  	 * first check if the root is null, therefore set the first element of the subset as the root
    	 * then if both nodes exist in the given set perform the following if conditions
         * if the the first given node already exist in the list of connected nodes connect the second node at the end of the list
	 * else if condition is the opposite of the first one where the second node already exist in the connected nodes
  	 * else the given two nodes are not in the list and just connect them to each other as next and previous
         * the for loop after the if condition is for keeping track of all the subsets given to ensure that if a pair already exist
	 * just connect that pair to the already connected nodes
	 */
	public void union(int A, int B) {
		Node firstNode = findNode(new Node(A));
		Node secondNode = findNode(new Node(B));

		if (root == null) {
			root = subsets[0];
		}

		if (firstNode != null && secondNode != null) {
			Node temp = root;

			if (rootContains(firstNode) && secondNode.getPrev() != null) {
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(secondNode.getPrev());
			} else if (rootContains(secondNode) && firstNode.getNext() != null) {
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(firstNode.getNext());
			} else {
				firstNode.setNext(secondNode);
				secondNode.setPrev(firstNode);
			}//end else if

			for (int index = 0; index < subsets.length; index++) {
				if (subsets[index] == null) {
					subsets[index] = firstNode;
					return;
				}
			}//end for
		}//end if
	}//end method

	/*
	 * find returns true if both the given nodes have the same root
	 */
	public boolean find(int A, int B) {
		return rootContains(new Node(A)) && rootContains(new Node(B));
	}//end method

	/*
	 * findNode is for finding a node inside the set given by the user
	 */
	private Node findNode(Node node) {
		for (int index = 0; index < getUniversal().length; index++) {
			if (node.getData() == getUniversal()[index].getData()) {
				return getUniversal()[index];
			}
		}
		return null;
	}//end method

	/*
	 * rootContains is for checking the main structure of the connected nodes to check
  	 * if a specified node already exist that is connected to the root
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
	}//end method
}//end class
