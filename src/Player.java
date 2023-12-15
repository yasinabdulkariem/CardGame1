// Yasin Abdulkariem 2023
import java.util.ArrayList;
public class Player {
    private int points;
    private String name;
    ArrayList<Card> hand;
    private ArrayList<Card> hand2;
    private int hand1Total;
    private int hand2Total;
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
    public ArrayList<Card> getHand2(){
        return hand2;
    }
    public int getHand1Total(){
        return hand1Total;
    }
    public int getHand2Total(){
        return hand2Total;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int thePoints){
        points = thePoints;
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public String getName(){
        return name;
    }
    public void addCard(Card newCard){
        hand.add(newCard);
    }
    public void addHand2(Card card){
        hand2.add(card);
    }
    public void addPoints(int thePoints){
        points += thePoints;
    }
    public void removePoints(int thePoints){
        points -= thePoints;
    }
    public int handValue(ArrayList<Card> hand){
        int total = 0;
        int aces = 0;
        for (Card card: hand){
            total += card.getPoint();
            if (card.getRank().equals("A")){
                aces++;
            }
        }
        while (total > 21 && aces > 0){
            aces--;
            total -= 10;
        }
        return total;
    }
    public void clearHand(){
        hand.clear();
        hand2.clear();
    }
    public void hand1Changes(){
        hand1Total = handValue(hand);
    }
    public void hand2Changes(){
        hand2Total = handValue(hand2);
    }
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
