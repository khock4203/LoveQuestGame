/**
 * List
 * Interface defining basic functionality for a list
 * This is the code we worked on in class
 * @author Leo Ureel, implemented by Cameron Cischke
 * @param <E> the type of object to be stored in the list
 */
public interface List<E> {
   public void add( E value, String name );
   public void add( int index, E value, String name);
   public E get( int index );
   public void remove( int index );
   public boolean isEmpty( );
   public int size( );
}