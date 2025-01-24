package com;

public class Transaction {
    private String FIO;
    private String pan;
    private int messageId;
    private double amount;
    private String ccy;
    private String operation;

    Transaction(String transactionData) {
        String[] data = transactionData.split(";");

        for (String str : data) {
            String[] flag = str.split("=");
            switch (flag[0]) {
                case "F2":
                    this.pan = flag[1];
                    break;
                case "F3":
                    this.operation = flag[1];
                    break;
                case "F5":
                    this.FIO = flag[1];
                    break;
                case "F35":
                    // super(flag[1]);
                    this.ccy = flag[1];
                    break;
                case "F37":
                    this.messageId = Integer.parseInt(flag[1]);
                    break;
                case "F51":
                    this.amount = Double.parseDouble(flag[1]) / 100;
                    break;
                default:
                    System.out.println("<'.'>");
                    break;
            }
        }
    }

    String getTransaction() {
        getCurrency();
        getTypeOperation();
        char space = ' ';
        return FIO + space + "по вашему счёту " + pan + " произведена операция " + operation + " на сумму " + amount
                + space + ccy;
    }

    String getTypeOperation() {
        switch (operation) {
            case "CWD":
                operation = "снятие наличных";
                return operation;
            case "DEP":
                operation = "пополнение депозита";
                return operation;
            case "PUR":
                operation = "покупка";
                return operation;
            default:
                return "Наша банковская система не поддерживает эту операцию";
        }
    }

    public String getCurrency() {
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

    String getPan() {
        return pan;
    }

    Integer getMessageId() {
        return messageId;
    }

    Double getAmount() {
        return amount;
    }

    String getOperation() {
        return operation;
    }
}
