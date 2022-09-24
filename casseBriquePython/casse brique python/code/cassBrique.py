# -*- coding: utf-8 -*-
"""
Created on Sun Mar  3 20:53:33 2019

@author: FRANK
"""
from tkinter import *
from random import *
import pickle as pik
import winsound
import sys
import os
from tkinter.messagebox import showinfo



#enregistrement de l'utilisateur
def commandeDeLogin(event):
    global entree,nomUser,listOfUser

    if entree.get().strip()  !="":#on recupere le nom ssi l'entrée n'est pas vide
        nomUser=entree.get()
        root.deiconify()#le canvas du jeu est caché jusqu'à la fermeture du widget top
        top.destroy()#fermeture du widget top
    else:
        pass


def init_can():
    global palette,ballon,hauteur,largeur,pos_palette,flag,mur,pos_mur,dt,niveau_jeuUser,largeur_palette,dtMur,nbreBrique
    niveau_jeuUser=1
    dt=50
    nbreBrique=20
    dtMur=90
    pos_mur=int(largeur/2.2)
    largeur_palette=int(largeur/2.2-largeur/3)
    pos_palette=int(largeur/1.85)#delimite la largeur de deplacement de ma palette
    flag=0
    """initialisation du canvas"""
    
    can.delete(ALL)
    afficher_brique(brique)
    palette = can.create_rectangle(int(largeur/1.85),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)
    mur = can.create_rectangle(int(largeur/1.7),hauteur/1.66,int(largeur/2.5),hauteur/1.7,fill=choice(couleur_mur))
    ballon = can.create_oval(largeur /2-rayon_ballon,hauteur/2-rayon_ballon,largeur/2+rayon_ballon,hauteur/2+rayon_ballon,fill='red') 
    

#canevas pour les niveaux suivant
def init_canforNextLevel():
    global palette,ballon,hauteur,largeur,pos_palette,flag,mur,niveau_jeuUser,nbreBrique,dt,pos_mur,dtMur
    pos_mur=int(largeur/2.2)
    niveau_jeuUser+=1
    nbreBrique=20
    dt=int(dt/2)+15#augmentation de la vitesse de jeu en fonction du niveau de jeu
    pos_palette=int(largeur/1.85)
    flag=0
    niveau_jeu.set("Niveau %i " %niveau_jeuUser)

    can.delete(ALL)
    can.config(bg=choice(couleur_can))
    afficher_brique(brique)
    if niveau_jeuUser==2:
        dtMur=85
   
        largeur_palette=int(largeur/2.2-largeur/3)
        pos_palette=int(largeur/1.85)

        palette = can.create_rectangle(int(largeur/1.85),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)

    if niveau_jeuUser>=3:
        dtMur=75
        largeur_palette=int(largeur/1.7-largeur/2.46)
        pos_palette=int(largeur/1.7)

        palette = can.create_rectangle(int(largeur/1.7),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)
    
    mur = can.create_rectangle(int(largeur/1.7),hauteur/1.66,int(largeur/2.5),hauteur/1.7,fill=choice(couleur_mur))
    ballon = can.create_oval(largeur /2-rayon_ballon,hauteur/2-rayon_ballon,largeur/2+rayon_ballon,hauteur/2+rayon_ballon,fill='red')

#le nouveau jeu lancer si le joueur a encore ses vies avec le score qui est conserver
def init_canforNextLife():
    global palette,ballon,hauteur,largeur,pos_palette,flag,mur,pos_mur
    pos_mur=int(largeur/2.2)
    flag=0
    """initialisation du canvas pour les autres vies"""
    can.delete(ballon)
    can.delete(palette)
    can.delete(mur)
    if niveau_jeuUser==1:
        largeur_palette=int(largeur/2.2-largeur/3)
        pos_palette=int(largeur/1.85)
        
        palette = can.create_rectangle(int(largeur/1.85),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)

    if niveau_jeuUser==2:
        largeur_palette=int(largeur/2.2-largeur/3)
        pos_palette=int(largeur/1.85)
        
        palette = can.create_rectangle(int(largeur/1.85),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)

    if niveau_jeuUser>=3:
        largeur_palette=int(largeur/1.7-largeur/2.46)
        pos_palette=int(largeur/1.7)

        palette = can.create_rectangle(int(largeur/1.7),hauteur,largeur/2.55,int(hauteur/1.025),fill=couleur_palette)
        
    mur = can.create_rectangle(int(largeur/1.7),hauteur/1.66,int(largeur/2.5),hauteur/1.7,fill=choice(couleur_mur))
    ballon = can.create_oval(largeur /2-rayon_ballon,hauteur/2-rayon_ballon,largeur/2+rayon_ballon,hauteur/2+rayon_ballon,fill='red')


#les évenements en entrée sur le clavier (pause, mouvement palette,restart, nouveau jeu si joueur perd toutes ses vies
def keyboard(event):
       global pos_palette,flag,dt,vie,largeur_palette
       
       
       if (event.keysym == 'Left' and (pos_palette-largeur_palette )>10):
         if flag:# mouvement si flag==1
                can.move(palette,-paletteX,paletteY)
                pos_palette=pos_palette-paletteX
       elif (event.keysym == 'Right' and pos_palette < largeur):
          if flag:
              can.move(palette,paletteX,paletteY)
              pos_palette=pos_palette+paletteX
       
           
       elif event.keysym == 'space' :
           #mouvementMurD()
           if flag == 0:#si flag=0 et qu'on appui sur espace ,flag prend 1 et tt bouge
            flag = 1
            mouvement()
            mouvementMur()
           elif flag == 1:#on met pause si flag=1 et qu'on appui sur espace
            flag = 0

           if vie==-1:#on rejour si les vies st fini et qu'il appui sur espace
             nouveauJeu()
       
       else:
             pass

#mvt du mur
def mouvementMur():
    global coordonneeBallon,murX,contactBallon,coordonneeMur,dtMur
  
   
    coordonneeMur = can.bbox(mur)
        
        #j'ai retirer le 80 pr la précision du contact
    if coordonneeMur[0] >= largeur-80:
        murX = -murX
    if coordonneeMur[2] <=80:
        murX = -murX
        
    can.move(mur,murX,0)
    if flag==1:
          root.after(dtMur,mouvementMur)


#mvt de la balle et contact avec les widget du canevas
def mouvement():
    global maxi,scoreUser,coordonneeBallon,coordonneePalette,coordonneeBrique,ballonY,ballonX,contactBallon,vie,flag,nbreBrique,nomUser,coordonneeMur
    global listScore,listOfUser,Hscore
    nomEtScore=[]
    #texte afficher si le joueur meur
    
       #on teste les 4 positions de la balle pour pouvoir determiner s'il fait un contact avec un element du caneva
    coordonneeBallon = can.bbox(ballon)
    if coordonneeBallon[1]+ballonY <= 0:
        winsound.PlaySound('sonPalette.wav',winsound.SND_ASYNC)
        ballonY = -ballonY

    if coordonneeBallon[3]+ballonY >= hauteur:
        if vie!=-1:
            vie-=1
            nbreVie.set("Vie: %i" %vie)
        if vie==0:
              flag=0
              
              #création du fichier et enregistrement des scores du joueur
              nomEtScore=[nomUser,scoreUser]
              listOfUser.append(nomEtScore)
              f = open('scoreCasseBrique.txt','rb')
              listAct = pik.load(f)
              f.close()
              listAct.append(nomEtScore)
              f = open('scoreCasseBrique.txt','wb')
              pik.dump(listAct,f)
              f.close()
              
              listScore.append(listOfUser[-1][1])
              Hscore=max(listScore)
              
              meilleurScore.set("Meilleur Score : %i" %Hscore)
              winsound.PlaySound('sonJeuPerdu.wav',winsound.SND_ASYNC)
              showinfo(title="JEU PERDU", message=" %s" %nomUser +" vous avez perdu!\n pour Rejouer appuyez sur espace ou sur le bouton Rejouer\n votre score est : %i"%scoreUser+"\n le meilleur score est : %i"%Hscore)
              vie=-1
        else:
            init_canforNextLife()
         
        
        
        #j'ai retirer la valeur du rayon  et le 9 est pr la précision du contact
    if coordonneeBallon[0]+ballonX > largeur-9 or coordonneeBallon[2]+ballonX < 9:
        winsound.PlaySound('sonPalette.wav',winsound.SND_ASYNC)
        ballonX = -ballonX
        
    #toutes les coordonnées de la balle qu'on devra tester leur contact avec les autres widgets
    contactBallon = can.find_overlapping(coordonneeBallon[0]+ballonX,coordonneeBallon[1]+ballonY,coordonneeBallon[2]+ballonX,coordonneeBallon[3]+ballonY)

    coordonneePalette= can.bbox(palette)
    if palette in contactBallon:
        winsound.PlaySound('sonPalette.wav',winsound.SND_ASYNC)
        ballonY = -ballonY

    
    coordonneeMur= can.bbox(mur)
    i = 0
    if mur in contactBallon:
        winsound.PlaySound('sonPalette.wav',winsound.SND_ASYNC)
        ballonY = -ballonY
        can.itemconfig(mur, fill=choice(couleur_mur))
        i+=1


    #on parcours les contacts du ballon, s'il ya contact on met cette brique dans la liste de brique a effacer et on change la le deplacement de la balle
    brique_a_effacer=[]#liste des briques qui serons en contact avec la balle à un instant t

    #on parcours les briques et on teste s'il ya contact avec la balle et en fction des couleurs on fait un traitement spécifique(changer de couleur ou effacer la brique
    for i in brique:
        
        coordonneeBrique= can.bbox(i)

        if i in contactBallon and can.itemcget(i,"fill")=='red':
            winsound.PlaySound('sonBrique.wav',winsound.SND_ASYNC)
            ballonY = -ballonY
            i=can.itemconfig(i, fill="orange")
            scoreUser += 10
            score.set("%i" % scoreUser)
         
        elif i in contactBallon and can.itemcget(i,"fill")=='orange':
            winsound.PlaySound('sonBrique.wav',winsound.SND_ASYNC)
            ballonY = -ballonY
            i=can.itemconfig(i, fill="yellow")
            scoreUser += 7
            score.set("%i" % scoreUser)
            
        elif i in contactBallon and can.itemcget(i,"fill")=='yellow':
            
            winsound.PlaySound('sonBrique.wav',winsound.SND_ASYNC)
            ballonY = -ballonY 
            brique_a_effacer.append(i)# la liste briq a ef recupère la briq qui a eu le contact avec la balle 
            if nbreBrique!=0:
              scoreUser += 5
              score.set("%i" % scoreUser)
              nbreBrique-=1
              if nbreBrique==0:
                   init_canforNextLevel()

    for i in brique_a_effacer:
        can.delete(i)# on efface la brique que l'on a mit ds brique_a_effacer
        brique.remove(i)#on retire la brique qui est affichée
        #on decremente le nombre de brique et on ajoute le score de 5 ssi il ya encore les briques
        
        
    can.move(ballon,ballonX,ballonY)
    if flag==1:
          root.after(dt,mouvement)


#méthodes qui va afficher les briques
def afficher_brique(brique):
    for i in range(0,int(hauteur/4),20):
        for j in range(0,largeur,100):
              couleur = choice(couleur_brique)
              la_brique=can.create_rectangle(j, i, j+100, i+20,fill=couleur)
              brique.append(la_brique)

#bouton pour lancer le jeu ou rejouer
def nouveauJeu():
       bnouveauJeu.config(text='Rejouer')
       init_score()
       can.config(bg='black')
       init_can()
#initialisation du score 
def init_score():
    global nomUser,niveau_jeu,scoreUser,vie,niveau_jeuUser,entree,Hscore,listOfUser,listScore
    afficher_brique(brique)
    scoreUser = 0
    nbreBrique=20
    niveau_jeuUser=1
    vie=3

    meilleurScore.set("Meilleur Score : %i" %Hscore)
    nbreVie.set("Vie : %i" %vie)
    nom.set("nom du Joueur:\n%s" %nomUser) 
    score.set("score: %i" %scoreUser)
    niveau_jeu.set("Niveau %i " %niveau_jeuUser)    


def aide():
  
   aideCan = Tk()
   aideCan.title('**********************AIDE AU JEU**************************')
   aide = Canvas(aideCan,bg='white',height=400,width=400)
   aide.grid(row=0,column=0)
   aide.create_text(200, 50,text=" Bienvenue dans l’aide de SUPER-CASSE-BRIQUE") 
   aide.create_text(200, 70,text=" Pour commencer, cliquez sur le bouton « NOUVEAU JEU » ")
   aide.create_text(200, 90,text=" pour initialiser le jeu.") 
   aide.create_text(200, 110,text=" Appuyez ensuite sur  « ESPACE » pour démarrer le niveau.")
   aide.create_text(200, 130,text=" Vous pouvez utilisez les touches GAUHE-DROITE pour déplacer ")
   aide.create_text(200, 150,text=" la palette de gauche a droite.")
   aide.create_text(200, 170,text=" Le but est de détruire toutes les briques afin de passer au niveau supérieur.")
   aide.create_text(200, 190,text=" A chaque fois que vous ne parvenez pas à réceptionner ")
   aide.create_text(200, 210,text=" correctement la balle, vous perdez une vie ;")
   aide.create_text(200, 230,text=" lorsque trois balles sont non réceptionnées vous perdez la partie.")
   aide.create_text(200, 250,text=" Cliquez sur  « REJOUER » pour recommencer" )
   aide.create_text(200, 270,text="  Cliquez sur « QUITTER » pour quitter le jeu. ")  

   aide.mainloop()



#initialisation des variables à utiliser
hauteur,largeur = 400,400
couleur_canvas = 'black'
couleur_palette='blue'
couleur_can=['purple','light blue','pink']
couleur_mur=['brown','red','blue','purple','orange','light blue','yellow']
couleur_brique = ['red','orange','orange','yellow','yellow','yellow','yellow','yellow']
rayon_ballon = 5
brique=[]
palette=None
mur=None
niveau_jeuUser=1
listDecollageX,listDecollageY=[-4,-5,4,5],[4,5]
ballonX, ballonY = choice(listDecollageX) ,choice(listDecollageY)
paletteX, paletteY = 10, 0
murX=5
dtMur=100
dt=50
pos_mur=int(largeur/2.2)
largeur_mur=int(largeur/2.2-largeur/3)
pos_palette=int(largeur/1.82)
largeur_palette=int(largeur/2.2-largeur/3)
flag=0
vie=3
nomUser=""
scoreUser=0
nbreBrique=20

f = open("scoreCasseBrique.txt",'rb')
listOfUser= pik.load(f)
f.close()

listScore=[]
for i in listOfUser:
    listScore.append(i[1])
Hscore=max(listScore)



#corps du programme où on positionnera les widgets 
root = Tk()
root.title('**********************CASSE BRIQUE**************************')

#interface de lancement pour récuperer le nom du joueur
top= Toplevel()
entree=Entry()#initialisation de l'entree
monFichier= 'scoreCasseBrique.txt' #j'y travail encore
top.geometry('450x480')#dimentionnement de l'interface de lancement
top.title('LOGIN SCREEN')
photo = PhotoImage(file='casse_briques.gif')
labelPhoto = Label(top, image=photo)
labelNom=Label(top,text='Nom Du Joueur')
entree=Entry(top)#recuperation de l'entree que le user va entrer
entree.bind('<Return>',commandeDeLogin)#le nom sera enregistrer à l'appui de entrer sur le clavier
labelPhoto.pack()
labelNom.pack()
entree.pack()


"""interface du jeu en question"""
can = Canvas(root,bg=couleur_canvas,height=hauteur,width=largeur)
bnouveauJeu = Button(root,text='Nouveau jeu',command=nouveauJeu)
baide = Button(root,text='INSTRUCTIONS',command=aide,bg='yellow')
quitter = Button(root,text='Quitter',command=root.destroy)

can.grid(row=0,column=0,columnspan=5,rowspan=10)

#positionnement des elements du canvas: label,bouton
bnouveauJeu.grid(column=5,row=0)
baide.grid(column=5,row=1)
quitter.grid(column=5,row=10)

nbreVie=StringVar()
nbreVie=StringVar()
score =StringVar()
nom= StringVar()
niveau_jeu =StringVar()
meilleurScore = StringVar()
labelVie = Label(root,textvariable=nbreVie,foreground="red")
labelScore = Label(root,textvariable=score)
labelNom = Label(root,textvariable=nom)
labelNiveau = Label(root,textvariable=niveau_jeu,foreground="red")
labelMeilleurScore = Label(root,textvariable=meilleurScore,foreground="blue")

labelVie.grid(column=5,row=8)
labelNiveau.grid(column=5,row=6)
labelScore.grid(column=5,row=5)
labelNom.grid(column=5,row=4)
labelMeilleurScore.grid(column=5,row=2)


root.bind('<Key>',keyboard)


root.withdraw()#pour que le canvas du jeu attende que l'interface de recuperation du nom se ferme
root.mainloop()