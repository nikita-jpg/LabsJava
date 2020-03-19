import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laba3 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> Links = new ArrayList<>();
        List<Document> docs = new ArrayList<>();
        Document doc = Jsoup.connect("https://student.mirea.ru/media/photo/").get();
        Elements elements = doc.getElementsByAttributeValue("class", "h3 g-font-weight-500 mb-1");
        Elements links = doc.getElementsByAttributeValue("class", "u-link-v2");
        elements.forEach(element -> {
            String title = element.text();
            titles.add(title);
        });
        links.forEach(link-> {
            String Link = link.attr("href");
            Links.add(Link);
        });
        titles.forEach(title-> {
            File file = new File("C:\\Users\\Nikita\\Desktop\\test\\" + removeChar(title, '\"'));
            file.mkdirs();
        });
        Links.forEach(Link->{
            Document l = null;
            try {
                l = Jsoup.connect("https://student.mirea.ru/" + Link).get();
                docs.add(l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        docs.forEach(l->{
            Elements aElements = l.getElementsByAttributeValue("class", "img-fluid u-block-hover__main--grayscale u-block-hover__img");
            aElements.forEach(aElement -> {
                BufferedImage image;
                File fileImg = new File("C:\\Users\\Nikita\\Desktop\\test\\" + removeChar(titles.get(docs.indexOf(l)), '\"') + "\\" + (aElements.indexOf(aElement)+1) + ".jpg");
                String url = aElement.attr("src");
                try {
                    URL photo = new URL("https://student.mirea.ru/" + url);
                    image = ImageIO.read(photo);
                    if (image != null)
                        ImageIO.write(image, "jpg", fileImg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
    public static String removeChar(String s, char c) {
        String r = "";
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != c) r += s.charAt(i);
        }
        return r;
    }
    }
