#ifndef PLAYER_H
#define PLAYER_H
#include <vector>
#include "Piece.h"
#include "Position.h"
#include <string>
#include <sstream>
#include "Color.h"

namespace std
{
/*!
 * \class Player
 * it's class to represent a instance of player
 */
class Player
{
private:
    /*!
       * \brief pieces of player
       * list of all pieces of this player
       */
    std::vector<Piece > pieces_ ;

    /*!
       * \brief says if i have hand
       *
       * says if i have hand ,by default it's false
       */
    bool hasHand_=false;

    /*!
       * \brief color of pieces of player
       *  color of pieces of player
       */
    Color color_ ;

public:
    /*!
      * \brief equals for this to given player
      * equals for this to given player
      *
      * \param given player
      *
      * \return true if given player is this one or false otherwise
      */
    inline bool equals(Player);
    /*!
      * \brief default constructor
      * default constructor of a Player
      */
    inline Player(){}

    /*!
     * \brief has hand getter
     * getter for who has the hand
     *
     * \return true if this player has hand or no otherwise
     */
    inline bool getHasHand(){
        return hasHand_;
    }

    /*!
     * \brief position setter
     * setter for position of a piece
     *
     * \param changing hand
     */
    inline void setHasHand(bool nextHand){
        hasHand_=nextHand;
    }

    /*!
     * \brief color of player
     * getter for color of player
     *
     * \return color for this
     */
    inline Color getColor(){
        return color_;
    }

    /*!
        * \brief constructor of player
        * constructor of a Player with his list of pieces
        *
        *  \param vector of pieces
        */
    inline Player(std::vector<Piece>,Color);

    /*!
          * \brief pieces getter
          * getter for all pieces of player
          *
          * \return list of pieces
          */
    inline std::vector<Piece >& getPieces();

    /*!
        * \brief remove the piece
        *
        * \param position of the piece
        */
    inline void removePiece(Position);

    /*!
        * \brief is my piece in this position
        * says if this piece is for this player
        *
        * \param given position
        */
    inline bool isMyPiece(Position);



    /*!
        * \brief player in string
        * player name reprented by his color
        *
        * \return player
        */
    inline string toString();

    /*!
        * \brief index of position
        * returns index of position in the list of pieces
        *
        * \param position of the piece
        * \return index of position
        */
    inline Piece & pieceOnSpecificPosition(Position);

    /*!
       * \brief delete laster position
       * delete laster position of this piece
       *
       * \param piece to delete last position
       */
    inline void deleteLasterPositions(Piece);

};
bool Player::equals(Player player){
    bool testPieces=true;
    for(unsigned i=0;i<pieces_.size();i++){
        if(!pieces_.at(i).equals(player.getPieces().at(i))){
            testPieces=false;
            break;
        }
    }
    if(color_==player.getColor() && testPieces && hasHand_==
            player.getHasHand()){
        return true;
    }
    return false;
}
Player::Player(vector <Piece > newPieces,Color color):
    pieces_{newPieces},
    color_{color}
{}

std::vector <Piece >& Player::getPieces(){
    return pieces_;
}

void Player::removePiece(Position position){
    if(isMyPiece(position)){
        for(unsigned i=0;i<getPieces().size();i++){
            if(pieces_[i].getPiecePosition().equals(position)){
                pieces_.erase(pieces_.begin()+i);
            }
        }
    }
}

bool Player::isMyPiece(Position position){
    size_t size= getPieces().size();
    for(size_t i=0;i<size;i++){
        if(position.equals(pieces_.at(i).getPiecePosition())){
            return true;
        }
    }
    return false;
}

string Player::toString(){
    if(color_==Color::RED){
        return "Player Red";
    }
    return "Player Blue";
}

Piece& Player::pieceOnSpecificPosition(Position position){
    for(unsigned i=0;i<pieces_.size();i++){
        if(pieces_[i].getPiecePosition().equals(position)){
            return pieces_[i];
        }
    }
}

void Player::deleteLasterPositions(Piece piece){
    for(unsigned i=0;i<pieces_.size();i++){
        if(pieces_.at(i).getPiecePosition()
                .equals(piece.getPiecePosition())){
            pieces_.at(i).removeMyLasterPositions();
        }
    }
}

}
#endif // PLAYER_H
