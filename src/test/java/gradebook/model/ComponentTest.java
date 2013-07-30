package gradebook.model;

import static gradebook.model.PrerequisitesArrayImpl.NONE;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComponentTest extends Component {
    
    Course cs1301;
    Class fall2012, spring2013;
    Section a4, b2, b9, a;
    Student stephen, thomas, philip, john;
    
    @Before
    public void setUp() throws Exception {
        setupCourse();
        setupClasses();
        setupSections();
        setupStudents();
    }

    @Test
    public void testLetterGrade() {
        assertEquals('B', letterGrade());
        assertEquals('A', cs1301.letterGrade());
        assertEquals('A', spring2013.letterGrade());
        assertEquals('C', a.letterGrade());
    }

    @Test
    public void testGPA() {
        assertEquals(3, Math.round(gpa()));
        assertEquals(4, Math.round(fall2012.gpa()));
        assertEquals(4, Math.round(b2.gpa()));
        assertEquals(2, Math.round(a.gpa()));
    }

    @Test
    public void testAverage() {
        assertEquals(78, Math.round(average()));
        assertEquals(96, Math.round(a4.average()));
        assertEquals(92, Math.round(b9.average()));
        assertEquals(62, Math.round(a.average()));
    }

    @Test
    public void testNumberOfStudents() {
        assertEquals(10, numberOfStudents());
        assertEquals(3, cs1301.numberOfStudents());
        assertEquals(2, spring2013.numberOfStudents());
        assertEquals(5, a.numberOfStudents());
    }

    @Test
    public void testLetterGradeValue() {
        assertEquals('A', letterGradeValue(4.0));
        assertEquals('A', letterGradeValue(3.5));
        assertEquals('B', letterGradeValue(3.49));
        assertEquals('B', letterGradeValue(3.0));
        assertEquals('B', letterGradeValue(2.5));
        assertEquals('C', letterGradeValue(2.49));
        assertEquals('C', letterGradeValue(1.5));
        assertEquals('D', letterGradeValue(1.49));
        assertEquals('D', letterGradeValue(0.5));
        assertEquals('F', letterGradeValue(0.49));
        assertEquals('F', letterGradeValue(0.0));
    }
    
    private void setupCourse() {
        cs1301 = new Course("CS", 1301, "Intro Computing", NONE);
        addChild(cs1301);
    }
    private void setupClasses() {
        fall2012 = cs1301.addClass(2013, 1);
        spring2013 = new Class(2013, 2, null);
        addChild(spring2013);
    }
    private void setupSections() {
        GradingScheme mockScheme =
                GradingSchemeFactory.makeGradingScheme("MOCK", null, null);
        a4 = fall2012.addSection("A4", mockScheme);
        b2 = spring2013.addSection("B2", mockScheme);
        b9 = spring2013.addSection("B9", mockScheme);
        a = new Section("A", mockScheme, null);
        addChild(a);
    }
    private void setupStudents() {
        stephen = a4.addStudent("Stephen", "Gillen", 902879494);
        philip = a4.addStudent("Philip", "Trandel", 981234567);
        thomas = a4.addStudent("Thomas", "Rowe", 995885939);
        b2.addStudent("Aaron", "Smith", 900000000);
        b9.addStudent("Taylor", "Jones", 920000000);
        a.addStudent("Alice", "Anderson", 770000000);
        a.addStudent("Bob", "Birdseye", 300000000);
        a.addStudent("Catalina", "Home", 910000000);
        a.addStudent("Daniel", "Lyons", 990000000);
        john = a.addStudent("John", "Cheese", 130000000);
    }

}
