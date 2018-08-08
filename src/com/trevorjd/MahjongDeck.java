package com.trevorjd;

import com.trevorjd.Mahjong.*;
import java.util.ArrayList;
import java.util.Random;

public class MahjongDeck
{
    private ArrayList<MahjongTile> deck;

    public void MahjongDeck()
    {

    }

    public void unsortedDeck()
    {
        deck = new ArrayList<MahjongTile>();
        addOneSetNumbers(); //27
        addOneSetNumbers(); //27
        addOneSetNumbers(); //27
        addOneSetNumbers(); //27 //108
        addOneSetWinds(); //4
        addOneSetWinds(); //4
        addOneSetWinds(); //4
        addOneSetWinds(); //4 //124
        addOneSetFlowers(); //4
        addOneSetSeasons(); //4 //132
        addOneSetDragons(); //3
        addOneSetDragons(); //3
        addOneSetDragons(); //3
        addOneSetDragons(); //3 //144
    }

    public boolean hasTiles()
    {
        if(deck.size() > 0) return true; else return false;
    }

    public int getSize() { return deck.size(); }

    private void addOneSetNumbers()
    {
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.ONE));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.TWO));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.THREE));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.FOUR));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.FIVE));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.SIX));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.SEVEN));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.EIGHT));
        deck.add(new MahjongTile(TileSuit.DOTS, TileValue.NINE));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.ONE));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.TWO));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.THREE));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.FOUR));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.FIVE));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.SIX));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.SEVEN));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.EIGHT));
        deck.add(new MahjongTile(TileSuit.BAMBOO, TileValue.NINE));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.ONE));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.TWO));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.THREE));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.FOUR));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.FIVE));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.SIX));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.SEVEN));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.EIGHT));
        deck.add(new MahjongTile(TileSuit.CHARACTERS, TileValue.NINE));
    }
    private void addOneSetWinds()
    {
        deck.add(new MahjongTile(TileSuit.WINDS, TileValue.EAST));
        deck.add(new MahjongTile(TileSuit.WINDS, TileValue.SOUTH));
        deck.add(new MahjongTile(TileSuit.WINDS, TileValue.NORTH));
        deck.add(new MahjongTile(TileSuit.WINDS, TileValue.WEST));
    }
    private void addOneSetFlowers()
    {
        deck.add(new MahjongTile(TileSuit.FLOWERS, TileValue.PLUM));
        deck.add(new MahjongTile(TileSuit.FLOWERS, TileValue.JUHUA));
        deck.add(new MahjongTile(TileSuit.FLOWERS, TileValue.BAMBOO));
        deck.add(new MahjongTile(TileSuit.FLOWERS, TileValue.ORCHID));
    }
    private void addOneSetSeasons()
    {
        deck.add(new MahjongTile(TileSuit.SEASONS, TileValue.SUMMER));
        deck.add(new MahjongTile(TileSuit.SEASONS, TileValue.SPRING));
        deck.add(new MahjongTile(TileSuit.SEASONS, TileValue.WINTER));
        deck.add(new MahjongTile(TileSuit.SEASONS, TileValue.AUTUMN));
    }
    private void addOneSetDragons()
    {
        deck.add(new MahjongTile(TileSuit.DRAGONS, TileValue.RED));
        deck.add(new MahjongTile(TileSuit.DRAGONS, TileValue.GREEN));
        deck.add(new MahjongTile(TileSuit.DRAGONS, TileValue.WHITE));
    }

    public void shuffleDeck()
    {
        ArrayList<MahjongTile> newDeck = new ArrayList<MahjongTile>();
        MahjongTile tile;
        Random rand = new Random(System.nanoTime());
        while (deck.size() > 0)
        {
            int randomIndex = rand.nextInt(deck.size());
            tile = new MahjongTile(deck.get(randomIndex).getSuit(), deck.get(randomIndex).getValue());
            newDeck.add(new MahjongTile(tile.getSuit(), tile.getValue()));
            deck.remove(randomIndex);

            boolean removedPair = false;

            int i = 0;
            while (!removedPair)
            {
                if (i < deck.size())
                {
                    // Seasons pair with other Seasons, Flowers with Flowers so...
                    if(tile.getSuit().equals(TileSuit.SEASONS))
                    {
                        if(deck.get(i).getSuit().equals(TileSuit.SEASONS))
                        {
                            newDeck.add(new MahjongTile(deck.get(i).getSuit(), deck.get(i).getValue()));
                            deck.remove(i);
                            removedPair = true;
                        } else i++;
                    } else
                    {
                        if(tile.getSuit().equals(TileSuit.FLOWERS))
                        {
                            if(deck.get(i).getSuit().equals(TileSuit.FLOWERS))
                            {
                                newDeck.add(new MahjongTile(deck.get(i).getSuit(), deck.get(i).getValue()));
                                deck.remove(i);
                                removedPair = true;
                            } else i++;
                        } else
                        {
                            if (deck.get(i).getSuit().equals(tile.getSuit())
                                    && deck.get(i).getValue().equals(tile.getValue()))
                            {
                                newDeck.add(new MahjongTile(deck.get(i).getSuit(), deck.get(i).getValue()));
                                deck.remove(i);
                                removedPair = true;
                            } else
                                {
                                    i++;
                                }
                        }

                    }

                } else
                    {
                        System.out.println("end of deck reached w/o a match");
                        removedPair = true;
                    }
            }


        }
        deck = (ArrayList<MahjongTile>) newDeck.clone();
    }

    public MahjongTile dealTile()
    {
        MahjongTile tile = deck.get(0);
        MahjongTile newTile = new MahjongTile(tile.getSuit(), tile.getValue());
        deck.remove(0);
        return newTile;
    }

    public void display()
    {
        for (MahjongTile m : deck)
        {
            System.out.println("tile: " + m.getSuit() + " " + m.getValue());
        }
    }
}
