#include "../metier/subject.h"
#include "mainwindow.h"
#include "mainwindowiitialisation.h"
#include <QApplication>

int main(int argc, char *argv[])
{
   QApplication a(argc, argv);
   MainWindowIitialisation w;
    w.show();
    return a.exec();
}
