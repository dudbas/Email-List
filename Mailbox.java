/**
 * The <code>Mailbox</code> class creates an mailbox with a collection of folders
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


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Mailbox implements Serializable {
    Folder inbox= new Folder();
    Folder trash=new Folder();
    ArrayList<Folder> folders= new ArrayList<Folder>();

    /**
     * Creates a mailbox
     */
    public Mailbox(){
        inbox.setName("Inbox");
        trash.setName("Trash");
        folders.add(inbox);
        folders.add(trash);
    }

    /**
     * Returns the arraylist of folders
     * @return
     * The current arraylist of folders
     *
     */
    public ArrayList<Folder> getFolders(){
        return folders;
    }

    /**
     * Adds a folder to the folders list
     * @param folder
     *
     * Folder to be added
     */
    public void addFolder(Folder folder){
        boolean flag=true;
        for(Folder i: folders){
            if(i.getName().equals(folder.getName())){
                flag=false;
            }
        }
        if(!flag){
            throw new IllegalArgumentException("NonExsisting Folder");
        }
        else{
            folders.add(folder);
        }

    }

    /**
     * Deletes the folder of the given name
     *
     * @param name
     * Name of the folder to be deleted
     *
     */
    public void deleteFolder(String name){
        boolean flag=false;
        for (int i=0; i<folders.size(); i++){
            if (folders.get(i).getName().equals(name)){
                folders.remove(i);
                flag=true;
                break;
            }
        }

        if(!flag){
            System.out.println("Folder was not found");
        }
    }

    /**
     * Composed of email
     */
    public void composeEmail(){
        Email compose= new Email();
        Scanner input= new Scanner(System.in);
        System.out.println("Enter recipient: ");
        compose.setTo(input.next());
        System.out.println("Enter CC recipients: ");
        compose.setCc(input.next());
        System.out.println("Enter BCC recipients: ");
        compose.setBcc(input.next());
        System.out.println("Enter Subject line: ");
        compose.setSubject(input.next());
        System.out.println("Enter Body: ");
        compose.setBody(input.next());

        System.out.println("Email added to Inbox");
        inbox.addEmail(compose);
    }

    /**
     * Moves email to trash
     * @param email
     * The curreent email
     */
    public void deleteEmail(Email email){
        trash.addEmail(email);
    }

    /**
     * Clears the trashcan
     */
    public void clearTrash(){
        trash.getEmails().clear();
    }

    /**
     * Moves email to a folder
     * @param email
     * The email to be moved
     * @param target
     * The target folder
     */
    public void moveEmail(Email email, Folder target){
        if (folders.contains(target)){
            target.addEmail(email);
        }
        else{
            inbox.addEmail(email);
        }
    }

    /**
     * Creates an email menu
     * @param x
     * Folder to be manipulated
     */
    public void emailOptions(Folder x){
        Scanner xd= new Scanner(System.in);
        boolean flag=true;
        while(flag){
            x.printtoString();

            System.out.println("M – Move email");
            System.out.println("D - Delete email");
            System.out.println("V - View email contents");
            System.out.println("SA - Sort by subject ascending");
            System.out.println("SD - Sort by subject descending");
            System.out.println("DA - Sort by date ascending");
            System.out.println("DD - Sort by date descending");
            System.out.println("R - Return to menu");

            switch(xd.next()){
                case "M": case"m":
                    System.out.println("Enter the index");
                    int index= xd.nextInt();
                    System.out.println("Enter the folder name");
                    String folnam=xd.next();
                    this.moveEmail(x.getEmails().get(index), this.getFolder(folnam));
                    break;
                case"D": case"d":
                    System.out.println("Enter the index");
                    int index1= xd.nextInt();
                    x.removeEmail(index1);
                    break;
                case"V": case"v":
                    System.out.println("Enter the index");
                    int index2= xd.nextInt();
                    x.getEmails().get(index2).toStringprint();
                    break;
                case"SA": case"sa":
                    try {
                        x.sortBySubjectAscending();
                    } catch (ListEmpty listEmpty) {
                        listEmpty.printStackTrace();
                    }
                    break;
                case"SD": case"sd":
                    try {
                        x.sortBySubjectDescending();
                    } catch (ListEmpty listEmpty) {
                        listEmpty.printStackTrace();
                    }
                    break;
                case"DA": case"da":
                    try {
                        x.sortByDateAscending();
                    } catch (ListEmpty listEmpty) {
                        listEmpty.printStackTrace();
                    }
                    break;
                case"DD": case"dd":
                    try {
                        x.sortByDateDescending();
                    } catch (ListEmpty listEmpty) {
                        listEmpty.printStackTrace();
                    }
                    break;
                case"R": case"r":
                    flag=false;
                    break;
            }

        }


    }

    /**
     * Gets folder with name
     * @param name
     * The name
     * @return
     * Folder of name
     */
    public Folder getFolder(String name){
        try{
            for (Folder i: folders){
                if (i.getName().equals(name)){
                    return i;
                }
            }
            return null;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid input");
        }
    }

    /**
     * Returns the folder index
     * @param name
     * The current folder name
     * @return
     * The int of the folder
     */
    public int getFolderIndex(String name){
        for(int i=0; i<folders.size(); i++){
            if(folders.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints the folder to a string
     */
    public void folderstoString(){
        for (Folder i: folders){
            System.out.println(i.getName());
        }
    }

    /**
     * The current arraylist of folders in mailbox
     * @return
     *
     * The current folders
     */

    public ArrayList<Folder> getFoldersArr(){
        return folders;
    }
    private static Mailbox mailbox;

    /**
     * The main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        mailbox= new Mailbox();
        Scanner sc= new Scanner(System.in);
        try{
            FileInputStream file= new FileInputStream("mySave.obj");
            ObjectInputStream f=new ObjectInputStream(file);
            mailbox= (Mailbox)f.readObject();
            file.close();
        }
        catch(Exception e){
            System.out.println("No file found, generating new mailbox");
        }
        boolean flag=true;
        while(flag){
            System.out.println("A - Add Folder");
            System.out.println("R - Remove Folder");
            System.out.println("C – Compose email");
            System.out.println("F – Open folder");
            System.out.println("I – Open Inbox");
            System.out.println("T – Open Trash");
            System.out.println("Q - Quit");

            System.out.println("Mailbox: ");
            System.out.println("---------------");
            mailbox.folderstoString();

            String input= sc.next();

            switch(input){
                case "A": case "a":
                    Folder temp= new Folder();
                    System.out.println("Please enter the name of the folder: ");
                    temp.setName(sc.next());
                    mailbox.addFolder(temp);
                    break;
                case "R": case "r":
                    System.out.println("Please enter the name of the folder to delete: ");
                    mailbox.deleteFolder(sc.next());
                    break;
                case "C": case "c":
                    mailbox.composeEmail();
                    break;
                case "F": case "f":
                    System.out.println("Which folder? ");
                    String u= sc.next();
                    Folder temp2=mailbox.getFolder(u);
                    if (temp2!=null){
                        mailbox.emailOptions(temp2);
                        mailbox.getFoldersArr().set(mailbox.getFolderIndex(u),temp2);
                    }
                    else{
                        System.out.println("Folder not found");
                    }
                    break;
                case "I": case "i":
                    Folder temp3=mailbox.getFolder("Inbox");
                    mailbox.emailOptions(temp3);
                    mailbox.getFoldersArr().set(mailbox.getFolderIndex("Inbox"),temp3);
                    break;
                case "T": case "t":
                    Folder temp4=mailbox.getFolder("Trash");
                    mailbox.emailOptions(temp4);
                    mailbox.getFoldersArr().set(mailbox.getFolderIndex("Trash"),temp4);
                    break;
                case "Q": case "q":
                    flag=false;
                    FileOutputStream file= new FileOutputStream("mySave.obj");
                    ObjectOutputStream f= new ObjectOutputStream(file);
                    f.writeObject(mailbox);
                    f.close();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

    }
}
