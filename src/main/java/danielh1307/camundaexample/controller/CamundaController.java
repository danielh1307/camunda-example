package danielh1307.camundaexample.controller;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CamundaController {

    private ProcessEngine processEngine;

    public CamundaController(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @PostMapping(path = "/processes")
    private String processPostDeploy(PostDeployEvent postDeployEvent) {
        // although process id is set in the .bpmn file, we have to use startProcessInstanceByKey here
        ProcessInstance payBillId = this.processEngine.getRuntimeService().startProcessInstanceByKey("PayBillId");

        return payBillId.getId(); // this returns ACT_HI_PROCINST.ID_
    }

    @GetMapping(path = "/tasks/ids")
    private String activeTaskIds() {
        TaskService taskService = this.processEngine.getTaskService();

        StringBuilder b = new StringBuilder();
        taskService.createTaskQuery().active().list().forEach((t) -> b.append(t).append("\n"));

        return b.toString();
    }

    @GetMapping(path = "/tasks/{id}/form-variables")
    private String taskForms(@PathVariable String id) {
        FormService formService = this.processEngine.getFormService();
        TaskFormData taskFormData = formService.getTaskFormData(id);

        StringBuilder b = new StringBuilder();
        List<FormField> formFields = taskFormData.getFormFields();
        formFields.forEach((f) -> b.append("[").append(f.getLabel()).append("=").append(f.getValue()).append("]\n"));

        return b.toString();
    }

    @GetMapping(path = "/tasks/{id}/variables")
    private String taskVariables(@PathVariable String id) {
        TaskService taskService = this.processEngine.getTaskService();

        StringBuilder b = new StringBuilder();
        taskService.getVariables(id).forEach((k,v) -> b.append("[").append(k).append("=").append(v).append("]\n"));

        return b.toString();
    }
}
