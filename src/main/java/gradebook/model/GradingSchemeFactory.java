package gradebook.model;

/**
 * This class allows for the creation of many different types of
 * GradingSchemes.  It plays the Factory role in the Factory pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class GradingSchemeFactory {

    /**
     * This method actually creates the new grading scheme, based on the
     * desired categories and weights.  If using an appropriate implementation,
     * other aspects, such as dropping the lowest score, can be set later.
     *
     * @param impl the desired implementation
     * @param categoryNames an array containing the names of the categories
     * @param weights the category weights, in order corresponding to the names
     * @return the new grading scheme
     */

    public static GradingScheme makeGradingScheme(String impl,
            String sectionFullQual, String[] categoryNames, int...weights) {
        GradingScheme newScheme = null;
        if (impl.toLowerCase().equals("mock")) {
            newScheme = new GradingSchemeMockImpl();
        } else if (impl.toLowerCase().equals("full")) {
            if (categoryNames.length == weights.length) {
                newScheme = new GradingSchemeFullImpl(categoryNames, weights,
                        sectionFullQual);
            } else {
                throw new IllegalArgumentException("Category/weight mismatch");
            }
        } else {
            throw new IllegalArgumentException("Invalid implementation");
        }
        return newScheme;
    }
}
