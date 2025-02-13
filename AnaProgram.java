//Remle Kanal 21120205069

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AnaProgram {

    static ArrayList<Uye> Uyeler; // Uye nesnelerinin tutulacağı liste

    public static void main(String[] args) throws IOException, MessagingException {

        AnaProgram ap = new AnaProgram();
        Dosyaislemleri di = new Dosyaislemleri();
        Uyeler = di.Dosyaoku(); // Uye nesneleri dosyadan okunuyor
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MENU");
            System.out.println("1- Elit üye ekleme");
            System.out.println("2- Genel Üye ekleme");
            System.out.println("3- Mail Gönderme");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Boş satırı işle

            switch (choice) {
                case 1:
                    ap.ElitUyeEkle(); // Elit üye ekleme
                    di.DosyaYaz(Uyeler); // Dosyaya yaz
                    break;
                case 2:
                    ap.GenelUyeEkle(); // Genel üye ekleme
                    di.DosyaYaz(Uyeler); // Dosyaya yaz
                    break;
                case 3:
                    ap.EmailKontrol(); // E-posta gönderme işlemi
                    break;
                default:
                    System.out.println("Geçersiz seçim");
                    break;
            }
            break;
        }
    }

    public void EmailKontrol() throws IOException, MessagingException {
        Dosyaislemleri di = new Dosyaislemleri();
        ArrayList<Uye> TumUyeler = di.Dosyaoku(); // Uye nesneleri dosyadan okunuyor
        String eposta_liste = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("MENU");
            System.out.println("1- Elit Üyelere Mail");
            System.out.println("2- Genel Üyelere Mail");
            System.out.println("3- Tüm Üyelere Mail");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Boş satırı işle

            switch (secim) {
                case 1:
                    eposta_liste = "";
                    for (Uye uye : TumUyeler) {
                        if (uye.getTip().equalsIgnoreCase("ELİT ÜYELER")) {
                            eposta_liste += uye.getEmail() + ",";
                        }
                    }
                    if (eposta_liste.endsWith(",")) {
                        eposta_liste = eposta_liste.substring(0, eposta_liste.length() - 1);
                    }
                    System.out.println("Elit Üyelere Gönderilecek Mesaj İçeriğini Giriniz:");
                    String Mail_icerik = scanner.nextLine();
                    EpostaGonder(eposta_liste, "Bilgi", Mail_icerik);
                    break;
                case 2:
                    eposta_liste = "";
                    for (Uye uye : TumUyeler) {
                        if (uye.getTip().equalsIgnoreCase("GENEL ÜYELER")) {
                            eposta_liste += uye.getEmail() + ",";
                        }
                    }
                    if (eposta_liste.endsWith(",")) {
                        eposta_liste = eposta_liste.substring(0, eposta_liste.length() - 1);
                    }
                    System.out.println("Genel Üyelere Gönderilecek Mesaj İçeriğini Giriniz:");
                    Mail_icerik = scanner.nextLine();
                    EpostaGonder(eposta_liste, "Bilgi", Mail_icerik);
                    break;
                case 3:
                    eposta_liste = "";
                    for (Uye uye : TumUyeler) {
                        eposta_liste += uye.getEmail() + ",";
                    }
                    if (eposta_liste.endsWith(",")) {
                        eposta_liste = eposta_liste.substring(0, eposta_liste.length() - 1);
                    }
                    System.out.println("Tüm Üyelere Gönderilecek Mesaj İçeriğini Giriniz:");
                    Mail_icerik = scanner.nextLine();
                    EpostaGonder(eposta_liste, "Bilgi", Mail_icerik);
                    break;
                default:
                    System.out.println("Geçersiz seçim");
                    break;
            }
            break;
        }
    }

    public void ElitUyeEkle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("İsim: ");
        String isim = scanner.nextLine();
        System.out.print("Soyisim: ");
        String soyisim = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Elit üye oluşturuluyor
        Uye elitUye = new Uye("ELİT ÜYELER", isim, soyisim, email);
        Uyeler.add(elitUye); // Listeye ekleniyor
    }

    public void GenelUyeEkle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("İsim: ");
        String isim = scanner.nextLine();
        System.out.print("Soyisim: ");
        String soyisim = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Genel üye oluşturuluyor
        Uye genelUye = new Uye("GENEL ÜYELER", isim, soyisim, email);
        Uyeler.add(genelUye); // Listeye ekleniyor
    }

    public void EpostaGonder(String aliciListesi, String konu, String metin) throws AddressException, MessagingException {
        // MailConfig sınıfını kullanarak bir Session nesnesi oluşturuyoruz.
        MailAyarlari mailConfig = new MailAyarlari("username", "password");
        Session session = mailConfig.getSession();

        // MimeMessage sınıfı kullanarak bir e-posta mesajı oluşturuyoruz.
        MimeMessage mesaj = new MimeMessage(session);
        mesaj.setFrom(new InternetAddress("your_email@example.com"));

        // Tüm alıcıları ekleyin
        mesaj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(aliciListesi));

        mesaj.setSubject(konu);
        mesaj.setText(metin);

        // Transport sınıfının send() metodunu kullanarak mesajı gönderiyoruz.
        Transport.send(mesaj);
        System.out.println("E-posta başarıyla gönderildi!");
    }
}
