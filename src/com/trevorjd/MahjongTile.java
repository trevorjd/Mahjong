package com.trevorjd;

import com.trevorjd.Mahjong.*;

public class MahjongTile
{

    private TileSuit suit;
    private TileValue tileValue;
    public int row;
    public int col;
    public int layer;

    public MahjongTile(TileSuit suit, TileValue value, TileLoc tl)
    {
        this.suit = suit;
        this.tileValue = value;
        this.row = tl.row;
        this.col = tl.col;
        this.layer = tl.layer;
    }

    public MahjongTile(TileSuit suit, TileValue value)
    {
        this.suit = suit;
        this.tileValue = value;
    }

    protected void setLoc(TileLoc tl)
    {
        this.row = tl.row;
        this.col = tl.col;
        this.layer = tl.layer;
    }

    protected TileLoc getLoc()
    {
        return new TileLoc(this.row, this.col, this.layer);
    }

    public TileSuit getSuit()
    {
        return suit;
    }

    public TileValue getValue()
    {
        return tileValue;
    }
}
