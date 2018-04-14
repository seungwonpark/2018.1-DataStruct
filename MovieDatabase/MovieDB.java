import java.util.Iterator;
import java.util.NoSuchElementException;

public class MovieDB{
	private MyLinkedList<Genre> l;

	// constructor
	public MovieDB(){
		l = new MyLinkedList<Genre>();
	}

	public void insert(MovieDBItem item){
		Genre itemGenre = new Genre(item.getGenre());
		boolean existsGenre = false;
		for(Genre genre : l){
			if(genre.equals(itemGenre)){
				genre.insert(item.getTitle());
				existsGenre = true;
				break;
			}
		}
		if(existsGenre == false){
			itemGenre.insert(item.getTitle());
			l.insert(itemGenre);
		}
	}

	public void delete(MovieDBItem item){
		Genre itemGenre = new Genre(item.getGenre());
		boolean existsGenre = false;
		for(Genre genre : l){
			if(genre.equals(itemGenre)){
				genre.delete(item.getTitle());
				existsGenre = true;

				// if all movies of this genre is deleted
				if(genre.getListSize() == 0){
					l.delete(genre);
				}
				break;
			}
		}
		// we will skip exception,
		// since we don't want to show errors when
		// user tries to delete MovieDBItem that doesn't exist
		// if(existsGenre == false){
		// 	// no such element
		// }
	}

	public MyLinkedList<MovieDBItem> search(String term){
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		for(Genre genre : l){
			MyLinkedList<MovieDBItem> temp = genre.search(term);
			for(MovieDBItem item : temp){
				results.insert(item);
			}
		}
		return results;
	}

	public MyLinkedList<MovieDBItem> items(){
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		for(Genre genre : l){
			MyLinkedList<MovieDBItem> temp = genre.getWholeList();
			for(MovieDBItem item : temp){
				results.insert(item);
			}
		}
		return results;
	}
}
class Genre implements Comparable<Genre>{
	private MyLinkedList<MovieDBItem> l;
	private String name;

	// constructors
	public Genre(){
		l = new MyLinkedList<MovieDBItem>();
	}

	public Genre(String name){
		l = new MyLinkedList<MovieDBItem>();
		this.name = name;
	}

	// methods to access instance varaibles
	public String getGenreName(){
		return name;
	}

	public MyLinkedList<MovieDBItem> getWholeList(){
		return l;
	}

	public int getListSize(){
		return l.size();
	}

	// insert/delete MovieDBItem to MyLinkedList<MovieDBItem>
	// with thie genre, and given title.
	public void insert(String title){
		l.insert(new MovieDBItem(this.name, title));
	}
	public void delete(String title){
		l.delete(new MovieDBItem(this.name, title));
	}

	// returns the list of items containing given term.
	public MyLinkedList<MovieDBItem> search(String term){
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		for(MovieDBItem item : l){
			if(item.getTitle().contains(term)){
				results.insert(item);
			}
		}
		return results;
	}

	@Override
	public int compareTo(Genre other){
		return this.name.compareTo(other.getGenreName());
	}
	@Override
	public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
	}
	@Override
	public boolean equals(Object obj){
		Genre now = (Genre) obj;
		return this.name.equals(now.name);
	}
}
