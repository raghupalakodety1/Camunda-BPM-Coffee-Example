package dojo.bpm.camunda.coffee.pflegeprozess;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import dojo.bpm.camunda.coffee.Constants;
import dojo.bpm.camunda.coffee.dao.KaffeeDao;
import dojo.bpm.camunda.coffee.model.Kaffee;


public class LoadCoffeeRequestDelegate implements JavaDelegate {
	
	private KaffeeDao kaffeeDao;
	private final static Logger LOGGER = Logger.getLogger("KaffeedatenPflege");

	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("Processing request by '"
				+ execution.getVariable("customerId") + "'...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CoffeeDSfachlich");
		kaffeeDao = new KaffeeDao(emf.createEntityManager());
		List<Kaffee> kaffees = kaffeeDao.getAllKaffees();
		LOGGER.info("Kaffees geladen: " + kaffees.size());
		execution.setVariable(Constants.KAFFEE_PROZESS_VARIABLE,
				getStringRepresentationForKaffeeList(kaffees));
		LOGGER.info("Kaffees gespeichert ");
	}

	private String getStringRepresentationForKaffeeList(List<Kaffee> kaffees) {
		String kaffeeListe = "";
		for (Kaffee kaffee : kaffees) {
			kaffeeListe += kaffee.getId() + ",";
			kaffeeListe += kaffee.getName() + ",";
			kaffeeListe += kaffee.getPreis() + ";";

		}
		LOGGER.info("Kaffeeliste: " + kaffeeListe);
		return kaffeeListe;

	}

}
