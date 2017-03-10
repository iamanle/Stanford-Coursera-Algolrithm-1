package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inversion {
	private long count = 0;
	
	public long merge_sort(int[] arr){
		split(arr,0,arr.length-1);
		return count;
	}
	
	// Devide into 2 sub arrays
	private void split(int[] arr, int start, int end){
		if(start < end){
			// Split into 2 array using mid
			int mid = (start + end )/2;
			// Recall for left and right to split
			split(arr, start, mid);
			split(arr, mid + 1, end);
			// merge left and right
			merged(arr, start, mid, end);
		}
		
	}
	
	// Each time merge, element from left array sort and merge into the final F then continue
	// if element from right array merge into F, then count the left over from the right and it to inver_count
	private void merged(int[] arr, int start, int mid, int end){
		// Copy element need to be merged to a cache arr
		int[] cache = new int[arr.length];
		for(int i = start; i <= end; i++){
			cache[i] = arr[i];
		}
		
		int left_cache = start;
		int right_cache = mid + 1;
		int sorted_ele = start;
		
		while(left_cache <= mid && right_cache <= end){
			if(cache[left_cache] > cache[right_cache]){
				arr[sorted_ele] = cache[right_cache];
				count = count + (mid + 1 - left_cache);
				right_cache++;
			} else {
				arr[sorted_ele] = cache[left_cache];
				left_cache++;
			}
			sorted_ele++;
		}
		
		// Since the original array has the element from right_cache
		// We copy the rest of left cache
		while(sorted_ele <= end && left_cache <= mid){
			arr[sorted_ele++] = cache[left_cache++];
		}

	}

	public static void main(String[] args){
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week1/data/IntegerArray.txt");
		try(Scanner in = new Scanner(file)) {
			ArrayList<Integer> al = new ArrayList<>();
			while(in.hasNextLine()){
				al.add(in.nextInt());
			}
			
			int[] arr = new int[al.size()];
			for(int i = 0; i <  al.size(); i++){
				System.out.println(al.get(i));
				arr[i] = al.get(i);
			}
			
			System.out.println("Inversions: " + new Inversion().merge_sort(arr));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
