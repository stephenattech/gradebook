package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHashMapImplTest {

    private Course cs1301;
    private Section a4;
    private Student alice;
    private GradebookItem hwk1;
    private Database db;
    private GradingScheme scheme;

    @Before
    public void setUp() throws Exception {
        String[] categoryNames = {"Homework", "Lab", "Test", "Quiz"};
        cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
        Class fall2012 = cs1301.addClass(2013, 1);
        a4 = fall2012.addSection("A4");
        alice = new Student("Alice", "Bob", 97, a4, 0);
        int[] weights = {3, 3, 4, 2};
        scheme = a4.setGradingScheme("Full", categoryNames, weights);
        GradebookCategory hwk = scheme.getCategory("Homework");
        hwk1 = hwk.addItem("HWK1", 40, 2);
        db = new DatabaseHashMapImpl();
        db.store(cs1301);
        db.store(a4);
        db.store(alice);
        db.store(hwk1);
    }

    @Test
    public void testLoad() {
        assertEquals(cs1301, db.load(cs1301.getFullQual()));
        assertEquals(a4, db.load(a4.getFullQual()));
        assertEquals(alice, db.load(alice.getFullQual()));
        assertEquals(hwk1, db.load(hwk1.getFullQual()));
    }

}
