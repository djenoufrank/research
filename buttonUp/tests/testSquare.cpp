#include "catch.hpp"
#include "../metier/button.h"
#include "../metier/square.h"
#include "../metier/button.h"
#include "../metier/color.h"
namespace std {


TEST_CASE("Testing is free")
{

    SECTION("verify that given position is free ")
    {
        Button but;
        vector<Button> buttons={but};
      Square sq=Square(buttons);
        REQUIRE(sq.isFree()==true);
              }

    SECTION("verify that given position isn't free ")
    {
        Color red = Color::RED;
        Button but;
        Button but1=Button(red);
        vector<Button> buttons={but,but1};
      Square sq=Square(buttons);
REQUIRE(sq.isFree()==false);
         }
}
TEST_CASE("Testing add button")
{

    SECTION("add button fine ")
    {
        Color red = Color::RED;
        Button but1=Button(red);
      Square sq=Square();
      sq.addButton(but1);
REQUIRE(sq.getButtons().size()==2);
              }
}
TEST_CASE("Testing remove all buttons")
{

    SECTION("remove all buttons fine ")
    {
        Color red = Color::RED;
        Button but1=Button(red);
        Button but2=Button(red);
        Button but3=Button(red);
        vector<Button> buttons={but1,but2,but3};
      Square sq=Square(buttons);
      sq.removeButtons();
REQUIRE(sq.getButtons().size()==1);
              }
}

TEST_CASE("Testing remove button")
{

    SECTION("remove button fine ")
    {
        Color red = Color::RED;
        Button but1=Button(red);
      Square sq=Square();
      sq.addButton(but1);
      sq.removeButton();
REQUIRE(sq.getButtons().size()==1);
              }
}
TEST_CASE("Testing has white color")
{

    SECTION("doesn't have white color ")
    {
        Color red = Color::RED;
         Color black = Color::BLACK;
        Button but1=Button(red);
        Button but2=Button(black);
        vector<Button> buttons={but1,but2};
      Square sq=Square(buttons);
REQUIRE(sq.getWhiteColor()==false);
              }
    SECTION("has white color ")
    {
        Color white = Color::WHITE;
         Color black = Color::BLACK;
        Button but1=Button(white);
        Button but2=Button(black);
        vector<Button> buttons={but1,but2};
      Square sq=Square(buttons);
REQUIRE(sq.getWhiteColor()==true);
              }
}
TEST_CASE("Testing top color")
{

    SECTION("top color is black")
    {
        Color red = Color::RED;
         Color black = Color::BLACK;
        Button but1=Button(red);
        Button but2=Button(black);
        vector<Button> buttons={but1,but2};
      Square sq=Square(buttons);
REQUIRE(sq.getTopColor()==Color::BLACK);
              }

    SECTION("top color is red")
    {
        Color red = Color::RED;
         Color black = Color::BLACK;
        Button but2=Button(red);
        Button but1=Button(black);
        vector<Button> buttons={but1,but2};
      Square sq=Square(buttons);
REQUIRE(sq.getTopColor()==Color::RED);
}
    SECTION("has white color is white")
    {
        Color white = Color::WHITE;
         Color black = Color::BLACK;
        Button but2=Button(white);
        Button but1=Button(black);
        vector<Button> buttons={but1,but2};
      Square sq=Square(buttons);
REQUIRE(sq.getTopColor()==Color::WHITE);
             }
}
}
