package com.trevorjd;

import static com.trevorjd.Mahjong.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardGUI
{
    private JFrame f;
    private JLabel tileLabel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel zLabel;
    private final int gridXSize = 40;
    private final int gridYSize = 40;
    private final int tWidth = (int) (gridXSize * 2);
    private final int tHeight = (int) (gridYSize * 2);
    private int xOffset;
    private int yOffset;

    protected BoardGUI(MahjongBoardLayout layout)
    {
        int gWidth = ((layout.getMAXCOL() + 2 + layout.getMAXLAY() / 2) * gridXSize)+100;
        int gHeight = (layout.getMAXROW() + 2 + layout.getMAXLAY() / 2) * gridYSize;
        xOffset = gridXSize+100;
        yOffset = gridYSize;
        f = new JFrame("Mahjong Solitaire");
        f.setSize(gWidth, gHeight);

        tileLabel = new JLabel();
        tileLabel.setBounds(tWidth + 30, tHeight, 200, 20);
        f.add(tileLabel);

        JLabel tileLabelTitle = new JLabel("Tile:");
        tileLabelTitle.setBounds(tWidth, tHeight, 50, 20);
        f.add(tileLabelTitle);

        xLabel = new JLabel();
        xLabel.setBounds(tWidth  + 40, tHeight * 2, 50, 20);
        f.add(xLabel);

        JLabel xLabelTitle = new JLabel("x Pos:");
        xLabelTitle.setBounds(tWidth, tHeight * 2, 50, 20);
        f.add(xLabelTitle);

        yLabel = new JLabel();
        yLabel.setBounds(tWidth  + 40, tHeight * 3, 50, 20);
        f.add(yLabel);

        JLabel yLabelTitle = new JLabel("y Pos:");
        yLabelTitle.setBounds(tWidth, tHeight * 3, 50, 20);
        f.add(yLabelTitle);

        zLabel = new JLabel();
        zLabel.setBounds(tWidth  + 40, tHeight * 4, 50, 20);
        f.add(zLabel);

        JLabel zLabelTitle = new JLabel("z Pos:");
        zLabelTitle.setBounds(tWidth, tHeight * 4, 50, 20);
        f.add(zLabelTitle);

        f.setLayout(null);
        f.setVisible(true);
    }

    protected void addButton(MahjongTile tile)
    {
        TileSuit suit = tile.getSuit();
        TileValue value = tile.getValue();
        JLabel button = new JLabel(suit.toString().substring(0,1) + value.toString().substring(0,1));
        int xpos = tile.col * gridXSize + xOffset + (int) (tile.layer * gridXSize * 0.1);
        int ypos = tile.row * gridYSize + yOffset + (int) (tile.layer * gridYSize * 0.1);
        button.setBounds(xpos, ypos, tWidth, tHeight);
        button.setText(tile.row + " " + tile.col + " " + tile.layer);
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        if(tile.layer == 1) { border = BorderFactory.createLineBorder(Color.blue, 1); }
        if(tile.layer == 2) { border = BorderFactory.createLineBorder(Color.cyan, 1); }
        if(tile.layer == 3) { border = BorderFactory.createLineBorder(Color.orange, 1); }
        if(tile.layer == 4) { border = BorderFactory.createLineBorder(Color.red, 1); }
        if(tile.layer == 5) { border = BorderFactory.createLineBorder(Color.green, 1); }
        if(tile.layer == 6) { border = BorderFactory.createLineBorder(Color.pink, 1); }
        button.setBorder(border);
        f.add(button);
    }

    protected void refreshGUI()
    {
        f.revalidate();
    }

    protected void setInfo(MahjongTile tile)
    {
        tileLabel.setText(tile.getSuit() + " " + tile.getValue());
        xLabel.setText(String.valueOf(tile.col));
        yLabel.setText(String.valueOf(tile.row));
        zLabel.setText(String.valueOf(tile.layer));
    }
}
