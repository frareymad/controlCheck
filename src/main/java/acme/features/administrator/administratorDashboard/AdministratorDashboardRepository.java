package acme.features.administrator.administratorDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//COMPOMENTS ===============================================================
	@Query("SELECT COUNT(i) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.COMPONENT")
	int getNumberOfComponents();

	@Query("SELECT  i.technology, i.retailPrice.currency,AVG(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getAverageRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,STDDEV(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getDeviationRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,MIN(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getMinimumRetailPriceOfComponentsByTechnologyAndCurrency();

	@Query("SELECT  i.technology, i.retailPrice.currency,MAX(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.COMPONENT GROUP BY  i.technology, i.retailPrice.currency")
	List<Object[]> getMaximumRetailPriceOfComponentsByTechnologyAndCurrency();


	//TOOLS =======================================================================
	@Query("SELECT COUNT(i) FROM Item i WHERE i.itemType = acme.entities.item.ItemType.TOOL")
	int getNumberOfTools();

	@Query("SELECT  i.retailPrice.currency,AVG(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY  i.retailPrice.currency")
	List<Object[]> getAverageRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,STDDEV(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY  i.retailPrice.currency")
	List<Object[]> getDeviationRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,MIN(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY   i.retailPrice.currency")
	List<Object[]> getMinimumRetailPriceOfToolsByCurrency();

	@Query("SELECT  i.retailPrice.currency,MAX(i.retailPrice.amount) FROM Item i WHERE  i.itemType = acme.entities.item.ItemType.TOOL GROUP BY   i.retailPrice.currency")
	List<Object[]> getMaximumRetailPriceOfToolsByCurrency();




	//PATRONAGES ================================================================

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.ACCEPTED")
	int getTotalNumberOfAcceptedPatronages();

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.DENIED")
	int getTotalNumberOfDeniedPatronages();

	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = acme.entities.patronage.PatronageStatus.PROPOSED")
	int getTotalNumberOfProposedPatronages();

	@Query("SELECT  p.status, AVG(p.budget.amount), p.budget.currency FROM Patronage p GROUP BY  p.status")
	List<Object[]> getAverageBudgetOfPatronagesByStatus();

	@Query("SELECT  p.status, STDDEV(p.budget.amount), p.budget.currency FROM Patronage p GROUP BY  p.status")
	List<Object[]> getDeviationBudgetOfPatronagesByStatus();

	@Query("SELECT  p.status, MIN(p.budget.amount), p.budget.currency FROM Patronage p GROUP BY  p.status")	
	List<Object[]> getMinimumBudgetOfPatronagesByStatus();

	@Query("SELECT  p.status, MAX(p.budget.amount), p.budget.currency FROM Patronage p GROUP BY  p.status")
	List<Object[]> getMaximumBudgetOfPatronagesByStatus();
	
	
	
	//EJERCICIO 3
	//CHIMPUM ====================================================================
	
	@Query("select COUNT(i.chimpum) from Item i  where i.itemType = acme.entities.item.ItemType.TOOL")
	int getNumberOfChimpum();
	
	@Query("SELECT  i.chimpum.budget.currency,AVG(i.chimpum.budget.amount) FROM Item i WHERE  i.itemType = 1 GROUP BY  i.chimpum.budget.currency")
	List<Object[]> getAverageBudgetOfChimpumsByCurrency();

	@Query("SELECT  i.chimpum.budget.currency,STDDEV(i.chimpum.budget.amount) FROM Item i WHERE  i.itemType = 1 GROUP BY  i.chimpum.budget.currency")
	List<Object[]> getDeviationBudgetOfChimpumsByCurrency();

	@Query("SELECT  i.chimpum.budget.currency,MIN(i.chimpum.budget.amount) FROM Item i WHERE  i.itemType = 1 GROUP BY  i.chimpum.budget.currency")
	List<Object[]> getMinimumBudgetOfChimpumsByCurrency();

	@Query("SELECT  i.chimpum.budget.currency,MAX(i.chimpum.budget.amount) FROM Item i WHERE  i.itemType = 1 GROUP BY  i.chimpum.budget.currency")
	List<Object[]> getMaximumBudgetOfChimpumsByCurrency();
	
	
	
	
	
	
	
	
	
	
	@Query("select sc.systemCurrency from SystemConfiguration sc ")
	String findSystemCurrency();



}
