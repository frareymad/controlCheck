package acme.features.inventor.chimpum;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;


@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor,Chimpum>{

	@Autowired
	protected InventorChimpumRepository chimpumRepo;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		final boolean result;
		int chimpumId;
		final Item item;
		
		chimpumId = request.getModel().getInteger("id");
		item = this.chimpumRepo.findItemByChimpumId(chimpumId);
		
		final int inventorId = request.getPrincipal().getActiveRoleId();
		
		result = !item.isPublished() && item.getInventor().getId() == inventorId;
		
		return result;
		
		
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
		
		model.setAttribute("id", entity.getId());
		
		request.unbind(entity, model, "code", "title", "description", "budget", "startPeriodOfTime", "endPeriodOfTime", "link");
		
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		Chimpum result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.chimpumRepo.findChimpumById(id);
		return result;
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
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		final int id = request.getModel().getInteger("id");
		final Item item = this.chimpumRepo.findItemByChimpumId(id);
		item.setChimpum(null);
		
		this.chimpumRepo.delete(entity);
		
	}

}
