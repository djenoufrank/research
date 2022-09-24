TEMPLATE = app
CONFIG += console c++17
QMAKE_CXXFLAGS += -pedantic-errors  -Wall -Wuninitialized
CONFIG -= app_bundle
CONFIG -= qt
CONFIG += ordered
SOURCES += \
    TestBoard.cpp \
    TestGame.cpp \
    main.cpp \
    testSquare.cpp

HEADERS += \
    catch.hpp \
