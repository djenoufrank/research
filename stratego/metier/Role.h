#ifndef ROLE_H
#define ROLE_H
namespace std
{

/*!
 * \enum Role
 * represent the role that one piece will have to play and his value
 */
enum ROLE{
    MARSHAL=10,
    GENERAL=9,
    COLONEL=8,
    MAJOR=7,
    COMMANDER=6,
    LIEUTENANT=5,
    SERGEANT=4,
    DEMINER=3,
    PATHFINDER=2,
    SPY=1,
    FLAG=-1,
    BOMB=12,
    EMPTY=0

};
}
#endif // ROLE_H
