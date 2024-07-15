package com.tangent.sorting.ui;

public class TextMethodPair {
    private final String text;
    private final ButtonMethods.Method method;
    public TextMethodPair(String text, ButtonMethods.Method method) {
        this.text = text;
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public ButtonMethods.Method getMethod() {
        return method;
    }
}
