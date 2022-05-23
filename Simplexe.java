public class Simplexe {
	int nbVarDec ;
	int nbContraintes ;
	double[] fctObj;
	int[][] cont=new int[][];
	String [] TypeContrainte={(">="),("<="),("=")}; //tableau de chaine qui contient le type de chaque contrainte//
	double[] b;
  public double inverserContraintes(double[] b){
	for (int i=1;i<=nbContraintes;i++){
		if (b[i]<0){
			b[i]= -b[i];
			return b[i];
		}
	}
}
public int CompteurbVartot() {
	int nbVarTot=nbVarDec;
	for (int i=1;i<=nbContraintes;i++){
			if (TypeContrainte[i].equals(">=")){
			nbVarTot++; 
			}
			else if(TypeContrainte[i].equals("<=")) {
				nbVarTot=nbVarTot+2;
			}
			else {
				nbVarTot=nbVarTot+2;
 
			}
		}
	}
