import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
    private MyLinkedList<Genre> l;
    public MovieDB() {
        l = new MyLinkedList<Genre>();
    }

    public void insert(MovieDBItem item) {
        // Insert the given item to the MovieDB.
        Genre nowGenre = new Genre(item.getGenre());
        boolean isNewGenre = true;
        for(Genre genre : l){
            if(nowGenre.equals(genre)){ // genre already exists in l
                genre.insert(item.getTitle());
                isNewGenre = false;
                break;
            }
        }
        if(isNewGenre){
            nowGenre.insert(item.getTitle());
            l.insert(nowGenre);
        }

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public void delete(MovieDBItem item) {
        // Remove the given item from the MovieDB.
        Genre nowGenre = new Genre(item.getGenre());
        for(Genre genre : l){ // find genre which is same with nowGenre
            if(nowGenre.equals(genre)){
                genre.delete(item.getTitle());
                break;
            }
        }
    	
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public MyLinkedList<MovieDBItem> search(String term) {
    	System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(Genre genre : l){
            MyLinkedList<MovieDBItem> temp = genre.search(term);
            for(MovieDBItem item : temp){
                results.insert(item);
            }
        }
        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        System.err.printf("[trace] MovieDB: ITEMS\n");
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(Genre genre : l){
            MyLinkedList<MovieDBItem> temp = genre.getList();
            for(MovieDBItem item : temp){
                results.insert(item);
            }
        }
    	return results;
    }
}

class Genre implements Comparable<Genre> {
    private MyLinkedList<MovieDBItem> l;
    private String name;

	public Genre(String name) {
		l = new MyLinkedList<MovieDBItem>();
        this.name = name;
	}

    public MyLinkedList<MovieDBItem> getList(){
        return l;
    }
    public MyLinkedList<MovieDBItem> search(String term){
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(MovieDBItem item : l){
            if(item.getTitle().contains(term)){
                results.insert(item);
            }
        }
        return results;
    }

    public void insert(String title){
        l.insert(new MovieDBItem(name, title));
    }
    public void delete(String title){
        l.delete(new MovieDBItem(name, title));
    }

	
	@Override
	public int compareTo(Genre o) {
        return this.name.compareTo(name);
	}
	@Override
	public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
	}
	@Override
	public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        if(this == obj) return true;
        Genre now = (Genre) obj;
        if(this.name == null){
            return now.name == null;
        }
        else{
            return this.name.equals(now.name);
        }
	}
}

// class MovieList implements ListInterface<String> {	
// 	public MovieList() {
// 	}

// 	@Override
// 	public Iterator<String> iterator() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public boolean isEmpty() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public int size() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public void add(String item) {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public String first() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}

// 	@Override
// 	public void removeAll() {
// 		throw new UnsupportedOperationException("not implemented yet");
// 	}
// }