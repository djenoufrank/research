#ifndef POSITION_H
#define POSITION_H
#include <iostream>



namespace std {

/*!
 * \brief represents the position of a square
 *  on the game board.
 */
class Position
{

private:

    /*!
 * \brief represents a row on the game board.
 */
    int line_;

    /*!
 * \brief represents a column of the game board.
 */
    int column_;

public:

    /*!
 * \brief default constructor of the Position class.
 */
    Position():
        line_(-1),column_(-1) {}

    /*!
 * \brief constructor with parameters
 * \param given line
 * \param given column
 */
    Position(int line, int colunm):
        line_(line), column_(colunm) {}

    /*!
 * \brief constructor with position has argument
 * \param  given position.
 */
    Position(const Position & position):
        line_(position.line_),column_(position.column_) {}

    /*!
 * \brief line of a position.
 * \return position line.
 */
    inline int getLine();

    /*!
 * \brief column of a position.
 * \return position column.
 */
    inline int getColumn();

    /*!
 * \brief  compare two positions
 * \param given position.
 * \return true if the positions are same, false otherwise.
 */
    inline bool operator == (Position & position);

    /*!
 * \brief compare two positions
 * \param given position.
 * \return true if the positions are different,
 *  false otherwise.
 */
    inline bool operator != (Position & position);
};

int Position::getLine() {
    return line_;
}

int Position::getColumn() {
    return column_;
}

bool Position::operator==(Position & position) {
    return position.line_ == this->line_ &&
            position.column_ == this->column_;
}

bool Position::operator!=(Position & position) {
    return position.line_ != this->line_ ||
            position.column_ != this->column_;
}

} // namespace std


#endif // POSITION_H
