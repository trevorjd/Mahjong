package com.trevorjd;

import java.util.Random;

import static com.trevorjd.Mahjong.MAXCOL;
import static com.trevorjd.Mahjong.MAXLAY;
import static com.trevorjd.Mahjong.MAXROW;

public class TileLoc
{
    public int row;
    public int col;
    public int layer;

    public TileLoc()
    {
        Random rand = new Random(System.nanoTime());
        row = rand.nextInt(MAXROW-1);
        col = rand.nextInt(MAXCOL-1);
        layer = MAXLAY-1;
    }

    public TileLoc(int row, int col, int layer)
    {
        this.row = row;
        this.col = col;
        this.layer = layer;
    }
}
