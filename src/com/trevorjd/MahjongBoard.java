package com.trevorjd;

import java.util.ArrayList;
import java.util.Random;

import static com.trevorjd.Mahjong.*;
import static java.lang.Math.max;

public class MahjongBoard
{
    // each tile is 2x2, thus allowing tiles to overlap/obstruct neighbours by half

    //Object[int row][int layer][com.trevorjd.MahjongTile]
    BoardGUI gui;
    MahjongDeck deck;
    MahjongBoardLayout gameBoard;

    public MahjongBoard()
    {
        deck = new MahjongDeck();
        deck.unsortedDeck();
        deck.shuffleDeck();
        //deck.display();
        //gameBoard = new MahjongBoardLayout(BoardLayout.TURTLE);
        //gameBoard.displayContents();
        prepareBoard(BoardLayout.TURTLE);


    }

    private void prepareBoard(BoardLayout layout)
    {

        gameBoard = new MahjongBoardLayout(layout);
        ArrayList<TileLoc> positionList = (ArrayList<TileLoc>) gameBoard.getLocations().clone();
        gui = new BoardGUI(gameBoard);
        while (deck.hasTiles())
        {
            // get the next tile off the deck
            MahjongTile tile = deck.dealTile();
            // get a random position from the to-do list
            Random rand = new Random(System.nanoTime());
            int i = 0;
            if(positionList.size() > 1) i = rand.nextInt(positionList.size()-1);
            // find the matching TilePosition on the gameBoard
            TileLoc tl = positionList.get(i);
            TilePosition tp = gameBoard.getTP(tl);
            // add the tile
            tp.setTile(new MahjongTile(tile.getSuit(), tile.getValue()));
            // drop the MahjongTile down as far as possible
            TileLoc finalPosition = dropTile(tp);
            // find and remove the used TilePosition from the to-do list
            for (int nuke = 0; nuke < positionList.size(); nuke++)
            {
                TileLoc nukeTL = positionList.get(nuke);
                if(nukeTL.row == finalPosition.row && nukeTL.col == finalPosition.col && nukeTL.layer == finalPosition.layer)
                {
                    //System.out.println("nuking: " + nukeTP.row + " " + nukeTP.col + " " + nukeTP.layer);
                    positionList.remove(nuke);
                }
            }
            // add a button to the TP
            TilePosition newTP = gameBoard.getTP(finalPosition);
            System.out.println("location L:" + newTP.layer + " R:" + newTP.row + " C:" + newTP.col + " hasTile: " + newTP.getTile().getSuit() + " " + newTP.getTile().getValue());
            newTP.setButton(gui.makeButton(newTP));

            // look up. If there's a valid position directly or partially above, disable this button.
            checkObstructed(newTP);
        }
        // gameBoard.displayContents();
        // now draw all the tiles, one layer at a time
        ArrayList<TilePosition> positions = gameBoard.getPositions();
        for (int layer = 0; layer < MAXLAY; layer++)
        {
            for (TilePosition tp : positions)
            {

                if(tp.layer == layer)
                {
                    System.out.println("location L:" + tp.layer + " R:" + tp.row + " C:" + tp.col + " hasTile: " + tp.hasTile()+ " " + tp.getTile().getSuit() + " : " + tp.getTile().getValue());

                    gui.addButton(tp);
                }
            }
        }
        gui.setGUIVisibility(true);
    }

    protected void checkObstructed(TilePosition tp)
    {
        // look up and around
        if(tp.row > 0 && tp.row < MAXROW -1 && tp.col > 0 && tp.col < MAXCOL -1 && tp.layer < MAXLAY -1)
        {
            TilePosition tpUp = gameBoard.getTP(new TileLoc(tp.row, tp.col, tp.layer + 1));
            TilePosition tpN = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col, tp.layer + 1));
            TilePosition tpS = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col, tp.layer + 1));
            TilePosition tpE = gameBoard.getTP(new TileLoc(tp.row, tp.col + 1, tp.layer + 1));
            TilePosition tpW = gameBoard.getTP(new TileLoc(tp.row, tp.col - 1, tp.layer + 1));
            TilePosition tpNE = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col + 1, tp.layer + 1));
            TilePosition tpNW = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col - 1, tp.layer + 1));
            TilePosition tpSE = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col + 1, tp.layer + 1));
            TilePosition tpSW = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col - 1, tp.layer + 1));
            if (tpUp != null || tpN != null || tpS != null || tpE != null || tpW != null
                    || tpNE != null || tpNW != null || tpSE != null || tpSW != null)
            {
                // There's a tile blocking me
                tp.setEnabled(false);
            }
        }

        // check for obstructions on the same layer (increments of two gridlines)
        if(tp.row >= 0 && tp.row < MAXROW - 1 && tp.col >= 2 && tp.col <= MAXCOL - 3)
        {
            TilePosition tpE = gameBoard.getTP(new TileLoc(tp.row, tp.col + 2, tp.layer));
            TilePosition tpW = gameBoard.getTP(new TileLoc(tp.row, tp.col - 2, tp.layer));
            TilePosition tpNE = gameBoard.getTP(new TileLoc(max(0, tp.row - 1), tp.col + 2, tp.layer));
            TilePosition tpNW = gameBoard.getTP(new TileLoc(max(0, tp.row - 1), tp.col - 2, tp.layer));
            TilePosition tpSE = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col + 2, tp.layer));
            TilePosition tpSW = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col - 2, tp.layer));
            if ((tpE != null || tpNE != null || tpSE != null) && (tpW != null || tpNW != null | tpSW != null))
            {
                // There's a tile blocking me
                tp.setEnabled(false);
            }
        }


    }

    protected TileLoc dropTile(TilePosition tp)
    {
        boolean dropped = false;
        while (!dropped)
        {
            if(tp.layer > 0)
            {
                TilePosition testTP = gameBoard.getTP(new TileLoc(tp.row, tp.col, tp.layer - 1));
                if (testTP != null)
                {
                    if(testTP.getTile() != null)
                    {
                        // the TilePosition(s) below tp already has a tile therefore tp can't go lower
                        dropped = true;
                    } else
                    {
                        // the TilePosition below tp doesn't have a tile
                        // move the tile down
                        testTP.setTile(tp.getTile());
                        tp.setTile(null);
                        tp = gameBoard.getTP(testTP.getLocation());
                    }
                } else
                {
                    // Location under tp is null (not a valid tp)
                    // if tp.layer > 0, this means the tp overlaps multiple rows or columns
                    // which we don't need to worry about here
                    dropped = true;
                }
            } else
            {
                // already on layer 0
                dropped = true;
            }
        }

        return tp.getLocation();
    }

 }