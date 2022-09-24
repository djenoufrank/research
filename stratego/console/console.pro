TEMPLATE = app
CONFIG += console c++17
QMAKE_CXXFLAGS += -pedantic-errors  -Wall -Wuninitialized
CONFIG -= app_bundle
CONFIG -= qt
CONFIG += ordered

SOURCES += \
        main.cpp

HEADERS += \
    Controller.h
