package gradebook.model;

/**
 * This interface provides a way of calculating grades for a particular section
 * of a course.  It plays the Strategy role in the Strategy pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public interface GradingScheme extends Storable {
    void setFullQual(String fullQual);

    double average(Student student);
    char letterGrade(Student student);
    double categoryAverage(Student student, String categoryName);
    char categoryLetterGrade(Student student, String categoryName);

    GradebookCategory getCategory(String categoryName);
    GradebookCategory addCategory(String categoryName, int weight);
    void removeCategory(String categoryName);

    void dropLowest(String dropInCategoryName);
    void clearDropLowest(String clearDropInCategoryName);
    void replaceLowest(String replaceInCategoryName,
            String replaceWithCategoryName);
    void clearReplaceLowest(String clearReplaceInCategoryName);
    void setCutoffs(int aCutoff, int bCutoff, int cCutoff, int dCutoff);
}
