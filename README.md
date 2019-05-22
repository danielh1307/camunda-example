# Camunda-Example

* Start application
* Camunda: http://localhost:8080
* Create user (by default, no user is configured in the database)

## Create new Process

`curl -X POST http://localhost:8080/processes`

## Task endpoints

### Active Tasks
`curl http://localhost:8080/tasks/ids`

### Form variables
`curl http://localhost:8080/tasks/{id}/form-variables`

### Variables
`curl http://localhost:8080/tasks/{id}/variables`
 