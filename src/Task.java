
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * A class that describes a task
 */
public class Task implements Serializable
{
    private static int current=1;
    private int index;
    private String project;
    private Status status;
    private String description;
    private Date date;
    private String name;
    private transient Display displayOption;
    private String method;
    /**
     * This is used for keeping the serialization of the object consistent
     */
    private static final long serialVersionUID=1L;



    /**
     * Constructor of the Task class
     * When a task is created, all of its attributes must be defined by the user
     */
    public Task()

{
    setDisplayOption(new DisplayText());
    setName();
    setMethod("text");
    setProject();
    setDescription();
    setDate();
    setStatus(Status.PENDING);
    index=current;
    current++;


}

    /**
     * A Task constructor that is used mainly for feeding the random test data
     * @param title the intended title of the task
     * @param project the name of the project the task is assigned to
     * @param description the intended description of the task
     * @param date the deadline of the task
     */
    public Task(int title, int project, String description,Date date)
    {
        setDisplayOption(new DisplayText());
        this.name = Integer.toString(title);
        this.project = Integer.toString(project);
        this.date = date;
        this.description=description;
        status = Status.PENDING;
        setMethod("text");
        index=current;
        current++;

    }

    /**
     * Getter method for task index
     * @return the index of the task
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Setter method for task status
     * @param status the intended status of the task
     * @return true if operation is successful
     */
    public boolean setStatus(Status status) {
        this.status = status;
        return true;
    }

    /**
     * Setter method for task deadline
     * @param date the intended deadline of the task
     * @return true if operation is succesful
     */
    public boolean setDate(Date date) {
        this.date = date;
        return true;
    }

    /**
     * Setter method for display option code
     * @param method the intended display option code for task
     * @return true if operation is successful
     */
    public boolean setMethod(String method) {
        this.method = method;
        return true;
    }

    /**
     * Setter method for display option code
     * @return the display option code of the task
     */
    public String getMethod()
    {
        return method;
    }

    /**
     * Sets the displayMethod of the task
     * @param displayMethod the required display method
     * @return true if operation is successful
     */
    public boolean setDisplayOption(Display displayMethod)
    {
        displayOption = displayMethod;
        return true;
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
     * Assigns a project to task
     * @return true if operation is successful
     */

    public boolean setProject()
    {
        Scanner sc =new Scanner(System.in);
        displayOption.display("Which project does it belong to?");
        project = sc.nextLine();
        return true;
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
     * Sets the deadline of the task. It checks if the date is in the past and does not allow
     * the entry until a valid date is entered
     * @return true if operation is successful
     */
    public boolean setDate()
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
            }
            catch (ParseException e)

            {
                displayOption.display("Please enter the date in the requested format\n");
            }
        }
        while(!success);
        return true;
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
     * @return true if operation is successful
     */
    public boolean setName()
    {
        Scanner sc =new Scanner(System.in);
        displayOption.display("Give task a name:");
        name=sc.nextLine();
        return true;
    }

    /**
     * Getter method for task description
     * @return the description of the task
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets description of the task
     * @return true if operation is successful
     */
    public boolean setDescription()
    {
        Scanner sc =new Scanner(System.in);
        displayOption.display("Enter task description\n");
        description=sc.nextLine();
        return true;
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
     * Checks if a task is pending
     * @return true if the task is pending
     */
    public boolean isOpen()
    {
        return status==Status.PENDING;
    }

    /**
     * Marks a task as expired
     * @return true if operation is successful
     */
    public boolean setExpired()
    {
        status=Status.EXPIRED;
        return true; }

    /**
     * Checks if a task is expired and marks it as expired if so.
     * @return true if operation is successful
     */
    public boolean checkExpiration()
    {
        if(date.before(new Date()))
            setExpired();
        return true;

    }

    /**
     * A comparison method to be used for comparing dates of two tasks
     * @param b A task whose date will be compared to the date of the current task
     * @return -1 if date of current task is earlier than the date of the other task
     *          1 if date of current task is later than the date of the other task
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

    /**
     * Implements the operation of editing a task
     * @return true if operation is successful
     */
    public boolean editTask()
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
            editChoice=scanner.next().toUpperCase();

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
                    return true;

                default:
                    displayOption.display("Please enter a valid option\n");


            }
        }


    return true;
    }

    /**
     * Defines how a task is displayed
     * @return the string swquence that describes a task
     */
    @Override
    public String toString()
    {
        String s="------------------------------------\n"+
                "Task ID:"+index+"\n" +
                "|Name:"+name+"\n" +
                "|Project:" +project+"\n" +
                "|Description:" +description+"\n"+
                "|Due Date:"+date +"\n" +
                "|Status:"+status+"\n" +
                "------------------------------------\n";
        return s;
    }


}