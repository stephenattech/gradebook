package gradebook.model;

import java.util.HashMap;

/**
 * This class implements the Database functionality in a simplified way,
 * placing the Storable items into a HashMap using the fully qualified names
 * as keys.  It plays the Adapter role in the Adapter pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */
public class DatabaseHashMapImpl implements Database {

    private HashMap<String, Storable> items;

    public DatabaseHashMapImpl() {
        items = new HashMap<String, Storable>();
    }

    @Override
    public void store(Storable item) {
        String key = item.getFullQual();
        items.put(key, item);
    }

    @Override
    public Storable load(String fullQual) {
        return items.get(fullQual);
    }

}
