package Ptyxiaki.Enums;

import lombok.Getter;

@Getter
public enum UserRole {
    DEVELOPER("Developer"),
    RECRUITER("Recruiter"),
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }
}
