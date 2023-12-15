// Yasin Abdulkariem 2023
public class Card {
    private String rank;
    private String suit;
    private int point;

    public Card(String theRank, String theSuit, int thePoint){
        rank = theRank;
        suit = theSuit;
        point = thePoint;
    }
    // This gets the rank
    public String getRank(){
        return rank;
    }
    // This gets the suit
    public String getSuit(){
        return suit;
    }
    // This gets the point
    public int getPoint(){
        return point;
    }
    // This sets the point
    public void setPoint(int thePoint){
        point = thePoint;
    }
    // This sets the rank of the card
    public void setRank(String theRank){
        rank = theRank;
    }
    // This sets the suit one of the 4 suits
    public void setSuit(String theSuit){
        suit = theSuit;
    }
    public String toString(){
        return rank + " of " + suit;
    }
}
