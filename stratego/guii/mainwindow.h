#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include "../metier/Game.h"
#include <QMainWindow>

QT_BEGIN_NAMESPACE

namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(std::Game getGame,QWidget *parent = nullptr);
    ~MainWindow();
private slots:
    void clicked();
    void on_reinitialize_clicked();
    void on_restart_clicked();

private:
    Ui::MainWindow *ui;
    std::Game  game;
    std::Game savingGame;
    std::vector<std::Position> currentPositionClicked;
    void playGame();
    void displayConnect();
    void displayAllPositions();
    void checkHasMove(std::Position);
};


#endif // MAINWINDOW_H
