package week4;

import java.util.ArrayList;

public class NodeEntry {
	private Node vertice;
	private ArrayList<Node> neighbors;
	
	public NodeEntry(Node n){
		vertice = n;
		neighbors = new ArrayList<Node>();
	}
	
	public NodeEntry(Node n, ArrayList<Node> neighbors){
		this(n);
		this.neighbors = neighbors;
	}
	
	public Node getVertice() {
		return vertice;
	}
	public void setVertice(Node vertice) {
		this.vertice = vertice;
	}
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}
	
	public String toString(){
		return vertice.getValue() +": " + neighbors.toString();
	}
}
