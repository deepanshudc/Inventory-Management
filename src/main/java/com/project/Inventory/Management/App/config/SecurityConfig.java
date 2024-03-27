package com.project.Inventory.Management.App.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	
	@Autowired
	DataSource dataSource;
	
	//brcypt 
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
    	http.authorizeHttpRequests(configurer->
    		configurer
    		 .requestMatchers("/inventory/items/edit/**").hasAnyRole("EMPLOYEE","ADMIN")
   		 	 .requestMatchers("/inventory/items").hasAnyRole("EMPLOYEE","ADMIN")
    		 .requestMatchers("/inventory/addUser").hasAnyRole("ADMIN")
    		 .requestMatchers("/inventory/user/**").hasAnyRole("ADMIN")
    		 .requestMatchers("/inventory/items/itemCategory").hasRole("ADMIN")
    		 .requestMatchers("/inventory/items/itemCategory/**").hasRole("ADMIN")
    		 .requestMatchers("/inventory/items/addItems").hasRole("ADMIN")
    		 .requestMatchers("/inventory/items/**").hasRole("ADMIN")
             .anyRequest().authenticated()
    		)
    	
    		.formLogin(form->
	    	 form
	    	   .loginPage("/loginPage")
	           .loginProcessingUrl("/authenticateUser")
	           .permitAll()
            ) 
            .logout(logout -> logout.permitAll()
            )
            .exceptionHandling(configurer ->
                     configurer.accessDeniedPage("/access-denied")
             );
    	
    	//use HTTP basic authentication
    	http.httpBasic(Customizer.withDefaults());
    	 

    	
    	http.csrf(csrf->csrf.disable());
    	
    	
    	return http.build();
    }
    
    @Bean
	public UserDetailsManager userDetailManager(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}
    
}
