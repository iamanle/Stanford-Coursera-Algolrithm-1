package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class GraphAdjList {
	HashMap<Integer, ArrayList<Integer>> lists;
	
	public GraphAdjList(){
		lists = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	public HashMap<Integer, ArrayList<Integer>> getLists(){
		return lists;
	}
	
	public ArrayList<Integer> getNeighbor(Integer v){
		if(lists.get(v) == null) return new ArrayList<>();
		return new ArrayList<>(lists.get(v));
	}
	
	public boolean contains(Integer v){
		return lists.containsKey(v);
	}
	
	public void addVertex(Integer v){
		lists.put(v, new ArrayList<>());
	}
	
	public void addEdge(Integer v, Integer e){
		(lists.get(v)).add(e);
	}
	
	public void addEntrance(Integer v, ArrayList<Integer> neighbors){
		lists.put(v, neighbors);
	}
	
	public void addWithArrayList(ArrayList<Integer> numb){
		Integer v = numb.remove(0);
		if(lists.containsKey(v)){
			numb.addAll(lists.get(v));
		}
		lists.put(v, numb);
	}
	
	public boolean removeEdge(Integer from, Integer to){
		if(!lists.containsKey(from) || !lists.containsKey(to)) return false;
		// Assume this is only undirected map
		if(lists.get(from).remove(to) && lists.get(to).remove(from)){
			return true;
		}
		return false;
	}
	
	public boolean removeVertice(Integer v){
		if(!lists.containsKey(v)) return false;
		ArrayList<Integer> neighbors = getNeighbor(v);
		for(Integer i : neighbors){
			if(lists.containsKey(i)){
				// remove object not at index
				lists.get(i).remove(v);
			}
		}
		lists.remove(v);
		return true;
	}
	
	public String adjacencyString() {
		String s = "Adjacency list";
		s += " (size Vertices: " + getNumVertices() + " Edges: " + getEdgeCount() + " integers):";

		for (int v : lists.keySet()) {
			s += "\n\t"+v+": ";
			for (int w : lists.get(v)) {
				s += w+", ";
			}
		}
		return s;
	}
	
	public void mergeVertex(Integer v1, Integer v2){
		// Direct all edges from v2 to v1
		ArrayList<Integer> v2_neighbors = getNeighbor(v2);
		lists.get(v1).addAll(v2_neighbors);
		
		// Remove v2
		lists.remove(v2);
		
		// Update the related edges
		for(Entry<Integer, ArrayList<Integer>> e : lists.entrySet()){
			ArrayList<Integer> currentList = e.getValue();
			for(Integer n : currentList){
				if(n.intValue() == v2.intValue()){
					currentList.set(currentList.indexOf(n), v1);
				}
			}
		}
		
		// Remove self-loop
		ArrayList<Integer> v1_neighbors = new ArrayList<>();
		for(Integer v : lists.get(v1)){
			if(v.intValue() != v1.intValue())
				v1_neighbors.add(v);
		}

		lists.put(v1, v1_neighbors);
		
	}
	
	public Integer getRandomVertice(){
		int nextInd = (int)(Math.random() * lists.keySet().size());
		return (Integer)(lists.keySet().toArray()[nextInd]);
	}
	
	public Integer getRandomEdgeAt(Integer v){
		int nextInd = (int)(Math.random() * lists.get(v).size());
		return lists.get(v).get(nextInd);
	}
	
	public ArrayList<Integer> getRandomEdge(){
		ArrayList<Integer> randomItems = new ArrayList<Integer>();
		Integer randomNode = getRandomVertice();
		while(lists.get(randomNode).size() == 0){
			randomNode =  getRandomVertice();
		}
		randomItems.add(randomNode);
		randomItems.add(getRandomEdgeAt(randomNode));
		return randomItems;

	}
	
	public int getNumVertices(){
		return lists.size();
	}
	
	
	public int getEdgeCount(){
		int count = 0;
		for(Entry<Integer, ArrayList<Integer>> e : lists.entrySet()){
			count+=((ArrayList<Integer>) e.getValue()).size();
		}
		return count;
	}
	
	public int contraction(){
		while(getNumVertices() > 2){
			// Pick a random edge
			ArrayList<Integer> e = getRandomEdge();

			// Contract to one vertice
			mergeVertex(e.get(0), e.get(1));
		}

		// Return number of edges of remaining vertices 
		// getNumEdge / 2?
		return getEdgeCount()/2;
	}
}
