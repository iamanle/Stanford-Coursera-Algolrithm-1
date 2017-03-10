package week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
	Map<Integer,NodeEntry> table;
	
	public Graph(){
		table = new HashMap<Integer, NodeEntry>();
	}
	
	public void addEdge(Node v, Node e){
		NodeEntry update_entry = table.get(v.getValue());
		if(update_entry != null){
			update_entry.getNeighbors().add(e);
		}
	}
	
	public void addEntrance(Node v, ArrayList<Node> neighbors){
		table.put(v.getValue(), new NodeEntry(v, neighbors));
	}
	
	public Map<Integer,NodeEntry> getTable(){
		return table;
	}
	
	public ArrayList<Node> getNeighbors(Node node){
		return table.get(node.getValue()).getNeighbors();
	}

	public void print(){
		for(Map.Entry<Integer,NodeEntry> e : table.entrySet()){
			System.out.print(e.getValue().getVertice() + " Neighbors: ");
			for(Node n : e.getValue().getNeighbors()){
				System.out.print(n.getValue() +",");
			}
			System.out.println();
		}
	}
	
	public String adjacencyString() {
		String s = "Adjacency list";
		s += " (size Vertices: " + getNumVertices() + " Edges: " + getEdgeCount() + " ):";

		for (NodeEntry v : table.values()) {
			s += "\n\t"+v.getVertice().getValue()+": ";
			for (Node w : v.getNeighbors()) {
				s += w.getValue()+", ";
			}
		}
		return s;
	}

	public int getNumVertices(){
		return table.size();
	}
	
	
	public int getEdgeCount(){
		int count = 0;
		for(Map.Entry<Integer,NodeEntry> e : table.entrySet()){
			count+=((ArrayList<Node>) e.getValue().getNeighbors()).size();
		}
		return count;
	}
	
	public Graph reverse(){
		Map<Integer,NodeEntry> new_table = new HashMap<>();
		for(Map.Entry<Integer,NodeEntry> e : table.entrySet()){
			// Some nodes are not added when reversed, so we need to check and add them
			ArrayList<Node> leader_list =new ArrayList<>();
			if(new_table.containsKey(e.getValue().getVertice().getValue())){
				leader_list = new_table.get(e.getValue().getVertice().getValue()).getNeighbors();
			}
			NodeEntry new_leader = new NodeEntry(e.getValue().getVertice());
			new_leader.setNeighbors(leader_list);
			new_table.put(e.getValue().getVertice().getValue(), new_leader);
			
			for(Node n : e.getValue().getNeighbors()){
				ArrayList<Node> current_list = new ArrayList<>();
				NodeEntry new_entry = new NodeEntry(n);
				
				if(new_table.containsKey(n.getValue())){
					current_list = new_table.get(n.getValue()).getNeighbors();
				}
				
				current_list.add(e.getValue().getVertice());
				new_entry.setNeighbors(current_list);
				new_table.put(n.getValue(), new_entry);
			}
		}
		Graph new_graph = new Graph();
		new_graph.table = new_table;
		return new_graph;
	}

}
