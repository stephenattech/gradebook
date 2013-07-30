package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradebookItemTest {
    
    Student alice, billy;
    GradebookItem midterm, finalExam;
    Section zero;
    
    @Before
    public void setUp() throws Exception {
        zero = new Section(null,null,null);
        alice = new Student("Alice", "Bob", 97, zero, 2);
        billy = new Student("Billy", "Bob", 99, zero, 5);
        midterm = new GradebookItem("Midterm", 200, 10, null);
        finalExam = new GradebookItem("Final Exam", 300, 10, null);
        midterm.setGrade(billy, 195);
        finalExam.setGrade(alice, 294);
    }

    @Test
    public void testSetGrade() {
        midterm.setGrade(alice, 199);
        finalExam.setGrade(billy, 297);
        assertEquals(199, (int)midterm.getGrade(alice));
        assertEquals(297, (int)finalExam.getGrade(billy));
    }

    @Test
    public void testClearGrade() {
        midterm.clearGrade(billy);
        assert(midterm.getGrade(billy) == null);
    }

    @Test
    public void testGetGrade() {
        assertEquals(195, (int)midterm.getGrade(billy));
        assertEquals(294, (int)finalExam.getGrade(alice));
        assert(midterm.getGrade(alice) == null);
        assert(finalExam.getGrade(billy) == null);
    }

    @Test
    public void testAddStudent() {
        midterm.addStudent();
        Student catalina = new Student("Catalina", "Home", 100, zero, 10);
        assert(midterm.getGrade(catalina) == null);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testAddStudent2() {
        midterm.addStudent();
        Student catalina = new Student("Catalina", "Home", 100, zero, 10);
        finalExam.getGrade(catalina);
    }

}
