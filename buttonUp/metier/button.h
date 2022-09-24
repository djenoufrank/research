#ifndef BUTTON_H
#define BUTTON_H
#include <iostream>
#include "color.h"


namespace std {


/*!
 * \brief the Button class of the game Button Up.
 */
class Button
{

private:

/*!
 * \brief represents the color of a button
 */
Color color_;

public:

/*!
 * \brief default constructor
 */
Button():
    color_(Color::EMPTY) {}

/*!
 * \brief Button class constructor with color as parameter
 * \param the color of the button
 */
explicit Button(Color & color):
    color_(color) {}

Button(const Button & button)= default;

/*!
 * \brief getter for color of the button
 * \return returns the color of the button
 */
inline Color getColor();

/*!
 * \brief compare to buttons
 * \param given button
 * \return true if there're the same and false otherwise
 */
inline bool operator == (Button & button);

/*!
 * \brief compare to buttons
 * \param given button
 * \return false if there're the same and true otherwise
 */
inline bool operator != (Button & button);

/*!
 * \brief button in string in gui
 *
 * print  button in string
 * \return reconizable button
 */
inline string to_stringGui();
};

bool Button::operator != (Button & button){
    return this->getColor() != button.getColor();
}

bool Button::operator== (Button &button){
    return this->getColor() == button.getColor();
}

Color Button::getColor() {
    return color_;
}
string Button::to_stringGui(){
    switch (color_)  {
    case Color::RED:
        return "RED";
    case Color::WHITE:
        return "WHITE";
    case Color::BLACK:
        return "BLACK";
    case Color::EMPTY:
        return "";
    }
    return "";
}
}//namespace std

#endif // BUTTON_H
