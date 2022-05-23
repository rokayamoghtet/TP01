# TP01
Développement de la méthode du
simplexe
• Si d ≥ 0, alors x + θ d ≥ 0 pour tout θ.
– plus θ est grand, plus le coût diminue
– θ*=+∞
– le problème est non borné
• S’il existe i tel que di < 0, la contrainte
xi+θ*di≥0
devient
θ ≤ -xi/di
 θ* doit vérifier toutes les contraintes
θ*= min{i ¦ di<0}(-xi/di
)


Algorithme du simplexe
1
En entrée
 nbVarDec : nombre de variables de décision du problème initial
 nbContraintes : nombre de contraintes du problème
 fctObj : tableau de réels qui contient les coefficients de la fonction objectif
 cont : matrice qui contient les coefficients des contraintes
 b : tableau de réels qui contient les constantes des contraintes
 typeContrainte : tableau de chaine qui contient le type de chaque contrainte Les valeurs possibles sont ‘<=’, ‘>=’ et ‘=’
Pour i = 1 à nbContraintes pas 1 Faire
Si c[i] < 0 Alors
Inverser la contrainte
FinSi
FinPour
// On compte le nombre total de variables (décision, écarts, artificielles)
nbVarTot = nbVarDec
Pour i = 1 à nbContraintes pas 1 Faire
Si typeContrainte[i] = ‘ <= ‘ Alors
nbVarTot = nbVarTot + 1 // variable d’écart
SinonSi typeContrainte[i] = ‘ >= ‘ Alors
nbVarTot = nbVarTot +2 // variable d’écart + variable artificielle
Sinon // cas de l’égalité
nbVarTot = nbVarTot + 1 // variable artificielle
FinSi
FinPour
// Allocation du tableau pour la fonction objectif
Allouer un tableau de nbVarTot réels nommé fctObjEA
// Allocation du tableau pour repérer les variables de bases
Allouer un tableau de nbVarTot booléens nommé VB
// Allocation de la matrice pour la standardisation des contraintes
Allouer une matrice de réels de dimensions (nbContraintes, nbVarTot) nommée contEA
// Allocation du tableau pour la correspondance entre les contraintes et les variables de base
Algorithme du simplexe
2
Allouer un tableau de nbContraintes entiers nommé noVBCont
// Initialisation des coefficients de la fonction objectif
Pour j = 1 à nbVarDec pas 1 Faire
fctObj[j] = fctObjEA[j]
FinPour
// La variable col permet de gérer un indice courant pour l’ajout de nouvelles variables
col = nbVarDec + 1
// Initialisation du coefficient pour les variables artificielles
M =??? // ??? vaut un très grand nombre positif
// Ajout des variables d’écart et des variables artificielles
Pour i = 1 à nbContraintes pas 1 Faire
Pour j = 1 à nbVarDec pas 1 Faire
contEA[i][j] =cont[i][j]
FinPour
Si typeContrainte[i] = ‘<=’ Alors
// On ajoute simplement une variable d’écart
contEA[i][col] = 1
fcTObjEA[j] = 0
VB[col] = VRAI
noVBCont[i] = col
col = col + 1
Si typeContrainte[i] = ‘>=’ Alors
// On ajoute une variable d’écart et une variable artificielle
conteEA[i][col] = -1
fcTObjEA[j] = 0
VB[col] =FAUX
col = col + 1
conteEA[i][col] = 1
fcTObjEA[j] = - M
VB[col] = VRAI
 
noVBCont[i] = col
col = col + 1
SINON
// On ajoute simplement une variable artificielle
contEA[i][col] = 1
fctObjEA[j] = -M
VB[col] = VRAI
noVBCont[i] = col
col = col + 1
FinSi
FinPour
// les différentes itérations du simplexe
// Initialisation du nombre epsilon pour éviter les bouclages
epsilon = ??? // ??? vaut un très petit nombre strictement positif
Faire
// On cherche la variable entrante parmi les variables hors base
noVarEntrante = 0 // numéro de variable indéfinie
Pour j = 1 à nbVarTot Pas 1 Faire
Si VB[j] = FAUX Alors
delta =fctObj[j]
Pour i = 1 à nbContraintes pas 1 Faire
delta = delta – contEA[i][j] * fctObjEA[noVBCont[i]]
FinPour
Si noVarEntrante = 0 Alors
deltaMax  delta
noVarEntrante = j
SinonSi delta > deltaMax Alors
deltaMax =delta
noVarEntrante = j
FinSi
FinPour
Si deltaMax <= 0 Alors
algoTermine = VRAI
typeSolution = ‘UNIQUE’
