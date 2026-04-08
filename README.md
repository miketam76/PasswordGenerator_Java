# Password Generator

A small Java utility for generating and validating passwords and OTPs (One-Time Passwords).

This project provides a single, static-style enum class `PasswordGenerator` with methods to:

- Generate a numeric OTP (`generateOTP()`)
- Generate a random password that meets complexity rules (`generatePassword()`)
- Validate a password against the project's rules (`validatePassword(String)`).

## What's new (v2.0)

- The generator now uses `java.security.SecureRandom` for stronger randomness.
- `generatePassword()` guarantees the generated password contains at least:
  - 1 upper-case letter
  - 1 lower-case letter
  - 1 digit
  - 1 allowed special character (from the set `!@#%$^&_.-`)
- Improved generation logic and shuffling to avoid predictable placement of character classes.

## Requirements / Rules

- Java 8 or later
- Password length: between 8 and 14 characters (inclusive)
- Allowed special characters: `!@#%$^&_.-`

Constants (defined in the source):

- MIN_PASSWORD_LENGTH = 8
- MAX_PASSWORD_LENGTH = 14
- MAX_OTP_LENGTH = 6

Source file: `src\com\password_generator\PasswordGenerator.java`

## Public API

All methods are static on `PasswordGenerator`:

- `String generateOTP()`
  - Returns a 6-digit numeric OTP (digits 0-9).

- `String generatePassword()`
  - Returns a random password with length `MAX_PASSWORD_LENGTH` (14) that is guaranteed
    to include at least one upper-case letter, one lower-case letter, one digit and one special character.

- `boolean validatePassword(String pass)`
  - Returns `true` if `pass` is between 8 and 14 characters and contains at least one lower-case,
    one upper-case, one digit and one allowed special character.

## Example usage

Create a small test class to call the generator methods. Example `TestPasswordGenerator.java`:

```java
public class TestPasswordGenerator {
    public static void main(String[] args) {
        String otp = com.password_generator.PasswordGenerator.generateOTP();
        String pwd = com.password_generator.PasswordGenerator.generatePassword();

        System.out.println("Generated OTP: " + otp);
        System.out.println("Generated password: " + pwd);
        System.out.println("Password valid? " + com.password_generator.PasswordGenerator.validatePassword(pwd));
    }
}
```

## Compile and run (Windows, cmd.exe)

From the project root (where `src` folder is located):

```cmd
:: compile the library and the test class
javac -d bin src\com\password_generator\PasswordGenerator.java src\TestPasswordGenerator.java

:: run the test
java -cp bin TestPasswordGenerator
```

If you prefer to compile only the library and use it from another project, compile only the library:

```cmd
javac -d bin src\com\password_generator\PasswordGenerator.java
```

Then include `bin` on the classpath from your application.

## Integration notes

- The class is implemented as an `enum` with a singleton-like style (`INSTANCE`) but exposes static methods. This keeps the class non-instantiable while providing a static API.
- If you need to change password length or allowed special characters, update the constants in the source file and recompile.

## Security notes

- `SecureRandom` is used for cryptographic-strength randomness. The generated passwords are suitable for typical application-level use, but for high-security systems you should review policies for entropy, length, and storage/handling.
- The OTP is numeric and 6 digits by default. For greater security, consider longer OTPs or time-based OTP schemes (TOTP).

## License

This project contains example/teaching code. Add a license file if you intend a specific license.

---
Generated on: 2026-04-07
