#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "squaregraphique.h"
MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->centralwidget->setStyleSheet("background-color: skyblue;");
    Game* game= new Game();
    game->addObserver(this);
    game->initialize();
    update(game);
    ui->play->setDisabled(true);
    ui->newGame->setDisabled(true);
    ui->labelBlack->setText("player black has : "+QString::number(
                                game_->getBlackScore())+" marks");
    ui->labelRed->setText("player red has : "+QString::number(
                              game_->getRedScore())+" marks");
    ui->labelMessage->setText("     the current player is : "+
                              QString::fromStdString(game_->getCurrent()
                                                     .getName()));
    ui->labelWinner->setText("");
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::update(Subject * game){
    game_=(Game*) game;
    Board & board = game_->getBoard();
    vector<Position> vecPositions=game_->getPositions();
    for(Position pos : vecPositions)
    {
        if(!board.getSquare(pos).isFree()){
            SquareGraphique * squareGraphique = new
                    SquareGraphique(pos.getLine(),
                                    pos.getColumn(),
                                    board.getSquare(pos).
                                    getButtons());

            switch (board.getSquare(pos).getTopColor()) {
            case Color::BLACK:
                squareGraphique->setStyleSheet(
                            "background-color: black;");
                QObject::connect(squareGraphique,
                                 SIGNAL(leftClick(int,int)),
                                 this, SLOT(getClick(int,int)));
                ui->gridLayout->addWidget(squareGraphique,
                                          pos.getLine(),pos.getColumn());
                break;
            case Color::RED:
                squareGraphique->setStyleSheet(
                            "background-color: red;");
                QObject::connect(squareGraphique,
                                 SIGNAL(leftClick(int,int)),
                                 this, SLOT(getClick(int,int)));
                ui->gridLayout->addWidget(squareGraphique,pos.getLine(),
                                          pos.getColumn());
                break;
            case Color::WHITE:
                squareGraphique->setStyleSheet(
                            "background-color: white;");
                QObject::connect(squareGraphique,
                                 SIGNAL(leftClick(int,int))
                                 , this, SLOT(getClick(int,int)));
                ui->gridLayout->addWidget(squareGraphique,pos.getLine()
                                          ,pos.getColumn());
                break;
            case Color::EMPTY:
                break;
            }
        }
    }
    ui->labelBlack->setText("player black has : "+
                            QString::number(
                                game_->getBlackScore())
                            +" marks");
    ui->labelRed->setText("player red has : "+QString::number(
                              game_->getRedScore())+" marks");
    ui->labelMessage->setText("     the current player is : "+
                              QString::fromStdString(
                                  game_->getCurrent()
                                              .getName()));
}
void MainWindow::getClick(int line, int column){
    Position pos= Position(line,column);
    vector<Button> listButtons=  game_->getBoard()
            .getSquare(pos).getButtons();
    string listColors="";
    for(Button button : listButtons)
    {
        listColors+=button.to_stringGui()+"\n ";
    }

    ui->listButtonHere->setText(
                QString::fromStdString("this square has"
                                       " buttons : "+listColors));

    if(game_->getBoard().getSquare(pos).getWhiteColor()){
        game_->select(pos.getLine(),pos.getColumn());
        ui->play->setDisabled(false);
    }else{
        ui->play->setDisabled(true);
    }
}

void MainWindow::on_play_clicked()
{
    ui->listButtonHere->setText("no button clicked");
    remove (ui->gridLayout);
    game_->applyMove();
    update(game_);
    ui->play->setDisabled(true);
    checkGame();

}

void MainWindow::remove ( QLayout* layout )
{
    QLayoutItem* child;
    while ( layout->count() != 0 ) {
        child = layout->takeAt ( 0 );
        if ( child->layout() != 0 ) {
            remove ( child->layout() );
        } else if ( child->widget() != 0 ) {
            delete child->widget();
        }

        delete child;
    }
}
void MainWindow::checkGame(){
    if(!game_->gameIsOver()){
        if (!game_->mancheIsOver()) {
            game_->swapPlayers();
        }else {
            if (!game_->gameIsOver()) {
                ui->newGame->setDisabled(false);
                ui->newGame->setStyleSheet
                        ("background-color: "
                         "yellow;font: 9pt Algerian;");
                game_->getBoard().removeButtons(
                            game_->getPositions().
                            at(game_->getIndiceLastPosition()));
                remove (ui->gridLayout);
                game_->initialize();
            }else {
                ui->newGame->setDisabled(false);
                ui->newGame->setStyleSheet(
                            "background-color: "
                            "yellow;font: 10pt Algerian;");
                ui->labelWinner->setText("the game is over And\n"
                                         "the winner is :\n"
                                         ""+QString::fromStdString(
                                             game_->getWinner().str()));
            }
        }
        update(game_);
    }
}


void MainWindow::on_newGame_clicked()
{
    MainWindow windowRestart;
    windowRestart.show();
    this->close();
}
