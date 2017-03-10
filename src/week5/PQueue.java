package week5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class PQueue<E> implements Iterable<E>{
	ArrayList<E> queue;
	Comparator<E> comp;
	
	public PQueue(){
		this.queue = new ArrayList<E>();
		this.comp = new Comparator<E>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(E o1, E o2) {
				return ((Comparable<E>)o1).compareTo(o2);
			}
		};
	}
	
	public PQueue(Comparator<E> comp){
		this.queue = new ArrayList<E>();
		this.comp = comp;
	}
	
	public void add(E e){
		queue.add(e);
		trickleUp();
	}
	
	public E remove(){
		swap(0,queue.size()-1);
		E data = queue.remove(queue.size()-1);
		trickleDown();
		return data;
	}
	
	private void trickleUp(){
		int i = queue.size() - 1;
		E curr = queue.get(i);
		
		while(curr != null && i > 0){
			int i_p = getParentIndex(i);
			E parent = queue.get(i_p);

			if(comp.compare(curr, parent) < 0){
				swap(i, i_p);
				i = i_p;
			} else {
				return;
			}
		}
	}
	
	private void trickleDown(){
		if(queue.size() == 0) return;
		int i = 0;
		E curr = queue.get(i);
		
		while(curr != null && 2*i+1 <  queue.size()){
			// Get the smallest child
			E left = queue.get(2*i + 1);
			E right = null;
			if(2*i + 2 < queue.size()) right = queue.get(2*i + 2);
			E smallest;
			int i_swap;
			
			int smallest_c;
			if(right == null){
				smallest_c = -1;
			}else{
				smallest_c = comp.compare(left, right);
			}
			
			if(smallest_c >= 0) {
				smallest = right;
				i_swap = 2*i + 2;
			} else {
				smallest = left;
				i_swap = 2*i + 1;
			}
			
			// Swap with smallest child if less than
			int smaller_c = comp.compare(curr, smallest);
			if(smaller_c > 0){
				swap(i,i_swap);
				i = i_swap;
			} else {
				return;
			}
		}
	}
	
	private void swap(int from, int to){
		if(from == to || from < 0 || to < 0
				|| from > queue.size() - 1 || to > queue.size() - 1) return;
		E tmp = queue.get(from);
		queue.set(from, queue.get(to));
		queue.set(to, tmp);
	}
	
	private int getParentIndex(int child){
		if(child % 2 == 0){
			return (child - 2)/2;
		}
		else {
			return (child - 1)/2;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}
	
}
