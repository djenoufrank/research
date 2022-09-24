#ifndef CONTROLLER_H
#define CONTROLLER_H
#include "../metier/Game.h"
#include "../view/view.h"
#include <vector>
#include <string>
namespace std{
/**
 * This class represent the dynamic of the game,
 * it has all method to controll the game
 */
class Controller {

public:
    /**
     * the constructor initialise all the attributs of the class
     */
    inline Controller() {}

    /**
     * this method is used to start the game by using other methode to show the
     * board on the screen and all the movement applied to a piece by the player
     */
    inline void startGame();
};

void Controller::startGame() {
    bool play = true;
    string choice = "0";

    while(play){
        View view;
        view.displayWelcome();
        vector<vector<Piece>> allPieces=view.
                displayInitializePositions();
        vector<Piece> redPieces=allPieces.at(0);
        vector<Piece> bluePieces=allPieces.at(1);

        Player playerRed(redPieces,Color::RED);
        Player playerBlue(bluePieces,Color::BLUE);
        Game game(playerRed,playerBlue);

        Position posi;

        while(!game.isOver()){
            vector<Position> positions;
            do {
                game.getPlayerRed().setHasHand(true);
                positions.clear();
                view.displayBoard(game);
                view.displayCurrentPlayer(game.getPlayerRed());
                positions=view.displayAskPositions();

                while(!game.getPlayerRed()
                      .isMyPiece(positions.at(0)) ||
                      game.getPlayerRed()
                      .pieceOnSpecificPosition(positions.at(0))
                      .maxNumberTurn(positions.at(1))){
                    if (!game.getPlayerRed().isMyPiece(positions.at(0))) {
                        cout<<"The choosen piece is not your !"<<endl;
                        cout<<"Try again please"<<endl;
                        positions=view.displayAskPositions();
                    }
                    else if (game.getPlayerRed()
                             .pieceOnSpecificPosition(positions.at(0))
                             .maxNumberTurn(positions.at(1))) {
                        cout<<"This piece has move more than"
                              " 3 times at the same positions, "
                              "try again with another piece  !"<<endl;
                    }
                }
                posi=game.getPlayerRed().pieceOnSpecificPosition(positions.at(0))
                        .getPiecePosition();
                game.move(positions.at(0),positions.at(1),
                          game.getPlayerRed(),game.getPlayerBlue());
            }while (game.getPlayerRed().isMyPiece(posi));
            game.getPlayerRed().setHasHand(false);

            do {
                game.getPlayerBlue().setHasHand(true);
                view.displayBoard(game);

                positions.clear();
                view.displayCurrentPlayer(game.getPlayerBlue());
                positions=view.displayAskPositions();

                while(!game.getPlayerBlue().isMyPiece(positions.at(0))
                      || game.getPlayerBlue().pieceOnSpecificPosition(positions
                                                                      .at(0)).maxNumberTurn(positions.at(1))){
                    if (!game.getPlayerBlue().isMyPiece(positions.at(0))) {
                        cout<<"The choosen piece is not your !"<<endl;
                        cout<<"Try again please"<<endl;
                        positions=view.displayAskPositions();
                    }
                    else if (game.getPlayerBlue()
                             .pieceOnSpecificPosition(positions.at(0))
                             .maxNumberTurn(positions.at(1))) {
                        cout<<"This piece has move more than "
                              "3 times at the same positions,"
                              " try again with another piece  !"<<endl;
                    }
                }
                posi=game.getPlayerBlue().pieceOnSpecificPosition(positions
                                                                  .at(0)).getPiecePosition();
                game.move(positions.at(0),positions.at(1),
                          game.getPlayerBlue(),game.getPlayerRed());
            }while (game.getPlayerBlue().isMyPiece(posi));
            game.getPlayerBlue().setHasHand(false);
        }
        view.displayWinner(game);
        cout<<"do you want to play again ? or leave the game"<<endl;
        cout<<"press 1 for yes and 2 for no"<<endl;
        cin>>choice;
        while (stoi(choice)!=1 && stoi(choice)!=2) {
            cout<<"please press 1 for yes and 2 for no"<<endl;
            cin>>choice;
        }
        if(stoi(choice)==2){
            play=false;
        }
    }
}
}
#endif // CONTROLLER_H
