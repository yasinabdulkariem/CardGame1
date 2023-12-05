// Yasin Abdulkariem 2023
public class Card {
    private int rank;
    private String suit;
    private String point;

    public Card(int theRank, String theSuit, String thePoint){
        rank = theRank;
        suit = theSuit;
        point = thePoint;
    }
    // This gets the rank
    public int getRank(){
        return rank;
    }
    // This gets the suit
    public String getSuit(){
        return suit;
    }
    // This gets the point
    public String getPoint(){
        return point;
    }
    // This sets the point
    public void setPoint(String thePoint){
        if (thePoint = point){
            point = thePoint;
        }
    }
    // This sets the rank of the card
    public void setRank(int theRank){
        if (rank >= 2 && rank <= 10){
            rank = theRank;
        }
    }
    // This sets the suit one of the 4 suits
    public void setSuit(String theSuit){
        if (theSuit.equals("Clubs") || theSuit.equals("Spades") || theSuit.equals("Diamonds") || theSuit.equals("Hearts")){
            suit = theSuit;
        }
    }
}
