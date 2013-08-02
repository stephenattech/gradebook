package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrerequisitesArrayImplTest {

    private Course cs1301, cs1331;
    private Prerequisites prereqs1331, prereqs2340;

    @Before
    public void setUp() throws Exception {
        cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
        ArrayList<Course> cs1301ArrayList = new ArrayList<Course>();
        cs1301ArrayList.add(cs1301);
        prereqs1331 = new PrerequisitesArrayImpl(cs1301ArrayList);
        cs1331 = new Course("CS", 1331, "Intro Object Oriented", prereqs1331);
        prereqs2340 = new PrerequisitesArrayImpl(cs1301, cs1331);
    }

    @Test
    public void testSize() {
        assertEquals(1, prereqs1331.size());
        assertEquals(2, prereqs2340.size());
    }

    @Test
    public void testGet() {
        assertEquals(cs1301, prereqs1331.get(0));
        assertEquals(cs1301, prereqs2340.get(0));
        assertEquals(cs1331, prereqs2340.get(1));
    }

}
