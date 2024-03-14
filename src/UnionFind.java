
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

	/*
	 * 
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
	 * 
	 */
	public boolean find(int A, int B) {
		return rootContains(new Node(A)) && rootContains(new Node(B));
	}//end method

	/*
	 * 
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
	 * 
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

	// == GETTER SETTERS == //
	public Node[] getUniversal() {
		return universal;
	}//end method
	
	public void setUniversal(Node[] universal) {
		this.universal = universal;
	}//end method
}//end class