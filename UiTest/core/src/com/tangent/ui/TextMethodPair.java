package com.tangent.ui;

public class TextMethodPair {
    private String text;
    private ButtonMethods.Method method;
    TextMethodPair(String text, ButtonMethods.Method method) {
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
