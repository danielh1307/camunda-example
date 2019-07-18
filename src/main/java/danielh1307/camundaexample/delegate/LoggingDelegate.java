package danielh1307.camundaexample.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LoggingDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("I am just logging something. My hash code is " + hashCode());

        delegateExecution.setVariable("loggingDelegateOutput", String.valueOf(hashCode()));
    }
}
