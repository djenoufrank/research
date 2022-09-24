
# Rapport du projet Stratego 

Membres du binome du groupe D122 :  
- Djenou Frank 55301
- La Grange Simon 53037


## Définition
Stratego est un jeu de société. ce jeu qui tiendra notre attention durant tout le quadri inclue une version console et une graphique. Sommairement, le but de ce jeu de plateau est de diriger une armée pour s’emparer du drapeau  
adverse, tout en protégeant le sien.
Nous allons donc ici mettre en pratique les cours assimilés au cour de Dev3 pour réaliser ce jeu.

	> Nous tenons à remarquer ici que nous ferons juste un bref appercue de notre travail en ceci que tout les détails liés à notre code(partie technique) sont déjà présents dans le mackdown du diagramme de classe ajouter à notre projet en .mdj .   

### les différentes classes nécessaires à notre travail:

#### Game
La classe Game va s'occuper du jeu en lui-même. il possède un tableau de jeu et deux joueurs.
- il initialise tout: les joueurs avec leur pièces, le tableau
- c'est lui qui est charger de déplacer une pièce lié à un joueur en confrontant les positions.
- il dit si la partie est terminer ou pas, aussi il détermine le vainqueur de la partie.
- il est aider du controleur qui l'envoi des informations controlées.


#### Position

Cette classe va être représentative de toutes les positions possibles du tableau de jeu grace à une abscisse et une ordonnée.

#### Piece

Cette classe représente une pièce du jeu avec des caractéristiques tels que un rôle, une position, un booléen pour savoir si elle peut bouger et un autre pour savoir si elle est visible par l'adversaire.
- elle peut effectuer un mouvement à la demande 
- elle peut être visible ou invisible dû au mode de jeu ou alors si elle est coincé dans une même position qu'une pièce adverse par attaque de celle-ci.

#### Rôle

Cette énumération va attribuer des rôles aux pièces ainsi que leur valeurs respectives.

#### Color

Cette énumération va attribuer des couleurs à un joueurs ceux ci me permettant d'afficher par exemple le vainqueur d'une partie.

#### Player
ceux qui joueront au game, nous avons 2 joueurs. L'un(rouge) rempli toutes ses pièces avant de donner la main à son adversaire(bleu) qui disposent de 40 pieces chacun.

#### Controller
Le controleur gère les interactions entre la vue et le model qui est ici notre game.
- il initialise de manière explicite le tableau en récupérant les pièces entrées par les joueurs et donne tout au model pour qu'il fasse l'initialisation proprement dite. 
- tout étant mit en place, il lance la partie.

#### View 

La vue gère tout ce qui concerne toute la partie affichage du jeu et les entrées du joueur.

#### View Graphique

La vue graphique gère tout ce qui concerne toute la partie affichage du jeu et les entrées du joueur sur la première page et le jeu meme en question sur la deuxieme page.
- la page mainwindowinitialisation recupère toutes les pièces choisies par les 2 joueurs
- la page mainwindow contient le jeu proprement dit 

### les technologies utilisées
- QtCreator
- doxyWizard
- startUml
- gitLab
- le language C++ version 17  
- Qt widget Application 

### les warning
- crt0_c.c:-1: error: undefined reference to `WinMain' :  
  cette erreur cause deux autres. Il est simplement dù au fait que je n'ai pas de main dans mon subProjet "test".
 catch.h contient deja un main, null besoin donc d'en créé dans main.cpp.

> **Note:** concernant notre diagramme de classe, il est ici à la racine du projet et nommé **diagramme de classe update.mdj**.