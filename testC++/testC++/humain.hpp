//ici on fait juste les déclarations sans implémenter les méthodes
#ifndef humain
#pragma once
#include <string>
  
class humain
{
  public:
	humain();//constructeur vide...on eu mettre un constructeur dans un autre
	humain(std::string nom, int age);
	~humain();
	humain(const humain &other);//constructeur de copie;pour cloner  un objet pr en fabriquer un autre
	
	void hello();
  private:
	  std::string nomH;
	  int ageH;

	  
};

#endif // !humain



