package training.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import training.user.UserDto;
import training.user.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final Map<String, UserDetailsImpl> userDetailsMap;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
        userDetailsMap = new HashMap<>();
    }

    public void updateUserAuth(String name, Collection<? extends GrantedAuthority> authorities){
        userDetailsMap.get(name).setAuthorities(authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = userService.findUserByName(name)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found, name: " + name));
        userDetailsMap.put(name, userDetails);
        return userDetails;
    }

    private class UserDetailsImpl implements UserDetails {

        private final UserDto userDto;

        UserDetailsImpl(UserDto userDto) {
            this.userDto = userDto;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Arrays.asList(() -> "ROLE_ADMIN");
        }

        @Override
        public String getPassword() {
            return userDto.getSurname();
        }

        @Override
        public String getUsername() {
            return userDto.getName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public void setAuthorities( Collection<? extends GrantedAuthority> authorities){

        }
    }
}
