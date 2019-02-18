import java.util.*;

public class ToDoList
{
    private ArrayList<Task> tasks;


    public ToDoList()
    {
        tasks =new ArrayList<>();
    }

    public void insertTask()
    {
        tasks.add(new Task());
    }

    public void removeTaskByName(String name)
    {
        Iterator<Task>  it= tasks.iterator();
        while(it.hasNext())
            if(it.next().getName().equals(name))
                it.remove();
    }

    public void removeTaskByProject(String project)
    {
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext())
            if(it.next().getProject().equals(project))
                it.remove();
    }

    public void removeTaskByResponsible(String assignee)
    {
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext())
            if(it.next().getAssignee().equals(assignee))
                it.remove();
    }

    public void removeExpired()
    {
        Iterator<Task> it =tasks.iterator();
        while(it.hasNext())
            if(it.next().isExpired())
                it.remove();

    }

    public void checkExpiration()
    {
        
    }


}


