import java.util.*;

public class SynonymsMapRunner{

  public static void main(String[] args){
    SynonymsMap map = new SynonymsMap();
    Scanner sc = new Scanner(System.in);

    while(true){
      String input = sc.nextLine();
      if(input.equals("GoodBye")){
	System.out.println("Do you want to add synonyms for the word GoodBye(Y/N)? ");
	String query = sc.nextLine();
	List<String> synonyms = new ArrayList<>();
        while(query.equals("Y")){
	  String syn = sc.nextLine();
	  if(!syn.equals("")){
		  synonyms.add(syn);
	  }
	  else{
		  map.addData(input, synonyms);
		  System.out.println(map.getsynonyms("GoodBye"));
		  break;
          }
	}
	break;
      }
      System.out.println(map.getsynonyms(input));
    }
  }

}
