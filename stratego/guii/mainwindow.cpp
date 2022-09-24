#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <windows.h>
#include "mainwindowiitialisation.h"
MainWindow::MainWindow(std::Game getGame,QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    ui->water42->setDisabled(true);
    ui->water43->setDisabled(true);
    ui->water46->setDisabled(true);
    ui->water47->setDisabled(true);
    ui->water52->setDisabled(true);
    ui->water53->setDisabled(true);
    ui->water56->setDisabled(true);
    ui->water57->setDisabled(true);
    ui->water42->setStyleSheet("QPushButton { "
                 "background-color: skyblue;"
                               "border-color: rgb(42, 23, 255); }");
    ui->water43->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water46->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water47->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water52->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water53->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water56->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    ui->water57->setStyleSheet("QPushButton { "
                               "background-color: skyblue;"
                                "border-color: rgb(42, 23, 255); }");
    this->savingGame=getGame;
    this->game=getGame;
    this->game.getPlayerRed().setHasHand(true);
    displayAllPositions();
    displayConnect();
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::displayAllPositions(){
    QString role="";
    for(unsigned int i =0; i<10; i++)
    {
        for(unsigned int j =0; j<10; j++)
        {
            std::Position pos(std::Position(j,i));
            if(game.getPlayerBlue().isMyPiece(pos)){
                if(game.getPlayerBlue().getHasHand()
                        || game.getPlayerBlue()
                        .pieceOnSpecificPosition(pos).isUnveiled()){
                    role = QString::fromStdString(game.getPlayerBlue()
                            .pieceOnSpecificPosition(pos).to_stringGui());
                }
                else if(!game.getPlayerBlue().getHasHand()){
                    if(game.getPlayerBlue().pieceOnSpecificPosition(pos)
                            .isUnveiled()){
                        game.getPlayerBlue().pieceOnSpecificPosition(pos)
                                .setUnveiled();
                    }
                    role = QString::fromStdString(game.getPlayerBlue()
                            .pieceOnSpecificPosition(pos).to_stringHide());
                }
                qobject_cast<QPushButton*>(ui->gridGame->
                            itemAtPosition(j,i)->widget())->setText(role);
                qobject_cast<QPushButton*>(ui->gridGame->
                            itemAtPosition(j,i)->widget())->setStyleSheet("QPushButton { background-color: blue; }");
            }
            else if(game.getPlayerRed().isMyPiece(pos)){
                if(game.getPlayerRed().getHasHand() ||
                        game.getPlayerRed().pieceOnSpecificPosition(pos)
                        .isUnveiled()){
                    role = QString::fromStdString(game.getPlayerRed()
                            .pieceOnSpecificPosition(pos).to_stringGui());
                }
                else if(!game.getPlayerRed().getHasHand()){
                    if(game.getPlayerRed().pieceOnSpecificPosition(pos)
                            .isUnveiled()){
                        game.getPlayerRed().pieceOnSpecificPosition(pos)
                                .setUnveiled();
                    }
                    role = QString::fromStdString(game.getPlayerRed()
                            .pieceOnSpecificPosition(pos).to_stringHide());
                }
                qobject_cast<QPushButton*>(ui->gridGame->
                                      itemAtPosition(j,i)->widget())->
                                                        setText(role);
                qobject_cast<QPushButton*>(ui->gridGame->
                                           itemAtPosition(j,i)->
                                           widget())->setStyleSheet("QPushButton {"
                                                               " background-color: red; }");
            }
            else if(ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water42 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water43 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water46 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water47 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water52 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water53 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water56 &&
                    ui->gridGame->itemAtPosition(j,i)->widget()!=ui->water57){
                qobject_cast<QPushButton*>(ui->gridGame->itemAtPosition(j,i)->
                                           widget())->setText("");
                qobject_cast<QPushButton*>(ui->gridGame->itemAtPosition(j,i)->
                                           widget())->setStyleSheet("QPushButton {"
                                                                    " background-color:"
                                                                    " rgb(81, 160, 45); }");
            }
        }
    }
    ui->labelRed->setText("player red has : "+QString::number(
                              this->game.getPlayerRed()
                              .getPieces().size())+
                          "\n remaining pieces");
    ui->labelBlue->setText("player blue has : "
                           ""+QString::number(
                               this->game.getPlayerBlue()
                               .getPieces().size())+"\n remaining pieces");

    if (this->game.isOver()) {
        for(unsigned int i =0; i<10; i++)
        {
            for(unsigned int j =0; j<10; j++)
            {
                qobject_cast<QPushButton*>(
                            ui->gridGame->itemAtPosition(j,i)
                            ->widget())->setDisabled(true);
            }
        }
        std::string text="the winner is \n"+
                this->game.winner().toString();
        ui->labelWinner->setText(QString::fromStdString(text));
        ui->labelWinner->setStyleSheet("font: 700 14pt Segoe Print;");
    }
}

void MainWindow::clicked(){
    QPushButton* button = (QPushButton*)sender();
    std::Position pos(std::Position(button->y()/51,
                                    button->x()/86));
    if(currentPositionClicked.empty()){
        currentPositionClicked.push_back(pos);
        button->setStyleSheet("QPushButton {"
                              " background-color: green; }");
        ui->labelWinner->setText("");

    }
    else if(currentPositionClicked.size()==1){
        std::Position pos0=currentPositionClicked.at(0);
        currentPositionClicked.push_back(pos);
        playGame();
        checkHasMove(pos0);
        displayAllPositions();
    }
}

void MainWindow::checkHasMove(std::Position posi){
    if(!game.getPlayerBlue().isMyPiece(posi) &&
            !game.getPlayerRed().isMyPiece(posi)) {
        if(game.getPlayerBlue().getHasHand()){
            game.getPlayerBlue().setHasHand(false);
            game.getPlayerRed().setHasHand(true);
        }
        else{
            game.getPlayerBlue().setHasHand(true);
            game.getPlayerRed().setHasHand(false);
        }
    }
}
void MainWindow::displayConnect(){
    QPushButton * redButtons[100];
    for(int i =0; i <100 ; i++){
        QString buttonName = "pos"+QString::number(i);
        redButtons[i] =MainWindow::findChild<QPushButton*>
                                    (buttonName);
        connect(redButtons[i], SIGNAL(released()),
                this,SLOT(clicked()));
    }
}

void MainWindow::playGame(){
    if(game.getPlayerRed().getHasHand()){
        if (game.getPlayerRed().isMyPiece(
                    currentPositionClicked.at(0)) &&
                !game.getPlayerRed()
                .pieceOnSpecificPosition(currentPositionClicked.at(0))
                .maxNumberTurn(currentPositionClicked.at(1))){
            game.move(currentPositionClicked.at(0),
                      currentPositionClicked.at(1),
                      game.getPlayerRed(),game.getPlayerBlue());
        }
        else{
            ui->labelWinner->setText("is not your piece or max number turn \n"
                                    " please reselect one piece \n that you want to move");
            ui->labelWinner->setStyleSheet("font: 700 10pt Segoe Print;");
        }
    }
    else if(game.getPlayerBlue().getHasHand()){
        if (game.getPlayerBlue().isMyPiece(currentPositionClicked.at(0)) &&
                !game.getPlayerBlue().pieceOnSpecificPosition(currentPositionClicked.at(0))
                .maxNumberTurn(currentPositionClicked.at(1))){
            if (game.getPlayerBlue().isMyPiece(currentPositionClicked.at(0)) &&
                    !game.getPlayerBlue().pieceOnSpecificPosition(currentPositionClicked.at(0))
                    .maxNumberTurn(currentPositionClicked.at(1))){
                game.move(currentPositionClicked.at(0),currentPositionClicked.at(1),
                          game.getPlayerBlue(),game.getPlayerRed());
            }
        }else{
            ui->labelWinner->setText("is not your piece or max number  \n turn"
                                    " please reselect one\n piece that you want to move");
            ui->labelWinner->setStyleSheet("font: 700 10pt Segoe Print;");
        }
    }
    currentPositionClicked.clear();
}

void MainWindow::on_reinitialize_clicked()
{
    MainWindowIitialisation* windowRestart =
            new MainWindowIitialisation();
    windowRestart->show();
    this->close();
}

void MainWindow::on_restart_clicked()
{
    MainWindow* windowGame =new MainWindow(this->savingGame);
    windowGame->show();
    this->close();
}

