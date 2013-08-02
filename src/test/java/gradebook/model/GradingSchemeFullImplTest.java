package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradingSchemeFullImplTest {

    private Section a4;
    private GradingScheme scheme;
    private GradebookCategory hwk, lab, test;
    private GradebookItem hwk1, hwk2, hwk3, lab1, lab2, test1, test2;
    private Student alice, billy;

    @Before
    public void setUp() throws Exception {
        String[] categoryNames = {"Homework", "Lab", "Test", "Quiz"};
        Course cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
        Class fall2012 = cs1301.addClass(2013, 1);
        a4 = fall2012.addSection("A4");
        alice = a4.addStudent("Alice", "Bob", 97);
        billy = a4.addStudent("Billy", "Bob", 99);
        int[] weights = {3, 3, 4, 2};
        scheme = a4.setGradingScheme("Full", categoryNames, weights);
        scheme.setCutoffs(85, 75, 65, 55);
        hwk = scheme.getCategory("Homework");
        lab = scheme.getCategory("Lab");
        test = scheme.getCategory("Test");
        hwk1 = hwk.addItem("HWK1", 40, 2);
        hwk2 = hwk.addItem("HWK2", 30, 2);
        hwk3 = hwk.addItem("HWK3", 10, 2);
        a4.addStudent("Catalina", "Home", 100);
        lab1 = lab.addItem("lab1", 60, 3);
        lab2 = lab.addItem("lab2", 40, 3);
        lab.setDropLowest();
        test1 = test.addItem("test1", 150, 3);
        test2 = test.addItem("test2", 50, 3);
        test.setReplaceLowest(hwk);
        hwk1.setGrade(alice, 26);
        hwk2.setGrade(alice, 30);
        lab1.setGrade(alice, 60);
        lab2.setGrade(alice, 30);
        test1.setGrade(alice, 60);
    }

    @Test
    public void testAverage() {
        assertEquals(86, Math.round(scheme.average(alice)));
    }

    @Test
    public void testLetterGrade() {
        assertEquals('A', scheme.letterGrade(alice));
    }

    @Test
    public void testCategoryAverage() {
        assertEquals(80, Math.round(scheme.categoryAverage(alice, "Homework")));
        assertEquals(100, Math.round(scheme.categoryAverage(alice, "Lab")));
        assertEquals(80, Math.round(scheme.categoryAverage(alice, "Test")));
    }

    @Test
    public void testCategoryLetterGrade() {
        assertEquals('B', scheme.categoryLetterGrade(alice, "Homework"));
        assertEquals('A', scheme.categoryLetterGrade(alice, "Lab"));
        assertEquals('B', scheme.categoryLetterGrade(alice, "Test"));
    }

    @Test
    public void testGetFullQual() {
        assertEquals("CS-1301.2013-1.A4.Alice-Bob", alice.getFullQual());
        assertEquals("CS-1301.2013-1.A4.Billy-Bob", billy.getFullQual());
        assertEquals("CS-1301.2013-1.A4.Homework.HWK3", hwk3.getFullQual());
        assertEquals("CS-1301.2013-1.A4.Test.test2", test2.getFullQual());
    }

}
