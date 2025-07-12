package workshopcucumberadvance.entities.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryType {
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("parentId")
	private Object parentId;
	@JsonProperty("parentName")
	private Object parentName;
	@JsonProperty("root")
	private Boolean root;

	public CategoryType(String categoryTypeId, String name) {
		this.setId(categoryTypeId);
		this.setName(name);
		this.setRoot(true);
		this.setParentId(null);
		this.setParentName(null);
	}
}
