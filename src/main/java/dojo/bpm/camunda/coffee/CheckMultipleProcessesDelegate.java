package dojo.bpm.camunda.coffee;

import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckMultipleProcessesDelegate implements JavaDelegate {

	// http://docs.camunda.org/guides/user-guide/#process-engine-process-engine-concepts-activity-instances
	// runtimeService.setVariableLocal(name, value);
	public void execute(DelegateExecution execution) throws Exception {

		String id = execution.getId();
		String procInstanceID = execution.getProcessInstanceId();
		String procBusinessKey = execution.getProcessBusinessKey();
		String procDefinitionId = execution.getProcessDefinitionId();

		String customer = (String) execution.getVariable("customerId");
		Map<String, Object> variableMap = execution.getVariables();
		Set<String> variableNames = execution.getVariableNames();

		String actInstanceID = execution.getActivityInstanceId();
		String currentActID = execution.getCurrentActivityId();
		String currentActName = execution.getCurrentActivityName();

		if (id.equals(procInstanceID))
			System.out.println("getId entspricht getProcessInstanceId");
		else
			System.out.println("getId ungleich getProcessInstanceId !!!!!!!!");

		System.out.println("ID: " + id);
		System.out.println("Prozess Instanz ID: " + procInstanceID);
		System.out.println("Prozess Business Key: " + procBusinessKey);
		System.out.println("Prozess Definition ID " + procDefinitionId);

		System.out.println("Wert der customerID: " + customer);
		System.out.println("Liste der PIVs: " + variableMap);
		System.out.println("Namen aller Variablen: " + variableNames);

		System.out.println("Aktivitäten Instanz ID: " + actInstanceID);
		System.out.println("aktuelle Aktivitäten ID: " + currentActID);
		System.out.println("aktueller Aktivitäten Name: " + currentActName);

		System.out.println("###### CamundaService ######");
		CamundaService.getInstance().printInfosOfProcesses("aufgabe-erhalten",
				"auftraggeber", customer);
		CamundaService.getInstance().printTaskListFor(customer);
	}
}
