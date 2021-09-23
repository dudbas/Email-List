/**
 * The <code>DateComparator</code> comparator is used to determine order
 *
 * @author
 * James Lam
 * 114439394
 * james.lam.2@stonybrook.edu
 * CSE214 Rec1
 * Homework #5
 *
 *
 */

import java.util.Comparator;

public class DateComparator implements Comparator {
    @Override
    /**
     * Compares two objects
     *
     * @param o1
     * First Name
     * @param o2
     * Second Name
     * @return
     * Returns which object is higher in alphabetical order
     */
    public int compare(Object o1, Object o2) {
        Email e1= (Email)o1;
        Email e2= (Email)o2;
        return e1.getTimestamp().compareTo(e2.getTimestamp());
    }
}
