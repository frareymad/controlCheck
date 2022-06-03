package acme.features.inventor.chimpum;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository chimpumRepo;
	protected InventorItemRepository itemRepo;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return true;
	}
	

	
	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
	}
	

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int itemId = request.getModel().getInteger("itemId");
		model.setAttribute("itemId", itemId);
		request.unbind(entity, model, "code", "title", "description", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
		
	}
	
	@Override
	public Chimpum instantiate (final Request<Chimpum> request) {
		assert request != null;
		
		
		
		
		final Chimpum res;
		res = new Chimpum();
		Date ini;
		Date end;
		
		ini = DateUtils.addMonths(new Date(System.currentTimeMillis() + 300000), 1);
		end = DateUtils.addMonths(ini, 1);
		end = DateUtils.addMinutes(end, 1);
		
		final Date moment;
        moment = new Date(System.currentTimeMillis() - 1);
        res.setCreationMoment(moment);
		
		res.setStartPeriodOfTime(ini);
		res.setEndPeriodOfTime(end);
		
		return res;
		
		
		}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Chimpum existing = this.chimpumRepo.findChimpumByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.chimpum.form.error.duplicated");
			
		
		}
		
		if(!errors.hasErrors("budget")) {
			final boolean accepted = this.chimpumRepo.findAcceptedCurrencies().matches("(.*)" + entity.getBudget().getCurrency()+ "(.*)");
			errors.state(request, accepted, "budget", "inventor.item.form.error.currency");
			errors.state(request, entity.getBudget().getAmount()>0, "budget", "inventor.chimpum.form.error.negative");
		}
		if(!errors.hasErrors("startPeriodOfTime")) {
			final Date fecha1=DateUtils.addMonths(entity.getCreationMoment(), 1);
			errors.state(request,entity.getStartPeriodOfTime().after(fecha1), "startPeriodOfTime", "inventor.chimpum.form.error.soon-to-close");
				
			}
		if(!errors.hasErrors("endPeriodOfTime")) {
			final Date fecha2=DateUtils.addMonths(entity.getStartPeriodOfTime(), 1);
			errors.state(request,entity.getEndPeriodOfTime().after(fecha2), "endPeriodOfTime", "inventor.chimpum.form.error.minimum");
				
			}
	}
			
	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		final int itemId = request.getModel().getInteger("itemId");
		final Item item = this.chimpumRepo.findItemById(itemId);
		item.setChimpum(entity);
		
		this.chimpumRepo.save(entity);
		
		
	}



}
