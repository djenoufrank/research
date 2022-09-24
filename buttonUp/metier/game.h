#ifndef GAME_H
#define GAME_H
#include <iostream>
#include <random>
#include <chrono>
#include <string.h>
#include <algorithm>
#include "board.h"
#include "subject.h"
#include "player.h"
#include <algorithm>

namespace std {
/*!
 * \brief The logic of all the game .
 */
class Game : public Subject {
private :
    /*!
     * \brief the game board
     */
    Board board_;
    vector<Position> positions_ =
    {
     Position(1,0),Position(5,0),
     Position(6,1),Position(6,3),
     Position(6,6),Position(5,7),
     Position(1,7),Position(0,5),
     Position(0,2)
    };
    /*!
     * \brief selected Position make by player
     */
    Position selected_;

    /*!
      * \brief current player
      */
    Player current_;

    /*!
      * \brief the opponent
      */
    Player opponent_;

    /*!
     * \brief redPoints
     */
    int redScore_ = 0;

    /*!
     * \brief blackPoints
     */
    int blackScore_ = 0;

    /*!
     * \brief indice of last Position
     */
    int indiceLastPosition_=-1;

    /*!
     * \brief can we swap players
     */
    bool canSwapPlayers_ = true;

    /*!
     * \brief  is some button is selected?
     */
    bool isSelected_ = false;
public:

    /*!
* \brief game constructor
*/
    Game():
        board_(),
        current_("RED",Color::RED),
        opponent_("BLACK",Color::BLACK) {}
    /*!
* \brief game destructor
*/
    ~Game(){}
    /*!
* \brief constructor of game by copy
*/
    inline Game(Game & game);

    /*!
* \brief initialize the game
*/
    inline void initialize();

    /*!
 * \brief select position
 * \param ligne of selection
 * \param colomn of selection
 */
    inline void select(int line,int column);

    /*!
 * \brief can i add button
 * \param given position
 * \return true i can add button on this position or
 *  false otherwise
 */
    inline bool canAddButton(Position & position);

    /*!
 * \brief get the Movable Positions
 * \return
 */
    inline vector<Position> getMovePositions();

    /*!
 * \brief apply the move
 */
    inline void applyMove();

    /*!
* \brief swap player that has hand
*/
    inline void swapPlayers();

    /*!
 * \brief says if the instance of game is over
 */
    inline bool mancheIsOver();

    /*!
 * \brief says if the game is over
 */
    inline bool gameIsOver();

    /*!
 * \brief counter of my score
 */
    inline void scoreCounter(Square square);

    /*!
 * \brief it goes through all the board game,
 * says number of buttons in all squares
 * \return numbers of buttons in differents squares
 */
    inline stringstream squareView();

    /*!
* \brief get the winner
* \return the winner
*/
    inline stringstream getWinner();

    /*!
* \brief get the winner in gui version
* \return the winner
*/
    inline stringstream getWinnerGui();

    /*!
 * \brief get all Positions
 * \return all positions
 */
    inline vector<Position> getPositions();

    /*!
 * \brief getter to knoww if we can swap Players
 * \return true if yes and false otherwise
 */
    inline bool getCanSwapPlayers();

    /*!
 * \brief getter to know laster positions
 * \return
 */
    inline int getIndiceLastPosition();

    /*!
 * \brief get the score of player red
 * \return the score of player red
 */
    inline int getRedScore();

    /*!
 * \brief get the score of player black
 * \return the score of player black
 */
    inline int getBlackScore();

    /*!
* \brief get current player
* \return current player
*/
    inline Player getCurrent();

    /*!
* \brief get opponent player
* \return opponent player
*/
    inline Player getOpponent();

    /*!
 * \brief getter for selected position
 * \return position selected
 */
    inline Position getSelected();

    /*!
 * \brief getter for board
 * \return board
 */
    inline Board & getBoard();


};

vector<Position> Game::getPositions() {
    return positions_;
}

bool Game::getCanSwapPlayers(){
    return canSwapPlayers_;
}

int Game::getIndiceLastPosition(){
    return indiceLastPosition_;
}

int Game::getRedScore(){
    return redScore_;
}

int Game::getBlackScore(){
    return blackScore_;
}

Player Game::getCurrent(){
    return current_;
}

Player Game::getOpponent(){
    return opponent_;
}

Board & Game::getBoard(){
    Board * board = &board_;
    return * board;
}

Position Game::getSelected(){
    return selected_;
}

void Game::initialize() {
    Color red = Color::RED;
    Color black = Color::BLACK;
    Color white = Color::WHITE;
    vector<Button> buttons =
    {Button(red), Button(red), Button(red),
     Button(black), Button(black), Button(black),
     Button(white), Button(white), Button(white)};
    unsigned seed = std::chrono::system_clock::now()
            .time_since_epoch()
            .count();
    shuffle (buttons.begin(), buttons.end(),
             default_random_engine(seed));
    for (size_t i = 0; i < positions_.size() ; i++) {
        board_.addButton(buttons.at(i), positions_.at(i));
    }
}

void Game::select(int line,int column){
    Position selected(line,column);
    if(board_.isInside(selected) &&
            !board_.isFree(selected)){
        if(board_.getSquare(selected).getWhiteColor()){
            selected_ = selected;
            isSelected_ = true;
        }else{
            selected_ = selected;
            isSelected_ = false;
        }
    }
}
bool Game::canAddButton(Position & position) {
    return !board_.getSquare(position).isFree();
}

vector<Position> Game::getMovePositions() {
    size_t size = positions_.size();
    vector<Position> positions;
    for (size_t i = 0; i < size; i++) {
        if(selected_ == positions_.at(i)) {
            if(i==0 || i==8) {
                if(i==8) {
                    if(canAddButton(positions_.at(0))){
                        positions.push_back(
                                    positions_.at(0));
                    }
                }
                for (size_t j = 1; j < size - 1; j++) {
                    if(canAddButton(positions_.at(j))){
                        positions.push_back(
                                    positions_.at(j));
                    }
                }
                if(i==0) {
                    if(canAddButton(positions_.at(8))){
                        positions.push_back(positions_.at(8));
                    }
                }
            } else {
                for (size_t j = i + 1; j < size; j++) {
                    if(canAddButton(positions_.at(j))){
                        positions.push_back(positions_.at(j));
                    }
                }
                for (size_t j = 0; j < i; j++) {
                    if(canAddButton(positions_.at(j))){
                        positions.push_back(positions_.at(j));
                    }
                }
            }
        }
    }
    return positions;
}

void Game::applyMove(){
    if(isSelected_){
        vector<Position> positions = getMovePositions();
        int nbPos = positions.size();
        int i = nbPos - 1;
        while (!board_.isFree(selected_)) {
            while (!board_.isFree(selected_) && nbPos == 1){
                Button button = board_.removeButton(selected_);
                Position position = positions.at(0);

                board_.addButton(button, position);
                vector<Button> but=board_.getSquare(position).getButtons();
                reverse(but.begin(),but.end());
                board_.getSquare(position).getButtons().clear();
                board_.getSquare(position).getButtons()=but;
            }
            if (nbPos > 1) {
                Button button = board_.removeButton(
                            selected_);
                Position position = positions.at(i);
                board_.addButton(button, position);
                nbPos--;
                i--;
            }
        }
    }

    isSelected_ = false;
    notify();
}

void Game::swapPlayers(){
    if (canSwapPlayers_) {
        if(!mancheIsOver()){
            Player * joueurCurrent = & current_;
            Player * joueurOpponent = & opponent_;
            Player tmp = *joueurCurrent;
            *joueurCurrent =*joueurOpponent;
            *joueurOpponent = tmp;
        }
    }
    canSwapPlayers_ = true;
}

bool Game::mancheIsOver() {
    bool result = false;
    int n = 0;
    int indice = 0;
    for (size_t i = 0; i < positions_.size() ; i++) {
        if(board_.isFree(positions_.at(i))){
            n++;
        }else {
            indice = i;
        }
    }
    if(n == 8) {
        result = true;
        scoreCounter(board_.getSquare(
                         positions_.at(indice)));
        indiceLastPosition_ = indice;
    }
    return result;
}

bool Game::gameIsOver() {
    if (redScore_ >= 15 || blackScore_ >= 15) {
        return true;
    }
    return false;
}

void Game::scoreCounter(Square square){
    int redScore = 0;
    int blackScore = 0;
    for (size_t i = 1; i < square.getButtons().size(); i++) {
        switch (i) {
        case 1:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 1;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 1;
            }
            break;
        case 2:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 2;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 2;
            }
            break;
        case 3:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 3;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 3;
            }
            break;
        case 4:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 4;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 4;
            }
            break;
        case 5:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 5;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 5;
            }
            break;
        case 6:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 6;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 6;
            }
            break;
        case 7:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 7;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 7;
            }
            break;
        case 8:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 8;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 8;
            }
            break;
        case 9:
            if (square.getButtons().at(i).getColor()
                    == Color::RED) {
                redScore = redScore + 9;
            }
            if (square.getButtons().at(i).getColor()
                    == Color::BLACK) {
                blackScore = blackScore + 9;
            }
            break;
        default:
            break;
        }
    }

    if (redScore > blackScore) {
        redScore_ = redScore_ + (redScore - blackScore);
    }
    if (redScore < blackScore) {
        blackScore_ = blackScore_ + (blackScore - redScore);
    }
}

stringstream Game::getWinner(){
    stringstream winner;
    if (redScore_ > blackScore_) {
        winner << "The Red player is the Winner with"
                  " Scores: " << redScore_
               <<"\n" << "The Black player lost with "
                         "Scores: " << blackScore_ <<"\n";
    }else if (redScore_ < blackScore_) {
        winner << "The Black player is the Winner with "
                  "Scores: " << blackScore_
               <<"\n" << "The Red player lost with "
                         "Scores: " << redScore_ <<"\n";
    }else {
        winner <<"The scores are the same"
              << "\n The Red player have Scores: "
              << redScore_
              <<"\n" << "The Black player have Scores: "
                        "" << blackScore_ <<"\n";
    }
    return winner;
}

stringstream Game::squareView(){
    stringstream ss;
    for (size_t i = 0; i < positions_.size() ; i++) {
        vector<Button> bts = board_.getSquare(
                    positions_.at(i)).getButtons();
        ss <<"Square ("<<positions_.at(i).getLine()
          <<","<<positions_.at(i).getColumn()
         <<") have: " << bts.size() - 1<<" buttons. \n";
    }
    return ss;
}
stringstream Game::getWinnerGui(){
    stringstream winner;
    if (redScore_ > blackScore_) {
        winner << "The Red player";
    }else if (redScore_ < blackScore_) {
        winner << "The Black player";
    }else {
        winner <<"The scores are the same";
    }
    return winner;
}
} // namespace std

#endif // GAME_H
