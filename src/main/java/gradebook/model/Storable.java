package gradebook.model;

/**
 * The objects that make up the gradebook implement this interface so they
 * can be stored in a database using the fully qualified name of the object
 * (the names of all the objects back up to the course).
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public interface Storable {
    String getFullQual();
}
