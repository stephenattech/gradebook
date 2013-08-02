package gradebook.model;

/**
 * This class models a section of a class in T-square, such as section A4 of
 * the Fall 2012 class of CS 1301.  It holds information about the section
 * code and a reference to the grading scheme (which may differ by section if
 * taught by different professors).  It, along with Course and Class,
 * implements the Composite role in the Composite pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class Section extends Component {
    private String sectionCode;
    private GradingScheme gradingScheme;
    private int nextIndex;

    public Section(String sectionCode, String classFullQual) {
        this.sectionCode = sectionCode;
        this.nextIndex = 0;
        setFullQual(classFullQual + "." + sectionCode);
    }
    public Student addStudent(String firstName, String lastName,
            long studentID) {
        Student newStudent = new Student(firstName, lastName, studentID,
                this, nextIndex++);
        super.addChild(newStudent);
        return newStudent;
    }

    public double average(Student student) {
        return gradingScheme.average(student);
    }
    public char letterGrade(Student student) {
        return gradingScheme.letterGrade(student);
    }
    public GradingScheme setGradingScheme(String impl, String[] categoryNames,
            int...weights) {
        gradingScheme = GradingSchemeFactory.makeGradingScheme(impl,
                getFullQual(), categoryNames, weights);
        return gradingScheme;
    }

    public String getSectionCode() {
        return sectionCode;
    }
}
