import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;




public class Task
{
    private String project;
    private Status status;

    private Date date;
    private String name;







public Task()

{   date=new Date();
    Scanner sc =new Scanner(System.in);
    System.out.print("Give task a name:");
    name=sc.nextLine();

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
            if(date.before(new Date()))
            {
                System.out.println("This day belongs to the past. Please enter a valid date");
                success=false;
            }




        } catch (ParseException e)
        {
            System.out.println("Please enter the date in the requested format");



        }

    }
        while(!success);

        status =Status.PENDING;
}


    public  String getProject()
    {
        return project;
    }

    public void setProject(String project)
    {
        this.project = project;
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
        return status==Status.COMPLETED;
    }

    public void setCompleted(boolean completed)
    {
        status=Status.COMPLETED;
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
        return status==Status.EXPIRED;
    }

    public void setExpired()
    {
        status=Status.EXPIRED;
    }

    public void checkExpiration()
    {
        if(date.before(new Date()))
            setExpired();

    }


/*class SortbyDate implements Comparator<Task>
{
    // Used for sorting in ascending order of
    // roll number*/
    public int compareTo(Task b)
    {
            if(getDate().before(b.getDate()))
                return -1;
            else if(getDate().after(b.getDate()))
                return 1;
            else
                return 0;
    }
}