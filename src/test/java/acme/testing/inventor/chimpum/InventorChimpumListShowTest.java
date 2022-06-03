package acme.testing.inventor.chimpum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorChimpumListShowTest extends TestHarness {

	// Test cases ---------------------------------------------------
	
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/chimpum/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String code, 
			final String title, final String description, final String budget, final String link) {
			super.signIn("inventor2", "inventor2");
			
			super.clickOnMenu("Inventor", "List my tools");
			super.checkListingExists();
			super.sortListing(0, "desc");
			super.clickOnListingRecord(0);
			super.clickOnButton("List chimpums");
			
			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			super.checkColumnHasValue(recordIndex, 2, budget);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("budget", budget);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
			
		}
}
