public class Edge{
	Station next;
	long dist;
	Edge(Station next, long dist){
		this.next = next;
		this.dist = dist;
	}

	@Override
	public String toString(){
		return " --(" + Long.toString(dist) + ")--> " + next;
	}
}