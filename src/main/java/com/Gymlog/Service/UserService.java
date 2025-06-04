package com.Gymlog.Service;

import com.Gymlog.Controllers.Request.UpdatePassword;
import com.Gymlog.Controllers.Request.UpdateRequest;
import com.Gymlog.Controllers.Request.UserRequest;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Exceptions.BussinesRuleException;
import com.Gymlog.Exceptions.EmailExistException;
import com.Gymlog.Exceptions.NotFoundException;
import com.Gymlog.Exceptions.PasswordConfirmIncorrectException;
import com.Gymlog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


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
        if(userRepository.findByEmailIgnoreCase(data.email()).isPresent()){
            throw new EmailExistException("EMAIL_EXIST", "Email ja cadastrado!");
        }

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


    public Optional<UserEntity> updateIsActive(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
        user.setActive(!user.isActive());
        return Optional.of(userRepository.save(user));
    }


    public Optional<Void> deleteUser(long id) {
        var user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
        userRepository.delete(user);
        return Optional.empty();
    }

    @Transactional
    public Optional<UserEntity> updateUser(UpdateRequest updateRequest, Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            userEntity.setUsername(updateRequest.username());
            userEntity.setHeight(updateRequest.height());
            userEntity.setWeight(updateRequest.weight());
            userEntity.setGoal(updateRequest.goal());
            userEntity.setGoalWeight(updateRequest.goalWeight());
            userEntity.setActivyLevel(updateRequest.activyLevel());
            userEntity.setAvatarUrl(updateRequest.avatarUrl());
            userEntity.setUpdatedAt(LocalDateTime.now());

            return Optional.of(userRepository.save(userEntity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<UserEntity> updatePassword(UpdatePassword updatePassword, Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        UserEntity userEntity = user.get();
        if(user.isPresent()){
             if(!encriptador.matches(updatePassword.confirmPassword(), userEntity.getPassword())){
                 throw new PasswordConfirmIncorrectException("PASSWORD_NOT_MATCH", "Senhas nao conferem!");
             }else{
                 userEntity.setPassword(encriptador.encode(updatePassword.newPassword()));
                 return Optional.of(userRepository.save(userEntity));
             }
        }
        return Optional.empty();
    }


    public Optional<UserEntity> getUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()){
            return Optional.of(user.get());
        }
        return Optional.empty();
    }

    public UserEntity findById(Long user) {
        return userRepository.findById(user).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
    }
}

