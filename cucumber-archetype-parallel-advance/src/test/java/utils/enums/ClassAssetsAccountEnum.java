package utils.enums;

public enum ClassAssetsAccountEnum {
    Taxable("entities.householdAssets.HouseholdPayloadAccounts");
    private final String classPath;

    private ClassAssetsAccountEnum(String classPath) {
        this.classPath = classPath;
    }

    public String getClassPath() {
        return classPath;
    }
}
