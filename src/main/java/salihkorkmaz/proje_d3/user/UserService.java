package salihkorkmaz.proje_d3.user;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import salihkorkmaz.proje_d3.email.EmailService;
import salihkorkmaz.proje_d3.user.dto.UserDTO;
import salihkorkmaz.proje_d3.user.exception.ActivationNotificationExcepiton;
import salihkorkmaz.proje_d3.user.exception.InvalidTokenException;
import salihkorkmaz.proje_d3.user.exception.NotFoundException;
import salihkorkmaz.proje_d3.user.exception.NotUniqueEmailException;


import java.util.UUID;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailService emailService;


    @Transactional(rollbackOn = MailException.class)//*Mail gönderiminde hata oluşurssa kullanıcı kaydı oluşturulmaz
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        } catch (MailException ex) {
            throw new ActivationNotificationExcepiton();
        }
    }


    public void activateUser(String token) {
        User inDB = userRepository.findByActivationToken(token);
        if (inDB == null) {
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAll(page);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}