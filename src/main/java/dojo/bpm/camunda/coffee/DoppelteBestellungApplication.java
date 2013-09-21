package dojo.bpm.camunda.coffee;

import java.util.List;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@ProcessApplication("Kaffee Bestellung App")
public class DoppelteBestellungApplication extends ServletProcessApplication {

	public void findeDoppelteInstanz(String auftraggeber) {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		RuntimeService runtimeService = processEngine.getRuntimeService();
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		TaskService taskService = processEngine.getTaskService();
		ManagementService managementService = processEngine
				.getManagementService();
		IdentityService identityService = processEngine.getIdentityService();
		HistoryService historyService = processEngine.getHistoryService();
		FormService formService = processEngine.getFormService();

		// http://docs.camunda.org/guides/user-guide/#process-engine-process-engine-concepts-activity-instances
		// runtimeService.setVariableLocal(name, value);

		List<ProcessInstance> prozesse = findeProzesse(runtimeService,
				"bestellprozess", "auftraggeber", "robert");

		if (existierenWeitereInstanzen(prozesse)) {
			System.out.println("ja");
		}
	}

	private List<ProcessInstance> findeProzesse(RuntimeService runtimeService,
			String prozess, String key, String value) {

		// http://docs.camunda.org/guides/user-guide/#process-engine-process-engine-concepts-querying-for-process-instances
		return runtimeService.createProcessInstanceQuery()
				.processDefinitionKey(prozess).variableValueEquals(key, value)
				.list();

	}

	private boolean existierenWeitereInstanzen(List<ProcessInstance> prozesse) {
		boolean result = false;

		if (prozesse.size() > 1) {
			result = true;
		}

		for (ProcessInstance p : prozesse) {
			System.out.println(p.getProcessDefinitionId());
			System.out.println(p.getId());
		}

		return result;
	}
}
