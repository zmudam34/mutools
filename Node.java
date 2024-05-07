public class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node() {
		this(null, null);
	}
	public Node(T theData) {
		this(theData, null);
	}
	public Node(T theData, Node<T> followingNode) {
		data = theData;
		next = followingNode;
	}
}
