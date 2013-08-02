package gradebook.model;

/**
 * This class exists solely for unit testing purposes.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class GradingSchemeMockImpl implements GradingScheme {

    private static final double PERCENT_CONVERTER = 10000000.0;
    private static final int D_CUTOFF = 60;
    private static final int ASCII_CONVERTER = 74;
    private static final int SCALE_WIDTH = 10;

    @Override
    public double average(Student student) {
        return student.getID() / PERCENT_CONVERTER;
    }

    @Override
    public char letterGrade(Student student) {
        double grade = average(student);
        if (grade >= D_CUTOFF) {
            return (char) (ASCII_CONVERTER - Math.round(grade) / SCALE_WIDTH);
        } else {
            return 'F';
        }
    }

    @Override
    public double categoryAverage(Student student, String categoryName) {
        return 0;
    }

    @Override
    public char categoryLetterGrade(Student student, String categoryName) {
        return 0;
    }

    @Override
    public GradebookCategory getCategory(String categoryName) {
        return null;
    }

    @Override
    public void dropLowest(String dropInCategoryName) {

    }

    @Override
    public void clearDropLowest(String clearDropInCategoryName) {

    }

    @Override
    public void replaceLowest(String replaceInCategoryName,
            String replaceWithCategoryName) {

    }

    @Override
    public void clearReplaceLowest(String clearReplaceInCategoryName) {

    }

    @Override
    public GradebookCategory addCategory(String categoryName, int weight) {
        return null;
    }

    @Override
    public void removeCategory(String categoryName) {

    }

    @Override
    public void setCutoffs(int aCutoff, int bCutoff,
            int cCutoff, int dCutoff) {

    }

    @Override
    public void setFullQual(String fullQual) {

    }

    @Override
    public String getFullQual() {
        return null;
    }

}
