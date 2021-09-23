/**
 * The <code>Email</code> class creates an Email object
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
import java.util.GregorianCalendar;

public class Email implements Serializable {
    String to;
    String cc;
    String bcc;
    String subject;
    String body;
    GregorianCalendar timestamp;

    /**
     * Constructor for email
     */
    public Email(){
        timestamp= new GregorianCalendar();
    }

    /**
     * Getters for the class
     */
    public String getTo(){
        return to;
    }
    public String getCc(){
        return cc;
    }
    public String getBcc(){
        return bcc;
    }
    public String getSubject(){
        return subject;
    }
    public String getBody(){
        return body;
    }
    public GregorianCalendar getTimestamp(){
        return timestamp;
    }

    /**
     * Setters
     */
    public void setTo(String n){
        to=n;
    }
    public void setCc(String n){
        cc=n;
    }
    public void setBcc(String n){
        bcc=n;
    }
    public void setSubject(String n){
        subject=n;
    }
    public void setBody(String n){
        body=n;
    }
    public void setTimestamp(GregorianCalendar n){
        timestamp=n;
    }

    /**
     * Prints out the email
     * PostCondition: An organized printout of the email
     */
    public void toStringprint(){
        System.out.println("To: " + to);
        System.out.println("CC: "+ cc);
        System.out.println("BCC: " + bcc);
        System.out.println("Subject: "+ subject);
        System.out.println(body);
    }
}
