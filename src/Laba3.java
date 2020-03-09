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
import java.util.Scanner;

public class Laba3 {


    static int i=1;
    public static BufferedImage Parse(String url) throws IOException {
        URL urlImage = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
        connection.setRequestMethod("GET");

        BufferedImage bufferedImage = ImageIO.read(urlImage);

        String expansion = url.substring(url.lastIndexOf('.'));
        String name = String.valueOf(i);
        ImageIO.write(bufferedImage,"png",new File("C:\\Users\\Nikita\\Desktop\\MyVideo\\"+name+expansion));
        i++;
        return bufferedImage;
    }
        public static void main(String[] args) throws IOException {

            ArrayList<BufferedImage> arr;
            String url = "https://student.mirea.ru/media/photo";
            Document doc = (Document) Jsoup.connect(url).get();
            Elements imgElement = doc.select("img");
            arr = new ArrayList<BufferedImage>();

            for( Element element: imgElement){
                Parse("https://student.mirea.ru/"+element.attr("src"));
            }

        }
    }
