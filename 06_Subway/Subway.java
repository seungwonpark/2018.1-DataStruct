import java.util.*;
import java.io.*;

/*
	Each station is represented as:
	stNo, stName, lineName
	(String), (String), (String)
	1222, 복정, K2
*/

class Station{ // called by stNo. 
	String stNo;
	String stName;
	String lineName;
	ArrayList<Edge> road;
	Station(String stNo, String stName, String lineName){
		this.stNo = stNo;
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

	@Override
	public String toString(){
		return "(" + stName + ", " + lineName + ")";
	}
}

class Edge{
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

class Status implements Comparable<Status>{
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

public class Subway{
	private static HashMap<String, Station> db
		= new HashMap<String, Station>(); // {stNo, Station}
	private static HashMap<String, ArrayList<Station>> stNameList
		= new HashMap<String, ArrayList<Station>>(); // {stName, {Station, Station, ... }}, ...
	public static void main(String args[]) throws IOException{
		BufferedReader data
			= new BufferedReader(new FileReader(args[0]));
		BufferedReader stdin
			= new BufferedReader(new InputStreamReader(System.in));
		try{
			// read and save station info.
			String dataline;
			while(!(dataline = data.readLine()).equals("")){ // until blank line
				String[] query = dataline.split(" ");
				String stNo = query[0]; // station number (1222)
				String stName = query[1]; // station name (복정)
				String lineName = query[2]; // station line name (K2)
				db.put(stNo, new Station(stNo, stName, lineName));
				// assumption: pair (stName, lineName) is unique.

				ArrayList<Station> temp = stNameList.get(stName);
				if(temp == null){
					temp = new ArrayList<Station>();
					temp.add(new Station(stNo, stName, lineName));
					stNameList.put(stName, temp);
				}
				else{
					temp.add(new Station(stNo, stName, lineName));
				}
			}			
		} catch (IOException e){
			throw new IOException("Exception occured while reading file(info. of stations)");
		}
		try{
			// read and establish connection between stations.
			String dataline; // 133 132 20000000
			while((dataline = data.readLine()) != null){ // until EOF
				String[] query = dataline.split(" ");
				String from = query[0]; // 133
				String to = query[1]; // 132
				long dist = Long.parseLong(query[2]); // 20000000
				// System.out.println(db.get(from));
				// System.out.println(db.get(to).toString());
				
				// Edge temp = new Edge(db.get(to), dist);
				// System.out.println("asdf\t" + db.get(from).toString() + temp.toString());
				// db.get(from).road.add(temp);
				db.get(from).road.add(new Edge(db.get(to), dist));
			}
			// connect all stations with same name, distance = 5
			for(ArrayList<Station> samename : stNameList.values()){
				if(samename.size() == 0) throw new IOException("samename size = 0");
				if(samename.size() == 1) continue;
				for(int i=0; i<samename.size(); i++){
					for(int j=i+1; j<samename.size(); j++){
						Station lhs = samename.get(i);
						Station rhs = samename.get(j);
						final long INTERCHANGE = 5;
						db.get(lhs.stNo).road.add(new Edge(rhs, INTERCHANGE));
						db.get(rhs.stNo).road.add(new Edge(lhs, INTERCHANGE));
					}
				}
			}
		} catch (IOException e){
			throw new IOException("Exception occured while reading file(info. of edges)");
		}
		for(Station x : db.values()){
			System.out.println(x.toString() + "\t\t" +  x.road.size());
			for(Edge adj : x.road){
				System.out.println(adj.toString());
			}
		}

		try{
			while(true){
				String dataline = stdin.readLine();
				if(dataline.equals("QUIT")) break;
				String[] query = dataline.split(" ");
				String from = query[0];
				String to = query[1];
				if(query.length == 3){
					find_leasttransfer(from, to);
				}
				else{
					find_path(from, to);
				}
			}
		}
		catch (IOException e){
			throw new IOException("Exception occured while reading stdin");
		}
	}
	static void find_path(String from, String to){
		PriorityQueue<Status> q = new PriorityQueue<Status>();
		HashSet<Station> visited = new HashSet<Station>();
		HashMap<Station, Long> answer = new HashMap<Station, Long>();
		for(Station start : stNameList.get(from)){
			q.add(new Status(start, 0, new ArrayList<Station>(){{add(start);}}));
		}
		while(q.size() != 0){
			Status temp = q.poll();
			if(visited.contains(temp)) continue;
			answer.put(temp.now, temp.ans);
			visited.add(temp.now);
			System.out.println(temp.now.toString());
			for(Edge adj : temp.now.road){
				System.out.println(adj.toString());
				if(!visited.contains(adj.next)){
					temp.history.add(adj.next);
					q.add(new Status(adj.next, temp.ans + adj.dist, temp.history));
				}
			}
		}
		System.out.println(answer.get(stNameList.get(to)));
	}
	static void find_leasttransfer(String from, String to){
		System.out.println("To be implemented");
	}
}