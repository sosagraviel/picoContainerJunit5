package workshopcucumberadvance.entities.login;

import lombok.Data;
import utils.constants.DataConstant;
import utils.enums.RegisterAccounts;

@Data
public class LoginPayload {
	@com.nimbusds.jose.shaded.gson.annotations.Expose
	private String email;
	@com.nimbusds.jose.shaded.gson.annotations.Expose
	private String password;

	public LoginPayload(RegisterAccounts registerAccounts) {
		switch (registerAccounts) {
			case superAdmin -> {
				this.setEmail(DataConstant.EMAIL_SUPER_ADMIN);
				this.setPassword(DataConstant.PASSWORD);
			}
			case normalAccount -> {
				this.setEmail(DataConstant.EMAIL_ACCOUNT_ADMIN);
				this.setPassword(DataConstant.PASSWORD);
			}
		}
	}
}
