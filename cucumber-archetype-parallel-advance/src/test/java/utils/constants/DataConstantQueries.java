package utils.constants;

public class DataConstantQueries {
	private DataConstantQueries() {

	}

	public static final String GET_EMAILS_QUERY_PREFIX = "/odata/FirmHouseholds?filter=contains(email, 'automation+";
	public static final String GET_EMAILS_QUERY = "')&select=id&top=10";
	private static final String BASE_GET_FIRM_HH_QUERY = "/odata/FirmHouseholds?filter=contains(email, 'automation+%s')&select=id&top=10";
	public static final String PATH_TO_LOGIN = "/api/auth/login";
	public static final String PATH_CATEGORY_CREATE = "/api/category-type/create";
	public static final String PATH_DELETE_CATEGORY_TYPE = "/api/category-type/delete/";
	public static final String QUERY_TO_GET_ALL_CATEGORIES = "/api/category-type/";
}
