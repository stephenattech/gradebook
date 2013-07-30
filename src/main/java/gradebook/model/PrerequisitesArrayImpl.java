package gradebook.model;

import java.util.Arrays;
import java.util.Collection;

/**
 * This class contains an array to hold the prerequisites for a course.  It
 * plays the Adapter role in the Adapter pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public class PrerequisitesArrayImpl implements Prerequisites {

    private String fullQual;

    public static final PrerequisitesArrayImpl NONE
            = new PrerequisitesArrayImpl(new Course[0]);

    private Course[] prerequisites;

    public PrerequisitesArrayImpl(Course...courses) {
        prerequisites = Arrays.copyOf(courses, courses.length);
    }
    public PrerequisitesArrayImpl(Collection<Course> courses) {
        prerequisites = courses.toArray(new Course[courses.size()]);
    }

    @Override
    public int size() {
        return prerequisites.length;
    }

    @Override
    public Course get(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("No negative indices!");
        }
        if (i >= size()) {
            String msg = "Input must be less than number of prerequisites, ";
            msg += "which is " + size();
            throw new IllegalArgumentException(msg);
        }
        return prerequisites[i];
    }

    public void setCourse(Course course) {
        fullQual = course.getName() + ".";
    }
    public String getFullQual() {
        return fullQual;
    }
}
