package skkumet.skkuting.dto.constant;

import lombok.Getter;

public enum AuthorizingPolicy {
    AUTO("auto"), BY_HOST("by_host");

    @Getter
    private String policy;

    AuthorizingPolicy(String policy) {
        this.policy = policy;
    }
}
