#ifndef SQUARE_H
#define SQUARE_H
#include <vector>
#include <iostream>
#include "button.h"
#include <algorithm>

namespace std {

/*!
 * \brief The Square of the game .
 */
class Square
{

private:

    /*!
 * \brief buttons_ vector containing all
 *  the buttons of the square
 */
    vector<Button> buttons_;

public:

    /*!
 * \briefthe default constructor of the Square class.
 */
    Square():
        buttons_(vector<Button>{Button()})
    {}

    /*!
 * \brief  constructor of the Square class
 * with vector of buttons
 * \param given vector of buttons.
 */
    explicit Square(vector<Button> buttons):
        buttons_(buttons) {}

    /*!
 * \brief says if given position is free
 * \return true if it's free and false otherwise
 */
    inline bool isFree();

    /*!
 * \brief add a button in a square.
 * \param given button.
 */
    inline void addButton(Button button);

    /*!
 * \brief remove the Button in the square
 * \return button removed
 */
    inline Button removeButton();

    /*!
 * \brief remove Buttons to this square
 */
    inline void removeButtons();

    /*!
 * \brief says if there is white buttons
 * \returnture if there is white button or false otherwise
 */
    inline bool getWhiteColor();

    /*!
 * \brief get all Buttons of this square
 * \return buttons of this square
 */
    inline vector<Button> getButtons();

    /*!
 * \brief getTopColor
 * \return
 */
    inline Color getTopColor();

    /*!
* \brief get All Colors of buttons in one square
*/
    //inline void getColorsInOneSquare();
};

vector<Button> Square::getButtons() {
    return buttons_;
}

Color Square::getTopColor() {
    int n = buttons_.size();
    return buttons_[n - 1].getColor();
}


bool Square::isFree() {
    return buttons_.size() == 1 && buttons_.at(0).getColor() == Color::EMPTY;
}

void Square::addButton(Button button) {
    buttons_.push_back(button);
}

Button Square::removeButton(){
    Button button = buttons_.at(1);
    vector<Button>::iterator it;
    it = buttons_.begin() + 1;
    buttons_.erase(it);
    return button;
}

void Square::removeButtons(){
    while (buttons_.size() > 1) {
        buttons_.pop_back();
    }
}

bool Square::getWhiteColor() {
    for (size_t i = 0; i < buttons_.size(); i++ ) {
        Button btn = buttons_[i];
        if(btn.getColor() == Color::WHITE){
            return true;
        }
    }
    return false;
}
} // namespace std


#endif // SQUARE_H
