package gradebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradebookCategoryTest {
    
    GradebookCategory hwk, lab, test;
    GradebookItem hwk1, hwk2, hwk3, lab1, lab2, test1, test2;
    Student alice, billy, catalina;
    
    @Before
    public void setUp() throws Exception {
        Section zero = new Section(null,null,null);
        hwk = new GradebookCategory("Homework", 3, "Homework");
        lab = new GradebookCategory("Lab", 3, "Lab");
        test = new GradebookCategory("Test", 4, "Test");
        alice = new Student("Alice", "Bob", 97, zero, 0);
        billy = new Student("Billy", "Bob", 99, zero, 1);
        hwk1 = hwk.addItem("HWK1", 40, 2);
        hwk2 = hwk.addItem("HWK2", 30, 2);
        hwk3 = hwk.addItem("HWK3", 10, 2);
        catalina = new Student("Catalina", "Home", 100, zero, 2);
        hwk.addStudent();
        lab1 = lab.addItem("lab1", 60, 3);
        lab2 = lab.addItem("lab2", 40, 3);
        lab.setDropLowest();
        test1 = test.addItem("test1", 150, 3);
        test2 = test.addItem("test2", 50, 3);
        test.setReplaceLowest(hwk);
        hwk1.setGrade(alice, 33);
        hwk2.setGrade(alice, 30);
        lab1.setGrade(alice, 60);
        lab2.setGrade(alice, 30);
        test1.setGrade(alice, 60);
    }

    @Test
    public void testAverage() {
        assertEquals(90, Math.round(hwk.average(alice)));
        assertEquals(100, Math.round(lab.average(alice)));
        assertEquals(90, Math.round(test.average(alice)));
    }

    @Test
    public void testTotalPossiblePoints() {
        assertEquals(80, hwk.totalPossiblePoints());
        assertEquals(100, lab.totalPossiblePoints());
        assertEquals(200, test.totalPossiblePoints());
    }

    @Test
    public void testAddItem() {
        lab.addItem("lab3", 10, 3);
        assertEquals(110, lab.totalPossiblePoints());
    }

}
