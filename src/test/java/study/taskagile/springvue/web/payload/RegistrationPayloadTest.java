package study.taskagile.springvue.web.payload;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationPayloadTests {
    private Validator validator;
    private RegistrationPayload payload;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        payload = new RegistrationPayload();
    }

    @Test
    @DisplayName("값이 비었을 때 - 모두 실패")
    void validate_blankPayload_shouldFail() {
        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(3, violations.size());
    }

    @Test
    @DisplayName("유효하지 않은 이메일 - 실패")
    void validate_payloadWithEmail_shouldFail() {
        payload.setUsername("test");
        payload.setPassword("testPass");
        payload.setEmailAddress("test");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Email address should be valid",
            violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("이메일 글자수 유효성 검증 - 실패")
    void validate_payloadWithEmailLength_shouldFail() {
        String emailId = generateRandomString(64);
        String domain = generateRandomString(32);

        payload.setUsername("test");
        payload.setPassword("testPass");
        payload.setEmailAddress(emailId + "@" + domain + ".com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Email address must not be more than 100 characters",
            violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("username 유효성 검증 - 실패")
    void validate_payloadWithUsername_shouldFail() {
        payload.setUsername(generateRandomString(1));
        payload.setPassword("testPass");
        payload.setEmailAddress("test@gmail.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Username must be between 2 and 50 characters",
            violations.iterator().next().getMessage());

        payload.setUsername(generateRandomString(51));
        violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Username must be between 2 and 50 characters",
            violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("password 유효성 검증 - 실패")
    void validate_payloadPassword_shouldFail() {
        payload.setUsername("test");
        payload.setPassword(generateRandomString(5));
        payload.setEmailAddress("test@gmail.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Password must be between 6 and 30 characters",
            violations.iterator().next().getMessage());

        payload.setUsername(generateRandomString(31));
        violations = validator.validate(payload);
        assertEquals(1, violations.size());
        assertEquals("Password must be between 6 and 30 characters",
            violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("정상입력시 유효성검증 size 0")
    void validate_success() {
        payload.setUsername("test");
        payload.setPassword(generateRandomString(6));
        payload.setEmailAddress("test@gmail.com");

        Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
        assertEquals(0, violations.size());
    }

    private String generateRandomString(int targetStringLength) {
        final int LEFT_LIMIT = 97;
        final int RIGHT_LIMIT = 122;
        Random random = new Random();

        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}