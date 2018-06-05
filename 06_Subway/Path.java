public class Path{
	String stName;
	boolean transfer = false;
	public Path(String stName, boolean transfer){
		this.stName = stName;
		this.transfer = transfer;
	}
	@Override
	public String toString(){
		return transfer ?
			'[' + stName + ']' : stName;
	}
}