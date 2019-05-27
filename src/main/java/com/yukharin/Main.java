package com.yukharin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bye bye world");
        System.out.println("Hello world " + inputNameFromStdin());
    }
  
    public static String inputNameFromStdin() {
        System.out.println("Please enter your name here");
        String input = null;
        String regex = "^[a-zA-Z]+$";
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
            while (input.isEmpty() || input == null || !input.matches(regex)) {
                System.out.println("Name can not be empty and should have only an uppercase and lowercase characters");
                input = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }
}
