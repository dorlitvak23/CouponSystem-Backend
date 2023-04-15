package app.core.security;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.exceptions.CouponsSystemException;
import app.core.login.ClientType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenManager {

	private final Map<UUID, Information> tokens;

	public UUID addToken(Information information) {
		deleteToken(information.getId());
		UUID newToken = UUID.randomUUID();
		while (tokens.containsKey(newToken)) {
			newToken = UUID.randomUUID();
		}
		tokens.put(newToken, information);
		return newToken;
	}

	private void deleteToken(int id) {
		tokens.entrySet().removeIf((token) -> token.getValue().getId() == id);
	}

	private int getUserIdAndvalidateToken(UUID token, ClientType clientType) throws CouponsSystemException {
		Information information = tokens.get(token);
		if (information == null) {
			throw new CouponsSystemException("Not valid token");
		}
		if (information.getClientType() != clientType) {
			throw new CouponsSystemException("Unauthorized Action");
		}
		return information.getId();
	}

	public int getAdminId(UUID token) throws CouponsSystemException {
		return getUserIdAndvalidateToken(token, ClientType.ADMIN);
	}

	public int getCompanyId(UUID token) throws CouponsSystemException {
		return getUserIdAndvalidateToken(token, ClientType.COMPANY);
	}

	public int getCustomerId(UUID token) throws CouponsSystemException {
		return getUserIdAndvalidateToken(token, ClientType.CUSTOMER);
	}

	@Scheduled(fixedRate = 1000 * 60)
	private void deleteExpiredTokens() {
		tokens.entrySet().removeIf((token) -> token.getValue().getExpirationTime().isBefore(LocalDateTime.now()));

	}

}
