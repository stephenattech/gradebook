package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
    
    Section a4;
    GradingScheme mockScheme;
    Student amy, andrea;
    
    @Before
    public void setUp() throws Exception {
        mockScheme = GradingSchemeFactory.makeGradingScheme("MOCK",null,null);
        a4 = new Section("A4", mockScheme, null);
        amy = a4.addStudent("Amy", "Dean", 855000000);
        andrea = a4.addStudent("Andrea", "Kelley", 994000000);
    }

    @Test
    public void testLetterGrade() {
        assertEquals('B', amy.letterGrade());
        assertEquals('A', andrea.letterGrade());
    }

    @Test
    public void testGPA() {
        assertEquals(3, Math.round(amy.gpa()));
        assertEquals(4, Math.round(andrea.gpa()));
    }

    @Test
    public void testAverage() {
        assertEquals(86, Math.round(amy.average()));
        assertEquals(99, Math.round(andrea.average()));
    }

    @Test
    public void testNumberOfStudents() {
        assertEquals(2, a4.numberOfStudents());
        assertEquals(1, amy.numberOfStudents());
        assertEquals(1, andrea.numberOfStudents());
    }

    @Test
    public void testGPAValue() {
        assertEquals(4, Math.round(Student.gpaValue('A')));
        assertEquals(3, Math.round(Student.gpaValue('B')));
        assertEquals(2, Math.round(Student.gpaValue('C')));
        assertEquals(1, Math.round(Student.gpaValue('D')));
        assertEquals(0, Math.round(Student.gpaValue('F')));
    }

}
