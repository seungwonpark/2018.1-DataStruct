// Node.java
//
// defines:
// public class Node<T>

import java.util.NoSuchElementException;

public class Node<T> {
    private T item;
    private Node<T> next;

    // constuctors
    public Node(T obj) {
        this.item = obj;
        this.next = null;
    }
    
    public Node(T obj, Node<T> next) {
    	this.item = obj;
    	this.next = next;
    }
    
    // methods to access instance variables
    public final T getItem() {
    	return this.item;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public boolean hasNext() {
        return this.next != null;
    }
    
    // methods to modify instance variables
    public final void setItem(T item) {
    	this.item = item;
    }
    
    public final void setNext(Node<T> next) {
    	this.next = next;
    }
    
    public final void insertNext(T obj) {
        Node<T> newNode = new Node<T>(obj, this.next);
        this.next = newNode;
    }
    
    public final void removeNext() {
        if(this.next == null){
            throw new NoSuchElementException();
        }
        this.next = this.next.next;
    }
}