package com.Gymlog.Service;

import com.Gymlog.Entity.UserEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private static final String NOME_ORIGEM = "GymLog";
    private static final String EMAIL_ORIGEM = "GymLog@gmail.com";
    public static final String URL_SITE = "http://localhost:8080";

    @Async
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_ORIGEM, NOME_ORIGEM);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao enviar email!", e);
        }
    }

    public void sendEmailVerification(UserEntity user) {
        String assunto = "Aqui está seu link para verificar o email";
        String conteudo = gerarConteudoEmail(
                "Olá [[name]],<br><br>" +
                        "Seja bem-vindo ao seu Diário de Academia! 💪<br>" +
                        "Estamos quase lá... só falta um passo para você começar sua jornada fitness conosco.<br><br>" +
                        "Clique no botão abaixo para verificar sua conta e liberar acesso completo ao seu diário de treinos e evolução:<br><br>" +
                        "<div style=\"text-align: center; margin: 30px 0;\">" +
                        "  <a href=\"[[URL]]\" target=\"_self\" style=\"" +
                        "    background-color: #28a745;" +
                        "    color: white;" +
                        "    padding: 12px 24px;" +
                        "    text-decoration: none;" +
                        "    border-radius: 6px;" +
                        "    font-weight: bold;" +
                        "    font-size: 16px;" +
                        "    display: inline-block;" +
                        "  \">VERIFICAR CONTA</a>" +
                        "</div>" +
                        "Fique à vontade para registrar seus treinos, metas e conquistas!<br><br>" +
                        "Nos vemos na próxima série 😉<br><br>" +
                        "<strong>Equipe Diário de Academia</strong>",
                user.getUsername(),
                URL_SITE + "/verify-user?code=" + user.getVerificationToken()
        );


        sendEmail(user.getEmail(), assunto, conteudo);
    }

    private String gerarConteudoEmail(String template, String nome, String url) {
        return template.replace("[[name]]", nome).replace("[[URL]]", url);
    }
}

