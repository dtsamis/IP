import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest
{

    Application app =new Application();


    @Test
    void testGetfamilyName()
    {
        assertTrue(app.getFamilyName().equals(""));
    }

}