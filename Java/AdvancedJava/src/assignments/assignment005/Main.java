package assignments.assignment005;

class ATM {
    private Card currentCard;

    class Card {
        String cardNumber;
        String holderName;
        int pin;

        Card(String cardNumber, String holderName, int pin) {
            this.cardNumber = cardNumber;
            this.holderName = holderName;
            this.pin = pin;
        }

        void printCardInfo() {
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Holder Name: " + holderName);
        }
    }

    void insertCard(Card c) {
        currentCard = c;
    }

    void validatePin(int enteredPin) {
        class PinValidator {
            boolean check() {
                return enteredPin == currentCard.pin;
            }
        }

        PinValidator validator = new PinValidator();
        if (validator.check()) {
            System.out.println("PIN Verified. Access Granted.");
        } else {
            System.out.println("Invalid PIN. Access Denied.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        ATM.Card myCard = atm.new Card("1234567890123456", "Sanjeevi", 1234);
        atm.insertCard(myCard);
        myCard.printCardInfo();
        atm.validatePin(1234);
        atm.validatePin(1111);
    }
}

