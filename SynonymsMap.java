import java.util.*;

public class SynonymsMap{
  private Map<String, Set<String>> data = new HashMap<>();

  SynonymsMap(){
    List<String> mornings = Arrays.asList("GoodMorning", "Shubhodaya", "Shubhohday", "Bonjour");
    List<String> evenings = Arrays.asList("GoodEvening", "Shubha sange", "susandhya", "Bonsoir");
    addData("GoodMorning", mornings);
    addData("GoodEvening", evenings);
  }

  public void addData(String word, List<String> synonyms){
    this.data.put(word, new HashSet<String>(synonyms));
  }

  public Set<String> getsynonyms(String word){
    return this.data.get(word);
  }

}
