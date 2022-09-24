#include <iostream>
#include <iomanip>
#include "../metier/game.h"
#include <regex>
#include <vector>

using namespace std;

void console(Game & game){
    Board & board = game.getBoard();
    cout<<"           **********************          "<<endl;
    cout<<"           Welcome to Button Up!           "<<endl;
    cout<<"           **********************          "<<endl;
    cout <<"    ";
    for (unsigned i = 0; i < board.getSquares().size() ; i++) {
        cout<<" 0" << i << " "  ;
    }
    cout<<endl;
    cout<<" ------------------------------------------"<<endl;
    for(unsigned i = 0; i < board.getSquares().size() ; i++) {
        cout <<" 0"<< i << " ";
        for (unsigned j = 0; j < board.getSquares().size(); j++) {
            Position position(static_cast<int>(i),static_cast<int>(j));
            if(board.getSquare(position).getTopColor()
                    == Color::WHITE){
                if(board.getSquare(position).
                        getWhiteColor()){
                    cout<<" WH*";
                }else {
                    cout<<" WH ";
                }

            }else if(board.getSquare(position).getTopColor() ==
                     Color::BLACK){
                if(board.getSquare(position).getWhiteColor()){
                    cout<<" BL*";
                }else {
                    cout<<" BL ";
                }
            }else if(board.getSquare(position).getTopColor() ==
                     Color::RED){
                if(board.getSquare(position).getWhiteColor()){
                    cout<<" RE*";
                }else {
                    cout<<" RE ";
                }
            }else if(board.isFree(position)){
                cout<<" -- ";
            }
        }
        cout<<" "<<endl;
    }
    cout<<" ------------------------------------------"<<endl;
    cout<< game.getCurrent().getName() <<" has the hand" << endl;
}

void displayHelp(){
    cout<<" please enter :"<< endl;
    cout<<"type 'select lign column' "
          "\n \t NB: you can only select positions containing "
          "a white button, so the position that has star." << endl;
    cout<<"type 'moves' : to see possible moves" << endl;
    cout<< "type 'apply' : to moves buttons."<<endl;
}

void displayQuit(){
    cout<<"****-Bye-*****" <<endl;
}

int main()
{
    cout << "ma console pour les test" << endl;
    cout<<" "<<endl;
    Game game;
    game.initialize();
    string command;
    vector<Position>movePositions;
    do{
        console(game);
        int line = -1;
        int col = -1;
        cout<<"please enter command : "<<endl;
        getline(cin,command);
        cin.clear();
        if(regex_search(command,regex("select"))){
            try {
                stringstream buffer(command);
                string subStr;
                char delim = ' ';
                vector<std::string> elements;
                while(getline(buffer,subStr,delim)){
                    elements.push_back(subStr);
                }
                line =  stoi(elements[1]);
                col = stoi(elements[2]);
                game.select(line,col);
                stringstream ss;
                ss << "buttons of positions (" << line << ","
                   << col << ") are selected.";
                cout << ss.str() << endl;
            } catch (invalid_argument & message) {
                cout << "\n Error: " << endl;
                cout << "please type 'select ligne column' "
                     <<endl;
                cout << "and be shure that it countains white "
                        "button \n" <<endl;
            } catch(out_of_range & message){
                cout << "that position is not in the board " <<endl;
            }
        }else if(regex_search(command,regex("moves"))){
            try{
                movePositions = game.getMovePositions();
                cout <<"selected buttons will be distribueted "
                       "in positions "<< endl;
                for(unsigned i = 0; i < movePositions.size();i++){
                    int arriveline = movePositions[i].getLine();
                    int arriveCol =  movePositions[i].getColumn();
                    cout<< i <<") lign : " << arriveline << " "
                        << "column : " << arriveCol<<endl;
                }
            }catch(invalid_argument & message){
                cout<<"we can show movement list ,error occur"<<endl;
            }
        }else if(regex_search(command,regex("apply"))){
            try{
                game.applyMove();
                if (!game.mancheIsOver()) {
                    game.swapPlayers();
                    cout<<game.squareView().str()<<"\n"<<endl;
                }else {
                    if (!game.gameIsOver()) {
                        console(game);
                        cout<<"\n this turn is over, the new one "
                              "is bellow."<<std::endl;
                        cout<<"The score of the RED player is: "
                           << game.getRedScore()<<"\n"<<endl;
                        cout<<"The score of the BLACK player is: "
                              ""<< game.getBlackScore()<<"\n"<<endl;
                        cout<<game.squareView().str()<<"\n"<<endl;
                        game.getBoard().removeButtons(game.getPositions()
                                                      .at(game.getIndiceLastPosition()));
                        game.initialize();
                        cout<<game.squareView().str()<<"\n"<<endl;
                    }else {
                        console(game);
                        cout<<"\n The Game is Over. \n"<<endl;
                        cout<<game.getWinner().str()<<"\n"<<endl;
                    }
                }
            }catch(invalid_argument & message){
                cout<<"there are no moves possible ,please retype your"
                      " selection"<<endl;
            }
        }else if(regex_search(command,regex("help"))){
            displayHelp();
        }else if(regex_search(command,regex("quit"))){
            displayQuit();
        }
    } while(command != "quit" && !game.gameIsOver());
}

