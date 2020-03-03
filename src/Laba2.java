import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Laba2 {


        public static void main(String[] args) {

            Stack<String> stack = new Stack<>();
            Deque<Double> chisla = new ArrayDeque<>();
            Scanner in = new Scanner(System.in);
            double Sum = 0;
            System.out.print("Введите выражение: ");
            String Expression = in.nextLine();
            String[] symbol = new String[Expression.length() + 100];
            int i = 1;
            int k = 1;
            symbol[0] = String.valueOf(Expression.charAt(0));
            //Разбитие строки на элементы и запихивание в массив
            while (k < Expression.length()) {
                if ((symbol[i - 1].charAt(0) >= '0' && symbol[i - 1].charAt(0) <= '9' && Expression.charAt(k) >= '0' && Expression.charAt(k) <= '9')
                        || (symbol[i - 1].length() > 1 && ((symbol[i - 1].charAt(1) >= '0' && symbol[i - 1].charAt(1) <= '9' && Expression.charAt(k) >= '0' && Expression.charAt(k) <= '9')))
                        || (i > 1 && Expression.charAt(k) >= '0' && Expression.charAt(k) <= '9' && symbol[i - 1].charAt(0) == '-' && symbol[i - 2].charAt(0) == '(')
                        ||(Expression.charAt(k) >= '0' && Expression.charAt(k) <= '9'&&i==1&&symbol[i-1].charAt(0)=='-')
                ) {
                    symbol[i - 1] = symbol[i - 1] + Expression.charAt(k);
                } else {

                    symbol[i] = "" + Expression.charAt(k);



                    if ((symbol[i - 1].charAt(0) <= '9' && symbol[i - 1].charAt(0) >= '0')|| (symbol[i - 1].length() > 1 && symbol[i - 1].charAt(1) >= '0' && symbol[i - 1].charAt(1) <= '9')) {
                        chisla.push(Double.parseDouble(symbol[i - 1]));
                    } else {

                        if (symbol[i - 1].charAt(0) == ')') {
                            boolean key = false;
                            while (1 > 0) {
                                if (stack.size() > 0 && stack.peek().equals("+")) {
                                    chisla.push(chisla.pop() + chisla.pop());

                                    stack.pop();
                                }
                                if (stack.size() > 0 && stack.peek().equals("-")) {
                                    chisla.push(-chisla.pop() + chisla.pop());
                                    stack.pop();
                                }
                                if (stack.size() > 0 && stack.peek().equals("/")) {
                                    double pochka = chisla.pop();
                                    chisla.push(chisla.pop() / pochka);
                                    stack.pop();
                                }
                                if (stack.size() > 0 && stack.peek().equals("*")) {
                                    chisla.push(chisla.pop() * chisla.pop());
                                    stack.pop();
                                }
                                if (stack.peek().equals("(")) {
                                    stack.pop();
                                    break;
                                }

                            }


                        } else if (stack.size() > 0 && (symbol[i - 1].charAt(0) == '*' || symbol[i - 1].charAt(0) == '/') && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                            if (stack.peek().equals("*")) {
                                chisla.push(chisla.pop() * chisla.pop());
                                stack.pop();
                            } else if (stack.peek().equals("/")) {
                                double pack = chisla.pop();
                                chisla.push(chisla.pop() / pack);
                                stack.pop();
                            }
                            stack.push(symbol[i - 1]);
                        } else if (symbol[i - 1].charAt(0) == '(' || stack.size() < 1 || symbol[i - 1].charAt(0) == '*' || symbol[i - 1].charAt(0) == '/') {
                            stack.push(symbol[i - 1]);
                        } else {
                            if ((symbol[i - 1].charAt(0) == '+' || symbol[i - 1].charAt(0) == '-') && !stack.peek().equals("(")) {
                                if (stack.size() > 0 && stack.peek().equals("+")) {
                                    chisla.push(chisla.pop() + chisla.pop());

                                    stack.pop();
                                    stack.push(symbol[i - 1]);
                                } else if (stack.size() > 0 && stack.peek().equals("-")) {
                                    chisla.push(-chisla.pop() + chisla.pop());
                                    stack.pop();
                                    stack.push(symbol[i - 1]);
                                } else if (stack.size() > 0 && stack.peek().equals("/")) {
                                    double pack = chisla.pop();
                                    chisla.push(chisla.pop() / pack);
                                    stack.pop();
                                    stack.push(symbol[i - 1]);
                                } else if (stack.size() > 0 && stack.peek().equals("*")) {
                                    chisla.push(chisla.pop() * chisla.pop());
                                    stack.pop();
                                    stack.push(symbol[i - 1]);
                                }
                            } else stack.push(symbol[i - 1]);
                        }


                    }

                    i++;
                }
                k++;

            }





            if (symbol[i - 1].charAt(0) <= '9' && symbol[i - 1].charAt(0) >= '0') {
                chisla.push(Double.parseDouble(symbol[i - 1]));
            }
            else {

                if (symbol[i - 1].charAt(0) == ')') {
                    boolean key = false;
                    while (1 > 0) {
                        if (stack.size() > 0 && stack.peek().equals("+")) {
                            chisla.push(chisla.pop() + chisla.pop());

                            stack.pop();
                        }
                        if (stack.size() > 0 && stack.peek().equals("-")) {
                            chisla.push(-chisla.pop() + chisla.pop());
                            stack.pop();
                        }
                        if (stack.size() > 0 && stack.peek().equals("/")) {
                            double pochka = chisla.pop();
                            chisla.push(chisla.pop() / pochka);
                            stack.pop();
                        }
                        if (stack.size() > 0 && stack.peek().equals("*")) {
                            chisla.push(chisla.pop() * chisla.pop());
                            stack.pop();
                        }
                        if (stack.peek().equals("(")) {
                            stack.pop();
                            break;
                        }
                    }


                } else if (symbol[i - 1].charAt(0) == '(' || stack.size() < 1 || symbol[i - 1].charAt(0) == '*' || symbol[i - 1].charAt(0) == '/') {
                    stack.push(symbol[i - 1]);
                } else {
                    if ((symbol[i - 1].charAt(0) == '+' || symbol[i - 1].charAt(0) == '-') && !stack.peek().equals("(")) {

                        if (stack.size() > 0 && stack.peek().equals("+")) {
                            chisla.push(chisla.pop() + chisla.pop());

                            stack.pop();
                        } else if (stack.size() > 0 && stack.peek().equals("-")) {
                            chisla.push(-chisla.pop() + chisla.pop());
                            stack.pop();
                        } else if (stack.size() > 0 && stack.peek().equals("/")) {
                            double pack = chisla.pop();
                            chisla.push(chisla.pop() / pack);
                            stack.pop();
                        } else if (stack.size() > 0 && stack.peek().equals("*")) {
                            chisla.push(chisla.pop() * chisla.pop());
                            stack.pop();
                        }
                    } else stack.push(symbol[i - 1]);
                }

            }


            while (stack.size() > 0) {

                if (stack.size() > 0 && stack.peek().equals("+")) {
                    chisla.push(chisla.pop() + chisla.pop());

                    stack.pop();
                }
                if (stack.size() > 0 && stack.peek().equals("-")) {
                    chisla.push(-chisla.pop() + chisla.pop());
                    stack.pop();
                }
                if (stack.size() > 0 && stack.peek().equals("/")) {
                    double pack = chisla.pop();
                    chisla.push(chisla.pop() / pack);
                    stack.pop();
                }
                if (stack.size() > 0 && stack.peek().equals("*")) {
                    chisla.push(chisla.pop() * chisla.pop());
                    stack.pop();
                }

            }


            System.out.print(chisla.peek());

        }


    }
