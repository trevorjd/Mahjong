package com.trevorjd;

public class TilePosition
{
    public int row;
    public int col;
    public int layer;
    private MahjongTile tile;

    public TilePosition()
    {

    }

    public TilePosition(TileLoc tl)
    {
        setPosition(tl);
    }

    public void setPosition(TileLoc tl)
    {
        this.row = tl.row;
        this.col = tl.col;
        this.layer = tl.layer;
    }

    public MahjongTile getTile()
    {
        return tile;
    }

    public void setTile(MahjongTile tile)
    {
        this.tile = tile;
    }
}
