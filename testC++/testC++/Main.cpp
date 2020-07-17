#include <iostream>
#include <ctime>//pr les fonctions liées au temps
#include "humain.hpp"

int main()
{
	std::srand(time(nullptr));
    int a = 9,b = 6, val = 0;
	int age = 0;
	std::cout << a / b << std::endl;
	

	std::cin >> val;
	std::cin.ignore();
	std::cout <<"vous avez entrer "<< val << std::endl;
	std::cout << val*(a-b) << std::endl;
	std::cout << "vous etes " << (age < 18 ? "mineur" : "majeur");
	

	humain H1{};
	humain H2{ "richard",2 };
	H1.hello();
	//std::cout << "monsieur " <<H2. << std::endl;
	
	//affiché une liste de grille 
	int x = 10; int y = 10;//taille de la grille
	int** grid = new int*[y];//grid est un pointeur qui pointe vers des pointeurs entier...un tableau de tableaux
	//allocation de chaque lignes(yi)
	for (int i=0; i < y; ++i) {
		grid[i] = new int[x];
	}
	for (int i = 0; i < y; ++i) {
		for (int j = 0; j < x; j++) {
			std::cout << grid[j][i] << std::endl;
		}
	}

	for (int i=0; i < y; ++i) {
		delete[] grid[i];
	}
	delete[] grid;

	std::cin.ignore();
	return 0;
}