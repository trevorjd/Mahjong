package com.trevorjd;

public class Mahjong
{
    protected static int MAXROW;
    protected static int MAXCOL;
    protected static int MAXLAY;
    protected static boolean DEBUG;
    protected static int SCORE;
    protected static int MAXSCORE;
    protected static MahjongBoard board;

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
        JUHUA,
        BAMBOO,
        DUMMY;
    }

    protected enum RelativePosition
    {
        UP,
        UPN,
        UPS,
        UPE,
        UPW,
        UPNE,
        UPNW,
        UPSE,
        UPSW,
        E,
        W,
        NE,
        NW,
        SE,
        SW;
    }

    protected enum BoardLayout
    {
        TURTLE;
    }

    public static void main(String[] args)
    {
        board = new MahjongBoard();
    }
}
