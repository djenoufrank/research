
# Rapport du projet ButtonUp

Membre du groupe D122 :  
- Djenou Frank 55301

## Définition
ButtonUp est un jeu de société. inclue une version console pour les test et une graphique pour la remise. Sommairement, le but de ce jeu de plateau est de diriger une armée de 3 boutons chacun(rouge ou noir) puis 3 espions appartenants aux deux adversaires.  
Le but du jeu est d'attendre en premier les 15 points 
Nous allons donc ici mettre en pratique les cours assimilés au cour de Dev3 pour réaliser ce jeu.

	> le diagramme de classe ajouter à notre projet en .mdj vous permettra d'avoir une vision generale du code derrière .   

### les différentes classes nécessaires à notre travail:

#### Game
La classe Game va s'occuper du jeu en lui-même. il possède un tableau de jeu et deux joueurs.
- il initialise tout: les joueurs avec leur boutons, le tableau avec ses positions
- c'est lui qui est charger de déplacer un bouton lié à un joueur en confrontant les positions.
- il dit si la partie est terminer ou pas, aussi il détermine le vainqueur de la partie.
- il change au moment opportein le joueur courant.

#### Position
Cette classe va être représentative de toutes les positions possibles du tableau de jeu grace à une abscisse et une ordonnée.

#### Button
Cette classe représente un bouton du jeu avec des caractéristiques tels que la couleur, le toString nous permettant d'avoir les boutons a une position.

#### Color
Cette énumération va attribuer une couleur à un joueur, ceux ci me permettant d'afficher par exemple le vainqueur d'une partie ou encore définir celui qui va commencer la partie(bouton rouge).

#### Player
ceux qui joueront au game, nous avons 2 joueurs. L'un(rouge)  et l'autre noir qui disposent de 3 boutons chacun avec 3 espions manipulables par les deux protagonistes.

#### Square
contenant un vecteur de bouton sur une position donnée nous permettant d'ajouter,enlever un ou plusieurs boutons, verifier si la position est vide,avoir la couleur du bouton le plus haut, savoir s'il y a un bouton blanc à cette position.

#### Board
le board represente le tableau de jeu, il a les squares sur un tableau de 10x10, il permet de verifier si un bouton est à une position.

#### View Graphique
La vue graphique gère tout ce qui concerne toute la partie affichage du jeu.  
- la page mainwindow contient le jeu proprement dit dont : un QWidget(gridLayout) affichant le jeu, un autre gridLayout(gridOptions) affichant le score de chacun et le bouton pour rejouer,
un hrizontal layout contenant le bouton pour jouer , le message du joueur actuel, le message au vainqueur.

### les technologies utilisées
- QtCreator
- doxyWizard
- startUml
- gitLab
- le language C++ version 17  
- Qt widget Application 

> **Note:** concernant notre diagramme de classe, il est ici à la racine du projet et nommé **diagramme de classe.mdj**.