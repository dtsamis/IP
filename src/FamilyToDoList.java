import java.io.Serializable;
import java.util.*;

/**
 * This is a class that describes the whole set of the individual
 * to do lists that belong to the members of the family
 */
public class FamilyToDoList implements Serializable
{
    HashMap<String, ToDoList> lists;
    private static final long serialVersionUID=1L;
    Display displayOption;
    String selectedUser;

    /**
     * Constructor for the Family List Directory
     * Creates an empty Family List Directory
     */
    public FamilyToDoList()
    {
        lists = new HashMap<>();
        selectedUser = "nobody";
    }

    /**
     * Adds a to do list for the given person
     * @param holder the name of the family member
     */
    public void addHolder(String holder)
    {
        lists.put(holder, new ToDoList(holder));
        displayOption.display(holder + " was added to the family todo list\n");
    }

    /**
     * Removes the to do list for the given person
     * @param holder the name of the family number
     */
    public void removeHolder(String holder)
    {
        try
        {
            lists.remove(holder);
            displayOption.display(holder + " was removed from the family todo list\n");
        } catch (NullPointerException e)
        {
            displayOption.display("Member not found\n");

        }
    }

    /**
     * Returns a list of family members that have a to do list
     */
    public void displayHolders()
    {
        displayOption.display(lists.keySet() + "\n");
    }

    /**
     * Selects the to do List for a specific family member
     */

    public void setSelectedUser()
    {
        Scanner scanner = new Scanner(System.in);
        displayOption.display("Please enter member's name:");
        String input = scanner.next();
        selectedUser=input;
        while (!lists.containsKey(input))
        {
            displayOption.display("Member not found\n Do you want to try again? (Y/N):");
            String choice = "";

            while (!choice.equals("y") && !choice.equals("n"))
            {
                displayOption.display("Please enter Y or N\n");
                choice = scanner.next().toLowerCase();
            }
            if (choice.equals("n"))
            {
                break;
            }
            else
                displayOption.display("Please enter member's name:");
            input = scanner.next();
            selectedUser=input;
        }
    }
}










