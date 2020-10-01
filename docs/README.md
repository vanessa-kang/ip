# User Guide

## Features 

### 1. Add Todo

Add a Todo task to the task list.

### 2. Add Deadline

Add a task with a deadline to the task list.

### 3. Add Event

Add a task with a time to the task list.

### 4. Mark task as done

Mark a task in the task list as done.

### 5. Delete task 

Delete a task from the task list.

### 6. List all tasks

List all tasks in the task list.

### 7. Find tasks

Search for tasks containing a keyword.

### 8. Help

List all available commands.

### 9. Save tasks to file

Save the task list to a .txt file.



## Usage

### `todo <task description>` - Add a Todo task.

Adds a Todo task to the task list.

Example of usage:

`todo write essay`

Expected outcome:

```
     Got it. I've added this task: 
          [T][✗] write essay
     Now you have 1 tasks in the list.
```

<br>

### `deadline <task description> /by <date>` - Add a Deadline.

Adds a Deadline task to the task list.

Example of usage:

`deadline submit project /by tomorrow night`

Expected outcome:

```
     Got it. I've added this task: 
          [D][✗] submit project (by: tomorrow night)
     Now you have 2 tasks in the list.
```

<br>

### `event <task description> /at <date>` - Add an Event.

Adds an Event task to the task list.

Example of usage:

`event presentation /at Monday afternoon`

Expected outcome:

```
     Got it. I've added this task: 
          [E][✗] presentation (at: Monday afternoon)
     Now you have 3 tasks in the list.
```

<br>

### `done <task number>` - Mark a task as done.

Marks a task in the task list as done.

Example of usage:

`done 3`

Expected outcome:

```
     Nice! I've marked this task as done:
     [✓] [E][✓] presentation (at: Monday afternoon)
```

<br>

### `delete <task number>` - Delete a task.

Deletes a task from the task list.

Example of usage:

`delete 3` 

Expected outcome:

```
     Noted. I've removed this task: 
          [E][✓] presentation (at: Monday afternoon)
     Now you have 2 tasks in the list.
```

<br>

### `list` - List all tasks.

Lists all tasks currently in the task list.

Example of usage:

`list`

Expected outcome:

```
     Here are the tasks currently in your list:
     1. [T][✗] write essay
     2. [D][✗] submit project (by: tomorrow night)
     3. [E][✓] presentation (at: Monday afternoon)
     4. [E][✗] product pitch (at: Tuesday evening)
     5. [T][✗] zoom call with tutor
```

<br>

### `find <keyword>` - Find tasks containing a given keyword.

Searches for tasks in the task list that contains a given keyword.

Example of usage:

`find zoom`

Expected outcome:

         Here are the matching tasks in your list:
         1. [T][✗] zoom call with tutor

<br>

### `help` - List all available commands.

Shows a list of all the available commands. 

Example of usage:

`help`

Expected outcome:

```      
     Currently available commands:
     • todo <task description> ------------------ Add Todo
     • deadline <task description> /by <date> --- Add Deadline
     • event <task description> /by <date> ------ Add Event
     • done <task number> ----------------------- Mark a task as done
     • delete <task number> --------------------- Delete a task
     • list ------------------------------------- List all tasks
     • find <keyword> --------------------------- Find tasks containing a keyword
     • bye -------------------------------------- Save and exit program
```

<br>

### `bye` - Exit program.

Saves task list to a .txt file, and exits program.

Example of usage:

`bye`

Expected outcome:

```
     Saving tasks to file...
     Tasks successfully saved! :)
     Bye. Hope to see you again soon!
```