package utils.constants;

import static utils.HelperMethods.currentDateFull;

public class DataConstantQueries {
	private DataConstantQueries() {

	}

	public static final String QUERY_TO_GET_CURRENT_USER_FIRM_ID = "/odata/CurrentUser?select=firmId";
	public static final String QUERY_PARENT_GROUP = "/odata/Groups?select=id&filter=parentGroupId eq null";
	public static final String TEMPLATE_PATH = "/api/reporting/templates/";
	public static final String DELETE_MEMBER_PATH = "/api/Members/";
	public static final String RESET_PASSWORD_PATH = "/api/user/password";
	public static final String GET_MEMBER_PATH_QUERY_PRE = "/odata/Firms( ";
	public static final String QUERY_TO_GET_CURRENT_USER_ID = "/odata/CurrentUser?select=id";
	public static final String QUERY_TO_GET_GROUPS_BELONGED = "/Members?select=id,name&expand=groups(select=id,name)";
	public static final String QUERY_TO_GET_SPECIFIC_MEMBER = "?select=id,name,surname,EmailAddress&expand=groups(select=id,name)";
	public static final String QUERY_TO_GET_CURRENT_USER_PERMISSIONS = "/odata/CurrentUser?expand=firm,groups";
	public static final String QUERY_TO_GET_GROUPS = "/odata/Groups";
	public static final String QUERY_TO_GET_MEMBERS = "/Members?count=true&top=0";
	public static final String PATH_TO_CREATE_HH = "/api/Authentication/Register/Household";
	public static final String PATH_FOR_HH = "/api/households/";
	public static final String PATH_FOR_SCENARIO = "/api/planScenarios/";
	public static final String PATH_FOR_MEMBER = "/api/Members/";
	public static final String PATH_FOR_MODEL_ALLOCATIONS = "/api/firm/model-allocations/";
	public static final String DELETE_HH_PATH = "/api/households/";
	public static final String DELETE_FIRM_PATH = "/api/firm/";
	public static final String ACTUAL_FOLDER_PATH = "Screenshots";
	public static final String GET_HH_MFA_PATH_QUERY = "/odata/FirmHouseholds?filter=contains(email, 'automation+mfa')&select=id&top=10";
	public static final String QUERY_TO_GET_CURRENT_USER_INFO = "/odata/CurrentUser";
	public static final String EXPECTED_LIFE_HUB_PDF_PATH = "downloads/ExpectedPdf/ExpectedLifeHubPdf.pdf";
	public static final String ACTUAL_LIFE_HUB_PDF_PATH = "downloads/ActualPdf/Life_Hub_-_" + currentDateFull().split("([A-Z])\\w")[0] + ".pdf";
	public static final String DELETE_INTEGRATION_PATH = "api/integration/User-Integrations/";
	public static final String GET_ASSETS_CLASS_TAXES_BY_NAME_QUERY = "/odata/TaxSettings/Default?select=id,isDefault,taxSettingAssets&expand=taxSettingAssets(select=id,assetType,assetName,ltcg,stcg,qualifiedDividends,ordinaryDividends,taxableInterest,taxExemptInterest,deferredGrowth)";
	public static final String GET_TEMPLATE_BY_NAME_QUERY = "/odata/Templates?filter=contains(Title, 'TemplateName')&select=id&top=10";
	public static final String GET_REPORT_BY_TEMPLATE_QUERY = "odata/Reports?expand=template(select=title)&filter=contains(Template/Title, 'TemplateName')";
	public static final String GET_MEMBER_PATH_QUERY = ")/Members?filter=contains(emailAddress, 'automation+Member')&select=id";
	public static final String GET_MEMBER_EDITED_PATH_QUERY = ")/Members?filter=contains(emailAddress, 'automation+memberEdited')&select=id";
	public static final String GET_MEMBER_DELETED_PATH_QUERY = ")/Members?filter=contains(emailAddress, 'automation+memberDeleted')&select=id";
	public static final String GET_MEMBER_CANCEL_PATH_QUERY = ")/Members?filter=contains(emailAddress, 'automation+memberCanceled')&select=id";
	public static final String GET_FIRM_SIGNUP_PATH_QUERY = "/odata/Firms?filter=contains(userEmail, 'automation+SignUp')&select=id&top=1";
	public static final String GET_IMPORTED_HH_QUERY = "/odata/FirmHouseholds?filter=contains(email,'jrosato+testrepeatingforbugfix')&select=id&top=10";
	public static final String GET_EMAILS_QUERY_PREFIX = "/odata/FirmHouseholds?filter=contains(email, 'automation+";
	public static final String GET_EMAILS_QUERY = "')&select=id&top=10";
	private static final String BASE_GET_FIRM_HH_QUERY = "/odata/FirmHouseholds?filter=contains(email, 'automation+%s')&select=id&top=10";
	public static final String GET_PLAN_BALANCE_SPENDING_CAPACITY_DASHBOARD_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "spendingCapacityDashboard");
	public static final String GET_PLAN_OTHER_INCOME_TAX_TREATMENT_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "otherIncomeTaxTreatment");
	public static final String GET_PLAN_INVESTMENT_COST_BASIS_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "investmentCostBasis");
	public static final String GET_PLAN_BALANCE_DASHBOARD_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "balanceDashboard");
	public static final String GET_PLAN_ADJUSTMENT_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "adjustmentDashboard");
	public static final String GET_PLAN_DASHBOARD_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "planDashboard");
	public static final String GET_PLAN_INCOME_S_SECURITY_ALL_BENEFIT_METHODS_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "ssAllBenefitsMethods");
	public static final String GET_PLAN_INCOME_S_SECURITY_ESTIMATE_PRIMARY_INSURANCE_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "ssEstimatePrimaryInsurance");
	public static final String GET_PLAN_INCOME_S_SECURITY_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "planIncomeSocialSecurity");
	public static final String GET_ONLY_LIFE_HUB_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "onlyLifeHub");
	public static final String GET_LIFE_HUB_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "lifeHub");
	public static final String GET_REPORT_CENTER_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "reportCenter");
	public static final String GET_HH_RESET_PASSWORD_PATH_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "Reset");
	public static final String GET_HH_ACTIVATE_LINK_PATH_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "activateLink");
	public static final String GET_TAX_CENTER_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "taxLab");
	public static final String GET_HH_MODEL_ALLOCATION_FROM_SETTINGS_PATH_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "modelAllocationFromSettings");
	public static final String GET_HH_MODEL_ALLOCATION_FROM_HH_PATH_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "modelAllocationFromHh");
	public static final String GET_HH_MODEL_ALLOCATION_PATH_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "modelallocations");
	public static final String GET_PORTFOLIO_OTHER_ASSET_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "planPortfolioOtherAssets");
	public static final String GET_PORTFOLIO_BANKING_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "planPortfolioBanking");
	public static final String GET_INVESTMENT_FROM_WARNINGS_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "portfolioInvestmentWarningsAWS");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_ROTH_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "distributionRoth");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_QUALIFIED_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "distributionQualifiedRetirement");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_OTHER_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "distributionOther");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_TAXABLE_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "distributionTaxable");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_INHERITED_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "distributionInherited");
	public static final String GET_FIRM_DISTRIBUTION_SETTINGS_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "firmDistributionSettings");
	public static final String GET_INVESTMENT_FROM_SIDE_PANEL_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "portfolioInvestmentSidePanelAWS");
	public static final String GET_INVESTMENT_FROM_HH_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "portfolioInvestmentHHInfoAWS");
	public static final String GET_PORTFOLIO_INVESTMENT_EMAILS_QUERY = String.format(BASE_GET_FIRM_HH_QUERY, "planPortfolioInvestment");
	public static final String PATH_TO_LOGIN = "/api/auth/login";
	public static final String PATH_CATEGORY_CREATE = "/api/category-type/create";
	public static final String PATH_DELETE_CATEGORY_TYPE = "/api/category-type/delete/";
	public static final String QUERY_TO_GET_ALL_CATEGORIES = "/api/category-type/";
}
