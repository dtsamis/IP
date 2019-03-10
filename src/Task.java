import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * A class that describes a task
 */
public class Task implements Serializable
{
    private String project;
    private Status status;
    private String description;
    private Date date;
    private String name;
    private transient Display displayOption;
    private String method;



    /**
     * Constructor of the Task class
     * When a task is created, all of its attributes must be defined by the user
     */
    public Task()

{
    setName();
    setMethod("text");
    setDisplayOption(new DisplayText());
    setProject();
    setDescription();
    setDate();
    setStatus(Status.PENDING);


}

    public Task(int title, int project, int description,Date date)
    {
        this.name = Integer.toString(title);
        this.project = Integer.toString(project);
        this.date = date;
        this.description=Integer.toString(description);
        status = Status.PENDING;
        setDisplayOption(new DisplayText());
        setMethod("text");

    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    };


    public void setDisplayOption(Display displayMethod)
    {
        displayOption = displayMethod;
    }

    /**
     * Getter method for attribute project
     * @return the name of the project the task belongs to
     */
    public  String getProject()
    {
        return project;
    }

    /**
     * Sets the name of the project the task belongs to
     *
     */
    public void setProject()
    {
        Scanner sc =new Scanner(System.in);
        displayOption.display("Which project does it belong to?");
        project = sc.nextLine();

    }


    /**
     * Getter method for attribute date
     * @return the date of completion of the task
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets the date of completion of the task
     *
     */
    public void setDate()
    {
        displayOption.display("Due Date? (Please use \"dd/MM/yyyy hh:mm\" format)");
        Scanner sc =new Scanner(System.in);
        boolean success=false;
        do
        {
            try
            {
                date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sc.nextLine());
                success=true;
                if(date.before(new Date()))
                {
                    displayOption.display("This day belongs to the past. Please enter a valid date:");
                    success=false;
                }




            } catch (ParseException e)
            {
                displayOption.display("Please enter the date in the requested format\n");



            }

        }
        while(!success);
    }

    /**
     *Checks if the task is completed
     * @return true if the task is completed, false if the task is not completed
     */
    public boolean isCompleted()
    {
        return status==Status.COMPLETED;
    }

    /**
     * Marks the task as completed
     */
    public void setCompleted()
    {
        status=Status.COMPLETED;
    }

    /**
     * Getter for the name attribute
     * @return the name of the task
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the task
     * @param name the name that task will take
     */
    public void setName()
    {
        Scanner sc =new Scanner(System.in);
        displayOption.display("Give task a name:");
        name=sc.nextLine();
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription()
    {


        Scanner sc =new Scanner(System.in);

        displayOption.display("Enter task description\n");
        description=sc.nextLine();
    }




    /**
     * Checks if a task is expired
     * @return true if the task is expired, false if not
     */
    public boolean isExpired()
    {
        return status==Status.EXPIRED;
    }

    /**
     * Marks the task as expired
     */
    public void setExpired()
    {
        status=Status.EXPIRED;
    }

    /**
     * Checks if a task is expired and marks it as expired if so.
     */
    public void checkExpiration()
    {
        if(date.before(new Date()))
            setExpired();

    }


    /**
     * A comparison method to be used for comparing dates of two tasks
     * @param b A task whose date will be compared to the date of the current task
     * @return -1 if date of current task is earlier than the date of the other task
     *         -2 if date of current task is later than the date of the other task
     *          0 if tasks have the same date
     */
    public int compareTo(Task b)
    {
            if(getDate().before(b.getDate()))
                return -1;
            else if(getDate().after(b.getDate()))
                return 1;
            else
                return 0;
    }

    public void editTask()
    {
        displayOption.display("What do you want to change?\n" +
        "(N) for Name\n" +
        "(T) for Time and Date\n" +
        "(D) for Description\n" +
        "(P) for Project\n" +
        "(R) to Return to the previous menu\n");
        Scanner scanner = new Scanner(System.in);
        String editChoice=null;
        String choices[]={"N","T","D","R","P"};


        while (!Arrays.asList(choices).contains(editChoice))
        {
            editChoice=scanner.next();

            switch(editChoice)
            {
                case "N":
                    displayOption.display("Enter the new name:");
                    name=scanner.next();
                    break;

                case "T":
                    setDate();
                    break;

                case "D":

                    setDescription();
                    break;

                case "P":

                    setProject();
                    break;

                case "R":
                    return;

                default:
                    displayOption.display("Please enter a valid option\n");


            }
        }



    }

    @Override
    public String toString()
    {
        String s="------------------------------------\n"+
                "|Name:"+name+"\n" +
                "|Project:" +project+"\n" +
                "|Due Date:"+date +"\n" +
                "|Status:"+status+"\n" +
                "------------------------------------\n";
        return s;
    }
}