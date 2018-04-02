// exception
class ListIndexOutOfBoundsException extends Exception{
	private String command;
	public ListIndexOutOfBoundsException(String command){
		super(String.format("input command: %s", command));
		this.command = command;
	}
	public String getCommand(){
		return command;
	}
}
