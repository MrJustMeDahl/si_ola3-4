package org.soft2;


import org.soft2.messaging.Consumer;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] bindingKeys = new String[]{"rental.order.create", "rental.return.trailer"};
        Consumer.consumeMessages(bindingKeys);
    }
}