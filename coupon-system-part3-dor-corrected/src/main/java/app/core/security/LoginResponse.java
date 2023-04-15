package app.core.security;

import java.util.UUID;

import app.core.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private UUID token;
	private int id;
	private String name;
	private String email;
	private ClientType clientType;

}
