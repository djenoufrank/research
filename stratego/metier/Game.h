#ifndef GAME_H
#define GAME_H
#include "Player.h"
#include <array>
#include "Position.h"
namespace std{
/*!
 * \class Game
 * interface of the model of the game
 */
class Game
{
private:

    /*!
        * \brief Red player
        * player one of game with red piece
        */
    Player playerRed_;

    /*!
        * \brief blue player
        * player two of game with blue piece
        */
    Player playerBlue_;

    /*!
        * \brief is move is possible?
        * says if asked move is possible, if it is not in
        *  diagonal position
        *
        * \return true if it not in diagonal and last
        * position minus new one
        * is equals to 0 .and false if it is
        */
    inline bool goodMove(Position,Position);

    /*!
        * \brief is move is possible for pathFinder?
        * says if asked move is possible, if it is not
        * in diagonal position for pahtFinder
        *
        * \return true if it not in diagonal and last
        * position minus new one
        * is equals to 0 .and false if it is
        */
    inline bool pathFinderGoodMove(Position, Position, Player &,Player &);

    /*!
        * \brief is move is possible at the end of the game
        * says if it is move is possible at the end of the game
        *
        * \param given player
        * \return true if possible and false otherwise
        */
    inline bool isAlwaysPossibleToMove(Player &);

    /*!
        * \brief move position to new one
        * move position to new one
        *
        */
    inline void piecePositionMoveToNewOne(Position,Position,
                                          Player &,Player &);
    /*!
        * \brief  player can't move
        * says if player can't move
        *
        * \param given piece
        * \param given player
        * \return true if he can't move and false otherwise
        */
    inline bool cannotMoveHere(Piece piece,Player & player);
public:
    /*!
        * \brief default constructor
        * default construtor of a game
        */
    inline Game(){}

    /*!
        * \brief constructor
        * construtor of a game
        */
    inline Game(Player playerRed ,Player playerBlue):
        playerRed_{playerRed},
        playerBlue_{playerBlue}
    {}

    /*!
         * \brief getter for red player
         *
         * \return red player
         */
    inline Player & getPlayerRed(){
        return playerRed_;
    }

    /*!
         * \brief getter for blue player
         *
         * \return blue player
         */
    inline Player & getPlayerBlue(){
        return playerBlue_;
    }

    /*!
    * \brief end of game
    *  says if the game is over or not
    *
    * \return true if game is over and false otherwise
    */
    inline bool isOver();

    /*!
    * \brief winner
    *  says who is a winner
    *
    * \return player who has win
    */
    inline Player winner();

    /*!
    * \brief move piece
    * moves one piece from one position given to another gives buy player
    * \param
    */
    inline void move(Position,Position,Player &,Player &);

    /*!
          * \brief this position is in the water
          *  says if given position is in the water
          *
          * \param position given by player
          *
          * \return true if it is and false if not
          */
    inline bool isInTheWater(Position);
};

bool Game::goodMove(Position  actualPosition, Position  nextPosition){
    if(actualPosition.getLine()-nextPosition.getLine()==0){
        if(actualPosition.getColumn()-nextPosition.getColumn()==1 ||
                actualPosition.getColumn()-nextPosition.getColumn()==-1){
            return true;
        }
    }
    else if(actualPosition.getColumn()-nextPosition.getColumn()==0){
        if(actualPosition.getLine()-nextPosition.getLine()==1 ||
                actualPosition.getLine()-nextPosition.getLine()==-1){
            return true;
        }
    }
    return false;
}

bool Game::isInTheWater(Position position){
    if(position.getLine()==4 && position.getColumn()==2){
        return true;
    }
    if(position.getLine()==5 && position.getColumn()==2){
        return true;
    }
    if(position.getLine()==4 && position.getColumn()==3){
        return true;
    }
    if(position.getLine()==5 && position.getColumn()==3){
        return true;
    }
    if(position.getLine()==4 && position.getColumn()==6){
        return true;
    }
    if(position.getLine()==5 && position.getColumn()==6){
        return true;
    }
    if(position.getLine()==4 && position.getColumn()==7){
        return true;
    }
    if(position.getLine()==5 && position.getColumn()==7){
        return true;
    }
    return false;
}
void Game::move(Position actualPosition,Position nextPosition,
                Player & player,Player & opponent){
    if(player.isMyPiece(actualPosition)){
        if(player.pieceOnSpecificPosition(actualPosition)
                .getRole()!=ROLE::FLAG &&
                player.pieceOnSpecificPosition(actualPosition)
                .getRole()!=ROLE::BOMB){
            if(!player.isMyPiece(nextPosition) &&
                    !isInTheWater(nextPosition)){
                if(pathFinderGoodMove(actualPosition,
                                      nextPosition,player,opponent)
                        || goodMove(actualPosition,nextPosition)){
                    if(player.pieceOnSpecificPosition(actualPosition)
                            .getRole()==ROLE::PATHFINDER){
                        if(pathFinderGoodMove(actualPosition,nextPosition
                                              ,player,opponent)){
                            piecePositionMoveToNewOne(actualPosition,
                                                      nextPosition,player,opponent);
                        }
                    }else {
                        if(goodMove(actualPosition,nextPosition)){
                            piecePositionMoveToNewOne(actualPosition,
                                                      nextPosition,player,opponent);
                        }
                    }
                }
            }
        }
    }
}

bool Game::pathFinderGoodMove(Position actualPosition,
                              Position  nextPosition , Player & player,
                              Player & opponent){
    if(actualPosition.getLine()<=nextPosition.getLine()){
        for(int i= actualPosition.getLine()+1;
            i<nextPosition.getLine();i++){
            Position pos(Position(i, actualPosition.getColumn()));
            if(player.isMyPiece(pos) || opponent.isMyPiece(pos) ){
                return false;
            }else if(this->isInTheWater(pos)){
                return false;
            }
        }
    }else{
        for(int i= actualPosition.getLine()-1;
            i>nextPosition.getLine();i--){
            Position pos(Position(i, actualPosition.getColumn()));
            if(player.isMyPiece(pos) || opponent.isMyPiece(pos) ){
                return false;
            }else if(this->isInTheWater(pos)){
                return false;
            }
        }
    }
    if( actualPosition.getColumn()<=nextPosition.getColumn()){
        for(int i= actualPosition.getColumn()+1;
            i<nextPosition.getColumn();i++){
            Position pos(Position( actualPosition.getLine(),i));
            if(player.isMyPiece(pos) || opponent.isMyPiece(pos) ){
                return false;
            }else if(this->isInTheWater(pos)){
                return false;
            }
        }
    }else{
        for(int i= actualPosition.getColumn()-1;
            i>nextPosition.getColumn();i--){
            Position pos(Position( actualPosition.getLine(),i));
            if(player.isMyPiece(pos) || opponent.isMyPiece(pos) ){
                return false;
            }else if(this->isInTheWater(pos)){
                return false;
            }
        }

    }
    if(actualPosition.getLine()-nextPosition.getLine()!=0
            && actualPosition.getColumn()-nextPosition
            .getColumn()!=0){
        return false;
    }
    return true;
}

void Game::piecePositionMoveToNewOne( Position actualPosition ,
                                      Position  nextPosition ,
                                      Player & player,
                                      Player & opponent){
    if (opponent.isMyPiece(nextPosition)){
        if(opponent.pieceOnSpecificPosition(nextPosition)
                .getRole()==ROLE::BOMB &&
                player.pieceOnSpecificPosition(actualPosition)
                .getRole()!=ROLE::DEMINER){
            opponent.pieceOnSpecificPosition(nextPosition)
                    .setUnveiled();
            player.removePiece(actualPosition);
        }else if(player.pieceOnSpecificPosition(actualPosition)
                 .getRole()==opponent.pieceOnSpecificPosition(nextPosition)
                 .getRole()){
            opponent.pieceOnSpecificPosition(nextPosition)
                    .setUnveiled();
            player.removePiece(actualPosition);
            opponent.removePiece(nextPosition);
        }else if(player.pieceOnSpecificPosition(actualPosition)
                 .getRole()==ROLE::SPY &&
                 opponent.pieceOnSpecificPosition(nextPosition)
                 .getRole()==ROLE::MARSHAL){
            opponent.pieceOnSpecificPosition(nextPosition).setUnveiled();
            opponent.removePiece(nextPosition);
            player.pieceOnSpecificPosition(actualPosition)
                    .movePiece(nextPosition);
        }else if(player.pieceOnSpecificPosition(actualPosition)
                 .getRole()==ROLE::DEMINER &&
                 opponent.pieceOnSpecificPosition(nextPosition)
                 .getRole()==ROLE::BOMB){
            opponent.pieceOnSpecificPosition(nextPosition).setUnveiled();
            opponent.removePiece(nextPosition);
            player.pieceOnSpecificPosition(actualPosition)
                    .movePiece(nextPosition);
        }else if(player.pieceOnSpecificPosition(actualPosition)
                 .getRole()>opponent.pieceOnSpecificPosition(nextPosition)
                 .getRole()){
            opponent.pieceOnSpecificPosition(nextPosition).setUnveiled();
            opponent.removePiece(nextPosition);
            player.pieceOnSpecificPosition(actualPosition)
                    .movePiece(nextPosition);
        }else if(player.pieceOnSpecificPosition(actualPosition).getRole()<
                 opponent.pieceOnSpecificPosition(nextPosition).getRole()){
            opponent.pieceOnSpecificPosition(nextPosition).setUnveiled();
            player.removePiece(actualPosition);
        }
    }else if(player.pieceOnSpecificPosition(actualPosition)
             .itIsInMyLasterPositions(nextPosition)){
        if (!player.pieceOnSpecificPosition(actualPosition)
                .maxNumberTurn(nextPosition)) {
            player.pieceOnSpecificPosition(actualPosition)
                    .movePiece(nextPosition);
        }
    }else{
        player.deleteLasterPositions(player
                                     .pieceOnSpecificPosition(actualPosition));
        player.pieceOnSpecificPosition(actualPosition)
                .movePiece(nextPosition);
    }
}

Player Game::winner(){
    if(!isAlwaysPossibleToMove(playerRed_)){
        return playerBlue_;
    }
    else if(!isAlwaysPossibleToMove(playerBlue_)){
        return playerRed_;
    }

    unsigned redSize=static_cast<unsigned>(playerRed_
                                           .getPieces().size());
    for(unsigned i=0;i<redSize;i++){
        if(playerRed_.getPieces().at(i)
                .getRole()==ROLE::FLAG){
            return playerRed_;
        }
    }
    unsigned blueSize=static_cast<unsigned>(playerBlue_
                                            .getPieces().size());
    for(unsigned i=0;i<blueSize;i++){
        if(playerBlue_.getPieces().at(i)
                .getRole()==ROLE::FLAG){
            return playerBlue_;
        }
    }
    return playerRed_;
}

bool Game::isAlwaysPossibleToMove(Player & player){
    for(unsigned i=0;i<player.getPieces().size();i++){
        if(player.getPieces().at(i).getRole()!=ROLE::FLAG
                && player.getPieces().at(i).getRole()!=ROLE::BOMB){
            if(!cannotMoveHere(player.getPieces().at(i),player)){
                return true;
            }
        }
    }
    return false;
}

bool Game::cannotMoveHere(Piece piece,Player & player){
    Position pos(Position(piece.getPiecePosition().getLine()+1,
                          piece.getPiecePosition().getColumn()));
    if(goodMove(piece.getPiecePosition(),pos)){
        if(!player.isMyPiece(pos)){
            if(!isInTheWater(pos)){
                return false;
            }
        }
    }
    Position pos2(Position(piece.getPiecePosition().getLine()-1,
                           piece.getPiecePosition().getColumn()));
    if(goodMove(piece.getPiecePosition(),pos2)){
        if(!player.isMyPiece(pos2)){
            if(!isInTheWater(pos2)){
                return false;
            }
        }
    }
    Position pos3(Position(piece.getPiecePosition().getColumn(),
                           piece.getPiecePosition().getColumn()+1));
    if(goodMove(piece.getPiecePosition(),pos3)){
        if(!player.isMyPiece(pos3)){
            if(!isInTheWater(pos3)){
                return false;
            }
        }
    }
    Position pos4(Position(piece.getPiecePosition().getLine(),
                           piece.getPiecePosition().getColumn()-1));
    if(goodMove(piece.getPiecePosition(),pos4)){
        if(!player.isMyPiece(pos4)){
            if(!isInTheWater(pos4)){
                return false;
            }
        }
    }
    return true;
}

bool Game::isOver(){
    unsigned redSize=static_cast<unsigned>(playerRed_
                                           .getPieces().size());
    bool flagIsDeadRed=true;
    bool flagIsDeadBlue=true;
    for(unsigned i=0;i<redSize;i++){
        if(playerRed_.getPieces().at(i)
                .getRole()==ROLE::FLAG){
            flagIsDeadRed= false;
        }
    }
    unsigned blueSize=static_cast<unsigned>(playerBlue_
                                            .getPieces().size());
    for(unsigned i=0;i<blueSize;i++){
        if(playerBlue_.getPieces().at(i)
                .getRole()==ROLE::FLAG){
            flagIsDeadBlue= false;
        }
    }
    if(!isAlwaysPossibleToMove(playerRed_)
            || !isAlwaysPossibleToMove(playerBlue_)){
        return true;
    }
    return flagIsDeadRed || flagIsDeadBlue;
}
}
#endif // GAME_H
