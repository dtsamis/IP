
import java.io.*;
import java.util.*;




public class Application implements Serializable
{
    FamilyToDoList family=new FamilyToDoList();
    private static final long serialVersionUID=1L;


    String displayMedia ="text";
    String familyName;

    Display displayOption;



    public void setfamilyName()
    {
        Scanner scanner = new Scanner(System.in);
        displayOption.display("\"--Welcome!--\n");
        displayOption.display("Please enter your family name:");
        familyName=scanner.next();
    }


    public void selectDisplay()
    {
        if (displayMedia.equals("text"))
            displayOption = new DisplayText();
        family.displayOption = new DisplayText();
    }


    public static void main(String[] args)
    {

     Application app = new Application();
     app.selectDisplay();

     app.setfamilyName();



     app.show();


    }










    public void show()
    {

        selectFile();
        displayRootMenu();
    }


        public void displayRootMenu()
    {

        displayOption.display("Please select one of the following options:\n\n");
        displayOption.display("(1) Display all family members\n");
        displayOption.display("(2) Select family member\n");
        displayOption.display("(3) Remove family member\n");
        displayOption.display("(4) Add a new family member\n");
        displayOption.display("(5) Save and Exit the application\n\n");

    }




    public void selectAction()
    {
        Scanner scanner = new Scanner(System.in);
        String choice;


        displayOption.display("\n--Welcome to the todoList of the "+familyName+" family--\n");


        do
        {
            displayRootMenu();
            choice = scanner.next();

            switch (choice)
            {
                case "1":
                    family.displayHolders();
                    selectAction();
                    break;

                case "2":
                    family.setSelectedUser();


                    try{
                        family.lists.get(family.selectedUser).toDoListHandler();
                        selectAction();

                    }
                    catch (NullPointerException E)
                    {
                        displayOption.display("No member selected!!");

                    }

                    break;


                case "3":
                    displayOption.display("Enter the name of the family member:\n");
                    family.removeHolder(scanner.next());
                    selectAction();
                    break;

                case "4":
                    displayOption.display("Enter the name of the new family member\n");
                    family.addHolder(scanner.next());
                    selectAction();
                    break;

                case "5":
                    displayOption.display("Thank you for using our application.\n");
                    saveToFile(familyName);
                    System.exit(0);



                default:
                    displayOption.display("Please enter a valid option (1 - 5):\n");
                    selectAction();
            }
        }
        while (!choice.equals("5"));

    }

    public void saveToFile(String familyName)
    {

        try
        {
            FileOutputStream fo = new FileOutputStream(familyName);

            ObjectOutputStream output = new ObjectOutputStream(fo);


            output.writeObject(family);
        }
        catch(FileNotFoundException f)
        {
            displayOption.display("File error!\n");
        }

      catch(IOException e)
      {

          e.printStackTrace();
           //displayOption.display("Write error\n");
      }
    }

    public void retrieveFromFile(String familyName)
    {
        File f=new File(familyName);
        if(!f.exists())
            return;

        try
        {
            FileInputStream fo = new FileInputStream(familyName);
            ObjectInputStream input = new ObjectInputStream(fo);

            family = (FamilyToDoList) input.readObject();
        }

        catch (IOException e)
        {
            e.printStackTrace();
            //displayOption.display(("Read Error!\n"));
        }
        catch (ClassNotFoundException c)
        {
            c.printStackTrace();
        }
    }

    public void selectFile()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            retrieveFromFile(familyName);
            selectAction();
        }
        catch(Exception e)
        {
            displayOption.display("No todolist for this family\nWould you like to try again or create a new todo list for this family? (T/C)\n");
            String option = scanner.next().toLowerCase();
            if (option.equals("t"))
                selectFile();
            else if (option.equals("c"))
                selectAction();
            else
            {

                displayOption.display("Please enter T or C\n");
                selectFile();
            }
        }
    }

}
