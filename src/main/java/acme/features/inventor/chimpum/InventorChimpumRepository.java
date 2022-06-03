package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {
	
	@Query("SELECT i.chimpum FROM Item i WHERE i.id = :id")
	Collection<Chimpum> findChimpumsByItemId(int id);
	
	@Query("SELECT i FROM Chimpum i WHERE i.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("SELECT sc.systemCurrency from SystemConfiguration sc ")
	String findSystemCurrency();
	
	@Query("SELECT i.budget from Chimpum i where i.id= :id")
	Money findChimpumPriceById(int id);
	
	@Query("SELECT i FROM Chimpum i WHERE i.code = :code")
	Chimpum findChimpumByCode(String code);
	
	@Query("SELECT sc.acceptedCurrencies FROM SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.chimpum.id = :chimpumId")
	Item findItemByChimpumId(int chimpumId);
}
