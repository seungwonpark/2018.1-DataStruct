import java.util.*;
/*
	Each station is represented as:
	stNo, stName, lineName
	(String), (String), (String)
	1222, 복정, K2
*/

public class Station{ // called by stNo. 
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