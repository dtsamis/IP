import java.util.*;
import java.util.stream.Collectors;



public class ToDoList
{

    private String holder;
    private ArrayList<Task> tasks;
    private String menu;

    /**
     * Getter method for the list of the tasks in the to do list
     * @return the list of hte tasks in the to do list
     */
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }


    /**
     * Constructor method that initializes the list of tasks in the to do list
     */
    public ToDoList(String holder)
    {

        tasks =new ArrayList<>();
        this.holder=holder;
         /*Show Task List (by date or project)
        >> (2) Add New Task
        >> (3) Edit Task (update, mark as done, remove)
        >> (4) Save and Quit*/
        menu="You are in "+holder+"'s todo list. Please select action:\n"
                +"(1) Show Tasks (Sorted by Date)\n"
                +"(2) Show tasks for a specific project\n"
                +"(3) Edit Task\n"
                +"(4) Save and return to Root Menu\n";
    }

    /**
     * Adds a new task to the to do list
     */
    public void insertTask()
    {
        getTasks().add(new Task());
    }

    /**
     * Removes the task with the given name
     * @param name the name of the task to be deleted
     */
    public void removeTaskByName(String name)
    {
        Iterator<Task>  it= getTasks().iterator();
        while(it.hasNext())
            if(it.next().getName().equals(name))
                it.remove();
    }

    /**
     * Removes all the tasks related to a specific project
     * @param project the name of the project to be removed
     */
    public void removeTaskByProject(String project)
    {
        Iterator<Task> it = getTasks().iterator();
        while(it.hasNext())
            if(it.next().getProject().equals(project))
                it.remove();
    }


    /**
     * Checks the to do list and removes all expired tasks
     */
    public void removeExpired()
    {
        Iterator<Task> it =getTasks().iterator();
        while(it.hasNext())
            if(it.next().isExpired())
                it.remove();

    }

    /**
     * Returns only the tasks of the to do list that belong to the specific project
     * @param project The name of the project we want to get the tasks for.
     */

    public List<Task> filterByProject( String project)
    {
        List<Task> filtered=
                getTasks().stream()
                        .filter(x->x.getProject().equals(project))
                        .collect(Collectors.toList());
        return filtered;


    }


    /**
     * Returns a list of all the tasks in the to do list sorted by date.
     * @return the tasks of the to do list sorted by date.
     */
    public List<Task> returnByDate()
    {
        List<Task> grouped=
                getTasks().stream()
                        .sorted((task1,task2)->task1.compareTo(task2))
                        .collect(Collectors.toList());
        return grouped;
    }





    public void showPersonsMenu(Display displayOption)
    {
        displayOption.display (menu);
    }


}


