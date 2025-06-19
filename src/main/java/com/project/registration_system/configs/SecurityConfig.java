// package com.project.registration_system.configs;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(csrf -> csrf.disable())
//                 .sessionManagement(management -> management
//                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authorizeHttpRequests(auth -> auth
//                                 .requestMatchers("/api/public/**").permitAll()  // allow public endpoints
//                                 .anyRequest().authenticated()                    // protect others
//                 )
//                 .httpBasic(basic -> basic.disable());  // disable Basic Auth challenge

//         // Add your JWT filter here if you have one
//         // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//         @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeHttpRequests().anyRequest().permitAll();
//         return http.build();
//     }
// }
