package com.example.anime.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // esto te dice que todas las peticiones son http
@RequestMapping("/users")  // este mapeado funciona con esto
public class UsersController {

//    @Autowired private UsersRepository usersRepository;
//    @Autowired private BCryptPasswordEncoder passwordEncoder;

//    @GetMapping("/")
//    public List<Users> todos() {
//        return usersRepository.findAll();
//    }

//    @PostMapping("/")
//    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {
//        if (usersRepository.findByUserName(userRegisterRequest.username) == null) {
//            Users user = new Users();
//            user.username = userRegisterRequest.username;
//            user.password = passwordEncoder.encode(userRegisterRequest.password);
//            usersRepository.save(user);
//            return ResponseEntity.ok()
//                    .body(usersRepository);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(Message.message( "Ja existeix un usuari amb el nom '" + userRegisterRequest.username + "'" ));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
//        if (usersRepository.findByUserId(id) == null) {
//            Users user = new Users();
//            usersRepository.deleteAllById(id);
//            return ResponseEntity.ok()
//                    .body( Message.message( "S'ha eliminat l'usuari amd id '" + id + "'" ));
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(Message.message("No s'ha trobat el user amd id '" + id  + "'"));
//    }

//    @DeleteMapping("/")
//    public ResponseEntity<?> deleteAll(){
//        usersRepository.deleteAll();
//        return ResponseEntity.ok().body( "S'ha eliminat tots els users" );
//    }





//    @PostMapping("/")
//    public ResponseEntity<?> createUsers(@RequestBody Users user) {
//        for (Users a : usersRepository.findAll()){
//            if(a.username.equals(user.getUsername()))
//                // error 409
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body(Message.message("Ja existeix un usuari amb el nom '" + user.getUsername() + "'"));
//        }
//        return ResponseEntity.ok().body(usersRepository.save(user));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUsers(@PathVariable UUID id){
//        for (Users a : usersRepository.findAll()){
//            if(a.animeid.equals(id))
//                usersRepository.delete(a);
//                return ResponseEntity.ok().body( "S'ha eliminat el user amd id '" + id  + "'" );
//        }
//        // error 404
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(Message.message("No s'ha trobat el user amd id '" + id  + "'"));
//
//    }




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
