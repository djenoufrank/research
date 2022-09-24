#ifndef OBSERVER_H
#define OBSERVER_H
#include <iostream>
#include <vector>
#include <iomanip>
#include "position.h"
#include "color.h"


namespace std {

class Subject;
/*!
 * \brief The observer of the game model .
 */
class Observer
{
public:

Observer() = default;

virtual ~Observer() = default;

virtual void update(Subject * s)=0;

inline bool operator == (Observer & observer);
};

bool Observer::operator== (Observer & observer){
   return this == &observer;
}

} // namespace std

#endif // OBSERVER_H
