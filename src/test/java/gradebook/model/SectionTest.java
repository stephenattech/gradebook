package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SectionTest {

    private Section a4, b4;
    private Student alice, barbara;

    @Before
    public void setUp() throws Exception {
        a4 = new Section("A4", null);
        a4.setGradingScheme("Mock", null);
        b4 = new Section("B4", null);
        b4.setGradingScheme("Mock", null);
        alice = a4.addStudent("Alice", "Bob", 950000000);
        barbara = b4.addStudent("Barbara", "Ann", 840000000);
    }

    @Test
    public void testAddStudent() {
        assertEquals(1, b4.numberOfChildren());
        assertEquals(0, alice.getIndex());
        assertEquals(0, barbara.getIndex());
        Student sean = a4.addStudent("Sean", "John", 1);
        Student billy = b4.addStudent("Billy", "Bob", 0);
        assertEquals(2, b4.numberOfChildren());
        assertEquals(1, sean.getIndex());
        assertEquals(1, billy.getIndex());
    }

    @Test
    public void testAverageStudent() {
        assertEquals(95, Math.round(a4.average(alice)));
        assertEquals(84, Math.round(b4.average(barbara)));
    }

    @Test
    public void testLetterGradeStudent() {
        assertEquals('A', a4.letterGrade(alice));
        assertEquals('B', b4.letterGrade(barbara));
    }

}
