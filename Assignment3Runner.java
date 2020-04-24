import java.util.Scanner;

class Assignment3Runner{
    public static void main(String[] args){
        Scanner scnn = new Scanner(System.in);
        String input = scnn.nextLine();
        CharacterPrime chpr = new CharacterPrime();

        chpr.character_to_primes(input);

    }
}