#ifndef BOARD_H
#define BOARD_H
#include <iostream>
#include "square.h"
#include "position.h"


namespace std {

/*!
 * \brief the table of the game
 */
class Board
{

private:

    /*!
 * \brief squares_ that has all squares off the table game.
 */
    vector<vector<Square>> squares_;

public:

    /*!
 * \brief default constructor
 */
    Board() :
        squares_(10, vector<Square>(10)){}

    /*!
 * \brief getSquare at given position
 * \param given position
 * \return square.
 */
    inline Square getSquare(Position & position);

    /*!
 * \brief says if it's free or no
 * \param given position
 * \return true if the position is inside the
 *  board or no otherwise
 */
    inline bool isInside(Position & position);

    /*!
 * \brief says if given position is free, so it's
 * desapear in
 * that position,
 * \param given position
 * \return true if the position is free or no otherwise
 */
    inline bool isFree(Position & position);

    /*!
 * \brief add button to the square at the given position
 * \param button to add.
 * \param given position
 */
    inline void addButton(Button & button, Position & position);

    /*!
 * \brief remove the Button
 * \param given position
 * \return button that are remove
 */
    inline Button removeButton(Position & position);

    /*!
 * \brief remove all buttons at the given position
 */
    inline void removeButtons(Position & position);

    /*!
 * \brief gives the color of the button on top
 * \param given position
 * \return the color at this position and on top
 */
    inline Color getTopColor(Position & position);

    /*!
 * \brief gives all buttons of the game
 * \return all buttons of the game
 */
    inline vector<vector<Square>> & getSquares();
};

vector<vector<Square>> & Board::getSquares() {
    vector<vector<Square>> * squares = & squares_;
    return *squares;
}

Square Board::getSquare(Position & position) {
    string message = "Position Error 1 : the position "
                     "is not in the board.";
    if(!isInside(position)){
        throw invalid_argument(message);
    }
    unsigned long line = static_cast<unsigned long>
            (position.getLine());
    unsigned long column = static_cast<unsigned long>
            (position.getColumn());
    return squares_[line][column];
}

bool Board::isInside(Position & position) {
    return (position.getLine() < 10 && position.getLine()
            >= 0) &&
            (position.getColumn() >= 0 && position.
             getColumn() < 10);
}

bool Board::isFree(Position & position){
    string message= "Position Error 2 : the position"
                    " is not in the board.";
    if(!isInside(position)){
        throw invalid_argument(message);
    }
    unsigned long line = static_cast<unsigned long>
            (position.getLine());
    unsigned long column = static_cast<unsigned long>
            (position.getColumn());
    return  squares_[line][column].isFree();
}

void Board::addButton(Button & button, Position & position) {
    unsigned long ligne = static_cast<unsigned long>
            (position.getLine());
    unsigned long colonne = static_cast<unsigned long>
            (position.getColumn());
    squares_[ligne][colonne].addButton(button);
}

Button Board::removeButton(Position & position) {
    unsigned long ligne = static_cast<unsigned long>
            (position.getLine());
    unsigned long colonne = static_cast<unsigned long>
            (position.getColumn());
    return squares_[ligne][colonne].removeButton();
}


void Board::removeButtons(Position & position) {
    unsigned long ligne = static_cast<unsigned long>
            (position.getLine());
    unsigned long colonne = static_cast<unsigned long>
            (position.getColumn());
    squares_[ligne][colonne].removeButtons();
}

Color Board::getTopColor(Position & position) {
    if(isInside(position)){
        Color color = getSquare(position).getTopColor();
        return color;
    }
    return Color::EMPTY;
}

} //namespace std


#endif // BOARD_H
