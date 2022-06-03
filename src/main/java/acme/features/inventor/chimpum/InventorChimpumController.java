package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor,Chimpum> {
	// Internal State --------------------------------------------------------------------------
	
		@Autowired
		protected InventorChimpumListService listChimpumService;
		
		@Autowired
		protected InventorChimpumShowService showChimpumService;
		
		@Autowired
		protected InventorChimpumCreateService createChimpumService;
		
		@Autowired
		protected InventorChimpumDeleteService deleteChimpumService;
		
		@Autowired
		protected InventorChimpumUpdateService updateChimpumService;
		
	// Constructors ----------------------------------------------------------------------------
		
		@PostConstruct
		protected void initialize() {
			super.addCommand("list", this.listChimpumService);
			super.addCommand("show", this.showChimpumService);
			super.addCommand("create", this.createChimpumService);
			super.addCommand("delete", this.deleteChimpumService);
			super.addCommand("update", this.updateChimpumService);
		
		}

}

