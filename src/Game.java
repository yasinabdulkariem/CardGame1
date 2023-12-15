// Yasin Abdulkariem 2023
import java.util.Scanner;


public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;
    private int currentBet;
    Scanner scan = new Scanner(System.in);
    // This holds all the information for the game
    public Game(){
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        String[] suits = {"Diamonds", "Hearts", "Spades", "Clubs"};
        deck = new Deck(ranks, suits, values);
        System.out.println("Enter the player's name: ");
        String pName = scan.nextLine();
        player = new Player(pName);
        dealer = new Player("Dealer");
        currentBet = 0;
    }
    // This includes how to play the game
    public void printInstructions(){
        System.out.println("This is BlackJack!");
        System.out.println("The goal of the game is to get closest to 21 without going over.");
        System.out.println("The dealer stands on a soft 17.");
        System.out.println("Your choices are hit, stand, split, and double down.");
        System.out.println("BlackJack is an automatic win!");
        System.out.println("Good Luck!");
        System.out.println();
    }
    // This is used to add a card when the hit function is used to hand1
    public void hitHand1(Player player){
        player.addCard(deck.deal());
        player.hand1Changes();
    }
    // This is used to add a card when the hit function is used to hand2
    public void hitHand2(Player player){
        player.addHand2(deck.deal());
        player.hand2Changes();
    }
    // This is used to see if the person busted or not on hand1
    public boolean bustHand1(){
        if (player.getHand1Total() > 21){
            return true;
        }
        return false;
    }
    // This is used to see if the person busted or not on hand2
    public boolean bustHand2(){
        if (player.getHand2Total() > 21){
            return true;
        }
        return false;
    }
    // This takes in all the information on betting
    public void bet(){
        Scanner bet = new Scanner(System.in);
        System.out.println("You have " + player.getPoints() + " how much do you want to bet?: ");
        currentBet = bet.nextInt();
        while (player.getPoints() < currentBet){
            System.out.println("You bet more than you have, try again: ");
            currentBet = bet.nextInt();
        }
        player.removePoints(currentBet);
    }
    // This is the starting hands for the player and dealer
    public void startingHand(){
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.printDealer();
        System.out.println(player);
    }
    // This resets everything back to normal
    public void reset(){
        player.clearHand();
        dealer.clearHand();
        currentBet = 0;
    }
    // This allows the player to split their cards into two different hands
    public void split(){
        player.addHand2(player.getHand().remove(1));
    }
    // This allows the player to double their money
    public void doubleDown(){
        if (currentBet < player.getPoints()){
            player.removePoints(currentBet);
        }
        else{
            System.out.println("Out of points!");
        }
    }
    // This allows the player to stand
    public void stand(){
        System.out.println("Stand");
    }
    // This checks to see if the hand1 got black jack
    public boolean blackJack(){
        return player.getHand1Total() == 21;
    }
    // This checks to see if the hand2 got black jack
    public boolean blackJackHand2(){
        return player.getHand2Total() == 21;
    }
    // This goes through the different functions you can do, split, hit, stand, double down
    public void afterTurn(){
        player.hand1Changes();
        if(blackJack()){
            System.out.println("You got Black Jack!");
            return;
        }
        Scanner pick = new Scanner(System.in);
        String choice1 = " ";
        while (!(choice1.equals("Stand") || choice1.equals("Double Down") || choice1.equals("Split") || choice1.equals("Hit"))){
            System.out.println("Pick: Stand, Double Down, Split, Hit");
            choice1 = pick.nextLine();
            if (choice1.equals("Stand")){
                stand();
                System.out.println("It is no longer your turn.");
            }
            else if (choice1.equals("Hit")){
                hitHand1(player);
                System.out.println(player);
                afterTurn();
                if (bustHand1()){
                    System.out.println("You Busted!");
                    return;
                }
            }
            else if (choice1.equals("Double Down")){
                doubleDown();
                hitHand1(player);
                System.out.println(player);
                System.out.println("It is no longer your turn.");
            }
            else if (choice1.equals("Split")){
                if (player.canSplit()){
                    split();
                    postSplit();
                    System.out.println("Hand #2");
                    afterTurn();
                }
                else{
                    System.out.println("You can't split with those!'");
                }
            }
        }
    }
    // This is what happens after you choose to split and have 2 hands
    public void postSplit(){
        String choice = " ";
        Scanner scan = new Scanner(System.in);
        while(!choice.equals("Stand")){
            if (bustHand2()){
                System.out.println("You Busted!");
                return;
            }
            if (blackJackHand2()){
                System.out.println("You got Black Jack!");
                return;
            }
            System.out.println("Pick: Hit or Stand");
            choice = scan.nextLine();
            if (choice.equals("Hit")){
                if (bustHand2()){
                    System.out.println("You Busted!");
                    return;
                }
                if (blackJackHand2()){
                    System.out.println("You got Black Jack!");
                    return;
                }
                hitHand2(player);
                System.out.println(player);
            }
        }
    }
    // This allows the dealer to play
    public void dealer(){
        System.out.println("Second Card");
        dealer.hand1Changes();
        hitHand1(dealer);
        dealer.printDealer();

        if (dealer.getHand1Total() == 21){
            System.out.println("Black Jack!");
            return;
        }
        if ((blackJack() || bustHand1()) && player.getHand2().isEmpty()){
            System.out.println("Dealer stands.");
            return;
        }
        while (dealer.getHand1Total() < 17){
            System.out.println("Dealer hits.");
            hitHand1(dealer);
            dealer.printDealer();
        }
        if(dealer.getHand1Total() > 21){
            System.out.println("Dealer has busted! :) ");
            return;
        }

        System.out.println("Dealer stands.");
        dealer.printDealer();
    }
    // This awards you the money if you won
    public void win(){
        System.out.println("Winner!");
        player.addPoints(currentBet * 2);
    }
    // This tells you if you lost
    public void lose(){
        System.out.println("Loser!");
    }
    // This gives you back your money when you tie
    public void push(){
        System.out.println("You pushed!");
        player.addPoints(currentBet);
    }
    // This checks hand1 to see if you won, lost, or pushed
    public void check1(){
        if (player.getHand1Total() == dealer.getHand1Total()){
            push();
        }
        else if (player.getHand1Total() <= 21 && dealer.getHand1Total() > 21){
            win();
        }
        else if (player.getHand1Total() < 21 && dealer.getHand1Total() <= 21 && player.getHand1Total() > dealer.getHand1Total()){
            win();
        }
        else if (player.getHand1Total() > 21){
            lose();
        }
        else if (player.getHand1Total() <= 21 && dealer.getHand1Total() <= 21 && player.getHand1Total() < dealer.getHand1Total()){
            lose();
        }
    }
    // This checks hand2 to see if you won, lost, or pushed
    public void check2(){
        if (player.getHand2Total() == dealer.getHand2Total()){
            push();
        }
        else if (player.getHand2Total() <= 21 && dealer.getHand2Total() > 21){
            win();
        }
        else if (player.getHand2Total() <= 21 && dealer.getHand2Total() <= 21 && player.getHand2Total() > dealer.getHand2Total()){
            win();
        }
        else if (player.getHand2Total() > 21){
            lose();
        }
        else if (player.getHand2Total() <= 21 && dealer.getHand2Total() <= 21 && player.getHand2Total() < dealer.getHand2Total()){
            lose();
        }
    }
    //This checks  to see if you actually won
    public void checkWin(){
        if (player.getHand2().isEmpty()){
            check1();
        }
        else{
            System.out.println("Hand #1");
            check2();
            System.out.println("Hand #2");
            check2();
        }
    }
    // This puts together the functions to play the game
    public void playGame(){
        Scanner scan = new Scanner(System.in);
        printInstructions();
        while (player.getPoints() >= 1){
            reset();
            bet();
            startingHand();
            afterTurn();
            dealer();
            checkWin();
            System.out.println("Points: " + player.getPoints());
            System.out.println("Play again: yes or no");
            String newGame = scan.nextLine();
            if(newGame.equals("no")){
                System.out.println("Thanks for playing! Come back soon!");
                return;
            }
        }
    }
    // This calls the actual game
    public static void main(String[] args){
        Game game = new Game();
        game.playGame();
    }
}
