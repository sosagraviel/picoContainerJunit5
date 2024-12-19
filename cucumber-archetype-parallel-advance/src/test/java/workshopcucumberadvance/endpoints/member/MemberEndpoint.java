package workshopcucumberadvance.endpoints.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import workshopcucumberadvance.entities.category.CategoryTypePayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.RequestFactory;
import utils.constants.DataConstantQueries;

public class MemberEndpoint {
	protected RequestFactory requestFactory;

	public MemberEndpoint(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	/**
	 * A method to add a category using the provided categoryParentId.
	 *
	 * @param categoryParentId the parent ID of the category
	 * @return the response after adding the category
	 */
	public Response addCategory(String categoryParentId) {
		CategoryTypePayload categoryPayload = new CategoryTypePayload("", categoryParentId);
		return getResponseCategoryType(categoryPayload);
	}

	/**
	 * A method to add a category without using the provided categoryParentId.
	 *
	 * @return the response after adding the category
	 */
	public Response addCategory() {
		CategoryTypePayload categoryPayload = new CategoryTypePayload("");
		return getResponseCategoryType(categoryPayload);
	}

	/**
	 * A method to get the response for the category type payload.
	 *
	 * @param categoryPayload the payload for the category type
	 * @return the response after processing the category payload
	 */
	private Response getResponseCategoryType(CategoryTypePayload categoryPayload) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Response response = this.requestFactory.makeRequest().body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryPayload))
							.post(DataConstantQueries.PATH_CATEGORY_CREATE);
			return response;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * Deletes a category with the given categoryTypeId.
	 *
	 * @param categoryTypeId the ID of the category to be deleted
	 * @return Two hundred if the deletion is successful, otherwise 400
	 */
	public int deleteCategory(Long categoryTypeId) {
		return JsonPath.from(this.requestFactory.makeRequest().body("{}")
						.delete(DataConstantQueries.PATH_DELETE_CATEGORY_TYPE + categoryTypeId.toString()).asString()).getBoolean("success") ? 200 : 400;
	}

	/**
	 * Retrieves all categories from the API.
	 *
	 * @return the response object containing the list of categories
	 */
	public Response getAllCategories() {
		return this.requestFactory.makeRequest()
						.get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES);
	}

	/**
	 * Retrieves the category with the given ID from the API.
	 *
	 * @param categoryId the ID of the category to retrieve
	 * @return the response object containing the category with the given ID
	 */
	public Response getCategoryById(String categoryId) {
		return this.requestFactory.makeRequest()
						.get(DataConstantQueries.QUERY_TO_GET_ALL_CATEGORIES + categoryId);
	}
}
