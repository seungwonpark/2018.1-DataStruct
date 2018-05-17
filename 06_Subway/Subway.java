import java.util.*;
import java.io.*;

/*
	Each station is represented as:
	stNo, stName, lineName
	(String), (String), (String)
	1222, 복정, K2
*/

class Station{ // called by stNo. 
	String stName;
	String lineName;
	ArrayList<Edge> road;
	Station(String stName, String lineName){
		this.stName = stName;
		this.lineName = lineName;
		this.road = new ArrayList<Edge>();
	}
	public boolean equalStName(Station o){
		return this.stName == o.stName;
	}
	public boolean equalLine(Station o){
		return this.lineName == o.lineName;
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
		HashMap<String, Station> db = new HashMap<String, Station>();
		try{
			// read and save station info.
			String dataline;
			while(!(dataline = data.readLine()).equals("")){ // until blank line
				String[] line = dataline.split(" ");
				String stNo = line[0]; // station number (1222)
				String stName = line[1]; // station name (복정)
				String lineName = line[2]; // station line name (K2)
				db.put(stNo, new Station(stName, lineName));
			}			
		} catch (IOException e){
			throw new IOException("Exception occured while reading file");
		}
		try{
			// read and establish connection between stations.
			String dataline;
			while((dataline = data.readLine()) != null){ // until EOF
				String[] line = dataline.split(" ");
				String from = line[0];
				String to = line[1];
				long dist = Long.parseLong(line[2]);
				db.get(from).road.add(new Edge(db.get(to), dist));
			}
			// TODO: 역 이름이 같은 stNo끼리 5분으로 잇기
		} catch (IOException e){
			throw new IOException("Exception occured while reading file");
		}
	}
}