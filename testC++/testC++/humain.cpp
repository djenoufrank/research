//ici on implémente les méthodes de la classe
#include "humain.hpp"
#include <iostream>
#include <string>

humain::humain()//constructeur vide
{
	std::cout << "ta first classe c++ construite" << std::endl;
}
humain::humain(std::string nom, int age) //constructeur avec argument
{
	nomH = nom;
	ageH = age;
}
humain::humain(const humain &other) {
	nomH = other.nomH;
	ageH = other.ageH;
}//other devient une copie de humain et ses infos seront savecom ceux de humain
//st save. vu qu'il est unique contrairement a humain alors on use les references 

humain::~humain()//destructeur
{
	std::cout << "destruction" << std::endl;
}
void humain::hello()
{
	std::cout << "une méthode pr dire bjr";
}