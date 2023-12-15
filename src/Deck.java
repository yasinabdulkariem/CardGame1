// Yasin Abdulkariem 2023
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points){
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++){
            for (String suit : suits){
                cards.add(new Card(ranks[i], suit, points[i]));
            }
        }
        cardsLeft = cards.size();
    }
    // Checks if there are any cards left
    public boolean isEmpty(){
        if (cardsLeft == 0){
            return true;
        }
        return false;
    }
    // gets the cards left
    public int getCardsLeft(){
        return cardsLeft;
    }
    // deals a new set of cards
    public Card deal(){
      if(isEmpty()){
          return null;
      }
      cardsLeft--;
      return cards.get(cardsLeft);
    }
    public void shuffle(){
        Card person;
        for (int i = cards.size() - 1; i > 0; i--){
            int randomIndex = (int)(Math.random() * (i + 1));
            person = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, person);
        }
    }
}
