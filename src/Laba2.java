import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.io.IOException;

public class Laba2 {

        public static int getPriority(char s)
        {
            switch (s)
            {
                case '^': return 4;
                case '*': case '/': return 3;
                case '+': case '-': return 2;
                case '(': case ')': return 1;
                default: return 0;
            }
        }
        public static String perevod(String vvod)
        {
            char n;
            String output = "";
            Stack opz = new Stack(vvod.length());
            for(int i = 0; i < vvod.length(); i++)
            {
                if(i == 0 && vvod.charAt(i) == '-') output+="0 ";
                while(Character.isDigit(vvod.charAt(i)))
                {
                    output+=vvod.charAt(i);
                    if(i == vvod.length()-1) break;
                    i++;
                }
                output+=" ";
                if(getPriority(vvod.charAt(i)) == 0)
                    continue;
                else
                {
                    switch (vvod.charAt(i))
                    {
                        case '(':
                            opz.push(vvod.charAt(i));
                            break;
                        case '*': case '/': case '+': case '-': case '^':

                        while(!opz.isEmpty() && (getPriority(vvod.charAt(i)) <= getPriority((char)opz.showTop())))
                        {
                            output+=opz.showTop();
                            opz.pop();
                        }
                        if(opz.isEmpty() || getPriority(vvod.charAt(i)) > getPriority((char)opz.showTop()) && !opz.isEmpty())
                        {
                            opz.push(vvod.charAt(i));
                        }
                        break;
                        case ')':
                            while ((char)opz.showTop() != '(')
                            {
                                output+=(char)opz.showTop();
                                opz.pop();
                            }
                            opz.pop();
                    }
                }
            }
            while(!opz.isEmpty()) {
                output += (char) opz.showTop();
                opz.pop();
            }
            return output;
        }
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            String input, output = "";
            input = sc.nextLine();

            output = perevod(input);

            Stack calc = new Stack(output.length());
            int x1, x2, otvet = 0;
            String num = "";
            for(int i = 0; i < output.length(); i++)
            {
                if(Character.isDigit(output.charAt(i)))  num+= output.charAt(i);
                else if(output.charAt(i) == ' ')
                {
                    if(num!="") {
                        calc.push(Integer.parseInt(num));
                        num = "";
                    }
                }
                else
                {
                    x2 = (int)calc.pop();
                    x1 = (int)calc.pop();
                    switch (output.charAt(i))
                    {
                        case '+': otvet = x1 + x2; break;
                        case '-': otvet = x1 - x2; break;
                        case '*': otvet = x1 * x2; break;
                        case '/': otvet = x1 / x2; break;
                        case '^': otvet = (int)Math.pow(x1,x2); break;
                        default: System.out.println("Ошибка"); break;
                    }
                    calc.push(otvet);
                }
            }
            System.out.println("Ответ: " + otvet);
        }
    }
