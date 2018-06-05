import java.util.*;
import java.io.*;

public class Subway{
	static final long INTERCHANGE = 5;
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
						db.get(lhs.stNo).road.add(new Edge(rhs, INTERCHANGE));
						db.get(rhs.stNo).road.add(new Edge(lhs, INTERCHANGE));
					}
				}
			}
		} catch (IOException e){
			throw new IOException("Exception occured while reading file(info. of edges)");
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
		for(Station start : stNameList.get(from)){
			q.add(new Status(start, 0, new ArrayList<Path>(){{add(new Path(start.stName, false));}}));
		}
		// perform dijkstra algorithm
		while(q.size() != 0){
			Status temp = q.poll();
			System.out.println("\t" + temp.showHistory() + "\t" + temp.ans);
			Station here = db.get(temp.now.stNo);
			if(visited.contains(here)) continue;
			if(to.equals(here.stName)){
				// print things when relaxation to destination is finished
				// and then break.
				System.out.println(temp.showHistory());
				System.out.println(temp.ans);
				break;
			}
			visited.add(here);
			for(Edge adj : here.road){
				if(!visited.contains(adj.next)){
					String nextName = adj.next.stName;
					if(temp.history.size() > 0 &&
						nextName.equals(temp.history.get(temp.history.size()-1).stName)){
						temp.history.remove(temp.history.size() - 1);
						temp.history.add(new Path(nextName, true));
					}
					else{
						temp.history.add(new Path(nextName, false));
					}
					q.add(new Status(adj.next, temp.ans + adj.dist, temp.history));
					// restore temp.history after insertion to p.q.
					temp.history.remove(temp.history.size() - 1);
				}
			}
		}
	}
	static void find_leasttransfer(String from, String to){
		System.out.println("To be implemented");
	}
}