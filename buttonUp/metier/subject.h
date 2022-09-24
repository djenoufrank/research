#ifndef SUBJECT_H
#define SUBJECT_H
#include<iostream>
#include "observer.h"
#include "position.h"
#include <list>


namespace std {

class Observer;


/*!
 * \brief La classe Subject représente la classe observee
 */
class Subject{

protected:

std::list<Observer*> observers_{};

Subject() = default;

public:

virtual ~Subject() = default;

Subject(const Subject &) = default;

inline void addObserver(Observer * observer);

inline void removeObserver(Observer * observer);

inline void notify();
};
void Subject::addObserver(Observer * observer) {
    observers_.push_back(observer);
}

void Subject::removeObserver(Observer * observer) {
    observers_.remove(observer);
}

void Subject::notify() {
    for(auto j : observers_) {
        j->update(this);
    }
}
} // namespace std


#endif // SUBJECT_H
