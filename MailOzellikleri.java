import java.util.Properties;

public class MailOzellikleri {

    private final String host;
    private final String port;
    private final String auth;
    private final String starttls;

    public MailOzellikleri(String host, String port, String auth, String starttls) {
        this.host = host;
        this.port = port;
        this.auth = auth;
        this.starttls = starttls;
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        return props;
    }

}
