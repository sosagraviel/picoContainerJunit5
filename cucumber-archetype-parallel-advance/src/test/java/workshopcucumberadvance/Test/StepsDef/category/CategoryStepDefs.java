package workshopcucumberadvance.Test.StepsDef.category;

import workshopcucumberadvance.endpoints.category.CategoryTypeEndpoint;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import utils.DriverFactory;
import utils.RequestFactory;
import utils.constants.DataConstant;
import utils.context.ScenarioContextInfoHolder;

public class CategoryStepDefs {
	private Response responseDeleteCategory;
	CategoryTypeEndpoint categoryTypeEndpoint;
	ScenarioContextInfoHolder context;
	RequestFactory requestFactory;
	DriverFactory driverFactory;

	public CategoryStepDefs(ScenarioContextInfoHolder context, RequestFactory requestFactory, DriverFactory driverFactory) {
		this.context = context;
		this.requestFactory = requestFactory;
		this.driverFactory = driverFactory;
		this.categoryTypeEndpoint = new CategoryTypeEndpoint(requestFactory);
	}

	@When("^User adds a new \"([^\"]*)\" category$")
	public void userAddsANewCategory(String categoryType) {
		Response categoryResponse = categoryTypeEndpoint.addCategory();
		this.context.setScenarioContext(DataConstant.CURRENT_CATEGORY_ID, JsonPath.from(categoryResponse.asString()).getString("id"));
	}

	@And("^User wipes the old categories$")
	public void userWipesTheCategories() {
		categoryTypeEndpoint.wipeOldCategories();
	}

	@And("^User deletes the current category$")
	public void userDeletesTheCurrentCategory() {
		String categoryId = (String) categoryTypeEndpoint.getCurrentCategory().get("id");
		responseDeleteCategory = categoryTypeEndpoint.deleteCategory(categoryId);
	}

	@Then("^the category was delete correctly$")
	public void theCategoryWasDeleteCorrectly() {
		Assertions.assertThat(responseDeleteCategory.getStatusCode())
						.withFailMessage("The status code expected was" + DataConstant.STATUS_200 + " the current was " + responseDeleteCategory.getStatusCode()).isEqualTo(DataConstant.STATUS_200);
	}

	@Then("^the category page is displayed$")
	public void theCategoryPageIsPresent() {
		this.driverFactory.getCategoryPage().isCategoryPageDisplayed();
	}

	@When("^User clicks Add category button$")
	public void userClicksAddCategoryButton() {
		this.driverFactory.getCategoryPage().clickCategoryAddButton();
	}

	@Then("^The category modal is displayed$")
	public void theCategoryModalIsDisplayed() {
		this.driverFactory.getCategoryPage().isAddCategoryModalDisplayed();
	}

	@When("^User enters the category information$")
	public void userEntersTheCategoryInformation() {
		this.driverFactory.getCategoryPage().createCategory();
	}

	@Then("^the category was created correctly$")
	public void theCategoryWasCreatedCorrectly() {
		this.driverFactory.getCategoryPage().isCategoryDisplayed();
	}

	@When("^User deletes the category \"([^\"]*)\"$")
	public void userDeletesTheCategory(boolean deleteCategory) {
		this.driverFactory.getCategoryPage().deleteCategory(deleteCategory);
	}

	@Then("^the category was delete UI$")
	public void theCategoryWasDeleteUI() {
		this.driverFactory.getCategoryPage().isListOfCategoryEmpty();
	}

	@When("^User updates the category \"([^\"]*)\"$")
	public void userUpdatesCategory(boolean updateCategory) {
		this.driverFactory.getCategoryPage().updateCategory(updateCategory);
	}

	@Then("^the category is correctly updated$")
	public void categoryWasUpdated() {
		this.driverFactory.getCategoryPage().isCategoryDisplayed();
	}
}
