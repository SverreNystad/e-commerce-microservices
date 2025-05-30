package com.ecommerce.notification;

public enum Topics {
    PAYMENT("payment-topic");

    public final String label;

    private Topics(String label) {
        this.label = label;
    }
}
