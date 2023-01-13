package com.nikulin.tests;

public enum WikiSearchItem {

    SOFTASSERTIONS("SoftAssertions"),
    HOME("home");

    private final String desc;

    WikiSearchItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
