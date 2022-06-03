package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum>{
	
	// Internal state ------------------------------------------------
	
		@Autowired
		protected InventorChimpumRepository chimpumRepo;
		

		
		@Override
		public boolean authorise(final Request<Chimpum> request) {
			assert request != null;
			return true;
		}

		@Override
		public Collection<Chimpum> findMany(final Request<Chimpum> request) {
			final Collection <Chimpum> result;
			final int itemId = request.getModel().getInteger("itemId");
			result = this.chimpumRepo.findChimpumsByItemId(itemId);
			return result;
		}
		@Override
		public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			
			request.unbind(entity, model, "code", "title", "description", "budget", "creationMoment", "startPeriodOfTime", "endPeriodOfTime", "link");
		}

}

