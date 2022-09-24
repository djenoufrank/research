#ifndef PIECE_H
#define PIECE_H
#include "Position.h"
#include <string>
#include <vector>
#include "Role.h"
#include <sstream>
#include <string>
namespace std
{
/*!
 * \class Piece
 * it's class to represent a piece
 */
class Piece
{
private:

    /*!
        * \brief  Position
        * position of a piece
        */
    Position position_ ;

    /*!
        * \brief role
        * role of one piece that gives his power against the others
        */
    ROLE role_;

    /*!
        * \brief piece is unveiled
        * says if piece is unveiled in case of level of game or if
        *  we want to attack
        *
        */
    bool unveiled_=false;

    /*!
        * \brief lasterPositions of this piece
        * gives lasterPositions of this piece
        *
        * \return all laster positions
        */
    vector<Position> lasterposition;

public:
    /*!
      * \brief equals for this to given piece
      * equals for this to given piece
      *
      * \param given piece
      *
      * \return true if given piece is this one or false otherwise
      */
    inline bool equals(Piece);
    /*!
        * \brief default constructor
        * default constructor of a piece
        */
    Piece(){}

    /*!
        * \brief constructor
        * constructor of a piece
        *
        * \param role of this piece
        * \param position of this piece
        * \param piece is it movable?
        */
    inline Piece(ROLE role,Position  position):
        position_{position},
        role_{role}
    {}

    /*!
     * \brief position getter
     * getter for position of a piece
     *
     * \return position of piece
     */
    inline Position & getPiecePosition(){
        return position_;
    }

    /*!
     * \brief increase laster positions
     * increase laster positions of that piece
     *
     *\param given position
     */
    inline void increaseLasterPosition(Position);

    /*!
     * \brief role of piece
     *  role of piece
     * \return role of piece
     */
    inline ROLE getRole(){
        return role_;
    }

    /*!
        * \brief this next position is in my last one
        * says if this next position is in my last one
        *
        * \param piece to know laster positions
        * \return true if it is and false otherwise
        */
    inline bool itIsInMyLasterPositions(Position);

    /*!
     * \brief remove laster positions
     * remove all laster positions of that piece
     *
     */
    inline void removeMyLasterPositions();

    /*!
     * \brief piece is unveiled
     * says if piece is unveiled
     * \return true if it's unveiled and false otherwise
     */
    inline bool isUnveiled(){
        return unveiled_;
    }

    /*!
     * \brief change piece unveiled
     * change piece unveiled if true put in false or if false put true
     */
    inline void setUnveiled(){
        unveiled_=!unveiled_;
    }

    /*!
     * \brief move  piece
     * to move instance of piece by position .
     *
     * \param position where player want to puts his piece
     */
    inline void movePiece(Position);

    /*!
     * \brief piece in string
     *
     * print  piece in string
     * \return reconizable piece
     */
    inline string to_string();
    /*!
     * \brief piece in string in gui
     *
     * print  piece in string
     * \return reconizable piece
     */
inline string to_stringGui();
    /*!
     * \brief piece in string  but hide
     * print piece in string  but hide
     *
     * \return piece with special character that's not reconize
     */
    inline string to_stringHide(){
        return "**";
    }

    /*!
     * \brief says if piece exceded 3 turns
     *says if piece exceded 3 turns successivly
     *
     * \return given position
     */
    inline bool maxNumberTurn(Position);
};

void Piece::movePiece(Position position){
    lasterposition.push_back(position);
    position_.move(position.getLine(),position.getColumn());
}
void Piece::removeMyLasterPositions(){
    lasterposition.clear();
}

bool Piece::equals(Piece piece){
    if(role_==piece.getRole() && (position_
       .equals(piece.getPiecePosition()) &&
            unveiled_==piece.isUnveiled())){
        return  true;
    }
    return false;
}
string Piece::to_string(){
    switch (role_)  {
    case ROLE::MARSHAL:
        return "10";
    case ROLE::GENERAL:
        return "09";
    case ROLE::COLONEL:
        return "08";
    case ROLE::MAJOR:
        return "07";
    case ROLE::COMMANDER:
        return "06";
    case ROLE::LIEUTENANT:
        return "05";
    case ROLE::SERGEANT:
        return "04";
    case ROLE::DEMINER:
        return "03";
    case ROLE::PATHFINDER:
        return "02";
    case ROLE::SPY:
        return "01";
    case ROLE::FLAG:
        return "FL";
    case ROLE::BOMB:
        return "BO";
    case ROLE::EMPTY:
        return "";
    }
    return "";
}

string Piece::to_stringGui(){
    switch (role_)  {
    case ROLE::MARSHAL:
        return "MARSHAL";
    case ROLE::GENERAL:
        return "GENERAL";
    case ROLE::COLONEL:
        return "COLONEL";
    case ROLE::MAJOR:
        return "MAJOR";
    case ROLE::COMMANDER:
        return "COMMANDER";
    case ROLE::LIEUTENANT:
        return "LIEUTENANT";
    case ROLE::SERGEANT:
        return "SERGEANT";
    case ROLE::DEMINER:
        return "DEMINER";
    case ROLE::PATHFINDER:
        return "PATHFINDER";
    case ROLE::SPY:
        return "SPY";
    case ROLE::FLAG:
        return "FLAG";
    case ROLE::BOMB:
        return "BOMB";
    case ROLE::EMPTY:
        return "";
    }
    return "";
}

void Piece::increaseLasterPosition(Position position){
    lasterposition.push_back(position);
}

bool Piece::maxNumberTurn(Position position){
    if(lasterposition.size()<7){
        return false;
    }
    increaseLasterPosition(position);
    for(unsigned i=0;i<lasterposition.size()-2;i++){
        if(lasterposition.at(i).getLine()!=
                lasterposition.at(i+2).getLine()
                || lasterposition.at(i).getColumn()
                !=lasterposition.at(i+2).getColumn() ){
            return false;
        }
    }
    return true;
}

bool Piece::itIsInMyLasterPositions(Position position){
    for(unsigned i=0;i<lasterposition.size();i++){
        if(lasterposition.at(i).equals(position)){
            return true;
        }
    }
    return false;
}
}
#endif // PIECE_H
