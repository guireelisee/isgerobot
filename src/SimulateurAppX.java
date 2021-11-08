
/**
* Programme principal avec la méthode main
*
* @author ISGE-BF
* @version 1.0
*/
public class SimulateurAppX {

	public static void main(String[] args) {

		String[][] carte;
		int[] ligne;
		int[] colonne;

		// création de l'environnement et récupération du terrain
		Terrain t = Environnement.creerEnvironnement(10, 10);

		// Initialisation d'un tableau 2D representant la cartographie du terrain
		carte = new String[t.getNbLignes()][t.getNbColonnes()];

		// Initialisation de deux tableaux pour stocker les coordonnées des portes
		ligne = new int[5];
		colonne = new int[5];

		// Booléen pour tester la decouverte de la victime
		boolean trouver = false;

		// Création des murs sur les colonnes à indice impaire
		for (int i = 0; i < t.getNbLignes(); i++) {
			if (i % 2 != 0) {
				t.ajouterMur(i);
			}
		}

		// Stockage de la cartographie du terrain
		for (int i = 0; i < t.getGrille().length; i++) {
			for (int j = 0; j < t.getGrille().length; j++) {
				if (t.isObstacle(i, j)) {
					carte[i][j] = "O";
				} else if (!t.isObstacle(i, j) && j % 2 != 0) {
					carte[i][j] = "P";
				} else {
					carte[i][j] = "V";
				}
			}
		}

		// Coordonnées des portes
		int index = 0;
		for (int i = 0; i < t.getGrille().length; i++) {
			for (int j = 0; j < t.getGrille().length; j++) {
				if (carte[i][j].equals("P")) {
					ligne[index] = i;
					colonne[index] = j;
					index++;
				}
			}
		}

		// Tri des coordonnées des portes par ordre croissant des colonnes
		for (int i = 0; i < colonne.length; i++) {
			for (int j = 0; j < colonne.length; j++) {
				if (colonne[i] < colonne[j]) {
					int x = ligne[i];
					int y = colonne[i];
					ligne[i] = ligne[j];
					colonne[i] = colonne[j];
					ligne[j] = x;
					colonne[j] = y;
				}
			}
		}

		// Création d'une victime
		t.ajouterVictimePositionAleatoireColonne9();

		// creation du robot
		Robot robot = new Robot(0, 0, "est");

		// ajout du robot sur le terrain
		t.ajouterRobot(robot);

		// met é jour les composants graphiques
		t.updateIHM();

		// Code de déplacement du robot

		index = 0;
		while (!trouver) {
			if (index == 0) { // Code pour franchir la 1ère porte
				System.out.println("PREMIERE ETAPE");

				if (ligne[index] == 0) { // Si la porte est sur la ligne 0, le robot avance de deux pas
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else { // Sinon il tourne à droite et se deplace jusqu'à sur la ligne de la porte, tourne à gauche et fait deux pas en avant
					robot.tournerDroite();
					for (int i = 0; i < ligne[index]; i++) {
						robot.avancer();
					}
					robot.tournerGauche();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				}
				index++;
			} else if (index == 1) { // Code pour franchir la 2ème porte
				System.out.println("DEUXIEME ETAPE");

				if (ligne[index - 1] > ligne[index]) { // Si la ligne actuelle est plus grande que la ligne sur laquelle se trouve la porte suivante, le robot tourne à gauche, fait (ligne_actuelle - ligne_suivante) pas pour se positionne sur la ligne de la porte, tourne ensuite à droite et  avance encore de deux pas en avant
					robot.tournerGauche();
					for (int i = 0; i < (ligne[index - 1] - ligne[index]); i++) {
						robot.avancer();
					}
					robot.tournerDroite();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else if (ligne[index - 1] < ligne[index]) { // Sinon si la ligne actuelle est plus petite que la ligne suivante, le robot tourne à droite, fait (ligne_suivante - ligne_actuelle) pas, tourne à gauche et fait deux pas en avant
					robot.tournerDroite();
					for (int i = 0; i < (ligne[index] - ligne[index - 1]); i++) {
						robot.avancer();
					}
					robot.tournerGauche();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else { // Si la ligne actuelle correspond à la ligne suivante, le robot fait immédiatement deux pas en avant
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				}

				index++;
			} else if (index == 2) { // Code pour franchir la 3ème porte
				System.out.println("TROISIEME ETAPE");

				if (ligne[index - 1] > ligne[index]) {
					robot.tournerGauche();
					for (int i = 0; i < (ligne[index - 1] - ligne[index]); i++) {
						robot.avancer();
					}
					robot.tournerDroite();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else if (ligne[index - 1] < ligne[index]) {
					robot.tournerDroite();
					for (int i = 0; i < (ligne[index] - ligne[index - 1]); i++) {
						robot.avancer();
					}
					robot.tournerGauche();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else {
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				}

				index++;
			} else if (index == 3) { // Code pour franchir la 4ème porte
				System.out.println("QUATRIEME ETAPE");

				if (ligne[index - 1] > ligne[index]) {
					robot.tournerGauche();
					for (int i = 0; i < (ligne[index - 1] - ligne[index]); i++) {
						robot.avancer();
					}
					robot.tournerDroite();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else if (ligne[index - 1] < ligne[index]) {
					robot.tournerDroite();
					for (int i = 0; i < (ligne[index] - ligne[index - 1]); i++) {
						robot.avancer();
					}
					robot.tournerGauche();
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				} else {
					for (int i = 0; i < 2; i++) {
						robot.avancer();
					}
				}

				index++;
			} else if (index == 4) { // Code pour franchir la 5ème porte
				System.out.println("CINQUIEME ETAPE");
				System.out.println("LIGNE ACTUELLE: " + robot.getLigne());
				switch (robot.getLigne()) {
					case 0:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerDroite();
						for (int i = 0; i < 10; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
					}
					break;

					case 1:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						robot.avancer();
						if (robot.isSurVictime()) {
							trouver = true;
							break;
						} else {
							robot.tournerDroite();
							robot.tournerDroite();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 2:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						for (int i = 0; i < 2; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerDroite();
							robot.tournerDroite();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 3:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						for (int i = 0; i < 3; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerDroite();
							robot.tournerDroite();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 4:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						for (int i = 0; i < 4; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerDroite();
							robot.tournerDroite();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 5:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						for (int i = 0; i < 5; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerDroite();
							robot.tournerDroite();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 6:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerDroite();
						for (int i = 0; i < 3; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerGauche();
							robot.tournerGauche();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 7:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerDroite();
						for (int i = 0; i < 2; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
						if (!trouver) {
							robot.tournerGauche();
							robot.tournerGauche();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 8:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerDroite();
						robot.avancer();
						if (robot.isSurVictime()) {
							trouver = true;
							break;
						}
						if (!trouver) {
							robot.tournerGauche();
							robot.tournerGauche();
							for (int i = 0; i < 10; i++) {
								robot.avancer();
								if (robot.isSurVictime()) {
									trouver = true;
									break;
								}
							}
						}
					}
					break;

					case 9:
					if (robot.isSurVictime()) {
						trouver = robot.isSurVictime();
						break;
					} else {
						robot.tournerGauche();
						for (int i = 0; i < 10; i++) {
							robot.avancer();
							if (robot.isSurVictime()) {
								trouver = true;
								break;
							}
						}
					}
					break;

					default:
					break;
				}
			}
		}

		System.out.println("BRAVO ! VICTIME SAUVEE.");

	}

}
