package com;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String clients_txt = "";
        String transactions_txt = "";

        try (FileInputStream reader = new FileInputStream("clients.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                clients_txt += (char) i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileInputStream reader = new FileInputStream("transactions.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                transactions_txt += (char) i;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String[] clientList = clients_txt.split("\r\n");
        String[] transactionList = transactions_txt.split("\r\n");

        ArrayList<Client> clients = new ArrayList<>();

        for (String clientData : clientList) {
            Client client = new Client(clientData);
            clients.add(client);
            for (String transactionData : transactionList) {
                client.wayToTransaction(transactionData);
            }
        }

        ArrayList<String> newClientList = new ArrayList<>();

        for (Client client : clients) {
            newClientList.add(client.getFIO());
            for (String strAccount : client.displayAccount()) {
                newClientList.add(strAccount);
            }
            newClientList.add("\n");
        }

        for (Client client : clients) {
            newClientList.add(client.getFIO());
            for (String strAccountAfterOperation : client.displayAccountAfterOperation()) {
                newClientList.add(strAccountAfterOperation);
            }
        }

        try (FileWriter writer = new FileWriter("clientsAfterOperation.txt")) {
            for (String str : newClientList) {
                writer.write(str);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}