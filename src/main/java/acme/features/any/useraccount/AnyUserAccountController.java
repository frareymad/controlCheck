package acme.features.any.useraccount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;


@Controller
public class AnyUserAccountController extends AbstractController<Any, UserAccount> {
	
	//Internal state --------------------------------------------------------------
		@Autowired
		protected AnyUserAccountListService 	listService;
		
		//Constructors ----------------------------------------------------------------
		
		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
		}

}