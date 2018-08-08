package com.trevorjd;

import com.trevorjd.Mahjong.*;

public class MahjongTile
{

    private TileSuit suit;
    private TileValue tileValue;

    public MahjongTile(TileSuit suit, TileValue value)
    {
        this.suit = suit;
        this.tileValue = value;
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
