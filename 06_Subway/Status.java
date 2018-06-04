import java.util.*;

public class Status implements Comparable<Status>{
	Station now;
	long ans;
	ArrayList<Station> history;
	Status(Station now, long ans){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<Station>();
	}
	Status(Station now, long ans, ArrayList<Station> history){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<Station>(history);
	}
	@Override
	public int compareTo(Status o){
		return this.ans < o.ans ? -1 : 1;
	}
}