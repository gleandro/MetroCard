package com.gleandro.metrocardapplication.service.impl;

import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private UserService userService;

    @Autowired
    public EmailService(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    public String enviarCorreo(String dni) {
        ApiResponse<UserEntity> userEntityApiResponse = this.userService.getUserByDNI(dni);
        if (Boolean.FALSE.equals(userEntityApiResponse.getSuccess())) {
            return "No se encontro el usuario";
        }
        String newPassword = Util.generatePassword(8);

        UserEntity userEntity = userEntityApiResponse.getData();
        userEntity.setPassword(newPassword);

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(userEntity.getEmail());
        mensaje.setSubject("Cambio de contraseña MetroCard");
        mensaje.setText("Se ah cambiado su contraseña exitosamente, su nueva contraseña es: " + newPassword);
        mailSender.send(mensaje);

        this.userService.updateUser(userEntity);

        return "Se envio el correo exitosamente";
    }
}
