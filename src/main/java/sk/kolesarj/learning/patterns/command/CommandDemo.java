package sk.kolesarj.learning.patterns.command;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

class BankAccount {
    private int balance;
    private final int overdraftLimit = -500;

    public boolean deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance);
        return true;
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void call();

    void undo();
}

class BankAccountCommand implements Command {
    private BankAccount account;

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;
    private boolean succeeded;


    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT -> {
                succeeded = account.deposit(amount);
            }
            case WITHDRAW -> {
                succeeded = account.withdraw(amount);
            }
        }
    }

    @Override
    public void undo() {
        if (!succeeded) return;
        switch (action) {
            case DEPOSIT -> {
                account.withdraw(amount);
            }
            case WITHDRAW -> {
                account.deposit(amount);
            }
        }
    }
}

public class CommandDemo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount);

        List<BankAccountCommand> commands = List.of(
                new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000)
        );

        for (Command c : commands) {
            c.call();
            System.out.println(bankAccount);
        }

        System.out.println("UNDOING");


        for (Command c : Lists.reverse(commands)) {
            c.undo();
            System.out.println(bankAccount);
        }

    }
}
