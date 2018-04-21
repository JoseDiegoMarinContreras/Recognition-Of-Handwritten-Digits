import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;


public class MyCanvas extends Canvas implements MouseListener,
                                                MouseMotionListener{
    
    int x1, y1, x2, y2;
    Color c;
    
    BufferedImage bufferImage;
    Graphics2D graphics2D;
    
    public MyCanvas(){
        this.setBounds(6, 6, 216, 216); 
        addMouseListener(this);
        addMouseMotionListener(this);
        
        x1 = x2 = y1 = y2 = -1;
        c = Color.WHITE;
        setBackground(Color.BLACK);
        createBurrerImage();
    }
    
    public void createBurrerImage(){
        bufferImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);  
        graphics2D = (Graphics2D)bufferImage.getGraphics();
        
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setStroke(new BasicStroke(6.6f));
    }
    
    public void generateImage(){
        graphics2D.dispose();
        try{
            ImageIO.write(bufferImage,"png",new File("temp.jpg"));
        }catch (Exception e) {
            
        }
        createBurrerImage();
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        
        g2D.setStroke(new BasicStroke(6.6f));
        g2D.setColor(c);
        graphics2D.setColor(c);
        
        if(x1 != -1 && x2 != -1){
            g2D.drawLine(x1, y1, x2, y2);
            graphics2D.drawLine(x1, y1, x2, y2);
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        paint(getGraphics());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x1 = x2 = -1;
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        paint(getGraphics());
        x1 = x2;
        y1 = y2;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }
}