/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.15.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QWidget *gridWidget;
    QGridLayout *gridLayout;
    QWidget *gridLayoutWidget_2;
    QGridLayout *gridOptions;
    QPushButton *newGame;
    QLabel *listButtonHere;
    QLabel *labelRed;
    QLabel *labelBlack;
    QWidget *horizontalLayoutWidget;
    QHBoxLayout *movesLayout;
    QPushButton *play;
    QVBoxLayout *verticalLayout;
    QLabel *labelMessage;
    QLabel *labelWinner;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(940, 705);
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        gridWidget = new QWidget(centralwidget);
        gridWidget->setObjectName(QString::fromUtf8("gridWidget"));
        gridWidget->setGeometry(QRect(100, 80, 491, 441));
        gridWidget->setStyleSheet(QString::fromUtf8("background-color: rgb(170, 255, 127);"));
        gridLayout = new QGridLayout(gridWidget);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        gridLayoutWidget_2 = new QWidget(centralwidget);
        gridLayoutWidget_2->setObjectName(QString::fromUtf8("gridLayoutWidget_2"));
        gridLayoutWidget_2->setGeometry(QRect(640, 80, 271, 361));
        gridOptions = new QGridLayout(gridLayoutWidget_2);
        gridOptions->setObjectName(QString::fromUtf8("gridOptions"));
        gridOptions->setContentsMargins(0, 0, 0, 0);
        newGame = new QPushButton(gridLayoutWidget_2);
        newGame->setObjectName(QString::fromUtf8("newGame"));
        newGame->setEnabled(true);
        newGame->setStyleSheet(QString::fromUtf8("border-color: rgb(255, 0, 4);\n"
"color: rgb(0, 0, 0);\n"
"background-color: rgb(255, 255, 255);\n"
"font: 9pt \"Algerian\";\n"
"selection-background-color: qradialgradient(spread:repeat, cx:0.5, cy:0.5, radius:0.077, fx:0.5, fy:0.5, stop:0 rgba(0, 169, 255, 147), stop:0.497326 rgba(0, 0, 0, 147), stop:1 rgba(0, 169, 255, 147));"));

        gridOptions->addWidget(newGame, 4, 0, 1, 1);

        listButtonHere = new QLabel(gridLayoutWidget_2);
        listButtonHere->setObjectName(QString::fromUtf8("listButtonHere"));
        QFont font;
        font.setFamily(QString::fromUtf8("SimSun"));
        font.setPointSize(14);
        font.setBold(false);
        font.setItalic(false);
        listButtonHere->setFont(font);
        listButtonHere->setStyleSheet(QString::fromUtf8("font: 14pt \"SimSun\";"));

        gridOptions->addWidget(listButtonHere, 3, 0, 1, 1);

        labelRed = new QLabel(gridLayoutWidget_2);
        labelRed->setObjectName(QString::fromUtf8("labelRed"));
        QFont font1;
        font1.setFamily(QString::fromUtf8("Kristen ITC"));
        font1.setPointSize(12);
        labelRed->setFont(font1);

        gridOptions->addWidget(labelRed, 1, 0, 1, 1);

        labelBlack = new QLabel(gridLayoutWidget_2);
        labelBlack->setObjectName(QString::fromUtf8("labelBlack"));
        QFont font2;
        font2.setFamily(QString::fromUtf8("Kristen ITC"));
        font2.setPointSize(12);
        font2.setBold(false);
        font2.setItalic(false);
        labelBlack->setFont(font2);

        gridOptions->addWidget(labelBlack, 0, 0, 1, 1);

        horizontalLayoutWidget = new QWidget(centralwidget);
        horizontalLayoutWidget->setObjectName(QString::fromUtf8("horizontalLayoutWidget"));
        horizontalLayoutWidget->setGeometry(QRect(100, 529, 491, 101));
        movesLayout = new QHBoxLayout(horizontalLayoutWidget);
        movesLayout->setObjectName(QString::fromUtf8("movesLayout"));
        movesLayout->setContentsMargins(0, 0, 0, 0);
        play = new QPushButton(horizontalLayoutWidget);
        play->setObjectName(QString::fromUtf8("play"));
        play->setStyleSheet(QString::fromUtf8("color:rgb(255, 170, 0);\n"
"background-color: rgb(85, 85, 0);\n"
"font: 14pt \"Viner Hand ITC\";"));

        movesLayout->addWidget(play);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        labelMessage = new QLabel(horizontalLayoutWidget);
        labelMessage->setObjectName(QString::fromUtf8("labelMessage"));
        labelMessage->setAutoFillBackground(false);
        labelMessage->setStyleSheet(QString::fromUtf8("color: rgb(85, 85, 0);\n"
"font: 700 italic 14pt \"Sitka Display\";"));

        verticalLayout->addWidget(labelMessage);

        labelWinner = new QLabel(horizontalLayoutWidget);
        labelWinner->setObjectName(QString::fromUtf8("labelWinner"));
        labelWinner->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Segoe UI\";\n"
"color: rgb(255, 255, 0);"));

        verticalLayout->addWidget(labelWinner);


        movesLayout->addLayout(verticalLayout);

        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 940, 21));
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        newGame->setText(QCoreApplication::translate("MainWindow", "NEW GAME", nullptr));
        listButtonHere->setText(QString());
        labelRed->setText(QString());
        labelBlack->setText(QString());
        play->setText(QCoreApplication::translate("MainWindow", "Apply", nullptr));
        labelMessage->setText(QCoreApplication::translate("MainWindow", "msg curent player", nullptr));
        labelWinner->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
