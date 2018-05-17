import java.util.*;
import java.io.*;

class Station{
	String stNo;
	ArrayList<Edge> road;
	Station(String stNo){
		this.stNo = stNo;
		this.road = new ArrayList<Edge>();
	}
	Station(String stNo, ArrayList<Edge> road){
		this.stNo = stNo;
		this.road = new ArrayList<Edge>(road);
	}
}

class Edge{
	Station next;
	long dist;
	Edge(Station next, long dist){
		this.next = next;
		this.dist = dist;
	}
}

class Status{
	Station now;
	long ans;
	ArrayList<String> history;
	Status(Station now, long ans){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<String>();
	}
	Status(Station now, long ans, ArrayList<String> history){
		this.now = now;
		this.ans = ans;
		this.history = new ArrayList<String>(history);
	}
}

public class Subway{
	public static void main(String args[]) throws IOException{
		BufferedReader data = new BufferedReader(new FileReader(args[0]));
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, String> st_line = new HashMap<String, String>();

		try{
			String dataline;
			while((dataline = data.readLine()) != null){ // until EOF
				String[] line = dataline.split(" ");
				String stNo = line[0]; // station number (1222)
				String stName = line[1]; // station name (복정)
				String lineName = line[2]; // station line name (K2)
				st_line.put(stNo, lineName);
			}			
		} catch (IOException e){
			throw new IOException("Exception occured while reading file");
		}
	}
}