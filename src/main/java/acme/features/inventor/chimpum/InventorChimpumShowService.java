package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{
	// Internal state --------------------------------------------------
	
		@Autowired
		protected InventorChimpumRepository chimpumRepo;
		
		// AbstractShowService<Inventor, Item> interface --------------------
		
		@Override
		public boolean authorise(final Request<Chimpum> request) {
			assert request != null;
			Integer id;
			Chimpum chimpum;
			final boolean result;
			id = request.getModel().getInteger("id");
			chimpum = this.chimpumRepo.findChimpumById(id);
			
			//result = request.getPrincipal().getActiveRoleId() == chimpum.getInventor().getId();
			//return result;
			return true;
		}

		@Override
		public Chimpum findOne(final Request<Chimpum> request) {
			assert request != null;
			
			Integer id;
			Chimpum chimpum;
			id = request.getModel().getInteger("id");
			chimpum = this.chimpumRepo.findChimpumById(id);
			return chimpum;
		}

		@Override
		public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			final int itemId  = request.getModel().getInteger("id");
			final String targetCurrency = this.chimpumRepo.findSystemCurrency();
			final Money actualCurrency = this.chimpumRepo.findChimpumPriceById(itemId);
				
			
			
			final MoneyExchange change = moneyExchange.computeMoneyExchange(actualCurrency, targetCurrency);
			final Money result = change.getTarget();
			
			
			model.setAttribute("priceInSC", result);
			
			final int id = request.getModel().getInteger("id");
            final Item item = this.chimpumRepo.findItemByChimpumId(id);

            model.setAttribute("published", item.isPublished());
 
			request.unbind(entity, model, "code", "title", "description", "budget", "creationMoment", "startPeriodOfTime", "endPeriodOfTime", "link");
			
		}
		
		

}

