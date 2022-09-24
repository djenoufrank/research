#ifndef CONTROLLER_H
#define CONTROLLER_H
#include "mainwindow.h"
#include "../metier/game.h"
class Controller{
public:
    void  playGame(MainWindow *w)    {

        Game * game=new Game();
        game->addObserver(w);
        game->initialize();
        w->update(game);
    }
};

#endif // CONTROLLER_H
