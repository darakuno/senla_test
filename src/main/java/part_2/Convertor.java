package part_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Convertor {

    public final static int AMOUNT_COURSES = 5;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Double> courses = new HashMap<>();

        while (courses.size() < AMOUNT_COURSES) {
            System.out.println("Введите название валюты (Например, USD, EUR, GBP): ");
            String name = input.next().toUpperCase();
            if (courses.containsKey(name)) {
                System.out.println("Эта валюта уже добавлена. Попробуйте другую.");
                continue;
            }
            double course = 0;
            boolean flag_correct_input = false;
            while (!flag_correct_input)
            {
                try {
                    System.out.println("Введите курс: ");
                    course = input.nextDouble();
                    flag_correct_input = true;
                }
                catch (Exception e) {
                    System.out.println("Ошибка ввода. Пожалуйста, введите числовое значение для курса.");
                    input.next();
                }
            }
            courses.put(name, course);
        }

        input.nextLine();
        while (true) {
            System.out.print("Введите валюту из введенных ранее, которую собираетесь конвертировать (или нажмите Enter для выхода): ");
            String from_currency = input.nextLine().toUpperCase();

            if (from_currency.isEmpty()) {
                System.out.println("Выход из программы.");
                break;
            }

            if (courses.containsKey(from_currency)) {
                double value = 0;
                boolean flag_correct_value = false;
                while (!flag_correct_value) {
                    try {
                        System.out.print("Введите сумму для конвертации: ");
                        value = input.nextDouble();
                        flag_correct_value = true;
                    } catch (Exception e) {
                        System.out.println("Ошибка ввода. Пожалуйста, введите числовое значение для суммы.");
                        input.next();
                    }
                }
                input.nextLine();

                System.out.println("Конвертация:");
                for (Map.Entry<String, Double> entry : courses.entrySet()) {
                    String to_currency = entry.getKey();
                    if (!from_currency.equals(to_currency)) {
                        double converted_value = value * (entry.getValue() / courses.get(from_currency));
                        System.out.printf(value + " " + from_currency + " = " + converted_value + " " + to_currency + "\n");
                    }
                }
            } else {
                System.out.print("Данной валюты нет в конвертере. Попробуйте еще раз.\n");
            }
        }
        input.close();
    }
}
