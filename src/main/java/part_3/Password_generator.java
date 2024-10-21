package part_3;

import java.util.Random;
import java.util.Scanner;

public class Password_generator {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 12;

    public static char generate_symbol(int symbol_type) {
        Random random = new Random();
        int asciiCode = switch (symbol_type) {
            case 0 -> random.nextInt(26) + 65; // 65 - 'A', 90 - 'Z'
            case 1 -> random.nextInt(26) + 97; // 97 - 'a', 122 - 'z'
            case 2 -> {
                String specialChars = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
                yield specialChars.charAt(random.nextInt(specialChars.length()));
            }
            case 3 -> random.nextInt(10) + 48; // 48 - '0', 57 - '9'
            default -> throw new IllegalArgumentException("Неверный тип символа");
        };
        return (char) asciiCode;
    }
    public static StringBuilder generate_password(int pass_length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(pass_length);
        for (int i=0; i <= 3; i++) {
            password.append(generate_symbol(i));
        }
        for (int i = 4; i < pass_length; i++) {
            int type_symbol = random.nextInt(4);
            password.append(generate_symbol(type_symbol));
        }
        return shuffle_password(password);
    }

    public static StringBuilder shuffle_password(StringBuilder password) {
        Random random = new Random();
        for (int i = password.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(j));
            password.setCharAt(j, temp);
        }
        return password;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Добро пожаловать в генератор паролей!");

            int pass_length = 0;
            boolean flag_correct_length = false;
            System.out.println("Введите желаемую длину пароля от 8 до 12 символов (или введите 0 для выхода):: ");

            while (!flag_correct_length) {
                try {
                    pass_length = input.nextInt();
                    if (pass_length >= MIN_LENGTH && pass_length <= MAX_LENGTH || pass_length == 0) {
                        flag_correct_length = true;
                    }
                    else {
                        System.out.println("Длина пароля должна быть от 8 до 12 символов. Попробуйте еще раз.");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка ввода. Попробуйте еще раз.");
                    input.next();
                }
            }

            if (pass_length == 0) {
                System.out.println("Выход из программы.");
                break;
            }

            StringBuilder password = generate_password(pass_length);
            System.out.println("Сгенерированный пароль: " + password);
        }
        input.close();
    }
}
