// Yasin Abdulkariem 2023
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    // This holds the information for the deck
    public Deck(String[] ranks, String[] suits, int[] points){
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++){
            for (String suit : suits){
                cards.add(new Card(ranks[i], suit, points[i]));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }
    // This checks if there are any cards left
    public boolean isEmpty(){
        if (cardsLeft == 0){
            return true;
        }
        return false;
    }
    // This gets the cards left
    public int getCardsLeft(){
        return cardsLeft;
    }
    // This deals a new set of cards
    public Card deal(){
      if(isEmpty()){
          return null;
      }
      cardsLeft--;
      return cards.get(cardsLeft);
    }
    // This shuffles the card to randomize them
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
