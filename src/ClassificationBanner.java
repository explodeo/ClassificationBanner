import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClassificationBanner {
    public static void showhelp() {
            System.out.println("Usage: java ClassificationBanner <bannerColor> <centerText> <textColor>");
            System.exit(-1);
    }

    public static void main(String[] args) {
        
        if (args.length != 3) {
            showhelp();
        }

        Color bannerColor;
        String centerText;
        Color centerTextColor;

        try {
            centerText = args[1];
            bannerColor = Color.decode(args[0]);
            centerTextColor = Color.decode(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid color format. Please use hex format (e.g., #RRGGBB).");
            return;
        }

        // Create a frame with no decorations (borderless)
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        
        // Set the frame to always be on top
        frame.setAlwaysOnTop(true);
        
        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Set the frame size to full width and 15 pixels tall
        frame.setSize(screenSize.width, 15);
        
        // Set the frame location to the top of the screen
        frame.setLocation(0, 0);
        
        // Create a panel with custom painting for the text
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background color
                g.setColor(bannerColor);
                g.fillRect(0, 0, getWidth(), getHeight());
                
                // Set font and color for the center text
                g.setFont(new Font("SansSerif", Font.BOLD, 13));
                g.setColor(centerTextColor);
                FontMetrics fm = g.getFontMetrics();
                int centerX = (getWidth() - fm.stringWidth(centerText)) / 2;
                int centerY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.drawString(centerText, centerX, centerY);
                
                // Set font and color for the right text
                g.setFont(new Font("SansSerif", Font.PLAIN, 8));
                String rightText = "press esc to close";
                int rightX = getWidth() - g.getFontMetrics().stringWidth(rightText) - 10;
                int rightY = getHeight() - (getHeight() - g.getFontMetrics().getHeight()) / 2 - fm.getDescent();
                g.drawString(rightText, rightX, rightY);
            }
        };
        panel.setOpaque(false);
        frame.add(panel);
        
        // Prevent the frame from being moved
        frame.setResizable(false);
        
        // Set the frame background to an opaque color
        frame.setBackground(bannerColor);
        
        // Add a key listener to detect the Escape key
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Hide the frame
                    frame.setVisible(false);
                    
                    // Create a timer to show the frame again after 30 seconds
                    Timer timer = new Timer(30000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            frame.setVisible(true);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });
        
        // Display the frame
        frame.setVisible(true);
        
        // Request focus to capture key events
        frame.requestFocusInWindow();
    }
}
