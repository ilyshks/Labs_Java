package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMTPServer {
    public boolean isValidEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    private String validateEmail(){
        Scanner in = new Scanner(System.in);
        String email = in.nextLine();
        while (!isValidEmail(email)){
            System.out.println("email не соответствует формату, введите снова!");
            System.out.print("email получателя: ");
            email = in.nextLine();
        }
        return email;
    }
    public void inputMail() throws MessagingException {
        Scanner in = new Scanner(System.in);
        System.out.println("Заполните необходимые поля для отправки сообщения:");
        System.out.print("email получателя: ");
        String email = validateEmail();
        System.out.print("Тема письма: ");
        String subject = in.nextLine();
        System.out.println("Далее введите текст письма, последней строкой отправьте 'Конец'.");
        System.out.println("Текст письма:");
        ArrayList<String> text = new ArrayList<>();
        String line = in.nextLine();
        while (!line.equals("Конец")) {
            text.add(line);
            line = in.nextLine();
        }
        System.out.println("Отлично! Отправляем письмо на адрес " + email);

        sendMail(email, subject, text);

    }

    public void sendMail(String email, String subject, ArrayList<String> text) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        //логин и пароль gmail пользователя
        String userLogin = "ilyagorunov.04@gmail.com";
        String userPassword = "ardx yqpf yeio gjiw";

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userLogin, userPassword);
            }
        });

        //создаем сообщение
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ilyagorunov.04@gmail.com"));
        //указываем получателя
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        //устанавливаем тему письма
        message.setSubject(subject);

        String textStr = "";
        for (String line: text)
            textStr = textStr + line;

        System.out.println(textStr);
        //добавляем текст письма
        message.setText(textStr);

        //отправляем сообщение:
        Transport.send(message);
    }
}
