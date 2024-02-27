package Ptyxiaki.Enums;

import lombok.Getter;

@Getter
public enum UserRole {
    DEVELOPER("Developer"),
    RECRUITER("Recruiter");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }
}
