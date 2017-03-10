package week4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SCC {
	private Scanner in;
	private Graph graph;
	private Graph graph_reversed;
	Map<Integer,NodeEntry> table;
	Map<Integer,NodeEntry> table_reversed;

	private void readFile() throws FileNotFoundException{
		graph = new Graph();
		graph_reversed = new Graph();
		table = graph.getTable();
		table_reversed = graph_reversed.getTable();
		
		File file = new File("/Users/anle/Documents/workspace/AlgoI/src/week4/data/SCC.txt");
		in = new Scanner(file); 

		while(in.hasNextLine()){
			String[] numbStr = in.nextLine().split("\\s+");
			Node tail = new Node(Integer.parseInt(numbStr[0]));
			Node head = new Node(Integer.parseInt(numbStr[1]));

			ArrayList<Node> al = new ArrayList<>();
			if(table.containsKey(tail.getValue())){
				al.addAll(table.get(tail.getValue()).getNeighbors());
			}
			al.add(head);
			graph.addEntrance(tail, al);
			
			// Reversed graph
			ArrayList<Node> al_reversed = new ArrayList<>();
			if(table_reversed.containsKey(head.getValue())){
				al_reversed.addAll(table_reversed.get(head.getValue()).getNeighbors());
			}
			al_reversed.add(tail);
			graph_reversed.addEntrance(head, al_reversed);
			
			if(!table_reversed.containsKey(tail.getValue())){
				graph_reversed.addEntrance(tail, new ArrayList<>());
			}
		}
	}

	public void processKosraju() throws FileNotFoundException{
		readFile();
		
		System.out.println("Run Kosaraju first pass:");
		DFSLoop(graph_reversed,true);
		graph_reversed.print();
		System.out.println();
		
		System.out.println("Finished time order");
		for(Integer i : finished){
			System.out.print(i +", ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Run Kosaraju second pass:");
		DFSLoop(graph,false);
		graph.print();
		System.out.println();
		
		System.out.println("Print Count:");
		for(Map.Entry<Node, Integer> e : count.entrySet()){
			System.out.println("Leader: " + e.getKey().getValue() + ", Count: " + e.getValue());
		}
	}
	
	private Node leader;
	private ArrayList<Node> visited;
	private Map<Node,Integer> count;
	private ArrayList<Integer> finished;
	
	private void DFSLoop(Graph graph, boolean first){
		// Init
		leader = null;
		visited = new ArrayList<>();
		count = new HashMap<>();

		List<Integer> keys = new ArrayList<>();
		if(first){
			// First pass: traverse the biggest nodes 
			finished = new ArrayList<>();
			keys = new ArrayList<Integer>(table_reversed.keySet());
			Collections.sort(keys);
			
			// Traverse in reversed order
			for(int i = keys.size() - 1; i >= 0; i--){
				// If current node is not visited then set it as leader
				Node current = table_reversed.get(keys.get(i)).getVertice();
				if(!visited.contains(current)){
					leader = current;
					DFS_2(graph, current, true);
				}
			}
		} else {
			// Second pass: traverse in finished order
			keys = new ArrayList<>(finished);
			
			// Traverse in reversed order
			for(int i = keys.size() - 1; i >= 0; i--){
				// If current node is not visited then set it as leader
				Node current = table.get(keys.get(i)).getVertice();
				if(!visited.contains(current)){
					leader = current;
					DFS_2(graph, current, false);
				}
			}
		}
	}

	private void DFS_2(Graph graph, Node current, boolean phase){
		Stack<Node> stack_traverse = new Stack<>();
		Stack<Node> stack_finish = new Stack<>();
		
		stack_traverse.push(current);
		stack_finish.push(current);
		
		while(!stack_traverse.isEmpty()){
			Node curr = stack_traverse.pop();
			curr.setLeader(leader);
			
			if(count.get(leader) == null){
				count.put(leader, 1);
			} else {
				count.put(leader, count.get(leader)+1);
			}
			
			visited.add(curr);
			boolean found_neighbor = false;
			
			for(Node neighbor : graph.getNeighbors(curr)){
				if(!visited.contains(neighbor)){
					stack_traverse.push(neighbor);
					if(phase){
						stack_finish.push(neighbor);
					}

					found_neighbor = true;
				} 
				
			}
			
			if(phase && !found_neighbor){
				finished.add(stack_finish.pop().getValue());
			}
			
		}
		
		if(phase){
			while(!stack_finish.isEmpty()){
				finished.add(stack_finish.pop().getValue());
			}
		}
		
	}
	
	private void DFS(Graph graph, Node current){
		visited.add(current);
		current.setLeader(leader);

		if(count.get(leader) == null){
			count.put(leader, 1);
		} else {
			count.put(leader, count.get(leader)+1);
		}

		for(Node neighbor : graph.getNeighbors(current)){
			if(!visited.contains(neighbor)){
				DFS(graph, table.get(neighbor.getValue()).getVertice());
			}
		}
		finished.add(current.getValue());
	}

	public static void main(String[] args) throws FileNotFoundException {
		SCC test = new SCC();
		test.processKosraju();
	}

}
