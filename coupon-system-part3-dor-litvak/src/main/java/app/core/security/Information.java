package app.core.security;

import java.time.LocalDateTime;

import app.core.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {

	private int id;
	private String email;
	private LocalDateTime expirationTime;
	private ClientType clientType;

}
