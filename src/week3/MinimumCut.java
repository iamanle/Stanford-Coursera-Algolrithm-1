package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinimumCut {
	private Scanner in;
	private GraphAdjList graph;

	public void readFile() throws FileNotFoundException{
		graph = new GraphAdjList();
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week3/data/kargerMinCut.txt");
		in = new Scanner(file);

		while(in.hasNextLine()){
			String[] numbStr = in.nextLine().split("\\s+");
			ArrayList<Integer> numb = new ArrayList<Integer>();
			for(String s : numbStr){
				numb.add(Integer.parseInt(s));
			}
			graph.addWithArrayList(numb);
		}
	}

	public GraphAdjList getGraph(){
		return graph;
	}

	public static void main(String[] args) throws FileNotFoundException{
		int min = Integer.MAX_VALUE;

		for(int i = 0; i < 100; i++){
			MinimumCut test = new MinimumCut();
			test.readFile();
			int cons = test.getGraph().contraction();
			if(min > cons) min = cons;
			System.out.println(cons);
			System.out.println("Smallest cut: " + min);
		}
	}
}
