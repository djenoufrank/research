#ifndef SQUAREGRAPHIQUE_H
#define SQUAREGRAPHIQUE_H
#include <QMouseEvent>
#include <QPushButton>
#include <iostream>
#include <QObject>
#include "../metier/game.h"
using namespace std;

/*!
 * \brief La classe SquareGraphique représente une pastille graphique du plateau de jeu graphique.
 */
class SquareGraphique : public QPushButton
{
    Q_OBJECT
private:
    /*!
     * \brief lign of button
     *
     */
    int lign_;
    /*!
     * \brief column of button
     */
    int column_;
    /*!
     * \brief vector of button
     */
    vector<Button> buttons_;

    /*!
     * \brief action when we click in button
     * \param event click
     */
    inline void mousePressEvent(QMouseEvent* evt);

public:

    /*!
     * \brief constructor
     * \param lign of button
     * \param column of button
     * \param color of button
     * \param parent of this object
     */
    inline SquareGraphique(int lign, int column,
                           vector<Button> buttons, QWidget *parent=0);
    ~SquareGraphique(){}

private:
signals:
    void leftClick(int lign,int column);
public slots:

};

SquareGraphique::SquareGraphique(int lign,int column,
                                 vector<Button> buttons, QWidget *parent):
    QPushButton(parent),
    lign_{lign},
    column_{column},
    buttons_{buttons}{
    setFixedSize(60,60);
}
void SquareGraphique::mousePressEvent(QMouseEvent *evt){
    if(evt->button() == Qt::LeftButton)
    {
        emit leftClick( lign_,column_);
    }
}

#endif // SQUAREGRAPHIQUE_H
