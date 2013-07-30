package gradebook.model;

/**
 * This class models a course in T-square, such as CS 1301.  It holds
 * information about the subject, course number, and name of the course as
 * well as a reference to its prerequisites.  It, along with Class and
 * Section, plays the Composite role in the Composite pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class Course extends Component {

    private String subject;
    private int courseNumber;
    private String name;
    private Prerequisites prerequisites;

    public Course(String subject, int courseNumber, String name,
            Prerequisites prerequisites) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.name = name;
        this.prerequisites = prerequisites;
        setFullQual(subject + "-" + courseNumber);
    }

    public Class addClass(int year, int term) {
        Class newClass = new Class(year, term, getFullQual());
        super.addChild(newClass);
        return newClass;
    }

    public String getSubject() {
        return subject;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getName() {
        return name;
    }

    public Prerequisites getPrerequisites() {
        return prerequisites;
    }

}
