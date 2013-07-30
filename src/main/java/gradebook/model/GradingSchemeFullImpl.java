package gradebook.model;

import java.util.HashMap;

/**
 * This class specifies an implementation for the grade calculation and
 * retrieval from the gradebook.  It plays the ConcreteStrategy role in the
 * Strategy pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */
public class GradingSchemeFullImpl implements GradingScheme {

    private HashMap<String, GradebookCategory> categories;
    private int[] cutoffs;
    private static final int A_DEFAULT = 90;
    private static final int B_DEFAULT = 80;
    private static final int C_DEFAULT = 70;
    private static final int D_DEFAULT = 60;
    private static final int TOP_GPA = 4;
    private String fullQual;

    public GradingSchemeFullImpl(String[] categoryNames, int[] weights,
            String sectionFullQual) {
        int i = 0;
        categories = new HashMap<String, GradebookCategory>();
        fullQual = sectionFullQual + ".";
        GradebookCategory newCategory;
        while (i < categoryNames.length && i < weights.length) {
            newCategory = new GradebookCategory(categoryNames[i], weights[i],
                    fullQual);
            categories.put(categoryNames[i], newCategory);
            i++;
        }
        cutoffs = new int[] {A_DEFAULT, B_DEFAULT, C_DEFAULT, D_DEFAULT};
    }

    @Override
    public void addStudent() {
        for (GradebookCategory category : categories.values()) {
            category.addStudent();
        }
    }

    @Override
    public double average(Student student) {
        double sum = 0;
        double weightSum = 0;
        for (GradebookCategory category : categories.values()) {
            Double categoryAvg = category.average(student);
            int categoryWeight = category.getWeight();
            if (categoryAvg != null) {
                sum += categoryAvg * categoryWeight;
                weightSum += category.getWeight();
            }
        }
        if (weightSum > 0) {
            return sum / weightSum;
        } else {
            return 0;
        }
    }

    @Override
    public char letterGrade(Student student) {
        return letterGrade(Math.round(average(student)));
    }

    @Override
    public double categoryAverage(Student student, String categoryName) {
        GradebookCategory category = categories.get(categoryName);
        return category.average(student);
    }

    @Override
    public char categoryLetterGrade(Student student, String categoryName) {
        GradebookCategory category = categories.get(categoryName);
        return letterGrade(Math.round(category.average(student)));
    }

    @Override
    public GradebookCategory getCategory(String categoryName) {
        return categories.get(categoryName);
    }

    @Override
    public GradebookCategory addCategory(String categoryName, int weight) {
        GradebookCategory newCategory;
        newCategory = new GradebookCategory(categoryName, weight, fullQual);
        categories.put(categoryName, newCategory);
        return newCategory;
    }
    @Override
    public void removeCategory(String categoryName) {
        categories.remove(categoryName);
    }

    @Override
    public void dropLowest(String dropInCategoryName) {
        GradebookCategory category = categories.get(dropInCategoryName);
        category.setDropLowest();
    }
    @Override
    public void clearDropLowest(String clearDropInCategoryName) {
        GradebookCategory category = categories.get(clearDropInCategoryName);
        category.clearDropLowest();
    }
    @Override
    public void replaceLowest(String replaceInCategoryName,
            String replaceWithCategoryName) {
        GradebookCategory category = categories.get(replaceInCategoryName);
        GradebookCategory replace = categories.get(replaceWithCategoryName);
        category.setReplaceLowest(replace);
    }
    @Override
    public void clearReplaceLowest(String clearReplaceInCategoryName) {
        GradebookCategory category =
                categories.get(clearReplaceInCategoryName);
        category.clearReplaceLowest();
    }
    @Override
    public void setCutoffs(int aCutoff, int bCutoff,
            int cCutoff, int dCutoff) {
        int[] newCutoffs = {aCutoff, bCutoff, cCutoff, dCutoff};
        cutoffs = newCutoffs;
    }

    private char letterGrade(long currentGrade) {
        char letterGrade = 'A';
        for (int i = 0; i < TOP_GPA && currentGrade < cutoffs[i]; i++) {
            letterGrade++;
        }
        if (letterGrade == 'E') {
            return 'F';
        } else {
            return letterGrade;
        }
    }

    @Override
    public void setFullQual(String fullQual) {
        this.fullQual = fullQual;
    }

    @Override
    public String getFullQual() {
        return fullQual;
    }
}
