package com.trevorjd;

import java.util.ArrayList;

import static com.trevorjd.Mahjong.MAXCOL;
import static com.trevorjd.Mahjong.MAXLAY;
import static com.trevorjd.Mahjong.MAXROW;
import static com.trevorjd.Mahjong.BoardLayout;
public class MahjongBoardLayout
{
    private TilePosition positions[][][];
    private String layoutDef[][];

    public MahjongBoardLayout(BoardLayout layout)
    {
        if (layout.equals(BoardLayout.TURTLE))
        {
            System.out.println("Making a " + BoardLayout.TURTLE);
            MAXROW = 16;
            MAXCOL = 30;
            MAXLAY = 5;
            positions = new TilePosition[MAXROW][MAXCOL][MAXLAY];
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
                for (int row = 0; row < MAXROW; row++)
                {
                    for(int col = 0; col < MAXCOL; col++)
                    {
                        String s = layoutDef[row][layer].replace(" ", "");
                        if('1' - s.charAt(col) == 0)
                        {
                            positions[row][col][layer] = new TilePosition(new TileLoc(row, col, layer));
                        }
                    }
                }
            }
        }
    }


    protected void displayContents()
    {
        for (int layer = 0; layer < MAXLAY; layer++)
        {
            for (int row = 0; row < MAXROW; row++)
            {
                for(int col = 0; col < MAXCOL; col++)
                {
                    String s; String v;
                    if(positions[row][col][layer].getTile() != null)
                    {
                        s = String.valueOf(positions[row][col][layer].getTile().getSuit());
                    } else s = "null";

                    if(positions[row][col][layer].getTile() != null)
                    {
                        v = String.valueOf(positions[row][col][layer].getTile().getValue());
                    } else v = "null";
                    System.out.println("validpos: " + layer + " " + row + " " + col
                            + " " + s + " " + v);
                }
            }
        }
    }


    public void addTP(TilePosition tp)
    {
        positions[tp.row][tp.col][tp.layer] = tp;
    }

    public TilePosition getTP(TileLoc tl)
    {
        return positions[tl.row][tl.col][tl.layer];
    }

    public int getMAXROW() { return MAXROW; }

    public int getMAXCOL() { return MAXCOL; }

    public int getMAXLAY() { return MAXLAY; }

    protected ArrayList<TilePosition> getLayer(int layer)
    {
        ArrayList<TilePosition> result = new ArrayList<TilePosition>();
        for (int row = 0; row < MAXROW; row++)
        {
            for (int col = 0; col < MAXCOL; col++)
            {
                if(positions[row][col][layer] != null)
                {
                    result.add(positions[row][col][layer]);
                }
            }
        }
        return result;
    }

    protected ArrayList<TilePosition> getPositions()
    {
        ArrayList<TilePosition> result = new ArrayList<TilePosition>();
        for (int row = 0; row < MAXROW; row++)
        {
            for (int col = 0; col < MAXCOL; col++)
            {
                for (int lay = 0; lay < MAXCOL; lay++)
                {
                    if(positions[row][col][lay] != null)
                    {
                        result.add(positions[row][col][lay]);
                    }
                }

            }
        }
        return result;
    }

    protected ArrayList<TilePosition> getLocations()
    {
        ArrayList<TilePosition> result = new ArrayList<TilePosition>();
        for (int row = 0; row < MAXROW; row++)
        {
            for (int col = 0; col < MAXCOL; col++)
            {
                for (int lay = 0; lay < MAXLAY; lay++)
                {
                    if(positions[row][col][lay] != null)
                    {
                        result.add(new TilePosition(new TileLoc(row, col, lay)));
                    }
                }

            }
        }
        return result;
    }
}
