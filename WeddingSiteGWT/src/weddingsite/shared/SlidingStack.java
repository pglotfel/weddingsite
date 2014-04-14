package weddingsite.shared;

import java.util.ArrayList;

public class SlidingStack<T> {
	
	private ArrayList<T> stack;
	private int size;
	private int current;
	private int actualSize;
	
	public SlidingStack(int size) {
		stack = new ArrayList<T>();
		this.size = size;
		current = 0;
		actualSize = -1;
		for(int i = 0; i < size; i++) {
			stack.add(null);
		}
	}
	
	public T pop() {
		if(actualSize < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(current == 0) {
			current = (size - 1);
		} else {
			current--;
		}
		
		actualSize--;
		return stack.get(current);		
	}
	
	public void push(T obj) {
		stack.set(current, obj);
		current = (current + 1) % size;
		if(actualSize < size - 1) {
			actualSize++;
		} 
	}
}
