package part_1;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Hangman_game {

    private static final String[] WORDS = {"коммуникация", "транслятор", "информация", "операция", "мотивация", "сенла"};
    private static final int MAX_LIVES = 7;

    public static void show_lives(int lives) {
        String show_lives = "♥".repeat(lives);
        show_lives += "♡".repeat(MAX_LIVES - lives);
        System.out.println("Количество жизней равно: " + show_lives);
    }

    public static void show_hangman(int lives) {
        String[] figure = {
                """
                ____
                |/   |
                |   \s
                |   \s
                |   \s
                |   \s
                |
                |_____""",
                                """
                ____
                |/   |
                |   (_)
                |   \s
                |   \s
                |   \s
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (_)
                |    |
                |    |   \s
                |   \s
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (_)
                |   \\|
                |    |
                |   \s
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (_)
                |   \\|/
                |    |
                |   \s
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (_)
                |   \\|/
                |    |
                |   /\s
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (_)
                |   \\|/
                |    |
                |   / \\
                |
                |_____""",
                                """
                 ____
                |/   |
                |   (хх)
                |   /|\\
                |    |
                |   | |
                |
                |_____"""
        };
        System.out.println(figure[MAX_LIVES - lives]);
    }


    public static void main(String[] args) {
        int lives = MAX_LIVES;
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String word_to_guess = WORDS[random.nextInt(WORDS.length)];
        String encoded = "_".repeat(word_to_guess.length());
        StringBuilder encoded_guessed_word = new StringBuilder(encoded);
        Set<String> guessed_chars = new HashSet<>();

        while (lives > 0 && encoded_guessed_word.indexOf("_") != -1) {
            System.out.println("Загаданное слово: " + encoded_guessed_word);
            show_lives(lives);
            show_hangman(lives);

            char entered_char;
            while (true) {
                System.out.println("Введите букву: ");
                String entered_str = input.next().toLowerCase();
                if (entered_str.length() == 1 && Character.isLetter(entered_str.charAt(0))) {
                    entered_char = entered_str.charAt(0);
                    break;
                } else {
                    System.out.println("Ошибка ввода: введите только одну букву.");
                }
            }

            if (guessed_chars.contains(String.valueOf(entered_char))) {
                lives--;
                System.out.println("Буква " + entered_char + " уже была найдена. Не стоит разбрасываться своими жизнями :)");
            } else {
                if (word_to_guess.indexOf(entered_char) >= 0) {
                    for (int i = 0; i < word_to_guess.length(); i++) {
                        if (word_to_guess.charAt(i) == entered_char) {
                            guessed_chars.add(String.valueOf(entered_char));
                            encoded_guessed_word.setCharAt(i, entered_char);
                        }
                    }
                } else {
                    lives--;
                    System.out.println("Буква " + entered_char + " не найдена.");
                }
            }
        }
        if (lives > 0) {
            System.out.println("Поздравляю! ฅ^•ﻌ•^ฅ \nВы угадали слово: " + word_to_guess);
        } else {
            show_hangman(lives);
            System.out.println("Вы проиграли. Было загадано слово: " + word_to_guess);
        }
        input.close();
    }
}
