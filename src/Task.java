import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Task
{
    private String project;
    private boolean isExpired;
    private String assignee;
    private Date date;
    private boolean isCompleted;
    private String name;





    public static void main(String[] args)
    {
        Task t = new Task();
        System.out.println(t.date);
    }

public Task()
{   date=new Date();
    Scanner sc =new Scanner(System.in);
    System.out.print("Give task a name:");
    name=sc.nextLine();
    System.out.println("Who is responsible for it?");
    assignee=sc.nextLine();
    System.out.println("Which project does it belong to?");
    project = sc.nextLine();
    //"07/10/96 4:5 PM, PDT"
    System.out.println("What is its deadline? (Please use \"dd/MM/yyyy hh:mm\" format");
    boolean success=false;
    do
    {
        try
        {
            date = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(sc.nextLine());
            success=true;




        } catch (ParseException e)
        {
            System.out.println("Please enter the date in the requested format");

        }

    }
        while(!success);
        isCompleted=false;
        isExpired=false;
    }


    public String getProject()
    {
        return project;
    }

    public void setProject(String project)
    {
        this.project = project;
    }

    public String getAssignee()
    {
        return assignee;
    }

    public void setAssignee(String assignee)
    {
        this.assignee = assignee;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }

    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isExpired()
    {
        return isExpired;
    }
}

