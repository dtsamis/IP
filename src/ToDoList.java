import javax.naming.NameNotFoundException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * A class that holds a list of tasks and the methods to handle them
 */
public class ToDoList implements Serializable
{

    private Display displayOption;
    private String holder;
    private ArrayList<Task> tasks;
    private String menu;
    private final int NUMBEROFTASKS=10;




    /**
     * Constructor method that initializes the list of tasks in the to do list
     */
    public ToDoList(String holder)
    {
        displayOption=new DisplayText();
        tasks =new ArrayList<>();
        this.holder=holder;
         /*Show Task List (by date or project)
        >> (2) Add New Task
        >> (3) Edit Task (update, mark as done, remove)
        >> (4) Save and Quit*/
        menu="You are in "+this.holder+"'s todo list. Please select action:\n"
                +"(1) Show Tasks (Sorted by Date)\n"
                +"(2) Show tasks for a specific project\n"
                +"(3) Add Task\n"
                +"(4) Edit Task\n"
                +"(5) Delete Task\n"
                +"(6) Delete Project\n"
                +"(7) Show expired tasks\n"
                +"(8) Return to Root Menu\n\n";
    }


    /*public void deleteTask(Task t)
    {
        Scanner scanner =new Scanner(System.in);
        String choice ="";

        while(!choice.equalsIgnoreCase("N")&&!choice.equalsIgnoreCase("P"))
        {
            choice=scanner.next().toLowerCase();
            String taskName;

            switch (choice)
            {

                case "n":

                    //displayOption.display("Please enter task name:");
                    // taskName=scanner.nextLine();
                    removeTaskByName(t.getName());
                    break;

                case "p":
                    //displayOption.display("Please enter project to delete:");
                    //taskName=scanner.nextLine();
                    removeTaskByProject(t.getProject());
            }
        }
    }*/


    public void editMenu()
    {
        Scanner scanner = new Scanner(System.in);
        Task t;
        try
        {
            t=selectTaskByName();
        }
        catch (NameNotFoundException n)
        {
            displayOption.display("No task with this name\n");
            return;
        }

        String editChoice;
        String options[] = {"U", "D", "C", "R"};

        do
        {
            displayOption.display("Please choose one of the following options:\n" +
                    "(U) for Update\n" +
                    "(D) for Delete\n" +
                    "(C) for marking as Completed\n" +
                    "(R) to return to the previous menu\n");

            editChoice = scanner.next().toUpperCase();


  /*      */

            switch (editChoice)
            {
                case "U":

                    t.editTask();
                    displayOption.display("Task was edited\n");
                    editMenu();
                    break;

                case "D":
                    removeTaskByName();
                    displayOption.display("Deletion completed\n");
                    editMenu();
                    break;

                case "C":
                    t.setCompleted();
                    displayOption.display("Task was marked as completed\n");
                    editMenu();
                    break;

                case "R":
                    toDoListHandler();
                    break;

                default:
                    /*displayOption.display("Please enter a valid option\n");
                    displayOption.display("(U) for Update\n" +
                            "(D) for Delete\n" +
                            "(C) for marking as Completed\n" +
                            "(R) to return to the previous menu\n");
*/
            }


        }


        while (!Arrays.asList(options).contains(editChoice));
        return;
    }


    public void editTask()
    {
        try
        {
            Task t=selectTaskByName();
            t.editTask();

        }
        catch(NameNotFoundException n)
        {
            displayOption.display("Task not found\n");
        }
    }

    public void expired()
    {
        Scanner scanner =new Scanner(System.in);
        List<Task> expired = findExpired();
        if (expired.size() > 0)
        {
            for(Task t:expired)
                displayOption.display(t+"\n");

            String removed = "";

            displayOption.display("Do you want to remove expired projects? (Y/N)\n");

            while (!removed.equalsIgnoreCase("Y") && !removed.equalsIgnoreCase("N")) {
                removed = scanner.next().toUpperCase();
                switch (removed) {
                    case "Y":
                        purgeExpired();

                        break;

                    case "N":

                        break;

                    default:
                        displayOption.display("Please enter Y for removal or N for keeping expired tasks in the list.\n");

                }
            }
        }
        else
        {
            displayOption.display("No expired task found\n");

        }
    }

    public void fillTestData()
    {
        Random rnd =new Random();

        for(int i=0;i<NUMBEROFTASKS;i++)
        {
            int year=rnd.nextInt(20)+2010;
            int month=rnd.nextInt(12)+1;
            int []day30={4,6,9,11};
            int []day31={1,3,5,7,8,10,12};
            int day;
            if(Arrays.asList(day30).contains(month))
                day=rnd.nextInt(30)+1;
            else if(Arrays.asList(day31).contains(month))
                day=rnd.nextInt(31)+1;
            else
                day=rnd.nextInt(28)+1;

            int hour=rnd.nextInt(23)+1;
            int minute=rnd.nextInt(60+1);
            Date d=(new GregorianCalendar(year,month,day,hour,minute)).getTime();

            int title=rnd.nextInt(20)+1;
            int project=rnd.nextInt(5)+1;
            int description=rnd.nextInt();

            tasks.add(new Task(title,project,description,d));
        }
    }


    /**
     * Returns only the tasks of the to do list that belong to the specific project
     *
     */

    public String filterByProject( )
    {
        displayOption.display("Enter project name:");
        Scanner scanner =new Scanner(System.in);
        String project=scanner.next();
        String filtered=
                getTasks().stream()
                        .filter(x->x.getProject().equals(project))
                        .sorted((task1,task2)->task1.compareTo(task2))
                        .map(x->x.toString())
                        .collect(Collectors.joining("\n"));
        return filtered;


    }


    public List<Task> findExpired()
    {
        tasks.stream()
                .forEach(Task::checkExpiration);
        List<Task> expired = tasks.stream()
                .filter(t->t.isExpired())
                .collect(Collectors.toList());
        return expired;
    }

    /**
     * Getter method for the list of the tasks in the to do list
     * @return the list of hte tasks in the to do list
     */
    public ArrayList<Task> getTasks()
    {
        return tasks;
    }


    /**
     * Adds a new task to the to do list
     */
    public void insertTask()
    {


        getTasks().add(new Task());
    }

    public void purgeExpired()
    {

        Iterator<Task>it =tasks.iterator();
        while(it.hasNext())
        {
            Task t=it.next();
            if(t.isExpired())
                it.remove();
        }

    }



    /**
     * Removes the task with the given name

     */
    public void removeTaskByName()
    {
        String name;
        displayOption.display("Enter task name:");
        Scanner scanner =new Scanner(System.in);
        name=scanner.next();
        int counts=0;
        Iterator<Task>  it= getTasks().iterator();
        while(it.hasNext())
            if(it.next().getName().equals(name))
            {
                it.remove();
                counts++;
            }
            if(counts==0)
                displayOption.display("No task found with that name\n");
    }

    /**
     * Removes all the tasks related to a specific project

     */
    public void removeTaskByProject()
    {
        String project;
        displayOption.display("Enter name of project to delete all tasks assigned to it.");
        Scanner scanner =new Scanner(System.in);
        project=scanner.next();
        displayOption.display("Are you sure that you want to delete all tasks assigned to project "+project+"? (Y/N)");
        String answer=scanner.next().toUpperCase();
        while(!answer.equalsIgnoreCase("Y")&&!answer.equalsIgnoreCase("N"))
        {


        }
        int counts=0;

        Iterator<Task> it = getTasks().iterator();
        while(it.hasNext())
            if(it.next().getProject().equals(project))
                it.remove();
            if(counts==0)
                displayOption.display("No tasks assigned to this project");
    }

    /**
     * Returns a list of all the tasks in the to do list sorted by date.
     * @return the tasks of the to do list sorted by date.
     */

    public String returnByDate()
    {
        String sorted=
                getTasks().stream()
                        .sorted((task1,task2)->task1.compareTo(task2))
                        .map(t->t.toString())
                        .collect(Collectors.joining("\n"));
        return sorted;
    }


    public Task selectTaskByName() throws NameNotFoundException
    {
        String name;
        Scanner scanner = new Scanner(System.in);
        displayOption.display("Enter task name:");
        name=scanner.next();

        for(Task t : tasks)
            if(t.getName().equals(name))
                return t;

        throw new NameNotFoundException();
    }

    public void setDisplayOption(Display displayMethod)
    {
        displayOption = displayMethod;
    }


    public void toDoListHandler()
    {
        displayOption.display(menu);
        String choice;
        Scanner scanner = new Scanner(System.in);


       /* "(1) Show Tasks (Sorted by Date)\n"
                +"(2) Show tasks for a specific project\n"
                +"(3) Edit Task\n"
                +"(4) Save and return to Root Menu\n";*/
        String listOptions[] = {"1", "2", "3", "4", "5", "6","7","8"};
        do
        {
            choice = scanner.next();

            switch (choice) {
                case "1":
                    displayOption.display(returnByDate());
                    toDoListHandler();
                    break;

                case "2":
                    displayOption.display(filterByProject());
                    toDoListHandler();
                    break;

                case "3":
                    insertTask();
                    displayOption.display("Task was added\n");
                    toDoListHandler();
                    break;

                case "4":

                    editTask();
                    toDoListHandler();
                    break;

                case "5":
                    removeTaskByName();
                    toDoListHandler();
                    break;

                case "6":
                    removeTaskByProject();
                    toDoListHandler();
                    break;

                case "7":
                    expired();
                    toDoListHandler();
                    break;

                case "8":

                    break;

                case "9":
                    fillTestData();
                    toDoListHandler();
                    break;
                default:
                    displayOption.display("Please select valid action:\n"
                            +"(1) Show Tasks (Sorted by Date)\n"
                            +"(2) Show tasks for a specific project\n"
                            +"(3) Add Task\n"
                            +"(4) Edit Task\n"
                            +"(5) Delete Task\n"
                            +"(6) Delete Project\n"
                            +"(7) Show expired tasks\n"
                            +"(8) Return to Root Menu\n\n");


            }

        } while (!Arrays.asList(listOptions).contains(choice));
    }















}


