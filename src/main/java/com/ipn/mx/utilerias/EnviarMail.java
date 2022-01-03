/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bryan
 */
public class EnviarMail {
    
    public void enviarCorreo(String destinatario, String asunto, String mensaje){
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.user", "tt8810660@gmail.com");
        p.setProperty("mail.smtp.auth", "true");
        
        Session s = Session.getDefaultInstance(p);
        
        MimeMessage elMensaje = new MimeMessage(s);
        try {
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            
            Transport t = s.getTransport("smtp");
            t.connect(p.getProperty("mail.smtp.user"),"Glorytown20");
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
            
        } catch (AddressException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        
        EnviarMail email = new EnviarMail();
        String destinatario = "tt8810660@gmail.com";
        String asunto = "Registro Satisfactorio";
        String texto = "Prueba1";
        
        email.enviarCorreo(destinatario, asunto, texto);
    }
    
}
