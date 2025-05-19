//package com.example.server_register.config.security;
//
//import com.example.server_register.repository.MemberRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final MemberRepo memberRepo;
//    private final JwtFilter jwtFilter;
//    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetails userDetails = User.withUsername("ruypa")
////                .password(passwordEncoder().encode("123"))
////                .roles("TEACHER")
////                .build();
////        UserDetails userDetailss = User.withUsername("ruypaa")
////                .password(passwordEncoder().encode("123"))
////                .roles("STUDENT")
////                .build();
////        return new InMemoryUserDetailsManager(userDetails, userDetailss);
//        return new UserInfoDetailsService(memberRepo);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // tạo tài khoản cần setPassword dưới dạng passwordencoder
//        // return new BCryptPasswordEncoder();
//        return new RawPasswordForTest();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req ->
//                        req
//                                .requestMatchers("/api/v1/members/login").permitAll()
////                                .requestMatchers("/api/v1/students/register/**").hasRole("STUDENT")
////                                .requestMatchers("/api/v1/section-classes/**").hasRole("STUDENT")
////                                .requestMatchers("/api/v1/semesters/**").hasRole("STUDENT")
////                                .requestMatchers("/api/v1/departments/**").hasRole("STUDENT")
////                                .requestMatchers("/api/v1/subjects/register/**").hasRole("STUDENT")
//                                .requestMatchers("/api/v1/students/register/**").permitAll()
//                                .requestMatchers("/api/v1/section-classes/**").permitAll()
//                                .requestMatchers("/api/v1/semesters/**").permitAll()
//                                .requestMatchers("/api/v1/departments/**").permitAll()
//                                .requestMatchers("/api/v1/subjects/register/**").permitAll()
//                                .requestMatchers("/api/v1/students/register/test").permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
////                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://26.95.61.160:5173"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}
//
//
//
