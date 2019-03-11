# Family ToDo List

## Individual Project for Novare Potential Software Development Academy at KTH.


* **Goal:** Create a todo list in a text based form.

* **Description:** This is a text application that allows multiple families to hold their lists on a folder, each one having each own file in the folder. The application can load the family todolists from the files, update them as needed, and store them back to the files.
* **How it works:** When the application starts, it asks for a familyname, if there is already a file with that familyname, the records are loaded from the corresponding file.
If there is no record for that family, a new file is created and the data is stored there.
Each person of the family can edit the corresponding todolist and all the todolists of the family are kept in a HashMap where keys are the name of hte persons and values their corresponding todolists.

* **Extra features:**
   * *Family todoList instead of individual todoLists*
   * *Multiple families use is supported as well*
   * *Purging Expired Tasks*
   * *Option to delete tasks by Name, or every task that belongs to a Project*




