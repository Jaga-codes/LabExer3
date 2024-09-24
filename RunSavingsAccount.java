package labexer;
import java.util.Scanner;

public class RunSavingsAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SavingsAccount savings = new SavingsAccount();

        System.out.print("Enter the interest rate: ");
        double interestRate = scanner.nextDouble();
        SavingsAccount.setInterestRate(interestRate);

        System.out.print("Enter the amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        savings.deposit(depositAmount);

        System.out.println("Your new balance is " + savings.getBalance() + " ₱\n");

        while (true) {
            System.out.print("Press D for another deposit or W to withdraw. Press X to exit: ");
            char action = scanner.next().charAt(0);
            System.out.println();

            if (action == 'D' || action == 'd') {
                System.out.print("Enter the amount to deposit: ");
                depositAmount = scanner.nextDouble();
                savings.deposit(depositAmount);
                System.out.println("Your new balance is " + savings.getBalance() + " ₱\n");
            } else if (action == 'W' || action == 'w') {
                System.out.print("Enter the amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                try {
                    savings.withdraw(withdrawAmount);
                    System.out.println("Your new balance is " + savings.getBalance() + " ₱\n");
                } catch (InsufficientFundsException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (action == 'X' || action == 'x') {
                System.out.println("Your balance is " + savings.getBalance() + " PHP.");
                break;
            } else {
                System.out.println("Invalid option. Please try again.\n");
            }
        }

        if (savings.getBalance() > 1000) {
            savings.addInterest();
            System.out.println("New balance with applied interest: " + savings.getBalance() + " PHP");
        }

        scanner.close();
    }
}


class SavingsAccount {
    private double balance;
    public static double interestRate = 0;

    public SavingsAccount() {
        this.balance = 0;
    }

    public static void setInterestRate(double newRate) {
        interestRate = newRate;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double withdraw(double amount) throws InsufficientFundsException {
        if (balance == 0) {
            throw new InsufficientFundsException("Insufficient funds: balance is zero.");
        } else if (balance < amount) {
            throw new InsufficientFundsException("Error: Your balance is " + balance + " PHP and you cannot withdraw " + amount + " ₱.");
        } else {
            balance -= amount;
        }
        return amount;
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }

    public static void showBalance(SavingsAccount account) {
        System.out.println("Current balance: " + account.getBalance() + " ₱");
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
