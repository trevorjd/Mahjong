package com.trevorjd;

import static com.trevorjd.Mahjong.*;
import com.trevorjd.Mahjong.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BoardGUI
{
    private JFrame f;
    private JLayeredPane layeredPane;
    private JLabel tileLabel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel zLabel;
    private final int gridXSize = 30;
    private final int gridYSize = 40;
    private final int tWidth = (int) (gridXSize * 1.8);
    private final int tHeight = (int) (gridYSize * 1.8);
    private int marginLeft;
    private int marginTop;
    private double xOffset;
    private double yOffset;
    private Map<String, Image> images;
    private Map<String, Integer> numbers;

    protected BoardGUI(MahjongBoardLayout layout)
    {
        xOffset = 0.1;
        yOffset = 0.1;
        loadImages();
        int gWidth = ((layout.getMAXCOL() + 2 + layout.getMAXLAY() / 2) * gridXSize);
        int gHeight = (layout.getMAXROW() + 2 + layout.getMAXLAY() / 2) * gridYSize;
        marginLeft = gridXSize;
        marginTop = gridYSize;
        f = new JFrame("Mahjong Solitaire");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //f.setSize(gWidth, gHeight);
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(gWidth, gHeight));
        f.getContentPane().add(layeredPane);
        f.pack();

        f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int result = okcancel("Really quit?");
                if(result == 0) gameOver();
            }
        });

        f.setLayout(null);
    }

    protected JButton makeButton(TilePosition tp)
    {
        TileSuit suit = tp.getTile().getSuit();
        TileValue value = tp.getTile().getValue();
        JButton button = new JButton(suit.toString().substring(0, 1) + value.toString().substring(0, 1));
        int xpos = tp.col * gridXSize + marginLeft + (int) (tp.layer * gridXSize * xOffset);
        int ypos = tp.row * gridYSize + marginTop + (int) (tp.layer * gridYSize * yOffset);
        button.setBounds(xpos, ypos, tWidth, tHeight);
        button.setText("");

        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    board.tileClickResponder(tp);
                }
            }
        );
        return button;
    }

    protected void destroy()
    {
        f.dispose();
    }

    protected void gameOver()
    {
        int result = okcancel("You matched " + (MAXSCORE - SCORE) + " of " + MAXSCORE + "\nPlay again?");
        if(result == 0) board.init(); else System.exit(0);
    }

    protected void gameOver(String message)
    {
        int result = okcancel(message + "\n" + "You matched " + (MAXSCORE - SCORE) + " of " + MAXSCORE + "\nPlay again?");
        if(result == 0) board.init(); else System.exit(0);
    }

    private int okcancel(String message) {
        int result = JOptionPane.showConfirmDialog((Component) null, message,
                "Mahjong Solitaire", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    protected void addButton(TilePosition tp)
    {
        JButton button = tp.getButton();
        button.setEnabled(tp.isEnabled());

        Image image;
        if (tp.getTile().getSuit().equals(TileSuit.SEASONS)
                || tp.getTile().getSuit().equals(TileSuit.DRAGONS)
                || tp.getTile().getSuit().equals(TileSuit.FLOWERS)
                || tp.getTile().getSuit().equals(TileSuit.WINDS))
        {
            image = images.get(String.valueOf(tp.getTile().getValue()));

        } else
        {
            String s = String.valueOf(tp.getTile().getSuit()) + String.valueOf(tp.getTile().getValue());
            image = images.get(s);
        }
        Image newImage = image.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        if (!tp.isEnabled()) newImage = image.getScaledInstance(button.getWidth() *2 / 3, button.getHeight() *2 / 3, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        button.setIcon(icon);
        button.setDisabledIcon(icon);


        layeredPane.add(button, tp.layer);
    }

    protected void removeButton(TilePosition tp)
    {
        layeredPane.remove(tp.getButton());
    }

    protected void displayGUI(boolean visible)
    {
        f.setVisible(visible);
        f.validate();
        f.repaint();
    }

    private void loadImages()
    {
        images = new HashMap<>();
        try
        {
            images.put("BAMBOO", ImageIO.read(getClass().getResource("images/bamboo.png")));
            images.put("ORCHID", ImageIO.read(getClass().getResource("images/orchid.png")));
            images.put("PLUM", ImageIO.read(getClass().getResource("images/plum.png")));
            images.put("JUHUA", ImageIO.read(getClass().getResource("images/juhua.png")));
            images.put("SPRING", ImageIO.read(getClass().getResource("images/spring.png")));
            images.put("SUMMER", ImageIO.read(getClass().getResource("images/summer.png")));
            images.put("AUTUMN", ImageIO.read(getClass().getResource("images/autumn.png")));
            images.put("WINTER", ImageIO.read(getClass().getResource("images/winter.png")));
            images.put("RED", ImageIO.read(getClass().getResource("images/red.png")));
            images.put("GREEN", ImageIO.read(getClass().getResource("images/green.png")));
            images.put("WHITE", ImageIO.read(getClass().getResource("images/white.png")));
            images.put("NORTH", ImageIO.read(getClass().getResource("images/north.png")));
            images.put("SOUTH", ImageIO.read(getClass().getResource("images/south.png")));
            images.put("EAST", ImageIO.read(getClass().getResource("images/east.png")));
            images.put("WEST", ImageIO.read(getClass().getResource("images/west.png")));
            images.put("CHARACTERSONE", ImageIO.read(getClass().getResource("images/c1.png")));
            images.put("CHARACTERSTWO", ImageIO.read(getClass().getResource("images/c2.png")));
            images.put("CHARACTERSTHREE", ImageIO.read(getClass().getResource("images/c3.png")));
            images.put("CHARACTERSFOUR", ImageIO.read(getClass().getResource("images/c4.png")));
            images.put("CHARACTERSFIVE", ImageIO.read(getClass().getResource("images/c5.png")));
            images.put("CHARACTERSSIX", ImageIO.read(getClass().getResource("images/c6.png")));
            images.put("CHARACTERSSEVEN", ImageIO.read(getClass().getResource("images/c7.png")));
            images.put("CHARACTERSEIGHT", ImageIO.read(getClass().getResource("images/c8.png")));
            images.put("CHARACTERSNINE", ImageIO.read(getClass().getResource("images/c9.png")));
            images.put("BAMBOOONE", ImageIO.read(getClass().getResource("images/b1.png")));
            images.put("BAMBOOTWO", ImageIO.read(getClass().getResource("images/b2.png")));
            images.put("BAMBOOTHREE", ImageIO.read(getClass().getResource("images/b3.png")));
            images.put("BAMBOOFOUR", ImageIO.read(getClass().getResource("images/b4.png")));
            images.put("BAMBOOFIVE", ImageIO.read(getClass().getResource("images/b5.png")));
            images.put("BAMBOOSIX", ImageIO.read(getClass().getResource("images/b6.png")));
            images.put("BAMBOOSEVEN", ImageIO.read(getClass().getResource("images/b7.png")));
            images.put("BAMBOOEIGHT", ImageIO.read(getClass().getResource("images/b8.png")));
            images.put("BAMBOONINE", ImageIO.read(getClass().getResource("images/b9.png")));
            images.put("DOTSONE", ImageIO.read(getClass().getResource("images/d1.png")));
            images.put("DOTSTWO", ImageIO.read(getClass().getResource("images/d2.png")));
            images.put("DOTSTHREE", ImageIO.read(getClass().getResource("images/d3.png")));
            images.put("DOTSFOUR", ImageIO.read(getClass().getResource("images/d4.png")));
            images.put("DOTSFIVE", ImageIO.read(getClass().getResource("images/d5.png")));
            images.put("DOTSSIX", ImageIO.read(getClass().getResource("images/d6.png")));
            images.put("DOTSSEVEN", ImageIO.read(getClass().getResource("images/d7.png")));
            images.put("DOTSEIGHT", ImageIO.read(getClass().getResource("images/d8.png")));
            images.put("DOTSNINE", ImageIO.read(getClass().getResource("images/d9.png")));
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
