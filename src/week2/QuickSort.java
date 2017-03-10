package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class QuickSort {
	int count = 0;
	public static void main(String[] args) {
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week2/data/QuickSort.txt");
		try(Scanner in = new Scanner(file)) {
			ArrayList<Integer> al = new ArrayList<>();
			while(in.hasNextLine()){
				al.add(in.nextInt());
			}
			
			int[] arr = new int[al.size()];
			for(int i = 0; i <  al.size(); i++){
				arr[i] = al.get(i);
			}
			
			QuickSort qs = new QuickSort();
			qs.quickSort(arr);
			
			System.out.println("Count: " + qs.count);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

	}
	
	public int[] quickSort(int[] arr){
		count += arr.length - 1;
		internalSort(arr, 0, arr.length-1);
		return arr;
	}

	private void internalSort(int[] arr, int left, int right){
		if(right == left) return;
		int pivot = chooseMedianPivot(arr, left, right);
		swap(arr, left, pivot);
		int pivot_new_position = partition(arr, left, right);
		
		if(left < pivot_new_position-1){
			count += (pivot_new_position-1) - left;
			internalSort(arr, left, pivot_new_position-1);
		}
		
		if(pivot_new_position+1 < right){
			count += right - (pivot_new_position+1);
			internalSort(arr, pivot_new_position+1, right);
		}
		
	}
	
	private int partition(int[] arr, int left, int right){
		int pivot = arr[left];
		int i = left+1;

		for(int j = left + 1; j <= right; j++ ){
			if(arr[j] < pivot){
				swap(arr,j,i);
				i = i + 1;
				
			}
		}

		swap(arr,left,i-1);
		return i-1;
	}
	
	private int choosePivot(int[] arr, int left, int right){
		return left;
	}
	
	private int chooseLastPivot(int[] arr, int left, int right){
		return right;
	}
	
	private int chooseMedianPivot(int[] arr, int left, int right){
		int mid = left + ((right - left)/2);
		if(arr[mid] < Math.max(arr[left],arr[right]) && arr[mid] > Math.min(arr[left],arr[right])) return mid;
		if(arr[left] < Math.max(arr[mid],arr[right]) && arr[left] > Math.min(arr[mid],arr[right])) return left;
		return right;
	}
	
	private void swap(int[] arr, int left, int right){
		int tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
	}
	
	
}

//private int chooseRandomPivot(int[] arr, int left, int right){
//	return (int)(Math.random()*(right-left) + left);
//}
//
//private int partition(int[] arr, int pivot, int left, int right){
//	while(left <= right){
//		while(arr[left] < arr[pivot]) left++;
//		while(arr[right] > arr[pivot]) right--;
//		if(left <= right) {
//			swap(arr, left, right);
//			left++;
//			right--;
//		}
//	}
//	swap(arr,left-1,pivot);
//	return left-1;
//}
