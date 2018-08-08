package com.trevorjd;

import javax.swing.*;

public class TilePosition
{
    protected int row;
    protected int col;
    protected int layer;
    private MahjongTile tile;
    private JButton button;

    protected TilePosition()
    {

    }

    protected TilePosition(TileLoc tl)
    {
        setLocation(tl);
    }

    protected void setLocation(TileLoc tl)
    {
        this.row = tl.row;
        this.col = tl.col;
        this.layer = tl.layer;
    }

    protected TileLoc getLocation()
    {
        return new TileLoc(row, col, layer);
    }

    protected MahjongTile getTile()
    {
        return tile;
    }

    public void setTile(MahjongTile tile)
    {
        this.tile = tile;
    }

    public JButton getButton()
    {
        return button;
    }

    public void setButton(JButton button)
    {
        this.button = button;
    }
}
