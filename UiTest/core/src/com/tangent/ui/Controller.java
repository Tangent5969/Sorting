package com.tangent.ui;

public class Controller
{
    static Button[] buttonList = new Button[2];
    static Slider[] sliderList = new Slider[2];
    static void logic() {
        buttonList[0] = new Button(100, 50, 100, 200, Button.ButtonMethod.Start);
        buttonList[1] = new Button(100, 50, 150, 300, Button.ButtonMethod.Pause);

        buttonList[0].setText("Start");

        sliderList[0] = new Slider(1, 100, 30, 400, 400, 150);
        sliderList[1] = new Slider(-5, 9, 2, 450, 600, 400);

        sliderList[1].setText("Number");

    }
}
