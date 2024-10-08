package vn.hoidanit.laptopshop.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private static final String[] PUBLIC_ENPOINT = {"/user/create", "/authent/login", "/auth/RefreshToken"};
    protected static String SIGNER_KEY = "6w+J0PeNKgeebn0i4wJhE5qvkExZLGxaTW0yHRICSNIxxQHSrVBfHHCe9fsFTHPb";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, PUBLIC_ENPOINT)
                .permitAll()
                .anyRequest()
                .authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtconfigure ->
                        jwtconfigure.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec jwtSecretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(jwtSecretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
    ;

    @Bean
    JwtAuthenticationConverter jwtAuthConverter() {
        JwtGrantedAuthoritiesConverter jwtGranted = new JwtGrantedAuthoritiesConverter();

        jwtGranted.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationconverter = new JwtAuthenticationConverter();
        jwtAuthenticationconverter.setJwtGrantedAuthoritiesConverter(jwtGranted);
        return jwtAuthenticationconverter;
    }
}
