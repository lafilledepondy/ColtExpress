<div style="text-align:right">
    25/04/24
</div>

# Colt Express

Un project de Licence 2 d'Informatique.

### Programmation Objet et Génie Logiciel

<div style="text-align:center">
    <img src="src/images/ups.png">
</div>

## Auteurs

Line HAJJAR et Gayathiri RAVENDIRANE

## Description

Colt Express est une adaptation numérique du célèbre jeu de société du même nom. Dans ce jeu, les joueurs endossent le rôle de bandits de trains dans le Wild West, rivalisant pour dérober le plus de richesses possible tout en évitant le Marshall et les balles des autres joueurs.

## Installation

Pour installer et exécuter Colt Express, suivez ces étapes :

1. Clonez le dépôt depuis GitLab :

```
git clone https://gitlab.dsi.universite-paris-saclay.fr/line.hajjar/colt-express.git
```

2. Accédez au répertoire du projet :

```
cd colt-express
```

3. Lancez le fichier Vue.java.

## Utilisation

Voici un aperçu des différentes façons d'utiliser le jeu :

1. Regardez le terminal et entrez le nombre de joueurs (bandits). Dans notre jeu, le nombre de bandits est compris entre 2 et 4.
2. Chaque joueur prend tour à tour pour planifier ses actions (un maximum 4 actions.). les actions disponibles sont :
   - **Déplacer** : les joueurs peuvent déplacer leur personnage bandit vers un wagon adjacent ou monter ou descendre du toit du wagon actuel.
   - **Tirer** : les joueurs peuvent tirer dans quatre directions : vers le haut, vers le bas, vers la droite ou vers la gauche. Tirer sur un autre joueur peut le faire lâcher son butin.
   - **Braquer** : les joueurs peuvent braquer un passager pour collecter du butin.
   - **Récupérer** : les joueurs peuvent récupérer un butin du par terre.

(**Retour** : revenir à l'étape précédente pour modifier les actions si nécessaire. Cliquez seulement avant de cliquer sur "Ready".)

3. Après la planification, cliquez sur le bouton "Ready". Le bouton "Action" ne peut pas être pressé tant que tous les joueurs ne sont pas prêts.
4. C'est au tour du joueur suivant de planifier.
5. Une fois que tous les joueurs ont terminé la planification et sont prêts, clique sur le bouton "Action" pour exécuter les actions planifiées par tous les joueurs.

## Interface utilisateur

Le jeu dispose d'une interface graphique où les joueurs peuvent voir le train, leurs personnages et les actions qu'ils peuvent entreprendre.

## Organisation du travail

Pour bien mener ce projet, nous avons utilisé diverses méthodes de collaboration. Le ‘‘pair
programming’’ a été notre approche principale, où chaque membre du groupe a contribué à la
rédaction du code. Même lorsque nous avons écrit des classes séparément, nous avons
systématiquement passé en revue le code de l'autre pour le déboguer ou l'améliorer. Nous avons
utilisé des sessions G-meet et également pratiqué la programmation côte à côte ‘‘virtuel’’.

Concernant la gestion du code source avec Git, nous avons travaillé sur des branches distinctes
pour éviter les conflits de versions.

## Organisation des classes et des méthodes

- **Train** (Train)

  - **Actions** (Train.Actions)

    - **Action :** (Train.Actions.Action) Classe abstraite représentant une action générique effectuée par une personne. Les actions spécifiques telles que le déplacement, le braquage, etc., sont implémentées dans des classes filles.
      **Braquer :** (Train.Actions.Braquer) Classe représentant l'action de braquer un passager pour collecter du butin.
    - **Deplacer :** Classe représentant l'action de déplacer une personne dans une direction donnée sur le toit ou à l'intérieur du wagon.
    - **Rob :** (Train.Actions.Rob) Classe représentant l'action de récupérer du butin.
    - **Tirer :** (Train.Actions.Tirer) Classe représentant l'action de tirer sur une cible.

  - **ButinType** (Train.ButinType)

    - **Butin :** (Train.ButinType.Butin) Classe abstraite représentant un objet de butin avec une valeur monétaire.
    - **Magot :** (Train.ButinType.Magot) Classe représentant un type spécifique de butin avec une valeur monétaire élevée.
    - **Bijoux :** (Train.ButinType.Bijoux) Classe représentant un type spécifique de butin avec une valeur monétaire fixe de 500.
    - **Bourse :** (Train.ButinType.Bourse) Classe représentant un type spécifique de butin avec une valeur monétaire aléatoire entre 0 et 500.

  - **CaseType** (Train.CaseType)

    - **Case :** (Train.CaseType.Case) Classe représentant une case dans le train avec des fonctionnalités pour placer et enlever des butins, ainsi que pour gérer les passagers et les bandits.
    - **Interieur :** (Train.CaseType.Interieur) Classe représentant une case intérieure du train.
    - **LocomotiveInt :** (Train.CaseType.LocomotiveInt) Classe représentant la case intérieure de la locomotive.
    - **LocomotiveRf :** (Train.CaseType.LocomotiveRf) Classe représentant la case sur le toit de la locomotive.
    - **Toit:** (Train.CaseType.Toit) Classe représentant une case sur le toit du train.

  - **PersonneType** (Train.PersonneType)

    - **Bandit :** (Train.PersonneType.Bandit) Classe représentant un bandit (joueur) dans le jeu, avec des fonctionnalités telles que le déplacement, le braquage, etc.
    - **Marshall :** (Train.PersonneType.Marshall) Classe représentant le marshall dans le jeu, chargé de maintenir l'ordre dans le train.
    - **Passager :** (Train.PersonneType.Passager) Classe représentant un passager dans le train, avec des fonctionnalités liées au butin.
    - **Personnage :** (Train.PersonneType.Personnage) Classe abstraite représentant une entité dans le jeu, avec des fonctionnalités communes telles que la position et la gestion du butin.
    - **Personne :** (Train.PersonneType.Personne) Classe abstraite représentant une entité dans le train, telle qu'un bandit ou un passager, dans le jeu.

  - **Direction** (Train.Direction) Enumération représentant les différentes directions dans lesquelles une action peut être effectuée.

  - **Joueur** (Train.Joueur) Classe représentant un joueur dans le jeu, avec des fonctionnalités telles que l'ajout, la suppression et l'exécution d'actions.

  - **Train** (Train.Train) Classe représentant le train lui-même, avec des fonctionnalités telles que l'initialisation des butins et l'affichage des emplacements dans le train.

- **Modéle Vue Controleur** (MVC)

  - **Vue** (MVC.Vue)

    - **AffichageTrain** (MVC.Vue.AffichageTrain) Classe responsable de l'affichage des composants du train à l'écran.

    - **ImageUtils** (MVC.Vue.ImageUtils) Classe extra pour travailler avec les images.

    - **Vue**(MVC.Vue.Vue) Classe représente la vue principale de l'application, contenant tous les éléments du train et des boutons interactifs pour le joueur

  - **Controleur** (MVC.Controleur) Classe gèrant les interactions utilisateur avec les boutons, effectuant les modifications nécessaires dans le modèle en réponse aux actions de l'utilisateur.

  - **Modele** (MVC.Modele) Classe gèrant la logique métier du jeu ainsi que la représentation des objets dans le train.

Cette structure orientée objet permet une organisation modulaire et extensible du code, facilitant ainsi le développement, la maintenance et l'ajout de nouvelles fonctionnalités au jeu Colt Express.

## Distinction entre la planification et l'exécution

Ce jeu se déroule en deux phases distinctes : la planification et l'exécution.

### Phase de planification

Pendant la phase de planification, chaque joueur prend tour à tour pour déterminer les actions que leur personnage effectuera lors de la phase d'exécution. Les actions possibles incluent le déplacement, le braquage, le tir et le retour en arrière. Chaque joueur peut planifier jusqu'à un maximum de quatre actions.

### Phase d'exécution

Une fois que tous les joueurs ont planifié leurs actions, la phase d'exécution commence. C'est là que le bouton "Action" entre en jeu. Lorsque ce bouton est activé, toutes les actions planifiées par les joueurs sont exécutées dans l'ordre.

Le bouton "Ready" agit comme une frontière entre la planification et l'exécution, permettant aux joueurs de séquencer leurs actions stratégiquement.

## Tests

Nous avons également inclus des tests unitaires pour vérifier le bon fonctionnement de différentes parties du jeu. Les tests sont regroupés dans un package distinct. L'inclusion de tests nous a permis de garantir la stabilité et la fiabilité du jeu, en identifiant et en corrigeant les problèmes potentiels dès leur apparition.

## Prise de recul

- Nous avons hésité entre l'utilisation d'un tableau 2 dimensionnel, Etage[][], pour représenter le toit et l'intérieur du wagon, ou plutôt la création d'une classe contenant une boolean disant si on est sur le toit ou pas. Finalement, nous avons opté pour une approche plus simple en utilisant un attribut booléen roof pour distinguer le toit de l'intérieur du wagon, avec différentes classes pour chaque type d'emplacement.
- Séparation de l'interface utilisateur entre la zone de jeu et les boutons d'interaction.
- Amélioration esthétique en remplaçant les dessins (formes géométriques) par des images. De même, pour les JButtons pour une expérience utilisateur plus attrayante et cohérente.
- Flexibilité dans le choix du nombre de joueurs, permettant une liberté d'adaptation aux préférences des utilisateurs.
- Ajouté un bouton "Retour" pour permettre aux joueurs d'annuler des actions ou de corriger des erreurs pendant sa partie.

## Conclusion

Ce projet a été une occasion d’acquérir des connaissances significatives. En effet, nous avons
acquis une compréhension approfondie de l’utilisation de Git via un terminal. De plus, nous avons
maîtrisé l’utilisation de JFrame, JPanell, JButton et BufferedImage pour animer le jeu. En travaillant
sur ce projet, nous avons également développé nos compétences dans le fonctionnement
fondamental d’une application de jeu.
