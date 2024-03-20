
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
	 * node exist in the root and the other exist in the disjoint set traverse to end
  	 * of the root then connect it the following else if is its opposite where the
    	 * second node exist in the root and the first node exist in the disjoint subsets
      	 * if that is the case connect the first node at the end of the root. Moreover,
	 * the next two else if is for connecting a node that is in the root and a node
  	 * that doesn't exist in the disjoint subsets meaning that they can just be
    	 * connected alone at the end of the root. Furthermore, the next else if is
      	 * that if both provided nodes are from a disjoint set this if condition would
	 * connect both of them and set the first provided node to be the root and if
  	 * none of these else if condition are satisified proceed to else where 
    	 * both nodes would just be connected to each other followed by storing it
      	 * to the disjoint subsets array.
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

		if(rootContains(firstNode) && disjointContains(secondNode)) {
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			if(secondNode.getPrev() != null) {
				temp.setNext(secondNode.getPrev());
			} else {
				temp.setNext(secondNode.getNext().getPrev());
			}
		}
		else if(rootContains(secondNode) && disjointContains(firstNode)) {
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			if(firstNode.getPrev() != null) {
				temp.setNext(firstNode.getPrev());
			} else {
				temp.setNext(firstNode.getNext().getPrev());
			}
		}	
		else if(rootContains(firstNode)) {
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(secondNode);
		} 
		else if(rootContains(secondNode)) {
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(firstNode);
		}
		else if(disjointContains(firstNode) && disjointContains(secondNode)) {
			Node firstRoot = firstNode;
			Node referenceToBeginning = firstRoot;
			while(firstRoot.getNext() != null) {
				firstRoot = firstRoot.getNext();
			}
			
			if(secondNode.getPrev() != null) {
				firstRoot.setNext(secondNode.getPrev());
			} else {
				firstRoot.setNext(secondNode);
			}
			
			if(firstNode.getPrev() != null) {
				root = referenceToBeginning.getPrev();
				return;
			}
			root = referenceToBeginning;
		}
		else {
			firstNode.setNext(secondNode);
			secondNode.setPrev(firstNode);
		}
		for(int index = 0; index < subsets.length; index++) {
			if(subsets[index] == null) {
				subsets[index] = firstNode;
				if(root == null) {
					root = subsets[0];
				}
				return;
			}
		}
		//@formatter:off
			System.out.println("\n"+
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n" +
						"┇ Elements " + A + " and " + B + " are now connected!\n" +
						"⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃⁃\n");
		//@formatter:on
	}// end method

	/*
	 * find first checks if the provided data of the user exist in the disjoint subset
  	 * to confirm if they are connected to each other else check the root
    	 * if both provided data have the same root return true else return false.
	 */
	public boolean find(int A, int B) {
		Node firstNode = new Node(A);
		Node secondNode = new Node(B);
		
		for(int index = 0; index < subsets.length; index++) {
			if(subsets[index] != null && subsets[index].getNext() != null) {
				if(subsets[index].getData() == firstNode.getData() && subsets[index].getNext().getData() == secondNode.getData()) {
					return true;
				}
				
				if(subsets[index].getData() == secondNode.getData() && subsets[index].getNext().getData() == firstNode.getData()) {
					return true;
				}
			}
		}
		return rootContains(firstNode) && rootContains(secondNode);
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
	}//end method

	/*
 	 * dijointContains checks the dsjoint subsets array to find a specific node if it exist
   	 * return true if that specified node is connected to a disjoint subset else false
 	 */
	private boolean disjointContains(Node node) {
		for(int index = 0; index < subsets.length; index++) {
			if(subsets[index] != null && subsets[index].getNext() != null) {
				if(node.getData() == subsets[index].getData() || node.getData() == subsets[index].getNext().getData()) {
					return true;
				}
			}
		}
		return false;
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
