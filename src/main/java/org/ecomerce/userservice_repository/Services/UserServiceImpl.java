package org.ecomerce.userservice_repository.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.common.network.Send;
import org.ecomerce.userservice_repository.DTO.SendEmailDTO;
import org.ecomerce.userservice_repository.EXCEPTIONS.ValidTokenNotFoundException;
import org.ecomerce.userservice_repository.Models.ENUMS.ISVARIFIED;
import org.ecomerce.userservice_repository.Models.Token;
import org.ecomerce.userservice_repository.Models.User;
import org.ecomerce.userservice_repository.Repository.UserServicRepo;
import org.ecomerce.userservice_repository.Repository.tokenServiceRepo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface{
    //to access database have a Repository
    private UserServicRepo userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private tokenServiceRepo tokenService;

    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;

    private UserServiceImpl(UserServicRepo userRepository, ObjectMapper objectMapper, BCryptPasswordEncoder passwordEncoder, tokenServiceRepo tokenServiceRep, KafkaTemplate kafkaTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenServiceRep;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }
    /**
     String passwordFromDb = user.getPassword();
     String encodedPassword=passwordEncoder.encode(password);
     if(passwordFromDb.equals(encodedPassword)) {
     Token token = new Token();
     token.setUser(user);
     }
     *****----NOT POSSIBLE THIS WAY OF DOING THINGS-----******
     **/

    @Override
    public Token Login(String email, String password) {
        Optional<User> checkUser =userRepository.findByEmail(email);
        if(checkUser.isEmpty()) {
            return null;
        }
        User user = checkUser.get();
        Token token = new Token();
        int count=0;
        if(passwordEncoder.matches(password, user.getPassword())) {
            List<Token> tokens = tokenService.findByUser_email(user.getEmail());
            if(tokens.size()>=2) {
                throw new RuntimeException("Maximum login limit reached! must logout from one device to login");

            }
            token.setUser(user);
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DAY_OF_MONTH, 30);

            Date  date30aysFromNow = calendar.getTime();
            //token.setExpryAt(new Date());
            token.setExpryAt(date30aysFromNow);

            token.setValue(RandomStringUtils.randomAlphanumeric(128));
            tokenService.save(token);
        }
        return token;

        //we should not expose token
    }

    @Override
    public User signup(String FirstName, String LastName, String username, String Email, String Password, Integer phoneNumber) throws JsonProcessingException {
        Optional<User> user = userRepository.findByEmail(Email);
            if(user.isPresent()) {
                return user.get();
            }
            User newUser = new User();
            newUser.setFirstName(FirstName);
            newUser.setLastName(LastName);
            newUser.setUsername(username);
            newUser.setEmail(Email);
                /**?we should not add password as it is
                        use bcrypt dependency

                 also is we use this ---->>>> BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                   we might need this BCryptPasswordEncoder object multiple place
                   so we should create a bean for this and inject it wherever we need to use
                   for this create a config packahe and use @Config

                   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                 **/
            newUser.setPassword(passwordEncoder.encode(Password));
            newUser.setPhone_Number(phoneNumber);
            newUser.setIsvarified(ISVARIFIED.VERIFIED);
            //publisher kafka
        /**
           what Things we need to send in an event ? so that notification service can process that
                ->Name
                ->email
                ->subject
                ->body
                ->tag
         */
        SendEmailDTO    emailDTO = new SendEmailDTO();
        emailDTO.setTo(newUser.getEmail());
        emailDTO.setSubject("Welcome User->");
        emailDTO.setBody("A-z tak sab padh dala hai na subh se bika hai aalu na hi bika hai kanda hoy hoye");

        kafkaTemplate.send(
                "sendNotification",objectMapper.writeValueAsString(emailDTO)
        );
        return userRepository.save(newUser);
    }

    @Override
    public void logout(String tokenValue) {
        //we will be able to loout if token is present in the db
        //if exp time and > current time
        //if deleted is false;
        Optional<Token>optionalToken=tokenService.findByValueAndIsDeletedAndExpryAtGreaterThan(tokenValue,false,new Date());
        if(optionalToken.isEmpty()) {
            throw new ValidTokenNotFoundException("Valid token not found");
        }
        Token token = optionalToken.get();
        token.setIsDeleted(true);
        tokenService.save(token);
    }

    @Override
    public User validateToken(String Token) {
        Optional<Token>optionalToken=tokenService.findByValueAndIsDeletedAndExpryAtGreaterThan(Token,false,new Date());
        if(optionalToken.isEmpty()) {
            throw new ValidTokenNotFoundException("Valid token not found");
        }
        Token token = optionalToken.get();
        return token.getUser();
    }
}
