package com.trevorjd;

import java.util.ArrayList;

import static com.trevorjd.Mahjong.MAXCOL;
import static com.trevorjd.Mahjong.MAXLAY;
import static com.trevorjd.Mahjong.MAXROW;
import static com.trevorjd.Mahjong.BoardLayout;
public class MahjongBoardLayout
{
    private ArrayList[] layers;
    private ArrayList<TilePosition> layerDefintion;
    private String layoutDef[][];

    public MahjongBoardLayout(BoardLayout layout)
    {
        if (layout.equals(BoardLayout.TURTLE))
        {
            System.out.println("Making a " + BoardLayout.TURTLE);
            MAXROW = 16;
            MAXCOL = 30;
            MAXLAY = 5;
            layers = new ArrayList[MAXLAY];
            layoutDef = new String[MAXROW][MAXLAY];

            layoutDef[0][0]  = "00 10 10 10 10 10 10 10 10 10 10 10 10 00 00";
            layoutDef[1][0]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[2][0]  = "00 00 00 10 10 10 10 10 10 10 10 00 00 00 00";
            layoutDef[3][0]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[4][0]  = "00 00 10 10 10 10 10 10 10 10 10 10 00 00 00";
            layoutDef[5][0]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[6][0]  = "00 10 10 10 10 10 10 10 10 10 10 10 10 00 00";
            layoutDef[7][0]  = "10 00 00 00 00 00 00 00 00 00 00 00 00 10 10";
            layoutDef[8][0]  = "00 10 10 10 10 10 10 10 10 10 10 10 10 00 00";
            layoutDef[9][0]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[10][0] = "00 00 10 10 10 10 10 10 10 10 10 10 00 00 00";
            layoutDef[11][0] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[12][0] = "00 00 00 10 10 10 10 10 10 10 10 00 00 00 00";
            layoutDef[13][0] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[14][0] = "00 10 10 10 10 10 10 10 10 10 10 10 10 00 00";
            layoutDef[15][0] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";

            layoutDef[0][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[1][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[2][1]  = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[3][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[4][1]  = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[5][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[6][1]  = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[7][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[8][1]  = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[9][1]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[10][1] = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[11][1] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[12][1] = "00 00 00 00 10 10 10 10 10 10 00 00 00 00 00";
            layoutDef[13][1] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[14][1] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[15][1] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";

            layoutDef[0][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[1][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[2][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[3][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[4][2]  = "00 00 00 00 00 10 10 10 10 00 00 00 00 00 00";
            layoutDef[5][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[6][2]  = "00 00 00 00 00 10 10 10 10 00 00 00 00 00 00";
            layoutDef[7][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[8][2]  = "00 00 00 00 00 10 10 10 10 00 00 00 00 00 00";
            layoutDef[9][2]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[10][2] = "00 00 00 00 00 10 10 10 10 00 00 00 00 00 00";
            layoutDef[11][2] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[12][2] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[13][2] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[14][2] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[15][2] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";

            layoutDef[0][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[1][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[2][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[3][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[4][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[5][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[6][3]  = "00 00 00 00 00 00 10 10 00 00 00 00 00 00 00";
            layoutDef[7][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[8][3]  = "00 00 00 00 00 00 10 10 00 00 00 00 00 00 00";
            layoutDef[9][3]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[10][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[11][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[12][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[13][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[14][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[15][3] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";

            layoutDef[0][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[1][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[2][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[3][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[4][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[5][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[6][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[7][4]  = "00 00 00 00 00 00 01 00 00 00 00 00 00 00 00";
            layoutDef[8][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[9][4]  = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[10][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[11][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[12][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[13][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[14][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
            layoutDef[15][4] = "00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";

            for (int layer = 0; layer < MAXLAY; layer++)
            {
                layerDefintion = new ArrayList<TilePosition>();
                for (int row = 0; row < MAXROW; row++)
                {
                    for(int col = 0; col < MAXCOL; col++)
                    {
                        String s = layoutDef[row][layer].replace(" ", "");
                        if('1' - s.charAt(col) == 0)
                        {
                            layerDefintion.add(new TilePosition(new TileLoc(row, col, layer)));
                        }
                    }
                }
                layers[layer] = layerDefintion;
            }
        }
    }

    protected void displayStructure()
    {
        for (int layer = 0; layer < MAXLAY; layer++)
        {
            layerDefintion = layers[layer];
            for (TilePosition tp : layerDefintion)
            {
                System.out.println("layer: " + layer + " validpos: " + tp.layer + " " + tp.row + " " + tp.col);
            }
        }
    }

    protected void displayContents()
    {
        for (int layer = 0; layer < MAXLAY; layer++)
        {
            layerDefintion = layers[layer];
            for (TilePosition tp : layerDefintion)
            {
                String s; String v;
                if(tp.getTile() != null)
                {
                    s = String.valueOf(tp.getTile().getSuit());
                } else s = "null";

                if(tp.getTile() != null)
                {
                    v = String.valueOf(tp.getTile().getValue());
                } else v = "null";
                System.out.println("validpos: " + tp.layer + " " + tp.row + " " + tp.col
                        + " " + s + " " + v);
            }
        }
    }

    public ArrayList<TilePosition> getLayer(int layer)
    {
        return layers[layer];
    }

    public int getNumberofLayers()
    {
        return layers.length;
    }

    public void addTile(MahjongTile tile, TilePosition tp)
    {
        ArrayList<TilePosition> thisLayer = layers[tile.layer];
        for (TilePosition thisTP : thisLayer)
        {
            if(thisTP.row == tp.row && thisTP.col == tp.col && thisTP.layer == tp.layer)
            {
                thisTP.setTile(tile);
            }
        }
    }

    public int getMAXROW() { return MAXROW; }

    public int getMAXCOL() { return MAXCOL; }

    public int getMAXLAY() { return MAXLAY; }
}
