#ifndef PLAYER_H
#define PLAYER_H
#include <iostream>
#include "color.h"


namespace std {
/*!
 * \brief the instance of player of the game .
 */
class Player{

private:

    /*!
 * \brief name of player
 */
    string name_;

    /*!
 * \brief color of player buttons
 */
    Color color_;

public :
    Player(){}
    /*!
* \brief Constructor of player
* \param name of player
* \param player Color
*/
    Player(string name, Color color):
        name_{name},
        color_(color){}

    /*!
 * \brief Constructeur with given player
 */
    inline Player(const Player & player):
        name_{player.name_},
        color_(player.color_){}

    /**
 * \brief get player name
 * \return name of the player
 */
    inline string getName();

    /*!
 * \brief get player color
 * \return player color.
 */
    inline Color getColor();

    /*!
 * \brief assign player
 * \param player
 */
    inline void operator = (Player & player);

    /*!
 * \brief operator = pour l'assignation d'un Player *
 *  a un Player
 * \param joueur
 */
    inline void operator = (Player * player);

    /*!
 * \brief compare 2 players
 * \param given player
 * \return true if there're different and false otherwise
 */
    inline bool operator ==(Player & player);

    /*!
 * \brief compare 2 players
 * \param given player
 * \return true if there're different and false otherwise
 */
    inline bool operator !=(Player & player);

};

bool Player::operator!=(Player & player){
    return (this->name_ != player.name_) ||
            (this->color_ != player.color_);
}

bool Player::operator==(Player & player){
    return (this->name_ == player.name_ )&&
            (this->color_ == player.color_);
}

void Player::operator=(Player & player){
    name_ = player.name_;
    color_ = player.color_;
}

string Player::getName() {
    return name_;
}

Color Player::getColor() {
    return color_;
}

} // namespace std
#endif // PLAYER_H
