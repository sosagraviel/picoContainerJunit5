package workshopcucumberadvance.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BodyPayload {

	/**
	 * name : John
	 */

	@JsonProperty("name")
	private String name;

	public BodyPayload(String name) {
		this.setName(name);
	}
}
