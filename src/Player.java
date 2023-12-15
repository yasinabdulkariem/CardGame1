// Yasin Abdulkariem 2023
import java.util.ArrayList;
public class Player {
    private int points;
    private String name;
    ArrayList<Card> hand;
    private ArrayList<Card> hand2;
    private int hand1Total;
    private int hand2Total;
    //This holds all the information for a player
    public Player(String theName) {
        points = 1000;
        name = theName;
        hand = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand1Total = 0;
        hand2Total = 0;
    }
    public Player(String theName, ArrayList<Card> theHand){
        points = 1000;
        name = theName;
        hand = theHand;
        hand2 = new ArrayList<>();
        hand1Total = 0;
        hand2Total = 0;
    }
    // This gets the second hand
    public ArrayList<Card> getHand2(){
        return hand2;
    }
    // This gets the hand1 total
    public int getHand1Total(){
        return hand1Total;
    }
    // This gets the hand2 total
    public int getHand2Total(){
        return hand2Total;
    }
    // This gets the points
    public int getPoints(){
        return points;
    }
    // This sets the points
    public void setPoints(int thePoints){
        points = thePoints;
    }
    // This gets hand1
    public ArrayList<Card> getHand(){
        return hand;
    }
    // This gets their name
    public String getName(){
        return name;
    }
    // This adds another card to the first hand
    public void addCard(Card newCard){
        hand.add(newCard);
    }
    // This adds another card to the second hand
    public void addHand2(Card card){
        hand2.add(card);
    }
    // This adds points
    public void addPoints(int thePoints){
        points += thePoints;
    }
    // This removes points
    public void removePoints(int thePoints){
        points -= thePoints;
    }
    // This takes the hands' value
    public int handValue(ArrayList<Card> hand){
        int total = 0;
        int aces = 0;
        for (Card card: hand){
            total += card.getPoint();
            if (card.getRank().equals("A")){
                aces++;
            }
        }
        do {
            if (total > 21 && aces > 0) {
                aces--;
                total -= 10;
            }
            else{
                break;
            }
        }
        while (true);
        return total;
    }
    // This resets/clears the hand
    public void clearHand(){
        hand.clear();
        hand2.clear();
    }
    // This updates any changes made to hand1
    public void hand1Changes(){
        hand1Total = handValue(hand);
    }
    // This updates any changes made to hand2
    public void hand2Changes(){
        hand2Total = handValue(hand2);
    }
    // This prints the dealer's hand
    public void printDealer(){
        System.out.println(name + "'s cards: "+ hand);
    }
    // Sees if the person can split their cards or not
    public boolean canSplit(){
        return hand.size() == 2 && hand.get(0).getRank().equals(hand.get(1).getRank());
    }
    public String toString(){
        if(hand2.isEmpty()){
            return name + " has " + points + " points\n" + name + "'s cards: " + hand;
        }
        else{
            return name + " has " + points + " points\n" + name + "'s cards: \n" + "Hand 1: " + hand + "\n" + "Hand 2: " + hand2;
        }
    }
}
