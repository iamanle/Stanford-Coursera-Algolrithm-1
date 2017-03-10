package week6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q2 {
	/**
	 * insert number to 2 pq
	 * 1 stores all numbers less than its current head
	 * 1 stores all numbers greater than its current head
	 * When there is imbalance, take 1 from another add to the other queue
	 */
	PriorityQueue<Long> min_queue;
	PriorityQueue<Long> max_queue;
	long sum;
	
	private void maintainMedian(){
		min_queue = new PriorityQueue<>();
		max_queue = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return (int)(o2.longValue() - o1.longValue());
			}
		});
		
		
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week6/data/median.txt");
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
			String curr = bufferedReader.readLine();
			if(curr != null) min_queue.add(Long.parseLong(curr));
			else  return;
			int i = 1;
			sum += min_queue.peek();
			
			while((curr =  bufferedReader.readLine()) != null){
				System.out.println(sumMedian(Long.parseLong(curr),i));
				i++;
			}
//			46832030
//			46831213
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private long sumMedian(long numb, int i){
		if(numb > min_queue.peek()){
			min_queue.add(numb);
		} else {
			max_queue.add(numb);
		}
		
		// Rebalance
		if(Math.abs(min_queue.size() - max_queue.size()) > 1){
			if(min_queue.size() < max_queue.size()){
				min_queue.add(max_queue.poll());
			} else {
				max_queue.add(min_queue.poll());
			}
		}
		
		// Get median
		long med = 0;
		if(i%2 == 0){
			med = (min_queue.peek() + max_queue.peek()) / 2;	
		} else{
			if(min_queue.size() > max_queue.size()) med = min_queue.peek();
			else med = max_queue.peek();
		}
		System.out.println(med);
		return sum += med;
	}

	public static void main(String[] args){
		new Q2().maintainMedian();
	}
}
