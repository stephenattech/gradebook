package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {

    private Course cs1301;

    @Before
    public void setUp() throws Exception {
        cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
    }

    @Test
    public void testAddClass() {
        cs1301.addClass(2013, 1);
        cs1301.addClass(2013, 2);
        assertEquals(2, cs1301.numberOfChildren());
    }
}
