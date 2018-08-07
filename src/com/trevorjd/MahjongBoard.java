package com.trevorjd;

import java.util.ArrayList;
import java.util.Random;

import static com.trevorjd.Mahjong.*;

public class MahjongBoard
{
    // each tile is 2x2, thus allowing tiles to overlap/obstruct neighbours by half

    //Object[int row][int layer][com.trevorjd.MahjongTile]
    TilePosition board[][][];
    BoardGUI gui;
    MahjongDeck deck;
    MahjongTile tile;
    MahjongBoardLayout gameBoard;

    public MahjongBoard()
    {
        deck = new MahjongDeck();
        deck.unsortedDeck();
        deck.shuffleDeck();
        //deck.display();
        //gameBoard = new MahjongBoardLayout(BoardLayout.TURTLE);
        //gameBoard.displayContents();
        //gameBoard.displayStructure();
        prepareBoard(BoardLayout.TURTLE);


    }

    private void prepareBoard(BoardLayout layout)
    {

        gameBoard = new MahjongBoardLayout(layout);
        gui = new BoardGUI(gameBoard);
        int placed = 0;
        while (deck.hasTiles())
        {
            for(int layerIndex = 0; layerIndex < gameBoard.getNumberofLayers(); layerIndex++)
            {
                ArrayList<TilePosition> thisLayer = (ArrayList<TilePosition>) gameBoard.getLayer(layerIndex).clone();
                while(!thisLayer.isEmpty())
                {
                    MahjongTile tile = deck.dealTile();
                    Random rand = new Random(System.nanoTime());
                    int i = 0;
                    if(thisLayer.size() > 1) i = rand.nextInt(thisLayer.size()-1);
                    TilePosition tp = thisLayer.get(i);
                    tile.setLoc(new TileLoc(tp.row, tp.col, tp.layer));
                    gameBoard.addTile(tile, tp);
                    gui.addButton(tile);
                    thisLayer.remove(i);
                    placed++;
                    System.out.println("Placed " + placed + " tiles. "
                            + tile.getSuit() + " "
                            + tile.getValue() + " "
                            + tile.row + " "
                            + tile.col + " "
                            + tile.layer
                    );
                }
            }
        }
    }

 }