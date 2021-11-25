package com.example.anime.controller;


import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Users;
import com.example.anime.repository.UsersRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/users")  // este mapeado funciona con esto
public class UsersController {

    @Autowired private final UsersRespository usersRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public UsersController(UsersRespository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
    public ResponseUsers todos() {
        return new ResponseUsers(usersRepository.findBy());
//        return ResponseEntity.ok()
//                .body(usersRepository.findAll());
    }


    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody UserRegister userRegister) {
        if (usersRepository.findByUsername(userRegister.username) == null) {
            Users user = new Users();
            user.username = userRegister.username;
            user.password = passwordEncoder.encode(userRegister.password);

            Users savedFile = usersRepository.save(user);
            FileResult fileResult = new FileResult(savedFile.userid, savedFile.password);

            return ResponseEntity.ok()
                    .body(usersRepository);
        }
        //error 409
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Message.message( "Ja existeix un usuari amb el nom '" + userRegister.username + "'" ));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
        Users u = usersRepository.findById(id).orElse(null);

        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Message.message("No s'ha trobat el user amd id '" + id  + "'"));
        }
        usersRepository.delete(u);
        return ResponseEntity.ok()
                .body( Message.message( "S'ha eliminat l'usuari amd id '" + id + "'" ));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(){
        usersRepository.deleteAll();
        return ResponseEntity.ok().body( "S'ha eliminat tots els users" );
    }









}







// ----------------------------------------------------------------------------------------------------------------------------------




//package com.example.anime;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//        import org.springframework.security.config.http.SessionCreationPolicy;
//        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//        import javax.sql.DataSource;
//
////public class SecurityConfig{
////}
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public BCryptPasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().csrf().disable()
//                .authorizeRequests()
//                .mvcMatchers("/users/register/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from usser where username = ?")
//                .authoritiesByUsernameQuery("select username, role from usser where username = ?")
//                .passwordEncoder(getPasswordEncoder());
//    }
//}
