package gradebook.model;

import java.util.HashMap;

/**
 * This class represents a category (such as Tests, Quizzes, etc.) that has
 * a particular weight (how much it is worth in a student's grade).
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */
public class GradebookCategory implements Storable {
    private String categoryName;
    private int weight;
    private HashMap<String, GradebookItem> items;
    private boolean dropLowest;
    private GradebookCategory replaceLowest;
    private String fullQual;
    private static final int MAX = 100;

    public GradebookCategory(String categoryName, int weight,
            String schemeFullQual) {
        this.categoryName = categoryName;
        this.weight = weight;
        items = new HashMap<String, GradebookItem>();
        dropLowest = false;
        replaceLowest = null;
        fullQual = schemeFullQual + categoryName;
    }
    public Double average(Student student) {
        double studentPoints = 0;
        int studentPoss = 0;
        for (GradebookItem item : items.values()) {
            Integer grade = item.getGrade(student);
            if (grade != null) {
                studentPoints += grade;
                studentPoss += item.getPossiblePoints();
            }
        }
        if (dropLowest) {
            int gradeOfLowest = 0;
            int possiblePointsOfLowest = 0;
            double lowestPercent = Double.MAX_VALUE;
            for (GradebookItem item : items.values()) {
                Double testPercent = item.getPercent(student);
                if (testPercent != null && testPercent < lowestPercent) {
                    gradeOfLowest = item.getGrade(student);
                    possiblePointsOfLowest = item.getPossiblePoints();
                    lowestPercent = testPercent;
                }
            }
            studentPoints -= gradeOfLowest;
            studentPoss -= possiblePointsOfLowest;
            if (replaceLowest != null) {
                Double replacePercent = replaceLowest.average(student) / MAX;
                if (replacePercent != null) {
                    double newGrade = replacePercent * possiblePointsOfLowest;
                    studentPoints += (int) Math.round(newGrade);
                    studentPoss += possiblePointsOfLowest;
                }
            }
        }
        if (studentPoss > 0) {
            return MAX * studentPoints / studentPoss;
        } else {
            return null;
        }
    }
    public int getWeight() {
        return weight;
    }
    public int totalPossiblePoints() {
        int sum = 0;
        for (GradebookItem item : items.values()) {
            sum += item.getPossiblePoints();
        }
        return sum;
    }
    public GradebookItem addItem(String name, int possiblePoints,
            int sectionSize) {
        GradebookItem newItem;
        newItem = new GradebookItem(name, possiblePoints, sectionSize,
                fullQual);
        items.put(name, newItem);
        return newItem;
    }
    public void setDropLowest() {
        dropLowest = true;
    }
    public void clearDropLowest() {
        dropLowest = false;
    }
    public void setReplaceLowest(GradebookCategory replaceLowest) {
        setDropLowest();
        this.replaceLowest = replaceLowest;
    }
    public void clearReplaceLowest() {
        setReplaceLowest(null);
    }
    public String getName() {
        return categoryName;
    }
    public void addStudent() {
        for (GradebookItem item : items.values()) {
            item.addStudent();
        }
    }
    public String getFullQual() {
        return fullQual;
    }
}
