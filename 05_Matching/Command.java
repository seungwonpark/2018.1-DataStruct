public interface Command{
	void apply(MyHashTable<LinkedList<Pair<Integer, Integer>>> db) throws Exception;
}

class FileCmd implements Command{
	private String arg;
	public FileCmd(String arg){
		this.arg = arg;
	}
	@Override
	public void apply(MyHashTable<LinkedList<Pair<Integer, Integer>>> db) throws Exception{
		FileReader fr = new FileReader(arg);
		BufferedReader br = new BufferedReader(fr);
		String dataline;
		for(int i=1;; i++){
			dataline = br.readLine();
			if(dataline == null) break;
			// asdfasdf
		}
	}
}

class SlotCmd implements Command{
	private int arg;
	public SlotCmd(String arg){
		this.arg = Integer.parseInt(arg);
	}

	@Override
	public void apply(MyHashTable<LinkedList<Pair<Integer, Integer>>> db) throws Exception{
		// asdfasdf
	}
}

class PhraseCmd implements Command{
	// private 
	@Override
	public void apply(MyHashTable<LinkedList<Pair<Integer, Integer>>> db) throws Exception{
		// asdfasdf
	}
}
