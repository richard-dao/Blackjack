// Java
import java.util.ArrayList;
import java.util.Scanner;
public class Blackjack {
	ArrayList<String> cards; // All possible cards
	ArrayList<String> dealerCards = new ArrayList<String>();
	ArrayList<String> playerCards = new ArrayList<String>();
	int playerScore;
	int dealerScore;
	double balance;
	double moneyBet;
	public Blackjack(double startingBalance) {
		String[] temp = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
		cards = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < 4; j++) {
				cards.add(temp[i]);
			}
		}
		
		balance = startingBalance;
	}
	
	public void beginGame() {
		while(balance > 0) {
			// Reset round
			newRound();
			
			//Take Bets, Begin Round
			takeBet();
			
			// 1st Round of Cards
			playerDraws();
			dealerDraws();
			
			// Play Round
			playRound();
			
		}
		System.out.println("Oops! You're out of money! Game Over!");
		
		
	}
	
	public void newRound() {
		playerScore = 0;
		dealerScore = 0;
		moneyBet = 0;
		dealerCards.removeAll(dealerCards);
		playerCards.removeAll(playerCards);
		System.out.println("---");
	}
	
	public void takeBet() {
		System.out.println("New Round! Dealer must stand on 17!");
		System.out.println("Current Balance: $" + balance);
		System.out.println("How much would you like to bet? (You can bet 0 if you want to play for fun)");
		Scanner inp = new Scanner(System.in);
		moneyBet = inp.nextDouble();
		System.out.println("Round begins!");
	}
	
	public void playRound() {
		System.out.println("Bet: $" + moneyBet);
		while (playerScore <= 21 && dealerScore <= 21) {
			// User Decision:
			String decision; 
			if (dealerScore >= 17) {
				decision = promptMovePlayerOnly();
			}
			else {
				decision = promptMove();
			}
			if (decision == "S") {
				System.out.println("Ok you stand");
				while (dealerScore < 17) {
					dealerDraws();
					System.out.println("Dealer hand: " + dealerScore);
				}
				if (dealerScore > 21) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				else if (dealerScore < playerScore) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				else if (dealerScore == playerScore) {
					System.out.println("Draw!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance;
					break;
				}
				else if (dealerScore > playerScore) {
					System.out.println("You lose!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance - moneyBet;
					break;
				}
				else {
					System.out.println("Error?");
					balance = balance;
					break;
				}
			}
			else if (decision == "H") {
				if (playerScore == 21 && (dealerScore < 21 || dealerScore > 21)) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				if (playerScore == 21 && dealerScore == 21) {
					System.out.println("Draw!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance;
					break;
				}
				if (dealerScore == 21 && (playerScore < 21 || playerScore > 21)) {
					System.out.println("You lose!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance - moneyBet;
					break;
				}
				if (playerScore > 21) {
					System.out.println("You lose!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance - moneyBet;
					break;
				}
				else if (dealerScore > 21) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				else if (dealerScore >= 17 && (playerScore > dealerScore)) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
			}
			else {
				if (playerScore == 21 && (dealerScore < 21 || dealerScore > 21)) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				if (playerScore == 21 && dealerScore == 21) {
					System.out.println("Draw!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance;
					break;
				}
				if (dealerScore == 21 && (playerScore < 21 || playerScore > 21)) {
					System.out.println("You lose!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance - moneyBet;
					break;
				}
				if (playerScore > 21) {
					System.out.println("You lose!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance - moneyBet;
					break;
				}
				else if (dealerScore > 21) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
				else if (dealerScore >= 17 && (playerScore > dealerScore)) {
					System.out.println("You win!");
					System.out.println("Your final hand: " + playerScore + " | Dealer final hand: " + dealerScore);
					balance = balance + moneyBet;
					break;
				}
			}
			
		}
	}
	public void dealerDraws(){
		int randHand = (int) ((Math.random() * (cards.size() - 0)) + 0);
		String dealerCard = cards.get(randHand);
		dealerCards.add(dealerCard);
		System.out.println("Dealer got a " + dealerCard);
		dealerScore += cardToScoreDealer(dealerCard);
	}
	
	public void playerDraws() {
		int randHand = (int) ((Math.random() * (cards.size() - 0)) + 0);
		String playerCard = cards.get(randHand);
		playerCards.add(playerCard);
		System.out.println("You got a " + playerCard);
		playerScore += cardToScore(playerCard);
	}

	public String promptMove() {
		System.out.println("Your Total: " + playerScore + " | Your Hand: " + returnHand(playerCards));
		System.out.println("Dealer Total: " + dealerScore + " | Dealer Hand: " + returnHand(dealerCards));
		System.out.println("---");
		Scanner inp = new Scanner(System.in);
		System.out.println("What do you want to do? (D = Double, H = Hit, S = Stand)");
		String userMove = inp.next();
		if (userMove.equals("H") || userMove.equals("h")) {
			playerDraws();
			dealerDraws();
			return "H";
		}
		if (userMove.equals("D") || userMove.equals("d")) {
			moneyBet = moneyBet * 2;
			System.out.println("New Bet: $" + moneyBet);
			playerDraws();
			dealerDraws();
			return "D";
		}
		if (userMove.equals("S") || userMove .equals("s")) {
			return "S";
		}
		return "S";
	}
	
	public String promptMovePlayerOnly() {
		System.out.println("Your Total: " + playerScore + " | Your Hand: " + returnHand(playerCards));
		System.out.println("Dealer Total: " + dealerScore + " | Dealer Hand: " + returnHand(dealerCards));
		System.out.println("---");
		Scanner inp = new Scanner(System.in);
		System.out.println("What do you want to do? (D = Double, H = Hit, S = Stand)");
		String userMove = inp.next();
		if (userMove.equals("H") || userMove.equals("h")) {
			playerDraws();
			return "H";
		}
		if (userMove.equals("D") || userMove.equals("d")) {
			moneyBet = moneyBet * 2;
			System.out.println("New Bet: $" + moneyBet);
			playerDraws();
			return "D";
		}
		if (userMove.equals("S") || userMove.equals("s")) {
			return "S";
		}
		return "S";
	}
	
	public int cardToScore(String card) {
		if (card == "A") {
			if (playerScore + 11 > 21) {
				return 1;
			}
			else {
				return 11;
			}
		}
		if (card == "K" || card == "Q" || card == "J") {
			return 10;
		}
		else {
			return Integer.parseInt(card);
		}
	}
	public int cardToScoreDealer(String card) {
		if (card == "A") {
			if (dealerScore + 11 > 21) {
				return 1;
			}
			else {
				return 11;
			}
		}
		if (card == "K" || card == "Q" || card == "J") {
			return 10;
		}
		else {
			return Integer.parseInt(card);
		}
	}
	
	public String returnHand(ArrayList<String> list) {
		String rStr = "";
		for (int i = 0; i < list.size(); i++) {
			rStr += list.get(i);
			rStr += " ";
		}
		return rStr;
	}
	
}


public class main {
	public static void main(String[] args) {
		Blackjack b = new Blackjack(100);
		b.beginGame();
    }
}
