package Projet;
import Observe.Observer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MiniaturesView extends JPanel implements Observer{
    private Application app;
    private ArrayList<MiniSlidePanel> miniSlides = new ArrayList<MiniSlidePanel>();
    private int nbSlide = 1;
    
    public MiniaturesView(Application app) {
        this.app = app;
        this.setPreferredSize(new Dimension(220,300));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.MiniSlidesInit();
        for(MiniSlidePanel current : this.miniSlides) {
           this.add(Box.createRigidArea(new Dimension(5,5)));
           current.getSlide().setHighlight(true);
           current.setBorder(BorderFactory.createLineBorder(Color.red, 2));
           this.add(current);
        }
        JButton add = new JButton("Add");
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                app.addSlide();
            }
        };
        add.addActionListener(buttonListener);
        this.add(add);
    }
    
    
    private void MiniSlidesInit() {
       for(Slide current : app.getSlides()) {
           this.miniSlides.add(new MiniSlidePanel(current, app));
       }     
    }
    

    @Override
    public void update(Application app) {
        this.app = app;
        this.miniSlides.removeAll(miniSlides);    
        this.MiniSlidesInit();     
        this.removeAll();
        for(MiniSlidePanel current : this.miniSlides) {
           this.add(Box.createRigidArea(new Dimension(5,5)));
           this.add(current);
        }
        this.invalidate();
        JButton add = new JButton("Add");
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                app.addSlide();
            }
        };
        this.nbSlide = this.miniSlides.size();
        add.addActionListener(buttonListener);
        this.add(add);
        System.out.println(app);
        this.setPreferredSize(new Dimension(180,180*nbSlide));
        for(MiniSlidePanel current : this.miniSlides){
            if(current.getSlide().getHighlight() == true){
                current.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            }
            else {
                current.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
        this.revalidate();
        this.repaint();
    }

    public int getNbSlide() {
        return nbSlide;
    }
    
    
}
