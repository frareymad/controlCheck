package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, 
		final String title, final String description, final String budget, final String link) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "List my tools");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("List chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update chimpum");
		
		super.clickOnMenu("Inventor", "List my tools");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("List chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String code, 
		final String title, final String description, final String budget, final String link) {
		
		super.signIn("inventor2", "inventor2");
		super.clickOnMenu("Inventor", "List my tools");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("List chimpums");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update chimpum");
		super.checkErrorsExist();
		
	}
		
	
}


