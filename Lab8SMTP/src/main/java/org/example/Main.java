package org.example;
import javax.mail.*;

public class Main {
    public static void main(String[] args) throws MessagingException {
        SMTPServer server = new SMTPServer();
        server.inputMail();
    }
}