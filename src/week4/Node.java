package week4;

public class Node implements Comparable<Node>{
	private int value;
	private Node leader;
	
	public Node(int v){
		value = v;
		leader = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeader() {
		return leader;
	}

	public void setLeader(Node leader) {
		this.leader = leader;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public int compareTo(Node o) {
		return this.getValue() - o.getValue();
	}

	
	public String toString(){
		return "Value : " + getValue()  
				+  (getLeader() == null ? "" : " Leader: " + getLeader().getValue());
	}
}
