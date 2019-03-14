import java.io.*;
import java.util.*;


/**
 * The main application that holds the whole execution of the program
 */
public class Application implements Serializable
{
    private FamilyToDoList family=new FamilyToDoList();
    private static final long serialVersionUID=1L;
    private String displayMedia ="text";
    private String familyName="";
    private Display displayOption;

    /**
     * Returns the name of the family to whom the todolist belongs to
     * @return
     */
    public String getFamilyName()
    {
        return familyName;
    }


    /**
     * Sets the name of the family
     */
    public void setfamilyName()
    {
        Scanner scanner = new Scanner(System.in);
        displayOption.display("\"--Welcome!--\n");
        displayOption.display("Please enter your family name:");
        familyName=scanner.next();
    }

    /**
     * Selects the desired method of display
     */
    public void selectDisplay()
    {
        if (displayMedia.equals("text"))
            displayOption = new DisplayText();
        family.displayOption = new DisplayText();
    }

    /**
     * The main method that runs that reads the family tasklists from file
     * updates them and stores them back to the file
     * @param args Any required arguments when running from command line
     */
    public static void main(String[] args)
    {
        Application app = new Application();
        app.selectDisplay();
        app.setfamilyName();
        app.show();

    }

    /**
     * Selects the appropriate file, loads the data and displays
     * options menu to the user.
     *
     */
    public void show()
    {
        selectFile();
        displayRootMenu();
    }

    /**
     *Displays the Main menu, where user can add, remove a member of the family
     * as well as selecting the todolist of a specific member and update it.
     */
    public void displayRootMenu()
    {
        displayOption.display("Please select one of the following options:\n\n");
        displayOption.display("(1) Display all family members\n");
        displayOption.display("(2) Select family member\n");
        displayOption.display("(3) Remove family member\n");
        displayOption.display("(4) Add a new family member\n");
        displayOption.display("(5) Save and Exit the application\n\n");
    }

    /**
     * Implements the main menu and the operations for members of the family
     */
    public void selectAction()
    {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do
        {
            displayRootMenu();
            choice = scanner.next();

            switch (choice)
            {
                case "1":
                    family.displayHolders();
                    selectAction();
                    break;

                case "2":
                    family.setSelectedUser();
                    try{

                        displayOption.display("\n--Welcome to the todoList of the "+familyName+" family--\n");
                        family.lists.get(family.selectedUser).showResults();
                        family.lists.get(family.selectedUser).toDoListHandler();
                        selectAction();
                    }
                    catch (NullPointerException E)
                    {
                        displayOption.display("No member selected!!");
                    }
                    break;


                case "3":
                    displayOption.display("Enter the name of the family member:\n");
                    family.removeHolder(scanner.next());
                    selectAction();
                    break;

                case "4":
                    displayOption.display("Enter the name of the new family member\n");
                    family.addHolder(scanner.next());
                    selectAction();
                    break;

                case "5":
                    displayOption.display("Thank you for using our application.\n");
                    saveToFile(familyName);
                    System.exit(0);

                default:
                    displayOption.display("Please enter a valid option (1 - 5):\n");
                    selectAction();
            }
        }
        while (!choice.equals("5"));
    }

    /**
     * Saves the family todolists to a file that has same name with the family
     * @param familyName the name of the family
     */
    public void saveToFile(String familyName)
    {
        try
        {
            FileOutputStream fo = new FileOutputStream(familyName);
            ObjectOutputStream output = new ObjectOutputStream(fo);
            output.writeObject(family);
        }
        catch(FileNotFoundException f)
        {
            displayOption.display("File error!\n");
        }
        catch(IOException e)
        {
            displayOption.display("Write error\n");
        }
    }

    /**
     * Loads the family todolists from the file that has same name
     * with the family.
     * @param familyName the name of the family
     */
    public void retrieveFromFile(String familyName)
    {
        File f=new File(familyName);
        if(!f.exists())
            return;

        try
        {
            FileInputStream fo = new FileInputStream(familyName);
            ObjectInputStream input = new ObjectInputStream(fo);
            family = (FamilyToDoList) input.readObject();
        }
        catch (IOException e)
        {
            displayOption.display(("Read Error!\n"));
        }
        catch (ClassNotFoundException c)
        {
            c.printStackTrace();
        }
    }

    /**
     * Checks if a file exists for a specific family
     * and loads the data from it.
     * If not, the user is asked if a new family record
     * should be created.
     */
    public void selectFile()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            retrieveFromFile(familyName);
            selectAction();
        }
        catch(Exception e)
        {
            displayOption.display("No todolist for this family\nWould you like to try again or create a new todo list for this family? (T/C)\n");
            String option = scanner.next().toLowerCase();
            if (option.equals("t"))
                selectFile();
            else if (option.equals("c"))
                selectAction();
            else
            {
                displayOption.display("Please enter T or C\n");
                selectFile();
            }
        }
    }
}
