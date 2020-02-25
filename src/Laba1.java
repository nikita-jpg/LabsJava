import java.util.Scanner;

public class Laba1 {

    public static void main(String[] args) {

        System.out.print("Введите через пробел количество купюр различного номинала: 1000, 500, 100, 30 \n");
        Scanner in = new Scanner(System.in);
        int arr[] = new int[4];
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int sum = a * 1000 + b * 500 + c * 100 + d * 30;

        System.out.print("Введите сумму, которую желаете получить \n");
        int x = in.nextInt();
        if (x > sum || (x % 10 != 0)) {
            System.out.print("Данную сумму невозможно выдать. Обратитесь в другой банкомат.\n");
        } else {
            while (x % 100 != 0 && x > 0 && d > 0) {
                x -= 30;
                arr[3]++;
                d--;//добавил уменьшение d
            }
            while (a > 0 && x - 1000 >= 0) {
                x -= 1000;
                a--;
                arr[0]++;
            }
            while (b > 0 && x - 500 >= 0) {
                x -= 500;
                b--;
                arr[1]++;
            }
            while (c > 0 && x - 100 >= 0) {
                x -= 100;
                c--;
                arr[2]++;
            }
            if (x == 0) {
                System.out.print(arr[0] + " купюр(ы) наминалом 1000, " + arr[1] + " купюр(ы) наминалом 500, \n"
                        + arr[2] + " купюр(ы) наминалом 100, " + arr[3] + " купюр(ы) наминалом 30");
            }
            else System.out.print("Данную сумму невозможно выдать. Обратитесь в другой банкомат.\n");//добавил эту строчку
        }

    }
}