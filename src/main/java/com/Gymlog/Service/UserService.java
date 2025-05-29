package com.Gymlog.Service;

import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encriptador;
    private  final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCaseAndVerifiedTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado!"));
    }

    @Transactional
    public UserEntity registerUser(@Valid UserRequest data) {
        var passwordEncrypted = encriptador.encode(data.password());
        var user = new UserEntity(data, passwordEncrypted);

        emailService.sendEmailVerification(user);
        return userRepository.save(user);
    }

    @Transactional
    public void verifyEmail(String code){
        var user = userRepository.findByVerificationToken(code).orElseThrow();
        user.verify();
        userRepository.save(user);
    }
}

