/**
 * The <code>Folder</code> class creates an ArrayList of Emails
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Folder implements Serializable {
    ArrayList<Email> emails;
    String name;
    int currentSortingMethod;
    // 1 sortSubjectA, -1 sortSubjectD, 2 sortDateA, -2 sortDateD

    /**
     * Creates a folder object
     */
    public Folder(){
        emails= new ArrayList<Email>();
        currentSortingMethod=0;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * Adds an Email to the current folder
     * @param email
     *
     * The email to be added
     */
    public void addEmail(Email email){
        try{
            switch(currentSortingMethod){
                case -2:
                    emails.add(email);
                    sortByDateDescending();
                    break;
                case -1:
                    emails.add(email);
                    sortBySubjectDescending();
                    break;
                case 1:
                    emails.add(email);
                    sortBySubjectAscending();
                    break;
                case 2:
                    emails.add(email);
                    sortByDateAscending();
                    break;
                default:
                    emails.add(email);
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    /**
     * Removes an email at an index
     * @param index
     * Integer index
     * @return
     * The email removed
     */
    public Email removeEmail (int index){
        try{
            Email r= emails.remove(index);
            return r;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Out of Bounds");
        }
    }

    /**
     * Flips the list
     */
    public void reverseList(){
        ArrayList<Email> temp= new ArrayList<Email>();
        for(int i=emails.size()-1; i>=0; i--){
            temp.add(emails.get(i));
        }
        emails=temp;
    }

    /**
     * Sorts by the subject ascending
     * @throws ListEmpty
     * If Folder is empty
     */
    public void sortBySubjectAscending() throws ListEmpty {
        if (emails.size()!=0){
            Collections.sort(emails, new NameComparator());
            currentSortingMethod=1;
        }
        else{
            throw new ListEmpty("List is empty");
        }
    }

    /**
     * Sorts by the subject descending
     * @throws ListEmpty
     * If folder is empty
     */
    public void sortBySubjectDescending() throws ListEmpty {
        if (emails.size()!=0){
            Collections.sort(emails,new NameComparator());
            reverseList();
            currentSortingMethod=-1;
        }
        else{
            throw new ListEmpty("List is empty");
        }
    }

    /**
     * Sorts by the date ascending
     * @throws ListEmpty
     * If folder is empty
     */
    public void sortByDateAscending() throws ListEmpty {
        if (emails.size()!=0){
            Collections.sort(emails, new DateComparator());
            reverseList();
            currentSortingMethod=2;
        }
        else{
            throw new ListEmpty("List is empty");
        }
    }

    /**
     * Sets the name of the folder
     * @param n
     * The name
     */
    public void setName(String n){
        name=n;
    }

    /**
     * Sorts by the date descending
     * @throws ListEmpty
     * If Folder is empty
     */
    public void sortByDateDescending() throws ListEmpty {
        if (emails.size()!=0){
            Collections.sort(emails, new DateComparator());
            reverseList();
            currentSortingMethod=-2;
        }
        else{
            throw new ListEmpty("List is empty");
        }
    }

    /**
     * Prints the folder neatly to string
     */
    public void printtoString(){
        System.out.println("Index |        Time       | Subject");
        System.out.println("-------------------------------------------");
        for (int i=0; i<emails.size(); i++){
            System.out.printf("%2d|  %8s| %8s",i+1, emails.get(i).getTimestamp().getTime(), emails.get(i).getSubject());
            System.out.println("");
        }
    }


}
