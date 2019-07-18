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
 