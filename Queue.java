/*
  This class models the queue data structure using two stacks.
  It contains the same methods as the stack class except 
  implemented differently to how a queue operates.
*/

public class Queue<T> {

	// instance variables
	private ListStack<T> s1;
	private ListStack<T> s2;

	// default constructor
	public Queue() {
		s1 = new ListStack<T>();
		s2 = new ListStack<T>();
	}

	// adding an element to the queue
	public void enqueue(T element) {
		// push elements from first stack to second
		while (s1.size() != 0) {
			s2.push(s1.pop());
		}
		// add to first and push elements back
		s1.push(element);
		while (s2.size() != 0) {
			s1.push(s2.pop());
		}
	}

	// removing an element from the queue
	public T dequeue() {
		return s1.pop();
	}

	// returning first in the queue
	public T first() {
		return s1.peek();
	}

	// checking if queue is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	// size of the queue
	public int size() {
		return s1.size();
	}

}
