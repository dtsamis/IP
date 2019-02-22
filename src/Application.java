
import java.io.*;
import java.util.*;




public class Application implements Serializable
{
    FamilyToDoList family=new FamilyToDoList();



    String displayMedia ="text";
    String familyName;

    Display displayOption;



    public void setfamilyName()
    {
        Scanner scanner = new Scanner(System.in);
        displayOption.display("\"--Welcome!--\n");
        displayOption.display("Please enter your family name:");
        familyName=scanner.next();
    }


    public void selectDisplay()
    {
        if (displayMedia.equals("text"))
            displayOption = new DisplayText();
        family.displayOption = new DisplayText();
    }


    public static void main(String[] args)throws ClassNotFoundException
    {

     Application app = new Application();
     app.selectDisplay();

     app.setfamilyName();


     app.show();


    }

    /**
     * Creates a new to do list for the given holder
     * @param holder the name of the family member to be added
     */
    public void addHolder(String holder)
    {
        family.addHolder(holder);
        displayRootMenu();

    }

    /**
     * Returns the to do list for the specific family member
     * @param holder the name of the family member whose to do list
     *               is going to be returned
     * @return the to do list for the specific family member
     */



    /**
     * Removes the to do list for the given person
     * @param holder the name of the family number
     */
    public void removeHolder(String holder)
    {
        family.removeHolder(holder);
    }


    public void show()throws ClassNotFoundException
    {

        selectAction();
        displayRootMenu();
    }


        public void displayRootMenu()
    {

        displayOption.display("Please select one of the following options:\n\n");
        displayOption.display("(1) Display all family members\n");
        displayOption.display("(2) Select family member\n");
        displayOption.display("(3) Remove family member\n");
        displayOption.display("(4) Add a new family member\n");
        displayOption.display("(5) Save and Exit the application\n\n");

    }

    public void selectAction() throws ClassNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        int choice;


            try
            {   setfamilyName();
                retrieveFromFile(familyName);
            }
            catch(Exception e)
            {
                displayOption.display("No todolist for this family\nWould you like to try again or create a new todo list for this family? (T/C)\n");
                String option = scanner.next().toLowerCase();
                if (option.equals("t"))
                    selectAction();
                else if (option.equals("c"))
                    displayRootMenu();
                else
                {
                    
                    displayOption.display("Please enter T or C\n");
                    selectAction();
                }
            }



        displayOption.display("--Welcome to the todoList of the "+familyName+" family\n\n");
        displayRootMenu();

        do
        {
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    family.displayHolders();
                    displayRootMenu();
                    break;

                case 2:
                    family.setSelectedUser();

                    try{
                        family.lists.get(family.selectedUser).toDoListHandler();
                    }
                    catch (NullPointerException E)
                    {
                        displayOption.display("No member selected!!");
                    }
                    finally
                    {
                        displayRootMenu();
                    }
                    break;


                case 3:
                    displayOption.display("Enter the name of the family member:\n");
                    family.removeHolder(scanner.next());
                    displayRootMenu();
                    break;

                case 4:
                    displayOption.display("Enter the name of the new family member\n");
                    family.addHolder(scanner.next());
                    displayRootMenu();
                    break;

                case 5:
                    displayOption.display("Thank you for using our application.\n");
                    System.exit(0);

                case 6:
                    displayRootMenu();
                    break;

                default:
                    displayOption.display("Please enter a valid option (1 - 5):\n");
                    displayRootMenu();
            }
        }
        while (choice!=5);

    }

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

    public void retrieveFromFile(String familyName) throws ClassNotFoundException
    {
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
    }

}
