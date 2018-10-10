package derivar;

import java.util.Arrays;

public class Graeffe {

    public int n;                                      //grado del polinomio
    public double[] raicesReales;
    public int numReales;                              //número de raíces reales
    public int numComplejas;                           //número de raíces complejas
    private double[][] a;                       //tabla de los coeficientes resultantes de elevar el polinomio al cuadrado
    private int pot2 = 1;                         //potencia de dos
    private int m;                              //número de iteracción
    private final int MAX_ITER = 10;                      //máximo número de veces que se eleva al cuadrado
    private static final double CERO = 0.0001;
    private double[] moduloComplejas = new double[2];

    public Graeffe(double[] coef) {
        n = coef.length - 1;                        //grado del polinomio
        System.out.println("Los coeficientes que llegan son: " + Arrays.toString(coef));
        raicesReales = new double[n];             // un polinomio de grado n tiene como máximo n raíces reales.
        a = new double[MAX_ITER][n + 1];
//la primera fila de la tabla guarda los coeficientes del polinomio
        for (int j = 0; j < n + 1; j++) {
            a[0][j] = coef[j];
        }
        for (int m = 1; m < MAX_ITER; m++) {
            for (int j = 0; j < n + 1; j++) {
                a[m][j] = 0.0;
            }
        }
        numReales = 0;
        numComplejas = 0;
    }

    private void tabla() {
        int k, l, signo;
//divide el polinomio por el primer coeficiente, las raíces no cambian
        for (int i = 1; i < n + 1; i++) {
            a[0][i] /= a[0][0];
        }
        a[0][0] = 1.0;
        m = 1;
        exterior:
        do {
//cuadrados
            for (int i = 0; i < n + 1; i++) {
                a[m][i] = a[m - 1][i] * a[m - 1][i];
                if (Double.isInfinite(a[m][i])) {
                    break exterior;
                }
            }
//dobles productos
            for (int i = 1; i < n; i++) {
                for (int s = 1; s < n / 2 + 1; s++) {
                    k = i - s;
                    l = i + s;
                    if ((k < 0) || (l > n)) {
                        break;   //términos simétricos
                    }
                    signo = (s % 2 == 0) ? +1 : -1;
                    a[m][i] += signo * 2 * a[m - 1][k] * a[m - 1][l];
                    if (Double.isInfinite(a[m][i])) {
                        break exterior;
                    }
                }
            }
            m++;
        } while (m < MAX_ITER);

        m--;
//potencia de m de 2
        pot2 = 1;
        for (int i = 1; i <= m; i++) {
            pot2 *= 2;
        }
    }
//valor de un polinomio para una variabel real

    public double valorPolinomio(double x) {
        double y = 0.0;
//sucesivas potencias de x, se puede utilizar tambien la funcion Math.pow
        double[] pot_x = new double[n + 1];
        pot_x[0] = 1.0;
        for (int i = 1; i < n + 1; i++) {
            pot_x[i] = pot_x[i - 1] * x;
        }
//valores de los sucesivos términos
        for (int i = 0; i < n + 1; i++) {
            y += a[0][i] * pot_x[n - i];
        }
        return y;
    }

    private void raizRealSimple(int j) {
//valor absoluto de la raíz
        // System.out.println("Raiz simple");
        double logaritmo = (Math.log(a[m][j]) - Math.log(a[m][j - 1])) / pot2;
        double raiz = Math.exp(logaritmo);
//determinación del signo, y1 o y2 tienen que ser casi cero

        raicesReales[numReales] = (Math.abs(valorPolinomio(raiz)) < Math.abs(valorPolinomio(-raiz))) ? raiz : -raiz;
        numReales++;
    }

    private void raizRealDoble(int j) {
//valor absoluto de la raíz
        double logaritmo = (Math.log(a[m][j + 1]) - Math.log(a[m][j - 1])) / (2 * pot2);
        double raiz = Math.exp(logaritmo);
        boolean bPositiva = false, bNegativa = false;
        if (Math.abs(valorPolinomio(raiz)) < CERO) {
            raicesReales[numReales] = raiz;
            numReales++;
            bPositiva = true;
        }
        if (Math.abs(valorPolinomio(-raiz)) < CERO) {
            raicesReales[numReales] = -raiz;
            numReales++;
            bNegativa = true;
        }
        if (bPositiva && !bNegativa) {
            raicesReales[numReales] = raiz;
            numReales++;
        }
        if (!bPositiva && bNegativa) {
            raicesReales[numReales] = -raiz;
            numReales++;
        }
    }

    private boolean cambiaSigno(int j) {
        double logaritmo;
        for (int k = 2; k <= m; k++) {
            if (a[k][j] > 0) {
                continue;
            }
            numComplejas++;
//máximo dos raíces complejas, 4 contando sus respectivas conjugadas
            if (numComplejas < 3) {
                logaritmo = (Math.log(a[m][j + 1]) - Math.log(a[m][j - 1])) / (2 * pot2);
                moduloComplejas[numComplejas - 1] = Math.exp(logaritmo);
                return true;
            }
        }
        return false;
    }

    public void hallarRaices() {
        tabla();
//el pimer coeficiente a[m][0] es siempre 1
        for (int i = 1; i < n + 1; i++) {      //i es la raíz
            if (cambiaSigno(i)) {
//raíz compleja y su correspondiente conjugada
                i++;
                continue;
            }
//raíces simple y dobles
            double logaritmo = Math.log(a[m][i]) - 2 * Math.log(a[m - 1][i]);
            if (Math.abs(logaritmo) < CERO) {
                raizRealSimple(i);
            } else {
                raizRealDoble(i);
                i++;
                continue;
            }
        }
        if (numComplejas == 1) {
        }
        if (numComplejas == 2) {
        }
    }

    public double[] mostrarRaices() {
        hallarRaices();
        System.out.println("Raíces reales");
        for (int i = 0; i < numReales; i++) {
            System.out.println((double) Math.round(raicesReales[i] * 100) / 100 + " ---> " + valorPolinomio(raicesReales[i]));
        }
        return raicesReales;
    }
}
