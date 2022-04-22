package acme.features.authenticated.inventor.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository{
	
	//Dado el id de un determinado inventor de un item lo comparo con el id del usuario logueado y veo que ese item sea de tipo COMPONENT
	
	@Query("SELECT i FROM Item i WHERE i.inventor.id = :id AND i.itemType = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findComponentsByInventorId(int id);
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	

}
