import java.util.*;

/**
 * This is a class that describes the whole set of the individual
 * to do lists that belong to the members of the family
 */
public class FamilyToDoList
{
    HashMap<String,ToDoList> lists;

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
        lists.put(holder,new ToDoList());
    }

    /**
     * Removes the to do list for the given person
     * @param holder the name of the family number
     */
    public void removeHolder(String holder)
    {
        lists.remove(holder);
    }

    /**
     * Returns a list of family members that have a to do list
     */
    public void displayHolders()
    {
        System.out.println(lists.keySet());
    }

    /**
     * Selects the to do List for a specific family member
     * @param holder The name of the family member that the to do list belongs to.
     * @return the to do list of the specific family number
     */
    public ToDoList chooseListByHolder(String holder)
    {
        return lists.get(holder);
    }




}