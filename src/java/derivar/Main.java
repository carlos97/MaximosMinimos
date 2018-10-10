package derivar;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static double[] funcion;
    static double[] funcion1;
    static double[] funcion2;
    static double[] raices;
    static double[] op;
    static double[] evaluar;
    static int grado;
    static Derivada d;

    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("Digite el grado del polinomio: ");
        Scanner s = new Scanner(System.in);
        grado = Integer.parseInt(s.nextLine());
        funcion = new double[grado + 1];
        for (int i = 0; i <= grado; i++) {
            System.out.println("Digite el valor del coeficiente de x^" + (grado - i));
            funcion[i] = Double.parseDouble(s.nextLine());
        }
        funcion1 = funcion; // Funcion1 es para guardar la funcion original y no se pierda
        d = new Derivada(grado, funcion);
        funcion = d.Derivar(1); //Funcion derivada
        funcion2 = funcion;
        if (funcion[0] < 45 && funcion[0] > -45) {
            Graeffe f = new Graeffe(funcion);
            raices = f.mostrarRaices();
            funcion2 = d.Derivar(1); //Funcion 2 es la segunda derivada

            System.out.println("\nLa primera derivada es: " + Arrays.toString(funcion));
            System.out.println("La segunda derivada es: " + Arrays.toString(funcion2));
            System.out.println("Las raices de la funcion son:" + Arrays.toString(raices));
            //-----------------------------------------------------------------------------
            for (int j = 0; j < raices.length; j++) {
                op = new double[raices.length];
                for (int i = 0; i < funcion2.length - 1; i++) {
                    if (i == 0) {
                        op[j] += funcion2[i] * Math.pow(raices[j], grado);
                    } else {
                        op[j] += funcion2[i] * raices[j];
                    }
                }
                double[] op1 = new double[raices.length];
                op1[j] = op[j] + funcion2[funcion2.length - 1];
                evaluar = new double[raices.length];

                String[] raiz1 = new String[raices.length];
                if (op1[j] <= 0) {
                    raiz1[j] = String.valueOf(raices[j]) + " es un Maximo";
                    System.out.println("\n" + raiz1[j]);

                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    System.out.println("Maximo (" + raices[j] + "," + evaluar[j] + ")");
                } else {
                    raiz1[j] = String.valueOf(raices[j] + " es un Minimo");
                    System.out.println("\n" + raiz1[j]);
                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    System.out.println("Minimo (" + raices[j] + "," + evaluar[j] + ")");
                }
            }
            //---------------------------------------------------------------------------
            if (grado == 1) {
                System.out.println("No se encontraron raices, no hay maximos ni minimos");
            }
        } else {
            Polinomio p = new Polinomio();
            raices = Polinomio.raices(funcion);
            funcion2 = d.Derivar(1); //Funcion 2 es la segunda derivada

            System.out.println("\nLa primera derivada es: " + Arrays.toString(funcion));
            System.out.println("La segunda derivada es: " + Arrays.toString(funcion2));
            System.out.println("Las raices de la funcion son:" + Arrays.toString(raices));
            //-----------------------------------------------------------------------------
            for (int j = 0; j < raices.length; j++) {
                op = new double[raices.length];
                for (int i = 0; i < funcion2.length - 1; i++) {
                    if (i == 0) {
                        op[j] += funcion2[i] * Math.pow(raices[j], grado);
                    } else {
                        op[j] += funcion2[i] * raices[j];
                    }
                }
                double[] op1 = new double[raices.length];
                op1[j] = op[j] + funcion2[funcion2.length - 1];
                evaluar = new double[raices.length];

                String[] raiz1 = new String[raices.length];
                if (op1[j] <= 0) {
                    raiz1[j] = String.valueOf(raices[j]) + " es un Maximo";
                    System.out.println("\n" + raiz1[j]);

                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    System.out.println("Maximo (" + raices[j] + "," + evaluar[j] + ")");
                } else {
                    raiz1[j] = String.valueOf(raices[j] + " es un Minimo");
                    System.out.println("\n" + raiz1[j]);
                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    System.out.println("Minimo (" + raices[j] + "," + evaluar[j] + ")");
                }
            }
            //---------------------------------------------------------------------------
            if (grado == 1) {
                System.out.println("No se encontraron raices, no hay maximos ni minimos");
            }
        }
    }
}
