import javax.mail.Session;
import java.util.Properties;

public class MailAyarlari extends MailOzellikleri {

    private String username;
    private String password;
    private String protocol;

    public MailAyarlari(String username, String password) {
        super("smtp.gmail.com", "587", "true", "true");
        this.protocol = "smtp";
        this.username = username;
        this.password = password;
    }

    public Session getSession() {
        Properties props = this.getProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
    }
}
