package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradebookItemTest {

    private Student alice, billy;
    private GradebookItem midterm, finalExam;
    private Section zero;

    @Before
    public void setUp() throws Exception {
        zero = new Section(null, null);
        alice = zero.addStudent("Alice", "Bob", 97);
        billy = zero.addStudent("Billy", "Bob", 99);
        midterm = new GradebookItem("Midterm", 200, 10, null);
        finalExam = new GradebookItem("Final Exam", 300, 10, null);
        midterm.setGrade(billy, 195);
        finalExam.setGrade(alice, 294);
    }

    @Test
    public void testSetGrade() {
        midterm.setGrade(alice, 199);
        finalExam.setGrade(billy, 297);
        assertEquals(199, (int) midterm.getGrade(alice));
        assertEquals(297, (int) finalExam.getGrade(billy));
    }

    @Test
    public void testClearGrade() {
        midterm.clearGrade(billy);
        assert (midterm.getGrade(billy) == null);
    }

    @Test
    public void testGetGrade() {
        assertEquals(195, (int) midterm.getGrade(billy));
        assertEquals(294, (int) finalExam.getGrade(alice));
        assert (midterm.getGrade(alice) == null);
        assert (finalExam.getGrade(billy) == null);
    }

    @Test
    public void testAddStudent() {
        Student catalina = zero.addStudent("Catalina", "Home", 100);
        assert (midterm.getGrade(catalina) == null);
    }

}
