import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
       FrameTest frame = new FrameTest();

       JLabel label = new JLabel();
       label.setText("Hello World!");
       label.setForeground(Color.BLUE);
      // label.setBackground(Color.BLACK);
      //  label.setOpaque(true);


       frame.add(label);
    }
} 