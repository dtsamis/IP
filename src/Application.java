import java.util.*;
import java.util.stream.Collectors;


/*Show  >> (1) Display all family members that have their own to do list
        >> (2) Choose family member
        >> (3) Remove family member from the list
        >> (4) Add a new family member to the list



/**
 * This is the main class that takes care of the interaction with the user
 */
public class Application
{
    FamilyToDoList family=new FamilyToDoList();
    DisplayText displayText = new DisplayText();
    ToDoList selectedUser;







    public static void main(String[] args)
    {

     Application app = new Application();


     app.displayRootMenu();


    }

    /**
     * Creates a new to do list for the given holder
     * @param holder the name of the family member to be added
     */
    public void addHolder(String holder)
    {
        family.addHolder(holder);
    }

    /**
     * Returns the to do list for the specific family member
     * @param holder the name of the family member whose to do list
     *               is going to be returned
     * @return the to do list for the specific family member
     */
    public ToDoList chooseListByHolder(String holder)
    {
        return family.chooseListByHolder(holder);

    }


    /**
     * Removes the to do list for the given person
     * @param holder the name of the family number
     */
    public void removeHolder(String holder)
    {
        family.removeHolder(holder);
    }

    public void displayRootMenu()
    {
        displayText.display("--Welcome to your family toDo list--\n\n");
        displayText.display("Please select one of the following options:\n\n");
        displayText.display("(1) Display all family members\n");
        displayText.display("(2) Select family member\n");
        displayText.display("(3) Remove family member\n");
        displayText.display("(4) Add a new family member\n");
        displayText.display("(5) Exit the application\n");

    }

    public void selectAction()
    {
        Scanner scanner = new Scanner(System.in);
        int choice;
        displayRootMenu();

        do
        {
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    family.displayHolders();
                    break;

                case 2:
                    displayText.display("Enter the name of the family member:\n");
                    selectedUser=chooseListByHolder(scanner.next());
                    break;

                case 3:
                    displayText.display("Enter the name of the family member:\n");
                    family.removeHolder(scanner.next());
                    break;

                case 4:
                    displayText.display("Enter the name of the new family member");
                    family.addHolder(scanner.next());
                    break;

                case 5:
                    displayText.display("Thank you for using our application.\n");
                    System.exit(0);

                default:
                    displayText.display("Please enter a valid option (1 - 5):\n");
            }
        }
        while (choice<0||choice>5);

    }

}
