package workshopcucumberadvance.entities.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static utils.HelperMethods.currentDateFull;

@Data
public class MemberPayload {
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("categoryType")
	private CategoryType categoryType;
	@JsonProperty("document")
	private String document;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("startDate")
	private String startDate;

	public MemberPayload(CategoryType categoryType, String categoryTypeId, String name) {
		this.setFirstName("automation Member");
		this.setLastName("Sosa");
		this.setDocument("1234567");
		this.setActive(true);
		this.setStartDate(currentDateFull());
		this.setCategoryType(new CategoryType(categoryTypeId, name));
	}
}
