package com.trevorjd;

import java.util.ArrayList;
import java.util.Random;

import static com.trevorjd.Mahjong.*;
import static com.trevorjd.Mahjong.RelativePosition.*;

public class MahjongBoard
{
    // each tile is 2x2, thus allowing tiles to overlap/obstruct neighbours by half

    //Object[int row][int layer][com.trevorjd.MahjongTile]
    BoardGUI gui;
    MahjongDeck deck;
    MahjongBoardLayout gameBoard;
    TilePosition firstSelection;
    TilePosition secondSelection;
    ArrayList<TilePosition> workingList;

    public MahjongBoard()
    {
        init();
    }

    protected void init()
    {
        if(gui != null) gui.destroy();
        deck = new MahjongDeck();
        deck.unsortedDeck();
        deck.shuffleDeck();
        //deck.display();
        //gameBoard = new MahjongBoardLayout(BoardLayout.TURTLE);
        //gameBoard.displayContents();
        prepareBoard(BoardLayout.TURTLE);
    }

    protected void tileClickResponder(TilePosition tp)
    {
        if(firstSelection == null) {
            firstSelection = tp;
            //System.out.println("Set firstSelection");
        }
        else
        {
            secondSelection = tp;
            //check player did not click the same tile twice
            if(firstSelection != secondSelection)
            {
                TileValue firstV = firstSelection.getTile().getValue();
                TileValue secondV = secondSelection.getTile().getValue();
                TileSuit firstS = firstSelection.getTile().getSuit();
                TileSuit secondS = secondSelection.getTile().getSuit();
                /*
                System.out.println(" First: " + firstS
                        + " " + firstV
                        + " at L:" + firstSelection.layer
                        + " at R:" + firstSelection.row
                        + " at C:" + firstSelection.col
                );
                System.out.println("Second: " + secondS
                        + " " + secondV
                        + " at L:" + secondSelection.layer
                        + " at R:" + secondSelection.row
                        + " at C:" + secondSelection.col
                );
                */
                if(firstS.equals(secondS) && firstV.equals(secondV))
                {
                    processMatch(firstSelection, secondSelection);
                    checkRemainingMoves();
                } else
                {
                    if (firstS.equals(TileSuit.SEASONS) && secondS.equals(TileSuit.SEASONS))
                    {
                        processMatch(firstSelection, secondSelection);
                        checkRemainingMoves();
                    } else
                    {
                        if (firstS.equals(TileSuit.FLOWERS) && secondS.equals(TileSuit.FLOWERS))
                        {
                            processMatch(firstSelection, secondSelection);
                            checkRemainingMoves();
                        } else
                        {
                            firstSelection = null;
                            secondSelection = null;
                            //System.out.println("Not a match.");
                        }
                    }
                }
            } else
                {
                    firstSelection = null;
                    secondSelection = null;
                    // System.out.println("Clicked same tile twice.");
                }
        }
    }

    private void checkRemainingMoves()
    {
        boolean foundMatch = false;
        for (TilePosition testTP : workingList)
        {
            if(testTP.isEnabled())
            {
                for (TilePosition testTP2 : workingList)
                {
                    if(testTP2.isEnabled() && testTP != testTP2)
                    {
                        TileSuit testSuit1 = testTP.getTile().getSuit();
                        TileSuit testSuit2 = testTP2.getTile().getSuit();
                        TileValue testValue1 = testTP.getTile().getValue();
                        TileValue testValue2 = testTP2.getTile().getValue();
                        if(testSuit1.equals(TileSuit.SEASONS) && testSuit2.equals(TileSuit.SEASONS)) foundMatch = true;
                        else if(testSuit1.equals(TileSuit.FLOWERS) && testSuit2.equals(TileSuit.FLOWERS)) foundMatch = true;
                        else if(testSuit1 == testSuit2 && testValue1 == testValue2) foundMatch = true;
                    }
                }
            }
        }
        SCORE = workingList.size() / 2;
        if(!foundMatch)
        {
            if(SCORE == 0)
            {
                gui.gameOver("Congratulations!");
            } else gui.gameOver("No more moves possible.");

        }
    }

    private void processMatch(TilePosition firstP, TilePosition secondP)
    {
        // System.out.println("Matched. Doing stuff now...");
        workingList.remove(firstP);
        workingList.remove(secondP);
        gui.removeButton(firstP);
        gui.removeButton(secondP);
        for (TilePosition testTP : workingList)
        {
            checkObstructed(testTP, true);
        }
        firstSelection = null;
        secondSelection = null;
        gui.displayGUI(true);
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
            // System.out.println("location L:" + newTP.layer + " R:" + newTP.row + " C:" + newTP.col + " hasTile: " + newTP.getTile().getSuit() + " " + newTP.getTile().getValue());
            newTP.setButton(gui.makeButton(newTP));

        }
        // gameBoard.displayContents();
        // now draw all the tiles, one layer at a time
        workingList = gameBoard.getPositions();

        for (int layer = 0; layer < MAXLAY; layer++)
        {
            for (TilePosition tp : workingList)
            {
                // disable button if obstructed
                checkObstructed(tp, false);
                if(tp.layer == layer)
                {
                    gui.addButton(tp);
                }
            }
        }
        gui.displayGUI(true);
    }

    protected void checkObstructed(TilePosition tp, boolean debug)
    {
        boolean isObstructed = false;
        boolean didSomething = false;


        // layer MAXLAY can't be upward
        // row 0 can't be northward
        // col 0 can't be westward
        // row MAXROW can't be southward
        // col MAXCOL can't be eastward
        if(tp.col <= 1 || tp.col >= MAXCOL - 2)
        {
            // outer column, any row
            if(tp.layer == MAXLAY)
            {
                // outer column, any row, top layer; no action required
                didSomething = true;
            } else
            {
                // layer is not top, look straight up
                isObstructed = testObstructed(tp, UP);
                if(tp.row == 0)
                {
                    // outer column, first row, NOT top layer; look southUp
                    isObstructed = testObstructed(tp, UPS);
                    didSomething = true;
                } else if(tp.row == MAXROW)
                {
                    // outer column, last row, NOT top layer; look northUp
                    isObstructed = testObstructed(tp, UPN);
                    didSomething = true;
                } else
                {
                    // outer column, middle row, NOT top layer; look northUp and southUp
                    isObstructed = testObstructed(tp, UPN);
                    isObstructed = testObstructed(tp, UPS);
                    didSomething = true;
                }
            }
        } else
        {
            // middle column, test if pinned east AND west

            if(tp.layer == MAXLAY - 1)
            {
                // a middle column, any row, top layer; look east and west
                if(tp.row == 0)
                {
                    // row 0; test E || SE && W || SW
                    if((testObstructed(tp, SE) || testObstructed(tp, E)) && (testObstructed(tp, SW) || testObstructed(tp, W)))
                    {
                        isObstructed = true;
                        didSomething = true;
                    }
                } else if (tp.row == MAXROW)
                {
                    // row MAXROW; test E || NE && W || NW
                    if((testObstructed(tp, NE) || testObstructed(tp, E)) && (testObstructed(tp, NW) || testObstructed(tp, W)))
                    {
                        isObstructed = true;
                        didSomething = true;
                    }
                } else
                {
                    // middle row; test E || SE || NE && W || SW || NW
                    if((testObstructed(tp, NE) || testObstructed(tp, E) || testObstructed(tp, SE)) &&
                            (testObstructed(tp, NW) || testObstructed(tp, W) || testObstructed(tp, SW)))
                    {
                        isObstructed = true;
                        didSomething = true;
                    }
                }
            } else
            {
                // a middle column, NOT top layer; all of the above plus upwards
                if(tp.row == 0)
                {
                    // row 0; test E || SE && W || SW
                    boolean upne = testObstructed(tp, UPNE); boolean upnw = testObstructed(tp, UPNW);
                    boolean upse = testObstructed(tp, UPSE); boolean upsw = testObstructed(tp, UPSW);
                    boolean upe = testObstructed(tp, UPE); boolean upw = testObstructed(tp, UPW);
                    boolean se = testObstructed(tp, SE); boolean sw = testObstructed(tp, SW);
                    boolean e = testObstructed(tp, E); boolean w = testObstructed(tp, W);

                    if((se || e) && (sw || w) || upe || upw || upne || upnw || upse || upsw)
                    {
                        isObstructed = true;
                        didSomething = true;
                    } else didSomething = true;
                } else if (tp.row == MAXROW)
                {
                    // row MAXROW; test E || NE && W || NW
                    boolean upne = testObstructed(tp, UPNE); boolean upnw = testObstructed(tp, UPNW);
                    boolean upe = testObstructed(tp, UPE); boolean upw = testObstructed(tp, UPW);
                    boolean ne = testObstructed(tp, NE); boolean nw = testObstructed(tp, NW);
                    boolean e = testObstructed(tp, E); boolean w = testObstructed(tp, W);
                    if((ne || e) && (nw || w) || upne || upnw || upe || upw)
                    {
                        isObstructed = true;
                        didSomething = true;
                    } else didSomething = true;
                } else
                {
                    // middle row, not top layer; test E || SE || NE && W || SW || NW
                    boolean ne = testObstructed(tp, NE); boolean nw = testObstructed(tp, NW);
                    boolean se = testObstructed(tp, SE); boolean sw = testObstructed(tp, SW);
                    boolean upne = testObstructed(tp, UPNE); boolean upnw = testObstructed(tp, UPNW);
                    boolean upse = testObstructed(tp, UPSE); boolean upsw = testObstructed(tp, UPSW);
                    boolean e = testObstructed(tp, E); boolean w = testObstructed(tp, W);
                    boolean up = testObstructed(tp, UP);

                    if( (e || ne || se) && (w || nw || sw) || upne || upse || upnw ||upsw || up)
                        {
                            isObstructed = true;
                            didSomething = true;
                        } else didSomething = true;
                }

            }
        }


        if(didSomething)
        {
            if(isObstructed)
            {
                tp.setEnabled(false);
            } else
            {
                gui.removeButton(tp);
                tp.setEnabled(true);
                gui.addButton(tp);
            }
        }

    }

    protected boolean testObstructed_old(TilePosition tp, RelativePosition rp)
    {
        // returns true if tile is obstructed
        boolean result = true;
        switch (rp) {
            case UP :
            {
                TilePosition tpUp = gameBoard.getTP(new TileLoc(tp.row, tp.col, tp.layer + 1));
                if(tpUp == null) result = false;
                break;
            }
            case UPN :
            {
                TilePosition tpUpN = gameBoard.getTP(new TileLoc(tp.row -1 , tp.col, tp.layer + 1));
                if(tpUpN == null) result = false;
                break;
            }
            case UPS :
            {
                TilePosition tpUpS = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col, tp.layer + 1));
                if(tpUpS == null) result = false;
                break;
            }
            case UPE :
            {
                TilePosition tpUpE = gameBoard.getTP(new TileLoc(tp.row, tp.col + 1, tp.layer + 1));
                if(tpUpE == null) result = false;
                break;
            }
            case UPW :
            {
                TilePosition tpUpW = gameBoard.getTP(new TileLoc(tp.row, tp.col - 1, tp.layer + 1));
                if(tpUpW == null) result = false;
                break;
            }
            case UPNE :
            {
                TilePosition tpUpNE = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col + 1, tp.layer + 1));
                if(tpUpNE == null) result = false;
                break;
            }
            case UPNW :
            {
                TilePosition tpUpNW = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col - 1, tp.layer + 1));
                if(tpUpNW == null) result = false;
                break;
            }
            case UPSE :
            {
                TilePosition tpUpSE = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col + 1, tp.layer + 1));
                if(tpUpSE == null) result = false;
                break;
            }
            case UPSW :
            {
                TilePosition tpUpSW = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col - 1, tp.layer + 1));
                if(tpUpSW == null) result = false;
                break;
            }
            case E :
            {
                TilePosition tpE = gameBoard.getTP(new TileLoc(tp.row, tp.col + 2, tp.layer));
                if(tpE == null) result = false;
                break;
            }
            case W :
            {
                TilePosition tpW = gameBoard.getTP(new TileLoc(tp.row, tp.col - 2, tp.layer));
                if(tpW == null) result = false;
                break;
            }
            case NE :
            {
                TilePosition tpNE = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col + 2, tp.layer));
                if(tpNE == null) result = false;
                break;
            }
            case NW :
            {
                TilePosition tpNW = gameBoard.getTP(new TileLoc(tp.row - 1, tp.col - 2, tp.layer));
                if(tpNW == null) result = false;
                break;
            }
            case SE :
            {
                TilePosition tpSE = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col + 2, tp.layer));
                if(tpSE == null) result = false;
                break;
            }
            case SW :
            {
                TilePosition tpSW = gameBoard.getTP(new TileLoc(tp.row + 1, tp.col - 2, tp.layer));
                if(tpSW == null) result = false;
                break;
            }
        }
        return result;
    }

    protected boolean testObstructed(TilePosition tp, RelativePosition rp)
    {
        boolean result = false;
        for (TilePosition testTP : workingList)
        {
            switch (rp) {
                case UP :
                {
                    if(testTP.row == tp.row && testTP.col == tp.col && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPN :
                {
                    if(testTP.row == tp.row -1 && testTP.col == tp.col && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPS :
                {
                    if(testTP.row == tp.row +1 && testTP.col == tp.col && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPE :
                {
                    if(testTP.row == tp.row && testTP.col == tp.col +1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPW :
                {
                    if(testTP.row == tp.row && testTP.col == tp.col -1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPNE :
                {

                    if(testTP.row == tp.row -1 && testTP.col == tp.col +1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPNW :
                {
                    if(testTP.row == tp.row -1 && testTP.col == tp.col -1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPSE :
                {
                    if(testTP.row == tp.row +1 && testTP.col == tp.col +1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case UPSW :
                {
                    if(testTP.row == tp.row +1 && testTP.col == tp.col -1 && testTP.layer == tp.layer + 1)
                    {
                        result = true;
                    }
                    break;
                }
                case E :
                {
                    if(testTP.row == tp.row && testTP.col == tp.col +2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
                case W :
                {
                    if(testTP.row == tp.row && testTP.col == tp.col -2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
                case NE :
                {
                    if(testTP.row == tp.row -1 && testTP.col == tp.col +2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
                case NW :
                {
                    if(testTP.row == tp.row -1 && testTP.col == tp.col -2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
                case SE :
                {
                    if(testTP.row == tp.row +1 && testTP.col == tp.col +2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
                case SW :
                {
                    if(testTP.row == tp.row +1 && testTP.col == tp.col -2 && testTP.layer == tp.layer)
                    {
                        result = true;
                    }
                    break;
                }
            }
        }
        return result;
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