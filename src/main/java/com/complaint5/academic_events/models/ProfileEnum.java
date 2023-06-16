package com.complaint5.academic_events.models;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileEnum {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (ProfileEnum profileEnum : ProfileEnum.values()) {
            if (code.equals(profileEnum.getCode())) {
                return profileEnum;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
