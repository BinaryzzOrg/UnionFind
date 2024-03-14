public class Node {
	// == FIELD VARIABLES == //
	private int data;
	private Node prev;
	private Node next;
	
	// == CONSTRUCTOR == //
	public Node(int data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}//end CONSTRUCTOR
	
	public int getData() {
		return data;
	}//end method
	
	public void setData(int data) {
		this.data = data;
	}//end method
	
	public Node getPrev() {
		return prev;
	}//end method
	
	public void setPrev(Node prev) {
		this.prev = prev;
	}//end method
	
	public Node getNext() {
		return next;
	}//end method
	
	public void setNext(Node next) {
		this.next = next;
	}//end method
}//end class
