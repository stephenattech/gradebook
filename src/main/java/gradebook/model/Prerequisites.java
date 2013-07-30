package gradebook.model;

/**
 * This interface provides a way of storing the prerequisites for a course
 * in a wrapper object without allowing students to change them.  It plays the
 * Target role in the Adapter pattern.
 *
 * @author Stephen Eric Gillen
 * @version 1.0 7/30/2013
 */

public interface Prerequisites extends Storable {
    int size();
    Course get(int i);
    void setCourse(Course course);
}
