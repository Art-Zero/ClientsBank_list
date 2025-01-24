package com;

import java.util.ArrayList;

public class Account {
    private String FIO;
    private String pan;
    private double balance;
    private String ccy;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    Account(String message) {
        String[] data = message.split(";");
        this.pan = data[0];
        this.balance = Double.parseDouble(data[1]) / 100;
        this.ccy = data[2];
    }

    String getAccount() {
        getCurrency();
        char space = ' ';
        return pan + space + balance + space + ccy;
    }

    String getAccountAfterOperation() {
        getBalance();
        char space = ' ';
        return pan + space + balance + space + ccy;
    }

    void createTransaction(String transactionData) {
        Transaction transaction = new Transaction(transactionData);
        transactions.add(transaction);
    }

    ArrayList<String> getTransactions() {
        ArrayList<String> transactionList = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            transactionList.add(transaction.getTransaction());
        }
        return transactionList;
    }

    String getCurrency() {
        switch (ccy) {
            case "841":
                ccy = "USD";
                return ccy;
            case "630":
                ccy = "RUB";
                return ccy;
            default:
                return "Наша банковская система не поддерживает эту валюту";
        }
    }

    void getBalance() {
        for (Transaction transaction : transactions) {
            if (transaction.getOperation().equals("снятие наличных")
                    || transaction.getOperation().equals("покупка")) {
                balance -= transaction.getAmount();
            } else if (transaction.getOperation().equals("пополнение депозита")) {
                balance += transaction.getAmount();
            } else {
                System.out.println(transaction.getTypeOperation());
            }
        }
    }

    void setFIO(String FIO) {
        this.FIO = FIO;
    }

    String getFIO() {
        return FIO;
    }

    String getPan() {
        return pan;
    }
}
