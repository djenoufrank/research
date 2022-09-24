#ifndef MAINWINDOWIITIALISATION_H
#define MAINWINDOWIITIALISATION_H

#include "../metier/Game.h"
#include <QMainWindow>
#include "../metier/observer.h"
#include "../metier/Game.h"
#include "../metier/Position.h"
#include "../metier/Piece.h"
#include "mainwindow.h"

namespace Ui {
class MainWindowIitialisation;
}


class MainWindowIitialisation : public QMainWindow
{
    Q_OBJECT
public:
    explicit MainWindowIitialisation(QWidget *parent = nullptr);
    ~MainWindowIitialisation();
private slots:
    void originRedClicked();
    void destinationRedClicked();
    void originBlueClicked();
    void destinationBlueClicked();
    void on_start_clicked();

private:
    Ui::MainWindowIitialisation *ui;
    QString permut="";
    std::Game subject;
    std::ROLE getRole(std::string role);
    void displayAskPositionBlue();
    void displayAskPositionRed();
    void enableStart();
};
#endif // MAINWINDOWIITIALISATION_H
