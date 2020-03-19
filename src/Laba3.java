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
import java.util.*;
import java.util.List;

public class Laba3 {

    //Ключ - тоже самое, что и "символ"
    //в словаре каждому ключу соответствует своё значение(работает как телефонная книга)

    public static void main(String[] args) {
        Map<Character,Integer> dictionary = new HashMap<Character, Integer>();//cам словарь
        System.out.print("Введите две строки, состоящих из каких-нибудь символов\n");
        Scanner in=new Scanner(System.in);
        int p=0;
        boolean Flag=false;//Флаг для вывода фразы "Комбинация неверна" только единожды
        String f=in.next();//первая строка
        String s=in.next();//вторая строка
        //заполняем словарь ключами и их значениями для первой строки
        for(int i=0;i<f.length();i++){
            if(!dictionary.containsKey(f.charAt(i))){
                dictionary.put(f.charAt(i),1);//если такого ключа раньше не было, то присваеваем значение 1
            }
            else dictionary.put(f.charAt(i),(dictionary.get(f.charAt(i)))+1);//если такой ключ раньше был, то прибавляем 1
        }
        //действия со второй строкой
        for(int i=0;i<s.length();i++){
            if(!dictionary.containsKey(s.charAt(i))){System.out.print("Комбинация неверна");Flag=true;break;}//если ключ из второй строки не существует в словаре, значит строки разные
            else if(dictionary.get(s.charAt(i))==0){System.out.print("Комбинация неверна");Flag=true;break;}//если ключ из второй строки уже равен нулю, но еще присутсвует во второй строке, значит строки разные
            else {dictionary.put(s.charAt(i),(dictionary.get(s.charAt(i)))-1); //в противном случае просто вычитаем 1 из значения данного ключа
                if(dictionary.get(s.charAt(i))==0){dictionary.remove(s.charAt(i));}//удаление ключа из словаря, если значение равно 0
            }

        }
        if(dictionary.size()==0){System.out.print("Комбинация верна");}//если в итоге в словаре ничего не осталось, значит всё ок
        else if(!Flag)System.out.print("Комбинация неверна");//если же это не так, значит ....
    }
    }
