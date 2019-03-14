import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest
{
    ToDoList todo =new ToDoList("Somebody");
    Date d1,d2,d3;
    Task t1,t2,t3;


    @BeforeEach public  void m1()

    {


        try

        {
            d1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/12/2000 12:00");
            t1 = new Task(1, 1, "none", d1);
        }
        catch (ParseException p)
        {
            p.printStackTrace();
        }

        try

        {
            d2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/12/2020 12:00");
            t2 = new Task(2, 2, "none", d2);
        }
        catch (ParseException p)
        {
            p.printStackTrace();
        }

        try

        {
            d3 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/12/2000 12:00");
            t3 = new Task(1, 1, "none", d3);
        }
        catch (ParseException p)
        {
            p.printStackTrace();
        }
        todo.getTasks().add(t1);
        todo.getTasks().add(t2);
        todo.getTasks().add(t3);



        }

    @Test
    void purgeExpired()
    {
        ToDoList todoAfter=new ToDoList("Someone");
        todoAfter.getTasks().add(t2);
        todo.getTasks().get(0).checkExpiration();
        todo.getTasks().get(2).checkExpiration();
        todo.purgeExpired();
        assertTrue(todo.getTasks().equals(todoAfter.getTasks()));
    }

    @Test
    void countOpen()
    {
        assertTrue(todo.countOpen()==3);
        todo.getTasks().get(0).setCompleted();
        assertTrue(todo.countOpen()==2);
    }

    @Test
    void countDone()
    {
        assertTrue(todo.countDone()==0);
        todo.getTasks().get(0).setStatus(Status.COMPLETED);
        assertTrue(todo.countDone()==1);
    }

}