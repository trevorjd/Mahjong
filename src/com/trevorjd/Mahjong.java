package com.trevorjd;

public class Mahjong
{
    protected static int MAXROW;
    protected static int MAXCOL;
    protected static int MAXLAY;

    protected enum TileSuit
    {
        DOTS,
        BAMBOO,
        CHARACTERS,
        WINDS,
        DRAGONS,
        SEASONS,
        FLOWERS,
        DUMMY;
    }

    protected enum TileValue
    {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        EAST,
        SOUTH,
        WEST,
        NORTH,
        RED,
        GREEN,
        WHITE,
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER,
        PLUM,
        ORCHID,
        JIUHUA,
        BAMBOO,
        DUMMY;
    }

    protected enum BoardLayout
    {
        TURTLE;
    }

    public static void main(String[] args)
    {
        MahjongBoard b = new MahjongBoard();
    }
}
