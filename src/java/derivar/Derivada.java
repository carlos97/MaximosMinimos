package derivar;

public class Derivada {
    public int grado;
    public double []coeficiente;
    
    public Derivada(int grado, double []coeficiente) {
        this.grado = grado;
        this.coeficiente = coeficiente;
    }
    //Método Derivada K-ésima
    public double [] Derivar(int k){
        int gradoDerivada = 0;
        double []coeficienteDerivada = {0};
        for(int i=1;i<=k;i++) {
            gradoDerivada = this.grado-1;
            coeficienteDerivada = new double[this.grado];
            int subindice=0;
            for(int j=1; j<=this.grado; j++) {
                coeficienteDerivada[subindice] = ((grado+1)-j)*this.coeficiente[j-1];
                subindice++;
            }
            this.grado = gradoDerivada;
            this.coeficiente = coeficienteDerivada;
        }
        return coeficiente;
    }
    
}
