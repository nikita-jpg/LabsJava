import java.util.Scanner;
import java.util.Stack;
public class Laba1 {

        private static final char cr = '(';
        private static final char cl = ')';
        private static final char sr = '[';
        private static final char sl = ']';
        private static final char fr = '{';
        private static final char fl = '}';

        public static boolean isCorrectBrackets(String value) {
            Stack<Character> stack = new Stack<Character>();
            char[] array = value.toCharArray();
            for(char c : array) {
                if((c == cl || c == sl || c == fl) && stack.empty()) {
                    return false;
                }

                if(c == cr || c == sr || c == fr) {
                    stack.push(c);
                }

                if((c == cl || c == sl || c == fl) && !stack.empty()) {
                    char t = stack.peek();
                    switch(c) {
                        case ')':
                            if(t == '(') stack.pop();
                            break;
                        case ']':
                            if(t == '[') stack.pop();
                            break;
                        case '}':
                            if(t == '{') stack.pop();
                            break;
                    }
                }
            }
            if(stack.empty()) {
                return true;
            } else {
                return false;
            }
        }

        public static void main(String[] args) {
            // тестируем
            Scanner in = new Scanner(System.in);
            System.out.print("Введите строку скобок: ");
            String str=in.nextLine();
            if(isCorrectBrackets(str)) {
                System.out.println("Скобки расставлены правильно");
            } else {
                System.out.println("Скобки расставлены неправильно");
            }


        }

    }