package week6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static long twoSum(ArrayList<Long> arr){
		long count = 0;
		Map<Long,Long> table = new HashMap<>();
		
		for(long t = -10000; t <= 10000; t++){
			table = new HashMap<>();
			
			// Calculate the remaining t-x and put to key, value is the main number
			for(int i = 0; i < arr.size(); i++){
				table.put(t - arr.get(i), arr.get(i));
			}
			
			// Scan for a pair
			for(int i = 0; i < arr.size(); i++){
				if(table.containsKey(arr.get(i))){
					count++;
					break;
				}
			}
			
			System.out.println(count);
		}
		
		
		return count;
	}
	
	private static ArrayList<Long> readFile(File file){
		ArrayList<Long> arrayList = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));){
			String curr;
			
			while((curr = bufferedReader.readLine()) != null){
				StringTokenizer stringTokenizer = new StringTokenizer(curr);
				arrayList.add(Long.parseLong(stringTokenizer.nextToken()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arrayList;
	}
	
	public static void main(String[] args) {
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week6/data/sum.txt");
		System.out.println("Result: " + twoSum(readFile(file)));
		
	}

}
