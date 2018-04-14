// MovieDB.java
//
// defines:
// public class MovieDB
// class Genre implements Comparable<Genre>
//
// MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
// 유지하는 데이터베이스이다. 

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MovieDB {
    private MyLinkedList<Genre> l;

    // constructor
    public MovieDB() {
        l = new MyLinkedList<Genre>();
    }

    // methods to modify instance variables
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
    }

    // methods to access instance variables
    public MyLinkedList<MovieDBItem> search(String term) {
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(Genre genre : l){
            // we define 'search' method inside Genre class
            // to refrain from directly using instance variable
            MyLinkedList<MovieDBItem> temp = genre.search(term);
            for(MovieDBItem item : temp){
                results.insert(item);
            }
        }
        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(Genre genre : l){
            // getList() method returns instance variable of Genre class.
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

    // constructor
	public Genre(String name) {
		l = new MyLinkedList<MovieDBItem>();
        this.name = name;
	}

    // methods to access instance variables
    public MyLinkedList<MovieDBItem> getList(){
        return l;
    }
    public MyLinkedList<MovieDBItem> search(String term){
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
        for(MovieDBItem item : l){
            // if 'term' is substring of item.getTitle(),
            // then insert item to results
            if(item.getTitle().contains(term)){
                results.insert(item);
            }
        }
        return results;
    }

    // methods to modify instance variables
    // insert, delete: implemented in MyLinkedList.java
    public void insert(String title){
        l.insert(new MovieDBItem(name, title));
    }
    public void delete(String title){
        l.delete(new MovieDBItem(name, title));
    }

	// define required methods to implement Comparable
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
