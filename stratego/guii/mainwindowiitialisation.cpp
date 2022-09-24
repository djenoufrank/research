#include "mainwindowiitialisation.h"
#include "ui_mainwindowiitialisation.h"
#include "../metier/Role.h"


MainWindowIitialisation::MainWindowIitialisation(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindowIitialisation)
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
    ui->water42->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water43->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water46->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water47->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water52->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water53->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water56->setStyleSheet("QPushButton { background-color: skyblue; }");
    ui->water57->setStyleSheet("QPushButton { background-color: skyblue; }");

    for(unsigned int i = 0; i <=39; i++){
        ui->bluePieces->itemAt(i)->widget()->setStyleSheet("QPushButton { background-color: blue; }");
    }
    for(unsigned int i = 0; i <=39; i++){
        ui->RedPieces->itemAt(i)->widget()->setStyleSheet("QPushButton { background-color: red; }");
    }

    displayAskPositionRed();
    connect(ui->start, &QPushButton::clicked, this, &MainWindowIitialisation::on_start_clicked);
}


std::ROLE MainWindowIitialisation::getRole(std::string role){
    if(role=="MARSHAL")  {
        return std::ROLE::MARSHAL;
    }else if(role=="SPY")  {
        return std::ROLE::SPY;
    }
    else if(role=="FLAG")  {
        return std::ROLE::FLAG;
    }
    else if(role=="BOMB")  {
        return std::ROLE::BOMB;
    }
    else if(role=="MAJOR")  {
        return std::ROLE::MAJOR;
    }
    else if(role=="SERGEANT")  {
        return std::ROLE::SERGEANT;
    }
    else if(role=="COLONEL")  {
        return std::ROLE::COLONEL;
    }
    else if(role=="GENERAL")  {
        return std::ROLE::GENERAL;
    }
    else if(role=="DEMINER")  {
        return std::ROLE::DEMINER;
    }
    else if(role=="COMMANDER")  {
        return std::ROLE::COMMANDER;
    }
    else if(role=="LIEUTENANT")  {
        return std::ROLE::LIEUTENANT;
    }
    else if(role=="PATHFINDER")  {
        return std::ROLE::PATHFINDER;
    }
    else  {
        return std::ROLE::EMPTY;
    }
}

MainWindowIitialisation::~MainWindowIitialisation()
{
    delete ui;
}
void MainWindowIitialisation::originRedClicked(){
    QPushButton* button = (QPushButton*)sender();

    if(permut.size()==0){
        permut=button->text();
        delete button;
    }
}

void MainWindowIitialisation::originBlueClicked(){
    QPushButton* button = (QPushButton*)sender();

    if(permut.size()==0){
        permut=button->text();
        delete button;
    }
}

void MainWindowIitialisation::destinationRedClicked(){
    QPushButton* button = (QPushButton*)sender();
    if(permut.size()!=0 && button->text().size()==0){
        button->setText(permut);
        button->setStyleSheet("QPushButton { background-color: black; }");
        permut="";
    }
    if(ui->RedPieces->count()==33){
        displayAskPositionBlue();
    }
}
void MainWindowIitialisation::destinationBlueClicked(){
    QPushButton* button = (QPushButton*)sender();
    if(permut.size()!=0 && button->text().size()==0){
        button->setText(permut);
        button->setStyleSheet("QPushButton { background-color: black; }");
        permut="";
    }
    if(ui->bluePieces->count()==33){
        enableStart();
    }
}

void MainWindowIitialisation::displayAskPositionRed(){

    for(unsigned int i = 0; i <=39; i++){
        ui->bluePieces->itemAt(i)->widget()->setDisabled(true);
    }
    QPushButton * redButtons[100];
    for(int i =0; i <40 ; i++){
        QString buttonName = "piece"+QString::number(i+1);
        redButtons[i] =MainWindowIitialisation::findChild<QPushButton *>(buttonName);
        connect(redButtons[i], SIGNAL(released()),this,SLOT(originRedClicked()));
    }
    for(int i =60; i <100 ; i++){
        QString buttonName = "pos"+QString::number(i);
        redButtons[i] =MainWindowIitialisation::findChild<QPushButton *>(buttonName);
        connect(redButtons[i], SIGNAL(released()),this,SLOT(destinationRedClicked()));
    }

}

void MainWindowIitialisation::displayAskPositionBlue(){

    for(unsigned int i = 0; i <=39; i++){
        ui->bluePieces->itemAt(i)->widget()->setDisabled(false);
    }
    QPushButton * blueButtons[100];
    for(int i =0; i <40 ; i++){
        QString buttonName = "pieceB"+QString::number(i+1);
        blueButtons[i] =MainWindowIitialisation::findChild<QPushButton *>(buttonName);
        connect(blueButtons[i], SIGNAL(released()),this,SLOT(originBlueClicked()));
    }
    for(int i =0; i <40 ; i++){
        QString buttonName = "pos"+QString::number(i);
        blueButtons[i] =MainWindowIitialisation::findChild<QPushButton *>(buttonName);
        connect(blueButtons[i], SIGNAL(released()),this,SLOT(destinationBlueClicked()));
    }
}

void MainWindowIitialisation::enableStart(){
    ui->start->setEnabled(true);
    ui->start->setStyleSheet("background-color: yellow;font: 9pt Algerian;");
}

void MainWindowIitialisation::on_start_clicked()
{
    std::vector<std::Piece> piecesRed;
    std::vector<std::Piece> piecesBlue;
    for(unsigned int i =0; i<10; i++)
    {
        for(unsigned int j =0; j<10; j++)
        {
            if(j<4){
                if(qobject_cast<QPushButton*>(ui->gridAllPieces->itemAtPosition(j,i)->widget())->text().toStdString()!=""){
                    std::Piece piece(getRole(qobject_cast<QPushButton*>(ui->gridAllPieces->itemAtPosition(j,i)->widget())->text().toStdString()),std::Position(j,i));
                    piecesBlue.push_back(piece);
                }
            }
            else if(j>5){
                if(qobject_cast<QPushButton*>(ui->gridAllPieces->itemAtPosition(j,i)->widget())->text().toStdString()!=""){
                    std::Piece piece(getRole(qobject_cast<QPushButton*>(ui->gridAllPieces->itemAtPosition(j,i)->widget())->text().toStdString()),std::Position(j,i));
                    piecesRed.push_back(piece);
                }
            }
        }

    }
    std::Player playerRed(piecesRed,std::Color::RED);
    std::Player playerBlue(piecesBlue,std::Color::BLUE);
    std::Game game(playerRed,playerBlue);
    this->subject=game;
    MainWindow* windowGame =new MainWindow(this->subject);
    windowGame->show();
    this->close();
}

