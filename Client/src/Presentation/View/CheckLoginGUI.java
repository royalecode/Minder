package Presentation.View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CheckLoginGUI extends JFrame {
    private String[] data = { "Java", "Javascript", "html", "C++" };

    public CheckLoginGUI(){
        setTitle("Fill profile information");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);

        JScrollPane sp = new JScrollPane(centerOption());
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(sp, BorderLayout.CENTER);
        add(southButton(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel centerOption(){
        JPanel center =  new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.setBackground(Color.WHITE);

        JButton jchooseImage = new JButton("CHOOSE PHOTO");
        jchooseImage.setBackground(Color.decode("#DF4B74"));
        jchooseImage.setForeground(Color.WHITE);
        jchooseImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        jchooseImage.setBorder(new EmptyBorder(10,10,10,10));

        JComboBox<String> jlanguage = new JComboBox<>(data);
        jlanguage.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlanguage.setBackground(Color.white);
        jlanguage.setBorder(new EmptyBorder(50,10 ,50,10));

        JTextArea jdesc = new JTextArea(0,0);
        jdesc.setLineWrap(true);
        jdesc.setWrapStyleWord(true);
        jdesc.setRows(2);
        jdesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        jdesc.setBackground(Color.white);
        jdesc.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(5,5 ,5,5)));

        jdesc.setText("Introduce here the description that defines yourself.");

        center.add(jchooseImage);
        center.add(jlanguage);
        center.add(jdesc);
        center.setBorder(new EmptyBorder(30,10,30,10));
        return center;
    }

    private JPanel southButton(){
        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(Color.WHITE);

        JButton jSaveBtn = new JButton("SAVE");
        jSaveBtn.setBackground(Color.decode("#DF4B74"));
        jSaveBtn.setForeground(Color.WHITE);

        south.add(jSaveBtn, BorderLayout.CENTER);
        south.setBorder(new EmptyBorder(10,10,10,10));
        return south;
    }
}