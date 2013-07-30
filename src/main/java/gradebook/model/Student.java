package gradebook.model;

/**
 * This class models a student enrolled in a section of a class.  Many Student
 * objects must be used to represent the same student in multiple classes.  It
 * holds the student's name and ID number and the section of the class in
 * which the student is enrolled.  It plays the Leaf role in the Composite
 * pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class Student extends Component implements Comparable<Student> {
    private String firstName, lastName;
    private long studentID;
    private Section section;
    private String comparator;
    private int index;

    private static final int PASSING_GRADES = 4;
    private static final int GRADES = 5;

    public Student(String firstName, String lastName, long studentID,
            Section section, int index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.section = section;
        this.comparator = lastName + "!" + firstName + "!" + studentID;
        this.index = index;
        setFullQual(section.getFullQual() + "." + firstName + "-" + lastName);
    }

    /**
     * Overriding methods in Component - this is the base case!
     */
    @Override
    public double gpa() {
        return gpaValue(letterGrade());
    }
    @Override
    public char letterGrade() {
        return section.letterGrade(this);
    }
    @Override
    public double average() {
        return section.average(this);
    }
    @Override
    public int numberOfStudents() {
        return 1;
    }

    public static double gpaValue(char letterGradeValue) {
        char ascii = 'F';
        return PASSING_GRADES * (ascii - letterGradeValue) / GRADES;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Student && compareTo((Student) other) == 0) {
            result = true;
        }
        return result;
    }
    @Override
    public int compareTo(Student other) {
        return comparator.compareTo(other.comparator);
    }
    @Override
    public int hashCode() {
        return comparator.hashCode();
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    public long getID() {
        return studentID;
    }
    public int getIndex() {
        return index;
    }
}
