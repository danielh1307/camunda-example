# Camunda-Example

* Start application
* Camunda: http://localhost:8080
* Create user (by default, no user is configured in the database)

## Create new Process

`curl -X POST http://localhost:8080/processes`

The process is stored in `ACT_HI_PROCINST`.

## Task endpoints
If a task is created, it is stored in `ACT_RU_TASK`. This contains "running" tasks. All tasks (history) are stored in `ACT_HI_TASKINST`. 

### Active Tasks
`curl http://localhost:8080/tasks/ids`

### Form variables
`curl http://localhost:8080/tasks/{id}/form-variables`

A form variable is defined in the forms tab of the task. It is presented as a form to the user. So the form variables are intended to be changed by the user when working on the task.

The `id` in the URL is the id of the task. If you have a process / instance id (`ACT_HI_PROCINST.PROC_INST_ID`) then you get the corresponding task id with `SELECT ID_ FROM ACT_RU_TASK WHERE PROC_INST_ID_ = <ID>`

### Variables
`curl http://localhost:8080/tasks/{id}/variables`

A variable is defined in the Input/Output tab of the task. It is not visible in the Camunda task view. But you can see (and change) it in the Camunda Cockpit (Processes detail).
 
## Passing values

To pass a value from e.g. a service task to a human task, see the concept of process variables: https://docs.camunda.org/manual/7.5/user-guide/process-engine/variables/

In short, define an output varaible on the service task. If it has a final value, just write it to the value field in the modeller. Otherwise, assign a value (e.g. `${loggingDelegateOutput}`), which you can set in your code with 

```java
delegateExecution.setVariable(<NAME>, <VALUE>)
```

Now you can assign this value in the human task by using `${loggingDelegateOutput}` again.