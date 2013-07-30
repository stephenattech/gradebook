package gradebook.model;

/**
 * This class models a class in T-square, such as the Fall 2012 class of
 * CS 1301.  It holds information about the year and term (semester or summer)
 * of the class.  It, along with Course and Section, implements the Composite
 * role in the Composite pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class Class extends Component {
    private int year, term;

    public Class(int year, int term, String courseFullQual) {
        this.year = year;
        this.term = term;
        setFullQual(courseFullQual + "." + year + "-" + term);
    }

    public Section addSection(String sectionCode, GradingScheme scheme) {
        Section newSection = new Section(sectionCode, scheme, getFullQual());
        super.addChild(newSection);
        return newSection;
    }

    public int getYear() {
        return year;
    }
    public int getTerm() {
        return term;
    }
}
