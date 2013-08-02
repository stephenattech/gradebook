package gradebook.model;

import java.util.HashMap;

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
    private HashMap<Integer, Integer> grades;
    private String fullQual;

    public GradebookItem(String name, int possiblePoints, int sectionSize,
            String categoryFullQual) {
        this.name = name;
        this.possiblePoints = possiblePoints;
        this.grades = new HashMap<Integer, Integer>();
        fullQual = categoryFullQual + "." + name;
    }
    public void setGrade(Student student, int grade) {
        grades.put(student.getIndex(), grade);
    }
    public void clearGrade(Student student) {
        grades.put(student.getIndex(), null);
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
    public String getFullQual() {
        return fullQual;
    }
}
