package gradebook.model;

/**
 * This interface defines the methods for storing Storable items in a database
 * and loading them back into memory.  Type casts will be necessary when
 * loading.  It plays the Target role in the Adapter pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */
public interface Database {
    void store(Storable item);
    Storable load(String fullQual);
}
