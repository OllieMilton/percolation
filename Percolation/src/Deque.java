import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node head;
	private Node tail;
	private int size;
	
	public Deque() {
		size = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}
		Node n = new Node(item);
		if (head != null) {
			head.prev = n;
			n.next = head;
		}
		head = n;
		if (tail == null) {
			tail = n;
		}
		size++;
	}
	
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}
		Node n = new Node(item);
		if (tail != null) {
			n.prev = tail;
			tail.next = n;
		}
		tail = n;
		if (head == null) {
			head = n;
		}
		size++;
	}
	
	public Item removeFirst() {
		Item result = null;
		if (head != null) {
			result = head.item;
			head = head.next;
			if (head == null) {
				tail = null;
			} else {
				head.prev = null;
			}
			size--;
		}
		return result;
	}
	
	public Item removeLast() {
		Item result = null;
		if (tail != null) {
			result = tail.item;
			tail = tail.prev;
			if (tail == null) {
				head = null;
			} else {
				tail.next = null;
			}
			size--;
		}
		return result;
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		for (int i=0; i < 100000; i++) {
			deque.addFirst(new Integer(i));
		}
		System.out.println("List size = "+deque.size());
		System.out.println("Last = "+deque.removeLast());
		System.out.println("First = "+deque.removeFirst());
		System.out.println("List size = "+deque.size());
		
		for (Integer i :deque) {
			System.out.println("Int = "+i);
		}
		while (deque.size() > 0) {
			System.out.println("Node removed = "+deque.removeLast());
		}
		System.out.println("Empty = "+deque.isEmpty());
				
		for (int i=0; i < 100000; i++) {
			deque.addLast(new Integer(i));
		}
		System.out.println("List size = "+deque.size());
		System.out.println("Last = "+deque.removeLast());
		System.out.println("First = "+deque.removeFirst());
		System.out.println("List size = "+deque.size());
		
		for (Integer i :deque) {
			System.out.println("Int = "+i);
		}
		while (deque.size() > 0) {
			System.out.println("Node removed = "+deque.removeFirst());
		}
		System.out.println("Empty = "+deque.isEmpty());
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node ref = head;
		
		@Override
		public boolean hasNext() {
			return ref != null;
		}

		@Override
		public Item next() {
			Item result = null;
			if (ref != null) {
				result = ref.item;
				ref = ref.next;
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		Item item;
		Node next;
		Node prev;
		Node(Item item) {
			this.item = item;
		}
	}
}
