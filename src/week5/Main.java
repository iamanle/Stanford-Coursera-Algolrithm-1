package week5;

public class Main {
	public static void main(String[] args){
		PQueue<Integer> pq = new PQueue<Integer>();
		
		pq.add(8);
		pq.add(7);
		
		pq.add(2);
////		pq.add(1);
////		pq.add(10);
		pq.add(15);
		pq.add(16);
		pq.add(14);
		pq.add(20);
		pq.add(21);
		for(Integer i : pq){
			System.out.println(i);
		}
		
		System.out.println("Test remove");
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
		System.out.println(pq.remove());
	}
}
