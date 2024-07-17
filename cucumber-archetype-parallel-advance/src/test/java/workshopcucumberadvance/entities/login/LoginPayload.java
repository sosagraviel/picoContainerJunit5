package workshopcucumberadvance.entities.login;

import com.google.gson.annotations.Expose;
import lombok.Data;
import utils.constants.DataConstant;
import utils.enums.RegisterAccounts;

@Data
public class LoginPayload {
	@Expose
	private String email;
	@Expose
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
