import java.util.Iterator;

/**
 * Creates basic functionality for a Linked List
 * This is the code we worked on in class, modified by Cameron Cischke
 * @author Cameron Cischke
 * @param <E> the type of object to be stored in the list
 * class: cs1131
 * lab: L01
 */
public class SinglyLinkedList<E> implements List<E>, Iterable<E> {
   private class Node {
      private E value;
      private Node nextNode = null;
      private String name = null;
   }

   private Node start = null;
   private int size = 0;

   /**
    * Adds an element to the end of the list
    * @param value the value to be inserted
    * @param name the name to be associated with the value
    */
   public void add( E value, String name ) {
      add( size, value, name );
   }

   /**
    * Adds an element to the specified index in the list
    * @param index the index to insert the element at
    * @param value the value to insert
    * @param name the name to be associated with the value
    */
   public void add( int index, E value, String name ) {
      if ( index < 0 || index > size ) {
         throw new RuntimeException( "Index Out of Bounds " + index );
      }
      Node newNode = new Node( );
      newNode.name = name;
      newNode.value = value;
      if ( index == 0 ) {
         newNode.nextNode = start;
         start = newNode;
      } else if (index == size) {
         Node current = findNode( index - 1 );
         current.nextNode = newNode;
      } else {
         Node current = findNode( index - 1 );
         newNode.nextNode = current.nextNode;
         current.nextNode = newNode;
         
      }
      size++;
   }

   /**
    * Finds the node at a specified index
    * @param index the index to search for
    * @return node at the index
    */
   private Node findNode( int index ) {
      Node currentNode = start;
      for( int i = 0; i < index; i++ ) {
         currentNode = currentNode.nextNode;
      }
      return currentNode;
   }

   /**
    * Gets the value of the node at the specified index
    * @param index the index to search for
    * @return the value of the node at that index
    */
   public E get( int index ) {
      if ( index < 0 || index >= size ) {
         throw new RuntimeException( "Index Out of Bounds " + index );
      }
      return findNode( index ).value;
   }

   /**
    * Removes the node at a specified index
    * @param index the index at which to remove the element
    */
   public void remove( int index ) {
      if ( index < 0 || index >= size ) {
         throw new RuntimeException( "Index Out of Bounds " + index );
      }
      if ( index == 0 ) {
         Node targetNode = start;
         start = start.nextNode;
         targetNode.nextNode = null;
      } else {
         Node prevNode = findNode( index - 1 );
         Node targetNode = prevNode.nextNode;
         Node afterNode = targetNode.nextNode;
         prevNode.nextNode = afterNode;
         targetNode.nextNode = null;
      }
      size--;
   }

   /**
    * Gets the name of the node at the specified index
    * @param index the index to get the name at
    * @return the name associated with the node at the index given
    */
   public String getName( int index ) {
      if ( index < 0 || index >= size ) {
         throw new RuntimeException( "Index Out of Bounds " + index );
      }
      return findNode( index ).name;
   }

   /**
    * Checks to see if the list is empty
    * @return false if it is not empty, true if it is
    */
   public boolean isEmpty( ) {
      return size == 0;
   }

   /**
    * Gets the size of the list
    * @return the size of the list
    */
   public int size( ) {
      return size;
   }

   /**
    * Creates an iterator for the list
    * @return null
    */
   public Iterator<E> iterator() {

      return null;
   }

}