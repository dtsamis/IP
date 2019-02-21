import javax.naming.NameNotFoundException;
import java.util.*;

/**
 * This is a class that describes the whole set of the individual
 * to do lists that belong to the members of the family
 */
public class FamilyToDoList
{
    HashMap<String,ToDoList> lists;
    Display displayOption;

    /**
     * Constructor for the Family List Directory
     * Creates an empty Family List Directory
     */
    public FamilyToDoList()
    {
        lists=new HashMap<>();
    }

    /**
     * Adds a to do list for the given person
     * @param holder the name of the family member
     */
    public void addHolder(String holder)
    {
        lists.put(holder,new ToDoList(holder));
        displayOption.display(holder +" was added to the family todo list\n" +
                "Would you like to enter "+holder+"'s list now? (Y/N)");
        Scanner scanner =new Scanner(System.in);
        String choice;
        do
        {
            choice = scanner.next().toUpperCase();
             switch (choice)
             {
                 case "Y":
                     lists.get(holder).setDisplayOption(displayOption);
                     lists.get(holder).toDoListHandler();
                     break;

                 case "N":

                     break;

                 default:
                     displayOption.display("Please enter Y or N:");

             }
        }while(!choice.equals("Y")&&!choice.equals("N"));

    }

    /**
     * Removes the to do list for the given person
     * @param holder the name of the family number
     */
    public void removeHolder(String holder)
    {
        lists.remove(holder);
        displayOption.display(holder+" was removed from the family todo list\n");
    }

    /**
     * Returns a list of family members that have a to do list
     */
    public void displayHolders()
    {
        displayOption.display(lists.keySet()+"\n");
    }

    /**
     * Selects the to do List for a specific family member
     * @param holder The name of the family member that the to do list belongs to.
     * @return the to do list of the specific family number
     */
    public ToDoList chooseListByHolder(String holder) throws NameNotFoundException
    {
        try
        {
            displayOption.display("You are now in " + holder + "'s todo list\n");
            return lists.get(holder);
        }
        catch(NullPointerException n)
        {
            throw new NameNotFoundException("Member not found\n");
        }
    }




}