import java.util.Objects;
import java.util.Scanner;

public class Main {

    //TODO Add support for authors
    //TODO Create GUI for creating recipes

    public static void main(String[] args) {
        Recipe[] recipes = null;

        while (true) {
            System.out.println("What would you like to do?\n 1 - Load\n 2 - Enter a new recipe\n 3 - Print a recipe book\n Q - Quit");
            Scanner terminal = new Scanner(System.in);
            String input = terminal.nextLine().toUpperCase();
            if (Objects.equals(input, "1")) {
                System.out.println("Loading...");
                recipes = Loader.Load();
            } else if (Objects.equals(input, "2")){
                System.out.println("Not yet available.");
            } else if (Objects.equals(input, "3")){
                if (recipes != null){
                    System.out.println("Printing...");
                    Printer.Print(recipes);
                } else
                    System.out.println("No recipes have been loaded.");
            } else if (Objects.equals(input, "Q")){
                break;
            } else {
                System.out.println("Instruction not recognised.");
            }
        }
    }
}