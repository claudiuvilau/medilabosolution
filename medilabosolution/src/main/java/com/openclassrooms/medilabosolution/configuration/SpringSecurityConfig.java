package com.openclassrooms.medilabosolution.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Cette classe permet de préconfigurer et de personnaliser les fonctions de
 * sécurité
 * 
 * @author Claudiu VILAU
 * 
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig {

        /**
         * 
         * Cette méthode sert créer un utilisateur
         * 
         * À noter que l’authentification se fera par
         * Spring Security, mais qu'il n'y a pas besoin de mettre en place d'inscription
         * ou de gestion de droits pour ce projet. De ce fait, le mot de passe est en
         * dur. L'utilisateur est en mémoire.
         * 
         * @return l'utilisateur créé dans cette methode en mémoire
         */
        @Bean
        public UserDetailsService users() {
                UserDetails user = User.builder()
                                .username("user")
                                .password(
                                                bCryptPasswordEncoder().encode("user"))
                                .roles("USER").build();
                return new InMemoryUserDetailsManager(user);
        }

        /**
         * Cette méthode sert à construire la chaîne de filtres selon notre
         * configuration
         * 
         * @param http la classe HttpSecurity est sollicitée pour appliquer la chaîne de
         *             filtres de sécurité aux requêtes HTTP
         * @return la requête HTTP avec les filtres de
         *         sécurité appliqués
         */
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http

                                .authorizeHttpRequests(authorize -> authorize
                                                .anyRequest()
                                                .authenticated())
                                .formLogin(login -> login
                                                .defaultSuccessUrl("/app/login/ok", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/app/app-logout")
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true));

                return http.build();
        }

        /**
         * Algorithme de hachage
         * 
         * @return le mot de passe haché
         * 
         */
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {

                return new BCryptPasswordEncoder();

        }

}