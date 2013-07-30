package gradebook.model;

import java.util.ArrayList;

/**
 * This class represents an item in a gradebook (a particular test, quiz,
 * assignment, etc).
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */
public class GradebookItem implements Storable {
    private String name;
    private int possiblePoints;
    private ArrayList<Integer> grades;
    private String fullQual;

    public GradebookItem(String name, int possiblePoints, int sectionSize,
            String categoryFullQual) {
        this.name = name;
        this.possiblePoints = possiblePoints;
        this.grades = new ArrayList<Integer>();
        for (int i = 0; i < sectionSize; i++) {
            grades.add(i, null);
        }
        fullQual = categoryFullQual + "." + name;
    }
    public void setGrade(Student student, int grade) {
        grades.set(student.getIndex(), grade);
    }
    public void clearGrade(Student student) {
        grades.set(student.getIndex(), null);
    }
    public Integer getGrade(Student student) {
        return grades.get(student.getIndex());
    }
    public Double getPercent(Student student) {
        Integer test = getGrade(student);
        if (test == null) {
            return null;
        } else {
            double studentGrade = test;
            return studentGrade / possiblePoints;
        }
    }
    public String getName() {
        return name;
    }
    public int getPossiblePoints() {
        return possiblePoints;
    }
    public void addStudent() {
        grades.add(null);
    }
    public String getFullQual() {
        return fullQual;
    }
}
