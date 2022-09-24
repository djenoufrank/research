#include "catch.hpp"
#include "../metier/board.h"
#include "../metier/square.h"
#include "../metier/position.h"
#include "../metier/color.h"
namespace std {


TEST_CASE("Testing is inside")
{

    SECTION("given position is inside the 10x10 board")
    {
        Position pos;// I put it -1,-1 by default
        Board board=Board();
        REQUIRE(board.isInside(pos)==false);
              }

    SECTION("given position isn't inside the 10x10 board")
    {
        Position pos=Position(1,1);
        Board board=Board();
        REQUIRE(board.isInside(pos)==true);
         }
}
}
