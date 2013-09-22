package dojo.bpm.camunda.coffee;

import java.util.List;

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
import org.camunda.bpm.engine.task.Task;

public class CamundaService {

	ProcessEngine processEngine;
	RuntimeService runtimeService;
	TaskService taskService;
	RepositoryService repositoryService;
	ManagementService managementService;
	IdentityService identityService;
	HistoryService historyService;
	FormService formService;

	private static CamundaService mySingelton = null;

	public static CamundaService getInstance() {
		if (mySingelton == null)
			mySingelton = new CamundaService();
		return mySingelton;
	}

	// http://docs.camunda.org/guides/user-guide/#process-engine-process-engine-concepts-querying-for-process-instances
	private CamundaService() {
		this.processEngine = ProcessEngines.getDefaultProcessEngine();

		this.runtimeService = processEngine.getRuntimeService();
		this.repositoryService = processEngine.getRepositoryService();
		this.taskService = processEngine.getTaskService();
		this.managementService = processEngine.getManagementService();
		this.identityService = processEngine.getIdentityService();
		this.historyService = processEngine.getHistoryService();
		this.formService = processEngine.getFormService();
	}

	private void repoService() {
		// repositoryService.activateProcessDefinitionById(processDefinitionId);
		// repositoryService.activateProcessDefinitionById(processDefinitionId,
		// activateProcessInstances, activationDate)
		// repositoryService.activateProcessDefinitionByKey(processDefinitionKey)
		// repositoryService.activateProcessDefinitionByKey(processDefinitionKey,
		// activateProcessInstances, activationDate)

		// repositoryService.createProcessDefinitionQuery();
		// repositoryService.getProcessDefinition(processDefinitionId);
		// repositoryService.suspendProcessDefinitionById(processDefinitionId);
	}

	private void mngmtService() {
		// managementService.deleteJob(jobId);
		// managementService.executeJob(jobId);
	}

	private void identityService() {
		// identityService.createUserQuery();
		// identityService.getUserInfo(userId, key);
	}

	private void historyService() {
		// historyService.createHistoricActivityInstanceQuery()
	}

	private void formService() {
		// formService.getRenderedStartForm(processDefinitionId)
		// formService.getStartFormData(processDefinitionId);
		// formService.getTaskFormData(taskId)
	}

	public void printTaskListFor(String customer) {
		List<Task> taskList = getTasksListBy("customerId", customer);
		System.out.println("####### TasksListe iterieren #######");
		for (Task t : taskList) {
			System.out.println("Task ID: " + t.getId());
			System.out.println("Task Definition Key: "
					+ t.getTaskDefinitionKey());
			System.out.println("Task Name: " + t.getName());
			System.out.println("Task Owner: " + t.getOwner());
			System.out.println("Task Bearbeiter: " + t.getAssignee());
			System.out.println("Task Creation Time: " + t.getCreateTime());
			System.out.println("Task Frist Ende: " + t.getDueDate());
			System.out.println("Prozess Definition ID: "
					+ t.getProcessDefinitionId());
		}
		System.out.println("####### TasksListe iterieren fertig #######");
	}

	public List<Task> getTasksListBy(String key, String value) {
		return taskService.createTaskQuery()
				.processVariableValueEquals(key, value).list();
	}

	public void printInfosOfProcesses(String process, String key, String value) {
		List<ProcessInstance> processList = getProcessInstanceListBy(process,
				key, value);
		System.out
				.println("####### iteriere über alle Prozesse in Liste #######");
		for (ProcessInstance p : processList) {
			System.out.println("ProzessDefinitionID: "
					+ p.getProcessDefinitionId());
			System.out.println("id: " + p.getId());
		}
		System.out.println("####### iterieren fertig #######");
	}

	public List<ProcessInstance> getProcessInstanceListBy(String process,
			String key, String value) {
		return this.runtimeService.createProcessInstanceQuery()
				.processDefinitionKey(process).variableValueEquals(key, value)
				.list();

	}
}
