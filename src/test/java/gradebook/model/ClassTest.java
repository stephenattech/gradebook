package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClassTest {

    private Class fall2012;

    @Before
    public void setUp() throws Exception {
        fall2012 = new Class(2013, 1, null);
    }

    @Test
    public void testAddSection() {
        fall2012.addSection("A4");
        fall2012.addSection("B4");
        assertEquals(2, fall2012.numberOfChildren());
    }

}
