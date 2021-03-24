package com.github.hqxqyly.remex.boot.encrypt;

import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 加密 @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 * 
 * @author Qiaoxin.Hong
 *
 */
public class RemexBCryptPasswordEncoder {

	private Pattern BCRYPT_PATTERN = Pattern
			.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
	private final Log logger = LogFactory.getLog(getClass());

	private final int strength;

	private final SecureRandom random;

	public RemexBCryptPasswordEncoder() {
		this(-1);
	}

	/**
	 * @param strength the log rounds to use, between 4 and 31
	 */
	public RemexBCryptPasswordEncoder(int strength) {
		this(strength, null);
	}

	/**
	 * @param strength the log rounds to use, between 4 and 31
	 * @param random the secure random instance to use
	 *
	 */
	public RemexBCryptPasswordEncoder(int strength, SecureRandom random) {
		if (strength != -1 && (strength < RemexBCrypt.MIN_LOG_ROUNDS || strength > RemexBCrypt.MAX_LOG_ROUNDS)) {
			throw new IllegalArgumentException("Bad strength");
		}
		this.strength = strength;
		this.random = random;
	}

	public String encode(CharSequence rawPassword) {
		String salt;
		if (strength > 0) {
			if (random != null) {
				salt = RemexBCrypt.gensalt(strength, random);
			}
			else {
				salt = RemexBCrypt.gensalt(strength);
			}
		}
		else {
			salt = RemexBCrypt.gensalt();
		}
		return RemexBCrypt.hashpw(rawPassword.toString(), salt);
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			logger.warn("Empty encoded password");
			return false;
		}

		if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
			logger.warn("Encoded password does not look like BCrypt");
			return false;
		}

		return RemexBCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}
}
