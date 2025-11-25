package pchub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // Permite acesso a recursos estáticos e páginas de login/cadastro
                .antMatchers("/css/**", "/js/**", "/assets/**", "/login", "/usuario/cadastro").permitAll()
                // Todas as outras requisições exigem autenticação
                .anyRequest().authenticated()
            .and()
            .formLogin()
                // URL da página de login
                .loginPage("/login")
                // Redireciona para a página inicial após login bem-sucedido
                .defaultSuccessUrl("/", true)
                .permitAll()
            .and()
            .logout()
                // URL para logout
                .logoutUrl("/logout")
                // Redireciona para a página de login após logout
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        
        // Desabilita CSRF para simplificar o desenvolvimento (DEVE SER HABILITADO EM PRODUÇÃO)
        // Por enquanto, vamos desabilitar para evitar problemas com os formulários existentes
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
