package es.us.isa.puri;


/**
 * 
 * @author josemgarcia
 *
 * @param <T>
 */
public interface StrictPartialOrderComparator<T>{
	boolean areComparable(T o1, T o2);
	double compare(T o1, T o2);
	boolean equals(Object obj);
	int hashCode();
}
