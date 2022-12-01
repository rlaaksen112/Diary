package com.spring.min.diary.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserSecurityService userSecurityService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login")     //로그인 url
                .defaultSuccessUrl("/")         //로그인 성공이 url
                .usernameParameter("memberId")	//usernameParameter("id")'는 유저 아이디에 해당하는 form의 name을 변경합니다. 이 부분은 없어도 되며, 그럼 default는 'username' 입니다. 위와 같이 변경했다면 input은 '<input type="text" name="id">' 처럼 되겠네요.
                .passwordParameter("memberPw")	//데이터에서 관여하는 것은 name만이다 인풋에 Id:같은 다른태그가 있을떄도 네임만 파라미터 지정된 이름으로 바꿔주면 된다.
                .successHandler(new customSuccessHandler()) //로그인 성공시 작동하는 핸들러 커스텀으로 만들어 주기 (채팅방 입장시 세션 아이디값이 필요해서 만들었음)


                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)


                .and()
                .csrf()
                .disable()

        ;
        return http.build();
    }


    @Bean   //패스워드 엔코더 사용.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
