package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHashMapImplTest {

    Course cs1301;
    Section a4;
    Student alice;
    GradebookItem hwk1;
    Database db;

    @Before
    public void setUp() throws Exception {
        String[] categoryNames = {"Homework", "Lab", "Test", "Quiz"};
        cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
        Class fall2012 = cs1301.addClass(2013, 1);
        a4 = fall2012.addSection("A4", null);
        alice = new Student("Alice", "Bob", 97, a4, 0);
        GradingScheme scheme = GradingSchemeFactory.makeGradingScheme("Full",
                a4.getFullQual(), categoryNames, 3, 3, 4, 2);
        a4.setGradingScheme(scheme);
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
