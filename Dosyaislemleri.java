import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Dosyaislemleri {

    // Dosya oku metodu: Kullanıcıları Uye nesneleri olarak okuyacak
    public ArrayList<Uye> Dosyaoku() throws IOException {
        ArrayList<Uye> uyeler = new ArrayList<Uye>();
        String fileName = "Kullanıcılar.txt";
        FileReader fr = new FileReader(fileName, Charset.forName("UTF-8"));

        try {
            BufferedReader bufferedReader = new BufferedReader(fr);
            String line = bufferedReader.readLine();
            String rol = "";

            while (line != null) {
                if (!"".equals(line)) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#") && line.endsWith("#")) {
                            rol = line.substring(1, line.length() - 1).trim();
                        } else {
                            rol = line.substring(1);
                        }
                    } else {
                        String[] data = line.split("\t");
                        String isim = data[0];
                        String soyisim = data[0];
                        String email = data[0];
                        // Uye nesnesi oluşturup listeye ekliyoruz
                        Uye uye = new Uye(rol, isim, soyisim, email);
                        uyeler.add(uye);
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uyeler;
    }

    // Dosyaya yazma metodu: Uye nesnelerini dosyaya yazacak
    public void DosyaYaz(ArrayList<Uye> uyeler) throws IOException {
        try {
            String fileName = "Kullanıcılar.txt";
            FileWriter writer = new FileWriter(fileName, false);
            writer.write("#ELİT ÜYELER#" + "\n");

            for (Uye uye : uyeler) {
                if (uye.getTip().equalsIgnoreCase("ELİT ÜYELER")) {
                    writer.write(uye.getIsim() + "\t");
                    writer.write(uye.getSoyisim() + "\t");
                    writer.write(uye.getEmail() + "\n");
                }
            }

            writer.write("#GENEL ÜYELER#" + "\n");
            for (Uye uye : uyeler) {
                if (uye.getTip().equalsIgnoreCase("GENEL ÜYELER")) {
                    writer.write(uye.getIsim() + "\t");
                    writer.write(uye.getSoyisim() + "\t");
                    writer.write(uye.getEmail() + "\n");
                }
            }

            writer.close();
            System.out.println("Bilgiler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    // Ana metod, test amacıyla dosya okuma ve yazma işlemi
    public static void main(String[] args) throws IOException {
        Dosyaislemleri di = new Dosyaislemleri();

        // Dosya okuma işlemi
        ArrayList<Uye> uyeler = di.Dosyaoku();
        for (Uye uye : uyeler) {
            System.out.println(uye.getTip() + ": " + uye.getIsim() + " " + uye.getSoyisim() + " " + uye.getEmail());
        }

        // Dosyaya yazma işlemi
        di.DosyaYaz(uyeler);
    }
}
