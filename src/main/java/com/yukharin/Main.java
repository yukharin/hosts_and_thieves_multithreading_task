package com.yukharin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world " + inputNameFromStdin());
    }

    public static String inputNameFromStdin() {
        System.out.println("Please enter your name here");
        String input = null;
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
            if (input.isEmpty() || input == null) {
                System.out.println("Name can not be empty");
                inputNameFromStdin();
            }
        }
        return input;
    }
}
