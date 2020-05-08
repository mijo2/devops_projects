import java.util.*;
import java.lang.*;

public class Quiz{
    public static void main(String[] arg) throws Exception{
        
        Scanner input = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Who is president of india", "Donald Trump", "Ramnath Kovind", "Narendra Modi", "None", "Ramnath Kovind"));
        questions.add(new Question("2+2 = ?", "1", "2", "3", "4", "4"));
        

        for(Question question: questions){
            Thread thread = new Thread(new T1());
            question.askQuestion();
            thread.start();
            String ans = input.nextLine();

            if(question.checkAnswer(ans)){
                System.out.println("Correct answer!!%n");
                thread.interrupt();
            }
            else{
                System.out.println("Incorrect answer!");
                System.exit(0);
            }

        }
        
        System.out.println("Congrats, you have passed!");
        System.exit(0);
    }
}