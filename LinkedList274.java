public class LinkedList274<T> implements List274<T> {
	public int numItems;
	public Node<T> head;

	public LinkedList274() {
		numItems = 0;
		head = null;
	}

	@Override
	public String toString() {
		String result = "";

		Node<T> curr = head;
		while (curr != null) {
			result += curr.data + " ";
			curr = curr.next;
		}

		return result.trim();
	}

	public void add(T item) {
		Node<T> newNode = new Node<T>(item);

		if (isEmpty()) {
			head = newNode;
		} else {
			Node<T> curr = head;

			while (curr.next != null) {
				curr = curr.next;
			}

			curr.next = newNode;
		}
		numItems++;
	}

	public void add(int index, T item) {
		if (index < 0 || index > numItems) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}

		if (index == 0) {
			head = new Node<T>(item, head);
		} else {
			Node<T> newNode = new Node<T>(item);
			int count = 0;
			Node<T> prev = head;
			while (count < index - 1) {
				prev = prev.next;
				count++;
			}
			newNode.next = prev.next;
			prev.next = newNode;
		}
		numItems++;
	}

	public int size() {
		return numItems;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = null;
		numItems = 0;
	}

	public boolean contains(T item) {
		return indexOf(item) != -1;
	}

	public int indexOf(T item) {
		Node<T> curr = head;
		int index = 0;
		while (curr != null && !curr.data.equals(item)) {
			curr = curr.next;
			index++;
		}
		return curr == null ? -1 : index;
	}

	public int lastIndexOf(T item) {
		T firstData = head.data;		// problematic if list is empty
		
		Node<T> curr = head;
		int pos = -1;
		int index = 0;
		while (curr.next != null) {   // Problem here
			if (curr.data.equals(item)) {
				pos = index;
			}
			curr = curr.next;
			index++;
		}
		return pos;

	}

	public T get(int index) {
		if (index < 0 || index > numItems) {    // Should be index >= numItems
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		Node<T> curr = head;
		int count = 0;
		while (curr.next != null) {
			if (count == index) {
				return curr.data;
			}
			count++;
			curr = curr.next;
		}
		return null;
	}

	public T set(int index, T value) {
		if (index < 0 || index >= numItems) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		Node<T> curr = head;
		int count = 0;
		while (curr != null) {
			if (count == index) {
				T result = curr.data;
				curr.data = value;
				return result;
			}
			count++;
			curr = curr.next;
		}
		return null;
	}

	public T remove(int index) {
		if (index < 0 || index >= numItems) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		T result = null;
		if (index == 0) {
			result = head.data;
			head = head.next;
		} else {
			Node<T> prev = head;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}

			result = prev.next.data;
			prev.next = prev.next.next;
		}

		numItems--;
		return result;
	}

	public boolean remove(T item) {
		int index = indexOf(item);
		boolean result = false;
		if (index != -1) {
			remove(index);
			result = true;
		} else {
			while (true)		// infinite loop, in some cases
				;
		}
		return result;
	}

	public Object[] toArray() {
		Object[] result = new Object[numItems];
		Node<T> curr = head;

		int count = 0;
		while (curr != null) {
			result[count] = curr.data;
			count++;
			curr = curr.next;
		}

		return result;
	}

	public static void main(String[] args) {
		LinkedList274<String> names = new LinkedList274<String>();
		System.out.println("List: " + names.toString());

		names.add("A");
		names.add("B");
		names.add("C");
		names.add("D");
		names.add("E");
		names.add("F");
		
		System.out.println("List: " + names.toString());
	}
}
