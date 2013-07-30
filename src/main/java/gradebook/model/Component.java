package gradebook.model;

import java.util.Collection;
import java.util.ArrayList;

/**
 * This abstract class is the superclass of Course, Class, Section, and
 * Student.  It allows all objects of those types to report grades uniformly.
 * It plays the Component role in the Composite pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public abstract class Component implements Storable {

    private Collection<Component> children;
    private String fullQual;
    private static final int TOPGPA = 4;
    private static final int PASSING_GRADES = 4;
    private static final int GRADES = 5;
    public Component() {
        children = emptyCollection();
    }

    public char letterGrade() {
        return letterGradeValue(gpa());
    }
    public double gpa() {
        double sum = 0;
        for (Component child : children) {
            sum += child.gpa() * child.numberOfStudents();
        }
        return sum / numberOfStudents();
    }
    public double average() {
        double sum = 0;
        for (Component child : children) {
            sum += child.average() * child.numberOfStudents();
        }
        return sum / numberOfStudents();
    }
    public int numberOfStudents() {
        int sum = 0;
        for (Component child : children) {
            sum += child.numberOfStudents();
        }
        return sum;
    }
    public static char letterGradeValue(double gpaValue) {
        char ascii = 'A';
        long ltr = GRADES * (TOPGPA - Math.round(gpaValue)) / PASSING_GRADES;
        return (char) (ascii + ltr);
    }

    public static Collection<Component> emptyCollection() {
        return new ArrayList<Component>();
    }
    public String getFullQual() {
        return fullQual;
    }

    protected void setFullQual(String fullQual) {
        this.fullQual = fullQual;
    }
    protected void addChild(Component child) {
        children.add(child);
    }
    protected int numberOfChildren() {
        return children.size();
    }
}
