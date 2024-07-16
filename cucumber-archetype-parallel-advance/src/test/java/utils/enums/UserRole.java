package utils.enums;

public enum UserRole {
	superAdmin,normalAccount,
	firmAdminEnterprise,
	advisorEnterprise,
	firmAdmin,
	firm,
	advisor,
	household,
	householdOnlyLifeHub,
	firmLegacyMfa,
	advLegacyMfa,
	hhLegacyMfa,
	firmFlat, firmFlatCancelMember, firmFlatDeleteMember, firmFlatEditMember,
	firmAdminEdit,
	firmAdvisorEdit, firmModelAllocationFromSettings, firmModelAllocationFromHh,
	advisorEdit, advisorEditForgotPassword,
	householdEdit,
	firmSettings, firmSettingsMktNearTerm, firmSettingsMktTraditional, firmSettingsMktLongTerm,
	firmDistributionSettings, distributionRoth, firmDistributionSettingsTraditional,
	distributionQualifiedRetirement, distributionInherited,
	distributionTaxable, distributionOther,
	firmAdvisor, firmAdvisorInvestment, firmAdvisorInvestmentSidePanel, ssEstimatePrimaryInsurance, ssAllBenefitsMethods,
	firmImportHousehold,
	firmOnlyLifeHub,
	firmEngine, accountIncluded, costBasis, otherIncomeTaxTreatment, firmOtherAssets
}
