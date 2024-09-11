package h05;

public class SortedLinkedList<E extends Comparable<E>> implements SortedLinkedListInterface<E>{
	
	// Class for the LinkedNode
	private static class LinkedNode<T> {
		private T item;
		private LinkedNode<T> next;

		private LinkedNode(T value) {
			item = value;
			next = null;
		}

		private LinkedNode(T value, LinkedNode<T> reference) {
			item = value;
			next = reference;
		}

		public String toString() {
			return this.item.toString();
		}
	}
	// End of LinkedNode definition
	
	// Variables 
	private int size;
	// Start of the Linked list, if empty it is null
	private LinkedNode<E> head;
	// End of the Linked list, if empty it is null
	private LinkedNode<E> tail;
	
	// Boolean to check invariants
	private void verify(boolean mustBeTrue) {
	    if (! mustBeTrue) {
	    	throw new java.lang.AssertionError("assertion error");
	    }
	}
	
	// Method to check invariants 
	private void checkInvariants() {
	    // uncomment the next line to skip the checks:
	    // return;
	    // either head and tail are both null, or neither is null.
	    // size is zero if and only if they are null, and otherwise is positive
	    verify((head == null) == (tail == null));
	    verify((size == 0) == (head == null));
	    verify(size >= 0);
	    // if the list only has one element, head should be the same as tail
	    // (and also if the list has no elements), otherwise they should differ
	    verify((head == tail) == (size <= 1));
	    // a non-null tail variable should always have a null "next" field
	    verify((tail == null) || (tail.next == null));
	    // check to make sure size is the same as the length of the list.
	    // this code takes O(n), so comment it out if performance is important
	    int measuredSize = 0;
	    LinkedNode<E> node = head;
	    // if visitedLast is null, the list is empty, and tail should also be null
	    LinkedNode<E> visitedLast = null;
	    while (node != null) {
	      visitedLast = node;
	      node = node.next;
	      measuredSize++;
	    }
	    verify(measuredSize == size);
	    // also make sure "last" really is the last node in the linked list
	    verify(visitedLast == tail);
	}
	
	// Constructor that initializes list
	public SortedLinkedList() {
		size = 0;
		head = null;
		tail = null;
		// Check if the list is a proper Linked List
		checkInvariants();
	}
	
	// Helper method for add() method------------------------------------------------------------------------------------------------------------
	private void addAtFront(E value) {
	    head = new LinkedNode<E>(value, head);
	    if (tail == null) {
	    	tail = head;
	    }
	}
	
	
//	private void addAtEnd(E value) {
//	    if (tail == null) {
//	    	throw new RuntimeException ("invalid call to addAtEnd, tail is null");
//	    }
//	    LinkedNode<E> newNode = new LinkedNode<E>(value);
//	    tail.next = newNode;
//	    tail = newNode;
//	}
	
//	private void addAfter(LinkedNode<E> reference, E value) {
//	    LinkedNode<E> newNode = new LinkedNode<E>(value, reference.next);
//	    reference.next = newNode;
//	    if (reference == tail) {  // if added at end, update tail value
//	    	tail = newNode;
//	    }
//	}
	
	// End of helper methods for add()-----------------------------------------------------------------------------------------------------------
	
	@Override
	public boolean add(E value) {
		LinkedNode<E> current = head;
		LinkedNode<E> prev = null;
		LinkedNode<E> newNode = new LinkedNode<E>(value);
		
		if(value == null) 
			return false;
		
		if(head == null || size == 0) {
			head = new LinkedNode<E>(value, head);
			size++;
			return true;
		}
		
		if(value.compareTo(head.item) < 0) {
			head = new LinkedNode<E>(value, head);
			size++;
			return true;
		}
		
		else if(value.compareTo(tail.item) > 0) {
			tail.next = newNode;
			tail = newNode;
			size++;
			return true;
		}
		
		if(value.compareTo(head.item) == 0) {
			return false;
		}
		
		if(size >= 2) {
			for(int i = 0; i < size-1; i++) {
				current = current.next;
				if(i == 0) 
					prev = head;
				else 
					prev = prev.next;
				
				if(value.compareTo(current.item) == 0) {
					return false;
				}
				
				if(value.compareTo(prev.item) > 0 && value.compareTo(current.item) < 0) {
					prev.next = newNode;
					newNode.next = current;
					size++;
					return true;
				}
				if(i == size - 2) {
					if(tail == null) {
						throw new RuntimeException ("invalid call to addAtEnd");
					}
					tail.next = newNode;
					tail = newNode;
					size++;
					return true;
				}
			}
		}

//		if(value == null) 
//			return false;
//		
//		// Adds very first element in the linked list 
//		if (size == 0 || head == null) {
//			addAtFront(value);
//			size++;
//			return true;
//		}
//		LinkedNode<E> current = head;
//		LinkedNode<E> prev = null;
//		for(int i = 0; i < size; i++) {
////			current = current.next;
//			// If there is a duplicate, does not add value
//			if(value.compareTo(current.item) == 0) {
//				return false;
//			}
//			// Adding value before head
//			else if(value.compareTo(head.item) < 0) {
//				LinkedNode<E> newNode = new LinkedNode<E>(value);
//				newNode.next = current;
//				head = newNode;
//				prev = current;
//				size++;
//				return true;
//			}
//			// Adding value that is greater than current 
//			else if(value.compareTo(current.item) > 0) {
//				LinkedNode<E> newNode = new LinkedNode<E>(value, current.next);
//				current.next = newNode;
//				size++;
//				return true;
//			}
//			// Adding value that is less than current
//			else if(value.compareTo(current.item) < 0) {
//				LinkedNode<E> newNode = new LinkedNode<E>(value, current);
//				current = newNode;
//				size++;
//			}
//			current = current.next;
////			prev = prev.next;
//		}
		return false;
	}

	@Override
	public int size() {
		// Returns size variable of the linked list
		return size;
	}

	@Override
	public E get(int index) {
		// given index if not out of bound, loop to index and return item
		if(index < 0 || index > size) {
			return null;
		}
		LinkedNode<E> element = head;
		for(int i = 0; i < index; i++) {
			if(index != i)
			element = element.next;
		}
		return element.item;
	}

	@Override
		public boolean remove(E value) {
			LinkedNode<E> search = head;
			LinkedNode<E> prev = null;
			
			// If Linked List is empty, then return false
			if (size == 0) {
				return false;
			}
			
			// If value is not in the Linked List, then return false
			if (indexOf(value) == -1)
				return false;
			
			// If size == 1, head = null
			if (size == 1 && value.compareTo(head.item)== 0) {
				head = null;
				size--;
				return true;
			}
			//If size is greater than 1
			if (size != 1) {
				//If value == head.item, moves the head to head.next
				if (value.compareTo(head.item)==0) {
					head = head.next;
					size--;
					return true;
				}
				for (int i = 0; i < size - 1; i++) {
					// Moves search pointer one place to the right
					search = search.next;
					// Delays prev pointer by one so it's always behind search
					if (i == 0) {
						prev = head;
					}else {
						prev = prev.next;
					}
					// If the value is found where search is at
					if (value.compareTo(search.item)== 0) {
						/**
						 * If there's no more node next to search, it means search == tail
						 * set tail to prev pointer, tail.next to null
						 */
						if(search.next == null) {
							tail = prev;
							tail.next = null;
							size--;
							return true;
						}
						/**
						 * If there are still node next to search
						 * prev.next = search.next (removes access to search)
						 */
						else {
							prev.next = search.next;
							size--;
							return true;
						}
					}
				}
			}
		
			return false;
		}

	
//		LinkedNode<E>current = head;
//		LinkedNode<E>prev = head;
//		
//		// No elements in the list
//		if(head == null || size == 0) {
//			return false;
//		}
//		// Removing last node in list
//		if(current.next == null) {
//			prev.next = null;
//			size--;
//			return true;
//		}
//		int counter = 0;
//		while(counter != size) {
//			if(value == head.item) {
//				head = head.next;
//				size--;
//				return true;
//			}
//			else if(value == current.item) {
//				prev = current;
//				current = current.next;
//				size--;
//				return true;
//			}
//			current = current.next;
//			counter++;
//		}
		// Loop to iterate through list 
//		for(int i = 0; i < size; i++) {
//			if(value == current.item) {
//				current.next = current.next.next;
//				size--;
//				return true;
//			}
//			else if(value == current.item) {
//				prev = current;
//				current = current.next.next;
//				size--;
//				return true;
//			}
//			current = current.next;
////			prev = prev.next;
//		}
		
		
		// Case 1: If size == 0 return false
		// Case 2: Best case, if head = the value, and size != 1
		// head = head.next . If size = 1, head = null
		// Case 3: Normal remove, if value found at node != head or search.prev.next == search.next
		// Case 4: If tail = value, search.prev = tail, search.prev.next = null
//		return false;
//	}

	@Override
	public int indexOf(E value) {
//		int index = -1;
//		LinkedNode<E> search = head;
//		for (int i = 0; i < size; i++) {
//			if(search == value) {
//				index = i;
//				break;
//			}
//			else {
//				search = search.next;
//			}
//		}
//		return index;
		int index = 0;
		if (size == 0) {
			return -1;
		}
		if (head.item.compareTo(value) == 0) {
			return 0;
		}
		LinkedNode<E> search = head;
		for(int i = 1; i < size; i++) {
			search = search.next;
			index++;
			if(search.item.compareTo(value) == 0) {
				return index;
			}
		}
		return -1;	
	}
	
	public String toString() {
//		String result = "";
//		LinkedNode<E> node = head;
//		for (int i = 0; i < size; i++) {
//			// add code here
//			if(node == head) {
//				result += node.item;
//			}
//			result += " " + node.item;
//			node = node.next;
//		}
//		return result;
		String str = "";
		if (head != null) {
			str += head.item;
			LinkedNode<E> current = head.next;
			while (current !=  null) {
				str += " " + current.item;
				current = current.next;
			}
		}
		return str;	
	}
	
	// For testing
	public static void main(String[] args) {
		
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		list.add("football");
		list.add("soccer");
		list.add("baseball");
		list.add("hockey");
		System.out.println(list);
		list.remove("baseball");
		System.out.println(list);
		list.remove("football");
		System.out.println(list);
	}
	
}
