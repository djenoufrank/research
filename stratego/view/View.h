#ifndef VIEW_H
#define VIEW_H
#include <stdexcept>
#include "../metier/Game.h"
#include "../metier/Position.h"
#include <map>
#include "../metier/Role.h"
namespace std{

/*!
 * \class View
 * class which prints progress of the game
 */
class View
{
private:
    /*!
     * \brief choosen role
     * choosen role for current player
     */
    ROLE roleEnum;
    /*!
     * \brief setter for role
     *
     * \param choosen role
     */
    inline void setRole(int role);
    /*!
     * \brief check good column
     *check good column
     *
     * \param given char
     */
    inline bool verificationsColumn(char c);

    /*!
     * \brief says if it is a role
     * says if it is a role
     * \param given role
     */
    inline bool itIsRole(string role);

public:

    /*!
     * \brief default constructor
     * default constructor of view
     */
    inline View(){}

    /*!
     * \brief prints message to start
     * says welcome to players
     */
    void displayWelcome();

    /*!
     * \brief prints the table of the game
     * prints the table with all pieces
     *
     * \param game where we play
     */
    inline void displayBoard(Game & game);

    /*!
     * \brief takes positions of pieces
     * takes list of positions which are string and we
     *  change it to position and return list of pieces
     *
     * \return list of positions
     */
    inline vector<vector<Piece>> displayInitializePositions();

    /*!
     * \brief occurencies of all piece
     *  occurencies of all piece for one player
     *
     * \return map with key(role) and value his occurency in the game
     */
    inline map<string,int> pieceOcurencies();

    /*!
     * \brief takes position of one piece
     * takes position of one piece which are string for column and we
     *  change it to position and return position addind line
     *
     * \return  position
     */
    inline Position displayAskPosition();

    /*!
     * \brief takes 2 positions
     * takes 2 positions for one piece which are string for column and we
     *  change it to position and return list of positions addind line
     *
     * \return  list of 2 positions
     */
    inline vector<Position> displayAskPositions();

    /*!
     * \brief prints who has win
     * prints which players has win
     *
     * \param game where we play
     */
    inline void displayWinner(Game game);

    /**
        * Display which player has to play.
        *
        * @param player The player that has to play.
        */
    inline void displayCurrentPlayer(Player player);

};

void View:: displayWelcome(){
    cout<<""<<endl;
    cout<<"                 Welcome in Stratego !!!"<<endl;
    cout<<""<<endl;
    cout<<"            we can start play Stratego now !!!"<<endl;
    cout<<""<<endl;
    cout<<""<<endl;
}
void View::displayWinner(Game game){
    cout<<"The winner is : "<<game.winner().toString()<<endl;
}

void View::displayBoard(Game & game){
    cout<<endl;
    cout<<"| CA || CB || CC || CD || CE || CF || CG || CH || CI || CJ |"<<endl;
    cout<<"============================================================"<<endl;
    cout<<endl;
    for(int line=0;line<10;line++){
        for(int column=0;column<10;column++){
            Position pos(Position(line,column));
            if(game.getPlayerRed().isMyPiece(pos)){
                if(game.getPlayerRed().getHasHand() || game.getPlayerRed().pieceOnSpecificPosition(pos).isUnveiled()){
                    cout<<"| "<<game.getPlayerRed().pieceOnSpecificPosition(pos).to_string()<<" |";
                }else{
                    cout<<"| "<<game.getPlayerRed().pieceOnSpecificPosition(pos).to_stringHide()<<" |";
                }
            }
            else  if(game.getPlayerBlue().isMyPiece(pos)){
                if(game.getPlayerBlue().getHasHand() || game.getPlayerBlue().pieceOnSpecificPosition(pos).isUnveiled()){
                    cout<<"| "<<game.getPlayerBlue().pieceOnSpecificPosition(pos).to_string()<<" |";
                }else{
                    cout<<"| "<<game.getPlayerBlue().pieceOnSpecificPosition(pos).to_stringHide()<<" |";
                }
            }else if(!game.isInTheWater(pos)){
                cout<<"|    |";
            }
            else if(game.isInTheWater(pos)){
                cout<<"| xx |";
            }
        }
        cout<<endl;
        cout<<"============================================================"<<endl;
    }
    cout<<endl;
}

void View::displayCurrentPlayer(Player player) {
    cout<<player.toString()<<" it's your turn"<<endl;
}

void View::setRole(int role){
    switch (role)  {
    case 1:
        roleEnum=ROLE::SPY;
        break;
    case 2:
        roleEnum=ROLE::PATHFINDER;
        break;

    case 3:
        roleEnum=ROLE::DEMINER;
        break;
    case 4:
        roleEnum=ROLE::SERGEANT;
        break;
    case 5:
        roleEnum=ROLE::LIEUTENANT;
        break;
    case 6:
        roleEnum=ROLE::COMMANDER;
        break;
    case 7:
        roleEnum=ROLE::MAJOR;
        break;
    case 8:
        roleEnum=ROLE::COLONEL;
        break;
    case 9:
        roleEnum=ROLE::GENERAL;
        break;
    case 10:
        roleEnum=ROLE::MARSHAL;
        break;
    case 11:
        roleEnum=ROLE::FLAG;
        break;
    case 12:
        roleEnum=ROLE::BOMB;
        break;
    default:
        break;
    }
}

map<string,int>View::pieceOcurencies(){
    map<string,int> occur;
    occur["10"]=1;
    occur["9"]=1;
    occur["8"]=2;
    occur["7"]=3;
    occur["6"]=4;
    occur["5"]=4;
    occur["4"]=4;
    occur["3"]=5;
    occur["2"]=8;
    occur["1"]=1;
    occur["11"]=1;
    occur["12"]=6;
    return occur;
}

Position View::displayAskPosition(){
    int line=0;
    int column=0;
    cout<<" enter you line: 1 to 10 "<<endl;
    cin>>line;
    while(line<1 || line>10 ){
        cout<<"wrong line please restart "<<endl;
        cin>>line;
    }
    line--;
    char aff;
    cout<<" now column : A to J "<<endl;
    cin>>aff;
    while(!(aff >= 'A' && aff <= 'J')){
        cout<<"wrong column please restart "<<endl;
        cin>>aff;
    }
    switch (aff)  {
    case 'A':
        column=0;
        break;
    case 'B':
        column=1;
        break;
    case 'C':
        column=2;
        break;
    case 'D':
        column=3;
        break;
    case 'E':
        column=4;
        break;
    case 'F':
        column=5;
        break;
    case 'G':
        column=6;
        break;
    case 'H':
        column=7;
        break;
    case 'I':
        column=8;
        break;
    case 'J':
        column=9;
        break;
    }
    return Position(line,column);
}

bool View::itIsRole(string role){
    vector <string> roles{"10",
                          "9",
                          "8",
                          "7",
                          "6",
                          "5",
                          "4",
                          "3",
                          "2",
                          "1",
                          "11",
                          "12"};
    for(unsigned i=0;i<roles.size();i++){
        if(roles.at(i)==role){
            return true;
        }
    }
    return false;
}

vector<Position> View::displayAskPositions(){
    vector<Position> vect;
    vect.push_back(displayAskPosition());//my position
    vect.push_back(displayAskPosition());//next position
    return vect;
}

vector<vector<Piece>> View::displayInitializePositions(){
    map<string,int> occur=pieceOcurencies();
    vector<Piece> myPieces;
    vector<vector<Piece>> bothPieces;
    cout<<"Please red player align your pieces :"<<endl;
    cout<<" you have to go from left to right and up to down"<<endl;
    for(int line=6;line<10;line++){
        for(int column=0;column<10;column++){
            map<string, int>::iterator iterator;
            cout<<"your choice number of role of piece"<<endl;
            for(iterator=occur.begin(); iterator!=occur.end(); iterator++){
                cout <<"role "<< iterator->first <<" remaining number "
                    << iterator->second <<endl;
            }
            string role;
            cout<<"gives role at position "<<line<<": "<<column<<endl;
            cin>>role;
            while(!itIsRole(role)){
                cout<<"Wrong entry"<<endl;
                cout<<"gives role at position "<<line<<": "<<column<<endl;
                cin>>role;

            }
            if(occur[role]<=0){
                cout<<"you can't add this type of role"<<endl;
                column--;
            }
            else{
                occur[role]=occur[role]-1;
                setRole(stoi(role));
                Piece piece(roleEnum,Position(line,column));
                myPieces.push_back(piece);
            }
        }
    }

    bothPieces.push_back(myPieces);
    map<string,int> occur2=pieceOcurencies();
    vector<Piece> myPieces2;
    cout<<"Please blue player align your pieces :"<<endl;
    cout<<" you have to go from left to right and up to down"<<endl;
    for(int line=0;line<4;line++){
        for(int column=0;column<10;column++){
            Position pos(Position(line,column));
            map<string, int>::iterator iterator;
            cout<<"your choice number of role of piece"<<endl;
            for(iterator=occur2.begin(); iterator!=occur2.end(); iterator++){
                cout <<"role "<< iterator->first <<" remaining number "
                    << iterator->second <<endl;
            }
            string role;
            cout<<"gives role at position "<<line<<": "<<column<<endl;
            cin>>role;

            while(!itIsRole(role)){
                cout<<"Wrong entry"<<endl;
                cout<<"gives role at position "<<line<<": "<<column<<endl;
                cin>>role;

            }
            if(occur2[role]<=0){
                cout<<"you can't add this type of role"<<endl;
                column--;
            }
            else{
                occur2[role]=occur2[role]-1;
                setRole(stoi(role));
                Piece piece(roleEnum,pos);
                myPieces2.push_back(piece);
            }
        }}
    bothPieces.push_back(myPieces2);
    return bothPieces;
}
}
#endif // VIEW_H

