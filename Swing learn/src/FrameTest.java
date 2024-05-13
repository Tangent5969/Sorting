import javax.swing.*;
import java.awt.*;

public class FrameTest extends JFrame {

    FrameTest() {
        this.setTitle("hello");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        // this.getContentPane().setBackground(Color.GREEN);
        this.setVisible(true);
    }
}
