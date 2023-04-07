package skkumet.skkuting.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record UserAccountPrincipal(
        String email,
        String nickname,
        String password,
        Integer studentNumber,
        String description,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static UserAccountPrincipal of(String email, String nickname, String password, Integer studentNumber, String description) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new UserAccountPrincipal(
                email,
                nickname,
                password,
                studentNumber,
                description,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
        );
    }

    public static UserAccountPrincipal from (UserAccountDto dto) {
        return UserAccountPrincipal.of(
                dto.email(),
                dto.nickname(),
                dto.password(),
                dto.studentNumber(),
                dto.description()
        );
    }



    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public enum RoleType {
        USER("ROLE_USER");
        @Getter
        private final String name;
        RoleType(String name) {
            this.name = name;
        }
    }
}
