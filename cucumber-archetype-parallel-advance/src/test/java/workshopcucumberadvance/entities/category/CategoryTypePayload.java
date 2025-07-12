package workshopcucumberadvance.entities.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryTypePayload {

  @JsonProperty("name")
  private String name;
  @JsonProperty("parentId")
  private String parentId;
  @JsonProperty("root")
  private Boolean root;

  public CategoryTypePayload(String categoryName) {
    this.setName(categoryName);
    this.setRoot(true);
  }

  public CategoryTypePayload(String categoryName, String categoryParentId) {
    this.setName(categoryName);
    this.setParentId(categoryParentId);
    this.setRoot(false);
  }
}
