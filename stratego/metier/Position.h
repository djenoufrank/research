#ifndef POSITION_H
#define POSITION_H

#include <iostream>
#include <sstream>
namespace std{

/*!
 * \class Position to create and move
 * differents pieces in sprecific position
 */
class Position
{
private:

    /*!
     * \brief line position
     */
    int line_;

    /*!
        * \brief column position
        */
    int column_;

public:
    /*!
        * \brief default constructor
        * default constructor of one position
        */
    inline Position():
        line_{-1},
        column_{-1}
    {}

    /*!
        * \brief constructor
        * constructor of one position
        *
        * \param x line
        * \param y column
        */
    inline Position(int x,int y):
        line_{x},
        column_{y}
    {}

    /*!
     * \brief line position getter
     * getter for position of a piece
     *
     * \return line position of piece
     */
    inline int getLine(){
        return line_;
    }

    /*!
     * \brief column position getter
     * getter for column position of a piece
     *
     * \return column position of piece
     */
    inline int getColumn(){
        return column_;
    }

    /*!
     * \brief move of position.
     * move one position to another that's gives
     *
     * \param line of position
     * \param column of position
     */
    inline void move(int line, int column);

    /*!
     * \brief equals positions.
     * compared this position and the one that's gives
     *
     * \param position that we have to compare with this
     */
    inline bool equals(Position);

    /*!
     * \brief position in string
     *
     * print  position in string
     * \return line and column of one position
     */
    inline string to_string();

};

void Position::move(int newLine, int newColumn){
    line_=newLine;
    column_=newColumn;
}

bool Position::equals(Position position){
    return (getLine()==position.getLine()
            && getColumn()==position.getColumn());
}

/*!
 * \brief piece in string
 *
 * print  piece in string
 * \return reconizable piece
 */
string Position::to_string(){
    std::stringstream stringPosition;
    stringPosition<<"{"<< line_<<","<<column_<<"}"<<endl;
    return stringPosition.str();
}
}
#endif // POSITION_H
