import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest
{
    static Task t;
    @BeforeAll public static void m1()
    {
        Date date = new Date();
        t = new Task(2, 3, "none", date);
    }
    @Test
    void getIndex()
    {

        assertTrue(t.getIndex()==1);
    }

    @Test
    void setStatus()
    {
        t.setStatus(Status.COMPLETED);
        assertTrue(t.getStatus().equals(Status.COMPLETED));
        assertFalse(t.getStatus().equals(Status.PENDING));
    }


    @Test
    void setMethod()
    {
        t.setMethod("GUI");
        assertTrue(t.getMethod().equals("GUI"));
        assertFalse(t.getMethod().equals("text"));
    }


    @Test
    void getProject()
    {
        assertTrue(t.getProject().equals("3"));
    }

    @Test
    void isCompleted()
    {
        assertFalse(t.isCompleted());
    }

    @Test
    void setCompleted()
    {
        t.setCompleted();
        assertTrue(t.getStatus().equals(Status.COMPLETED));
    }

    @Test
    void getName()
    {
    }

    @Test
    void setName()
    {
    }

    @Test
    void getDescription()
    {
    }

    @Test
    void setDescription()
    {
    }

    @Test
    void isExpired()
    {
    }

    @Test
    void isOpen()
    {
    }

    @Test
    void setExpired()
    {
    }

    @Test
    void checkExpiration()
    {
    }

    @Test
    void compareTo()
    {
    }

    @Test
    void editTask()
    {
    }


}