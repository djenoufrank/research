#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "../metier/game.h"
#include "../metier/observer.h"
#include<QLayoutItem>
QT_BEGIN_NAMESPACE
using namespace std;
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow, public Observer
{
    Q_OBJECT

    Game * game_;
public:
    explicit MainWindow(QWidget *parent = 0);

    ~MainWindow();
    virtual void update(Subject * s) override;
private :
    Ui::MainWindow *ui;
    QString permut="";
    void checkGame();
public slots :
    void remove ( QLayout* layout );
    void getClick(int lign,int column);
private slots:
    void on_play_clicked();
    void on_newGame_clicked();
};
#endif // MAINWINDOW_H
