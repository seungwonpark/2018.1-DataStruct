import java.util.*;

public class Status implements Comparable<Status>{
	Station now;
	long ans;
	ArrayList<Path> history;
	Status(Station now, long ans){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<Path>();
	}
	Status(Station now, long ans, ArrayList<Path> history){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<Path>(history);
	}
	public String showHistory(){
		StringBuilder output = new StringBuilder();
		for(Path passed : this.history){
			output.append(passed.toString() + " ");
		}
		return output.toString().trim();
	}

	@Override
	public int compareTo(Status o){
		return this.ans < o.ans ? -1 : 1;
	}
}