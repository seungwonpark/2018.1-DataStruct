public class MyPair implements Comparable<MyPair>{
	public int first;
	public int second;
	public MyPair(int first, int second){
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(MyPair o){
		if(this.first > o.first) return 1;
		else if(this.first < o.first) return -1;
		else{
			if(this.second > o.second) return 1;
			else if(this.second < o.second) return -1;
			else return 0;
		}
	}

	@Override
	public String toString(){
		return "(" + Integer.toString(first) + ", " + Integer.toString(second) + ")";
	}
}