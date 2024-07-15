package com.tangent.ui;

public class Controller
{
    static Button[] buttonList = new Button[2];
    static DropButton[] dropButtonList = new DropButton[2];
    static Slider[] sliderList = new Slider[2];
    static void logic() {
        buttonList[0] = new Button(100, 50, 100, 200, ButtonMethods.Method.Start);
        buttonList[1] = new Button(100, 50, 150, 300, ButtonMethods.Method.Pause);

        buttonList[0].setText("Start");

        TextMethodPair[] drop1 = {new TextMethodPair("Hello", ButtonMethods.Method.Blank), new TextMethodPair("sdj", ButtonMethods.Method.Blank)};
        TextMethodPair[] drop2 = {new TextMethodPair("Button 1", ButtonMethods.Method.Blank), new TextMethodPair("Button 2", ButtonMethods.Method.Blank), new TextMethodPair("Button 3", ButtonMethods.Method.Blank), new TextMethodPair("Button 4", ButtonMethods.Method.Blank), new TextMethodPair("Button 5", ButtonMethods.Method.Blank), new TextMethodPair("Button 6", ButtonMethods.Method.Blank), new TextMethodPair("Button 7", ButtonMethods.Method.Blank), new TextMethodPair("Button 8", ButtonMethods.Method.Blank), new TextMethodPair("Button 9", ButtonMethods.Method.Blank), new TextMethodPair("Button 10", ButtonMethods.Method.Blank), new TextMethodPair("Button 11", ButtonMethods.Method.Blank), new TextMethodPair("Button 12", ButtonMethods.Method.Blank)};
        dropButtonList[0] = new DropButton(150, 70, 750, 500, drop1);
        dropButtonList[1] = new DropButton(100, 40, 600, 700, drop2);
        dropButtonList[0].setText("Drop");

        sliderList[0] = new Slider(1, 100, 30, 400, 400, 150);
        sliderList[1] = new Slider(-5, 9, 2, 450, 600, 400);

        sliderList[1].setText("Number");
    }
}
