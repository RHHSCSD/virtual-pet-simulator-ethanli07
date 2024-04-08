/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package virtualpet;
import java.util.*;
import java.lang.Math;

/********************************************
* Program: Virtual Pet Simulator
* Date: March 18, 2024
* Author: Ethan Li
* Description: Simulates ownership of a pet
*********************************************/

public class VirtualPet {
    public static void main(String[] args) {
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
        
        //Initialize Objects
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        final String GAME_NAME = "snoopy";
        final String GAME_PASSWORD = "toto";
        final int MIN_NAME_LENGTH = 4;
        final int MAX_PET_STATS = 20;
        
        int incorrectPasswordCount = 0;
        int userMoney = 0;
        
        boolean gameExit = false;
        boolean entryAllowed = false;
        boolean namePet = false;
        boolean playPet = false;
        
        //Login 
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
        
        if (incorrectPasswordCount == 3) {
            System.exit(0);
        }
        
        //Menu Options
        while (gameExit == false) {
            if (namePet == false) {
                System.out.println("\n  1. Start     2. Instructions     3. Exit"   );
                System.out.print("\nWhere would you like to go? ");
                String userMenuOption = kb.nextLine();
                userMenuOption = userMenuOption.toLowerCase();

                //Menu
                switch (userMenuOption) {
                    case "1": 
                    case "start":
                        namePet = true;
                        break;
                    case "2": 
                    case "instructions":
                        break;
                    case "3":
                    case "exit":
                        gameExit = true;
                        break;
                }
            }
            else { //pet has alreay been created
                System.out.println("\n  1. Play/Interact     2. Instructions     3. Exit"   );
                System.out.print("\nWhere would you like to go? ");
                String userMenuOption = kb.nextLine();
                userMenuOption = userMenuOption.toLowerCase();

                //Menu
                switch (userMenuOption) {
                    case "1": 
                    case "play":
                    case "interact":
                        playPet = true;
                        namePet = false;
                        break;
                    case "2": 
                    case "instructions":
                        break;
                    case "3":
                    case "exit":
                        gameExit = true;
                        namePet = false;
                        playPet = false;
                        break;
                }
            }

            //Select Pet
            if (namePet == true) {
                //Pet Selection
                System.out.println("Cat, Dog, Rabbit");
                System.out.print("What pet would you like to choose? ");
                String petSelected = kb.nextLine();

                petSelected = petSelected.toLowerCase();
                while ((petSelected.equals("cat") == false) && (petSelected.equals("dog") == false) && (petSelected.equals("rabbit") == false)) {
                    System.out.println("Invalid Choice of Pet!");
                }
                
                System.out.println("You have selected the " + petSelected);
                
                //Pet Naming
                System.out.print("Would you like to name the pet yourself? ");
                String customName = kb.nextLine();
                customName = customName.toLowerCase();

                String petName = "";

                if (customName.equals("yes") == true) {
                    System.out.print("What would you like to name your pet: ");
                    petName = kb.nextLine();
                }
                else {
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

                System.out.println("Your pet, named " + petName + ", has been born!");

                //Pet Stats
                int maxPetHealth = 0;
                int maxPetHunger = 0;
                int maxPetEnergy = 0;
                int maxPetHappiness = 0;

                for (int i = 0; i < MAX_PET_STATS; i = i + 1) {
                    switch (r.nextInt(4)) {
                        case 0:
                            maxPetHealth += 1;
                            break;
                        case 1:
                            maxPetHunger += 1;
                            break;
                        case 2:
                            maxPetEnergy += 1;
                            break;
                        case 3:
                            maxPetHappiness += 1;
                            break;
                        default:
                            break;
                    } 
                }
                System.out.println("Max Pet Health: " + maxPetHealth + "\nMax Pet Hunger: " + maxPetHunger + "\nMax Pet Energy: " + maxPetEnergy + "\nMax Pet Happiness: " + maxPetHappiness);
            } //start game if
            else if (playPet == true) { //play with pet
                System.out.println("1. Number guessing game      2. Matching game");
                System.out.print("Which game do you want to play: ");
                int gameSelection = kb.nextInt();
                
                if (gameSelection == 1) {
                    int numberToBeGuessed = (r.nextInt(100) + 1);
                    boolean numberGuessed = false;
                    int numberOfIncorrectGuesses = 0;
                    int moneyEarned = 100;
                
                    while ((numberGuessed == false) && (numberOfIncorrectGuesses < 5)) {
                        System.out.println("Guess a number: ");
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
                            numberGuessed = true;
                        }
                    }
                    
                    System.out.println("You currently have $" + userMoney);
                }
                
                if (gameSelection == 2) {
                    String stringLetterList = "AABBCCDDEE";
                    String stringListHidden = "**********";
                    String stringToBeGuessed = "";
                    int moneyEarned = 100;
                    
                    while (stringLetterList.equals("") == false) {
                        int letterIndex = r.nextInt(stringLetterList.length());
                        stringToBeGuessed += stringLetterList.charAt(letterIndex);
                        stringLetterList = (stringLetterList.substring(0, letterIndex) + stringLetterList.substring(letterIndex + 1));
                    }
                    
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
                    
                    System.out.println("You solved it! Congragulations you have earned " + moneyEarned);
                    userMoney += moneyEarned;
                    
                    System.out.println("You currently have $" + userMoney);   
                }
            } // playPet if loop
        } //gameExit while loop
    }
}
