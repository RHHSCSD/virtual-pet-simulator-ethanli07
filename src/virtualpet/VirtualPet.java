/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package virtualpet;
import java.util.*;
import java.io.*;
import java.lang.Exception;

/********************************************
* Program: Virtual Pet Simulator
* Date: March 18, 2024
* Author: Ethan Li
* Description: Simulates ownership of a pet
*********************************************/

public class VirtualPet {
    public static void main(String[] args) throws Exception {
        //Initialize Objects
        Scanner kb = new Scanner(System.in);
        
        //Splash Art
        drawStartScreen();
        
        
        //Variables
        boolean gameExit = false;
        boolean petCreated = false;
        boolean playPet = false;
        
        String username = "";
        String password = "";
        String petSelected = "";
        String petName = "";
        
        int maxPetStatsArray[] = new int[4];
        int currentPetStatsArray[] = new int[4];
        
        int userMoney = 0;
        
        boolean userBooleanData[] = {gameExit, petCreated, playPet};
        String userStringData[] = {username, password, petSelected, petName};
        
        //Login 
        boolean entryAllowed = login();
        
        //Prevent user entry if they fail the login
        if (entryAllowed == false) {
          System.exit(0);
        }
        
        //Menu Options
        while (gameExit == false) {
            if (petCreated == false) {
                petCreated = petNotCreated(petCreated);
            }
            else { //pet has alreay been created
                playPet = petIsCreated(playPet);
            }

            //Let's the user select a pet and generates their name and stats
            if ((petCreated == true) && (petSelected.equals(""))) {
                petSelected = createPet();
                petName = namePet();
                maxPetStatsArray = petStats();
                currentPetStatsArray = maxPetStatsArray.clone();
                for (int i = 0; i < currentPetStatsArray.length; i++) {
                    currentPetStatsArray[i] -= 2;
                }
            }
            else if (playPet == true) { //if the pet is already created, let the user play with them
                System.out.println("1. Play a game      2. Interact with my pet");
                System.out.print("Do you want to play a game or interact with your pet? ");
                int userGameMenuChoice = kb.nextInt(); 
                
                if (userGameMenuChoice == 1) { //play a game with the pet
                    System.out.println("1. Number guessing game      2. Matching game");
                    System.out.print("Which game do you want to play: ");
                    int gameSelection = kb.nextInt();

                    if (gameSelection == 1) {
                        userMoney = numberGuessingGame(userMoney);
                    }

                    else if (gameSelection == 2) {
                        userMoney = letterUnscrambleGame(userMoney);  
                    }
                }
                
                else if (userGameMenuChoice == 2) {//interat with the pet
                    System.out.println("1. Play      2. Feed      3. Groom");
                    System.out.print("How do you wish to interact with your pet: ");
                    int userInteractionChoice = kb.nextInt();
                    
                    if (userMoney < 20) {//checks if they can afford it
                        System.out.println("Insufficient Money!");
                    }
                    else if (currentPetStatsArray[userInteractionChoice] == maxPetStatsArray[userInteractionChoice]) { //checks if the pet's stat is already maxed
                        System.out.println("You are already maxed out in this stat!");
                    }
                    else {
                        userMoney -= 20;
                        currentPetStatsArray[userInteractionChoice] += 1;
                    }
                }
            } // playPet if loop
        } //gameExit while loop
        
        saveUserData(userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoney);
    }

    //Helper Methods
    public static void drawStartScreen() {
        //Title
        System.out.println("                 Cat Simulator     \n");
        
        //Splash Art
        System.out.println("          *        &&&&&&&&&        *");
        System.out.println("                  &&&&&&&&&&&");
        System.out.println("                 &&&&&&&&&&&&&             *");
        System.out.println("   *             &&&&&&&&&&&&&");
        System.out.println("                 &&&&&&&&&&&&&   *");
        System.out.println("         *        &&&&&&&&&&&");
        System.out.println("                   &&&&&&&&&           *");
        System.out.println("          |\\___/|     /\\___/\\");
        System.out.println("          )     (     )    ~(");
        System.out.println("         =\\     /=   =\\~    /=");
        System.out.println("           )===(       ) ~ (");
        System.out.println("          /     \\     /     \\");
        System.out.println("          |     |     ) ~   (");
        System.out.println("         /       \\   /     ~ \\");
        System.out.println("         \\       /   \\~     ~/");
        System.out.println("  _/\\_/\\_/\\__  _/_/\\_/\\__~__/_/\\_/\\_/\\_/\\_/\\_");
        System.out.println("  |  |  |  |( (  |  |  | ))  |  |  |  |  |  |");
        System.out.println("  |  |  |  | ) ) |  |  |//|  |  |  |  |  |  |");
        System.out.println("  |  |  |  |(_(  |  |  (( |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |\\)|  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
    }
    
    public static boolean login() {
        Scanner kb = new Scanner(System.in);
        
        //Variables
        final String GAME_NAME = "snoopy";
        final String GAME_PASSWORD = "toto";
        int incorrectPasswordCount = 0;
        boolean entryAllowed = false;
        
        //3 attempts for the user to get their login right
        while (incorrectPasswordCount < 3 && entryAllowed == false) {
            System.out.print("Username: ");
            String userName = kb.nextLine();

            System.out.print("Password: ");
            String userPassword = kb.nextLine();
            
            if (userName.equals(GAME_NAME) && userPassword.equals(GAME_PASSWORD)) {
                entryAllowed = true;
            }
            else {
                incorrectPasswordCount += 1;
                System.out.println("Incorrect!");
            }
        }
        
        return entryAllowed;
    }
    
    public static boolean petNotCreated(boolean petCreated) {
        Scanner kb = new Scanner(System.in);
        
        //Provides menu options
        System.out.println("\n  1. Start     2. Instructions     3. Exit"   );
        System.out.print("\nWhere would you like to go? ");
        String userMenuOption = kb.nextLine();
        userMenuOption = userMenuOption.toLowerCase();

        //Accepts user inputs
        switch (userMenuOption) {
            case "1": 
            case "start":
                petCreated = true;
                break;
            case "2": 
            case "instructions":
                break;
            case "3":
            case "exit":
                System.exit(0);
                break;
        }
        
        return petCreated;
    }
    
    public static boolean petIsCreated(boolean playPet) {
        Scanner kb = new Scanner(System.in);
        
        //Provides menu options
        System.out.println("\n  1. Play/Interact     2. Instructions     3. Exit"   );
        System.out.print("\nWhere would you like to go? ");
        String userMenuOption = kb.nextLine();
        userMenuOption = userMenuOption.toLowerCase();

        //Accepts user inputs
        switch (userMenuOption) {
            case "1": 
            case "play":
            case "interact":
                playPet = true;
                break;
            case "2": 
            case "instructions":
                playPet = false;
                break;
            case "3":
            case "exit":
                System.exit(0);
                break;
         }
        
        return playPet;
    }
    
    public static String createPet() {
        Scanner kb = new Scanner(System.in);
        
        //Pet Selection
        System.out.println("Cat, Dog, Rabbit");
        System.out.print("What pet would you like to choose? ");
        String petSelected = kb.nextLine();

        petSelected = petSelected.toLowerCase();
        while ((petSelected.equals("cat") == false) && (petSelected.equals("dog") == false) && (petSelected.equals("rabbit") == false)) {
            System.out.println("Invalid Choice of Pet!");
        }
        
        //Ouput and returns the user's choice
        System.out.println("You have selected the " + petSelected);
        return petSelected;
    }
    
    public static String namePet() {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        String petName = "";
        final int MIN_NAME_LENGTH = 4;
        
        //Asks the user what they want to do
        System.out.print("Would you like to name the pet yourself? ");
        String customName = kb.nextLine();
        customName = customName.toLowerCase();

        //If the user wants to name the pet themselves
        if (customName.equals("yes") == true) {
            System.out.print("What would you like to name your pet: ");
            petName = kb.nextLine();
        }
        else { //Generates a custom name if they don't
            String vowelList = "aeiou";
            String consonantList = "bcdfghjklmnpqrstvwxyz";

            int maxLetterCount = r.nextInt(5) + MIN_NAME_LENGTH;

            for (int i = 0; i < maxLetterCount; i = i + 2) {
                petName += consonantList.charAt(r.nextInt(21));
                if (r.nextDouble() < 0.2) {
                    petName += petName.charAt(petName.length()-1);
                    i += 1;
                }

                petName += vowelList.charAt(r.nextInt(5));
                if (r.nextDouble() < 0.2) {
                    petName += petName.charAt(petName.length()-1);
                    i += 1;
                }
            }
        }
        
        //Output and returns the pet's name
        System.out.println("Your pet, named " + petName + ", has been born!");
        return petName;
    }
    
    public static int[] petStats() {
        Random r = new Random();
        
        //Variables
        final int MAX_PET_STATS = 12;
        int maxPetStatsArray[] = {2, 2, 2, 2}; 
        
        //Generates the pet's max stats
        for (int i = 0; i < MAX_PET_STATS; i++) {
            maxPetStatsArray[r.nextInt(4)] += 1;
        } 
        
        //Outputs and returns the pet's max stats
        System.out.println("Max Pet Health: " + maxPetStatsArray[0] + "\nMax Pet Hunger: " + maxPetStatsArray[1] + "\nMax Pet Energy: " + maxPetStatsArray[2] + "\nMax Pet Happiness: " + maxPetStatsArray[3]);
        return maxPetStatsArray; 
    }
    
    public static int numberGuessingGame(int userMoney) {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        int numberToBeGuessed = (r.nextInt(100) + 1);
        boolean numberGuessed = false;
        int numberOfIncorrectGuesses = 0;
        int moneyEarned = 100;
        
        //Checks if the user's guess is correct, gives them feedback if it isn't
        while ((numberGuessed == false) && (numberOfIncorrectGuesses < 5)) {
            System.out.print("Guess a number: ");
            int userGuess = kb.nextInt();

            if (userGuess < numberToBeGuessed) {
                System.out.println("Too Low");
                moneyEarned /= 2;
                numberOfIncorrectGuesses += 1;
            }
            else if (userGuess > numberToBeGuessed) {
                System.out.println("Too High");
                moneyEarned /= 2;
                numberOfIncorrectGuesses += 1;
            }
            else {
                System.out.println("Correct! Congragulations you have earned $" + moneyEarned);
                userMoney += moneyEarned;
                System.out.println("You currently have $" + userMoney);
                numberGuessed = true;
            }
        }
        
        //Tells the user if they've made too many incorrect guesses
        if (numberOfIncorrectGuesses == 5) {
            System.out.println("Too many incorrect guesses. You lose!");
        }
        
        return userMoney;
    }
    
    public static int letterUnscrambleGame(int userMoney) {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        String stringLetterList = "AABBCCDDEE";
        String stringListHidden = "**********";
        String stringToBeGuessed = "";
        int moneyEarned = 100;
        
        //Creates a scrambled string
        while (stringLetterList.equals("") == false) {
            int letterIndex = r.nextInt(stringLetterList.length());
            stringToBeGuessed += stringLetterList.charAt(letterIndex);
            stringLetterList = (stringLetterList.substring(0, letterIndex) + stringLetterList.substring(letterIndex + 1));
        }
        
        //Checks if the user's guess matches the hidden, scrambled string
        while (stringListHidden.equals(stringToBeGuessed) == false) {
            System.out.print("Guess your first index: ");
            int firstIndexGuessed = kb.nextInt();

            System.out.println(stringToBeGuessed);

            System.out.print("Guess your second index: ");
            int secondIndexGuessed = kb.nextInt();

            if (stringToBeGuessed.charAt(firstIndexGuessed) == stringToBeGuessed.charAt(secondIndexGuessed)) {
                stringListHidden = (stringListHidden.substring(0, Math.min(firstIndexGuessed, secondIndexGuessed)) + stringToBeGuessed.charAt(firstIndexGuessed) + stringListHidden.substring(Math.min(firstIndexGuessed, secondIndexGuessed) + 1, Math.max(firstIndexGuessed, secondIndexGuessed)) + stringToBeGuessed.charAt(firstIndexGuessed) + stringListHidden.substring(Math.max(firstIndexGuessed, secondIndexGuessed) + 1));
                System.out.println(stringListHidden);
            }
            else {
                System.out.println("Letter at index " + firstIndexGuessed + " is " + stringToBeGuessed.charAt(firstIndexGuessed));
                System.out.println("Letter at index " + secondIndexGuessed + " is " + stringToBeGuessed.charAt(secondIndexGuessed));
                moneyEarned /= 2;
            } 
        }
        
        //Congragulates the user if they won
        System.out.println("You solved it! Congragulations you have earned " + moneyEarned);
        userMoney += moneyEarned;
        System.out.println("You currently have $" + userMoney); 
        
        return userMoney;
    }
    
    public static void saveUserData(boolean userBooleanData[], String userStringData[], int maxPetStatsArray[], int currentPetStatsArray[], int userMoney) throws Exception {
        File userFile = new File(userStringData[0] + ".txt");
        PrintWriter output  = new PrintWriter(userFile);
        
        //Iterates through arrays, saving data to a File
        for (int i = 0; i < 3; i++) {
            output.println(userBooleanData[i]);
        }
        for (int i = 0; i < 3; i++) {
            output.println(userStringData[i]);
        }
        output.println(Arrays.toString(maxPetStatsArray)); 
        output.println(Arrays.toString(currentPetStatsArray));
        output.println(userMoney);
        
        output.close();
    }
}
