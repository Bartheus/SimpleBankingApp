package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static String username;
    public static String password;
    public static double money = 1000;

    public static NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    public static void main(String[] args) {
        greeting();
    }

    //GREETING BANK MENU
    public static void greeting() {
        System.out.println("\n");
        System.out.println("Welcome in our Bank");
        System.out.println("What u would like to do?");
        System.out.println("\n");
        System.out.println("(1) Login     (2) Register      (0) EXIT");

        Scanner userInputReader = new Scanner(System.in);
        while (true) {
            char userInput = userInputReader.next().charAt(0);
            switch (userInput) {
                case '1' ->
                        //login function
                        login();
                case '2' ->
                        //register function
                        register();
                case '0' ->
                        //quit app
                        quit();
                default -> System.out.println("Wrong Input");
            }
        }
    }

    //LOGIN FUCNTION LOGIC
    public static void login() {
        Scanner userCredentials = new Scanner(System.in);

        try {
            outer:
            while (true) {
                //Read input from a user (username and password)
                System.out.print("Username: ");
                String username = userCredentials.nextLine();

                System.out.print("Password: ");
                String password = userCredentials.nextLine();

                // Create reader to read user registered data
                BufferedReader readUserInput = new BufferedReader(
                        new FileReader("Register.txt"));
                // Read user input, first username then password in next line
                // (simple logic for only one user because every new registration
                // with BufferedWriter will be overridden

                String readUsername = readUserInput.readLine();
                String readUserPassword = readUserInput.readLine();

                //check every line if is not a null using while loop
                while ((readUsername != null && readUserPassword != null)) {
                    // check if user input equals the data in a file, check if on 1st line username equals
                    // the given one from user and check the second line for a password
                    if (readUsername.equals(username) && readUserPassword.equals(password)) {
                        bankingMenu();
                        break outer;
                    } else {
                        System.out.println("Wrong username or password.");
                        break;
                    }
                }
                readUserInput.close();
            }
        } catch (Exception error) {
            System.out.println("Wrong username or password.");
            error.printStackTrace();
        }
    }
    // USER REGISTER LOGIC
    public static void register() {
        System.out.println("REGISTER");
        // username and password scanner
        Scanner newUsernameReader = new Scanner(System.in);
        Scanner newPasswordReader = new Scanner(System.in);
        // read user input
        System.out.print("Your username: ");
        String newUsername = newUsernameReader.nextLine();
        System.out.print("Your password: ");
        String newPassword = newPasswordReader.nextLine();

        // write user input to the file
        try {
            BufferedWriter writeUserInput = new BufferedWriter(
                    new FileWriter("Register.txt"));
            writeUserInput.write(newUsername + '\n');
            writeUserInput.write(newPassword + '\n');
            writeUserInput.close();
        } catch (Exception error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
        System.out.println("\n");
        System.out.println("You have successfully created your account.");
        greeting();
    }

    // FUNCTION TO QUIT APP
    public static void quit() {
        System.exit(0);
    }
    // Banking application menu
    public static void bankingMenu() throws IOException {
        System.out.println("-------------------------------");
        System.out.println("| (1) Check your balance.     |");
        System.out.println("| (2) Make a bank transfer.   |");
        System.out.println("| (3) Take a Credit.          |");
        System.out.println("| (4) Logout.                 |");
        System.out.println("| (0) Quit.                   |");
        System.out.println("-------------------------------");

        Scanner userInputReader = new Scanner(System.in);
        char userInput = userInputReader.next().charAt(0);

        switch (userInput) {
            case '1' -> {
                checkBalance();
                break;
            }
            case '2' -> {
                bankTransfer();
            }
            case '3' -> {
                System.out.println("Take A Credit");
                takeCredit();
            }
            case '4' -> {
                logout();
            }
            case '0' -> {
                //Quit app
                quit();
            }
            default -> {
                System.out.println("Wrog input");
            }
        }

    }

    public static void confirmInput() throws IOException {
        System.out.print("[OK]");
        System.in.read();
    }

    public static void checkBalance() throws IOException {
        String balance = formatter.format(money);
        System.out.println("Your balance: " + balance);
        confirmInput();
        bankingMenu();
    }

    public static void takeCredit() throws IOException {
        Scanner amountValueReader = new Scanner(System.in);
        Scanner userInputReader = new Scanner(System.in);

        System.out.println("Please choose one of our offers:");
        System.out.println("(1)  5000$");
        System.out.println("(2)  10000$");
        System.out.println("(3)  15000$");
        System.out.println("(4)  25000$");
        System.out.println("(5)  50000$");
        System.out.println("(6)  Or choose your own amount:");

        char userInput = userInputReader.next().charAt(0);

        int[] creditAmounts = {5000, 10000, 15000, 25000, 50000};

        switch (userInput) {
            case '1' -> {
                money = money + creditAmounts[0];
                break;
            }
            case '2' -> {
                money = money + creditAmounts[1];
                break;
            }
            case '3' -> {
                money = money + creditAmounts[2];
                break;
            }
            case '4' -> {
                money = money + creditAmounts[3];
                break;
            }
            case '5' -> {
                money = money + creditAmounts[4];
                break;
            }
            case '6' -> {
                //TODO input validation
                System.out.print("Amount: ");
                int amountValue = amountValueReader.nextInt();
                money = money + amountValue;
                break;
            }
            default -> System.out.println("Wrong Input");
        }
        String balance = formatter.format(money);
        System.out.println("Your actual balance: " + balance);
        confirmInput();
        bankingMenu();
    }

    public static void bankTransfer() throws IOException {
        Scanner userInputReader = new Scanner(System.in);

        // Insert recipient data
        System.out.println("Bank Transfer");
        System.out.println("Name: ");
        String personName = userInputReader.nextLine();
        System.out.println("Surname: ");
        String personSurname = userInputReader.nextLine();
        System.out.println("Title: ");
        String transferTitle = userInputReader.nextLine();
        System.out.println("Banking nr : ");
        String personBankNr = userInputReader.nextLine();
        System.out.println("Amount : ");
        double transferAmount = userInputReader.nextDouble();

        money = money - transferAmount;

        System.out.println("\n");

        String textinBoldTransfer = "Transfer Data: ";
        System.out.println("\033[0;1m" + textinBoldTransfer+": ");

        System.out.println(personName + " " + personSurname);
        System.out.println(transferTitle);
        System.out.println(personBankNr);
        System.out.println(transferAmount);
        System.out.println("\n");

        String textinBoldBalance = "Your actual balance: ";
        System.out.println("\033[0;1m" +textinBoldBalance+ "" +  money );

        confirmInput();
        bankingMenu();
    }

    public static void logout() {
    username = "";
    password = "";
    System.out.println("You have been successfully logged out.");
    greeting();
    }
}


