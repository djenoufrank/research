#include "catch.hpp"
#include "../metier/Position.h"
#include "../metier/Piece.h"
#include "../metier/Player.h"
#include "../metier/Game.h"
#include "../metier/Color.h"
namespace std {


TEST_CASE("Testing isInTheWater")
{

    SECTION("verify that given position is in the water ")
    {
        vector<Piece> a;
        vector<Piece> b;
        a.push_back(Piece(ROLE::SPY,Position(5,2)));
        Player red(a,Color::RED);
        Player blue(b,Color::BLUE);
        Game game(red,blue);

        REQUIRE(game.isInTheWater(a.at(0).getPiecePosition())==true);
        }
    SECTION("verify that given position is not in the water ")
    {     vector<Piece> a;
          vector<Piece> b;
          a.push_back(Piece(ROLE::SPY,Position(7,2)));
          Player red(a,Color::RED);
          Player blue(b,Color::BLUE);
          Game game(red,blue);

          REQUIRE(game.isInTheWater(a.at(0).getPiecePosition())==false);
    }

    SECTION("Testing move and can't go in the water")
    {
        vector<Piece> a;
                 vector<Piece> b;
                 a.push_back(Piece(ROLE::SPY,Position(7,2)));
                 Player red(a,Color::RED);
                 Player blue(b,Color::BLUE);
                 Game game(red,blue);
             game.move(Position(7,2),Position(6,2),red,blue);
                 REQUIRE(game.getPlayerRed().isMyPiece(Position(7,2)));
         }
}


TEST_CASE("Testing isOver")
{
   SECTION("Testing isOver true")
    {
    vector<Piece> a;
    vector<Piece> b;
    a.push_back(Piece(ROLE::FLAG,Position(1,2)));
    a.push_back(Piece(ROLE::SPY,Position(2,2)));
    a.push_back(Piece(ROLE::SPY,Position(3,2)));
    a.push_back(Piece(ROLE::SPY,Position(4,2)));
    a.push_back(Piece(ROLE::SPY,Position(5,2)));
    a.push_back(Piece(ROLE::SPY,Position(6,2)));
    a.push_back(Piece(ROLE::SPY,Position(7,2)));
    a.push_back(Piece(ROLE::SPY,Position(8,2)));
    a.push_back(Piece(ROLE::SPY,Position(9,2)));
    b.push_back(Piece(ROLE::SPY,Position(1,3)));
    b.push_back(Piece(ROLE::SPY,Position(1,4)));
    b.push_back(Piece(ROLE::SPY,Position(1,6)));
    b.push_back(Piece(ROLE::SPY,Position(1,5)));
    b.push_back(Piece(ROLE::SPY,Position(1,9)));
    b.push_back(Piece(ROLE::FLAG,Position(2,4)));
    a.at(0).setUnveiled();
    Player red(a,Color::RED);
    Player blue(b,Color::BLUE);
    Game game(red,blue);

    REQUIRE(game.isOver()==true);
    }
   SECTION("Testing isOver false")
    {
    vector<Piece> a;
    vector<Piece> b;
    a.push_back(Piece(ROLE::FLAG,Position(1,2)));
    a.push_back(Piece(ROLE::SPY,Position(2,2)));
    a.push_back(Piece(ROLE::SPY,Position(3,2)));
    a.push_back(Piece(ROLE::SPY,Position(4,2)));
    a.push_back(Piece(ROLE::SPY,Position(5,2)));
    a.push_back(Piece(ROLE::SPY,Position(6,2)));
    a.push_back(Piece(ROLE::SPY,Position(7,2)));
    a.push_back(Piece(ROLE::SPY,Position(8,2)));
    a.push_back(Piece(ROLE::SPY,Position(9,2)));
    b.push_back(Piece(ROLE::SPY,Position(1,3)));
    b.push_back(Piece(ROLE::SPY,Position(1,4)));
    b.push_back(Piece(ROLE::SPY,Position(1,6)));
    b.push_back(Piece(ROLE::SPY,Position(1,5)));
    b.push_back(Piece(ROLE::SPY,Position(1,9)));
    b.push_back(Piece(ROLE::FLAG,Position(2,4)));
    Player red(a,Color::RED);
    Player blue(b,Color::BLUE);
    Game game(red,blue);

    REQUIRE(game.isOver()==false);
    }
}
TEST_CASE("Testing gives winner")
{
   SECTION("Testing winner is red")
    {
    vector<Piece> a;
    vector<Piece> b;
    a.push_back(Piece(ROLE::FLAG,Position(1,2)));
    a.push_back(Piece(ROLE::SPY,Position(2,2)));
    a.push_back(Piece(ROLE::SPY,Position(3,2)));
    a.push_back(Piece(ROLE::SPY,Position(4,2)));
    a.push_back(Piece(ROLE::SPY,Position(5,2)));
    a.push_back(Piece(ROLE::SPY,Position(6,2)));
    a.push_back(Piece(ROLE::SPY,Position(7,2)));
    a.push_back(Piece(ROLE::SPY,Position(8,2)));
    a.push_back(Piece(ROLE::SPY,Position(9,2)));
    b.push_back(Piece(ROLE::SPY,Position(1,3)));
    b.push_back(Piece(ROLE::SPY,Position(1,4)));
    b.push_back(Piece(ROLE::SPY,Position(1,6)));
    b.push_back(Piece(ROLE::SPY,Position(1,5)));
    b.push_back(Piece(ROLE::SPY,Position(1,9)));
    b.push_back(Piece(ROLE::FLAG,Position(2,4)));
    b.at(0).setUnveiled();
    Player red(a,Color::RED);
    Player blue(b,Color::BLUE);
    Game game(red,blue);

    REQUIRE(game.winner().equals(red));
    }
   SECTION("Testing winner is red")
    {
    vector<Piece> a;
    vector<Piece> b;
    a.push_back(Piece(ROLE::FLAG,Position(1,2)));
    a.push_back(Piece(ROLE::SPY,Position(2,2)));
    a.push_back(Piece(ROLE::SPY,Position(3,2)));
    a.push_back(Piece(ROLE::SPY,Position(4,2)));
    a.push_back(Piece(ROLE::SPY,Position(5,2)));
    a.push_back(Piece(ROLE::SPY,Position(6,2)));
    a.push_back(Piece(ROLE::SPY,Position(7,2)));
    a.push_back(Piece(ROLE::SPY,Position(8,2)));
    a.push_back(Piece(ROLE::SPY,Position(9,2)));
    b.push_back(Piece(ROLE::SPY,Position(1,3)));
    b.push_back(Piece(ROLE::SPY,Position(1,4)));
    b.push_back(Piece(ROLE::SPY,Position(1,6)));
    b.push_back(Piece(ROLE::SPY,Position(1,5)));
    b.push_back(Piece(ROLE::SPY,Position(1,9)));
    b.push_back(Piece(ROLE::FLAG,Position(2,4)));
    Player red(a,Color::RED);
    Player blue(b,Color::BLUE);
    Game game(red,blue);

    REQUIRE(game.winner().equals(red));
    }
}
}
