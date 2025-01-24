package com;

import java.util.ArrayList;

public class Client {
    private String FIO;
    private ArrayList<Account> accounts = new ArrayList<>();

    Client(String message) {
        String[] FIO_data = message.split(":");
        this.FIO = FIO_data[0];
        String[] data = FIO_data[1].split("/");
        for (String str : data) {
            Account account = new Account(str);
            account.setFIO(FIO_data[0]);
            accounts.add(account);
        }
    }

    ArrayList<String> displayAccount() {
        ArrayList<String> transactionOfAccount = new ArrayList<>();
        if (accounts.isEmpty()) {
            System.out.println("Error@Account_isEmpty");
        } else {
            for (Account account : accounts) {
                String str = "\t" + account.getAccount();
                transactionOfAccount.add(str + "\n");
                // System.out.println(str);
                for (String transaction : account.getTransactions()) {
                    transactionOfAccount.add("\t\t" + transaction + "\n");
                    // System.out.println("\t\t" + transaction);
                }
            }
        }
        transactionOfAccount.add("\n");
        return transactionOfAccount;
    }

    ArrayList<String> displayAccountAfterOperation() {
        ArrayList<String> accountAfterOperation = new ArrayList<>();
        for (Account account : accounts) {
            accountAfterOperation.add("\t" + account.getAccountAfterOperation() + "\n");
            // System.out.println("\t" + account.getAccountAfterOperation());
        }

        return accountAfterOperation;
    }

    void wayToTransaction(String transactionData) {
        String[] str = transactionData.split(";");
        for (Account account : accounts) {
            String pan = "F2=" + account.getPan();
            if (str[0].equals(pan)) {
                account.createTransaction(transactionData);
            }
        }
    }

    String getFIO() {
        return FIO + "\n";
    }
}
