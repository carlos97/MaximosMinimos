package derivar;

public class Verificar {

    double a, b, c, d;
    double delta0, delta1, delta;

    public Verificar(double coefi[]) {
        a = coefi[0];
        b = coefi[1];
        c = coefi[2];
        d = coefi[3];
        //----
        delta0 = ((b * b) - (3 * a * c));
        delta1 = ((2 * b * b * b) - (9 * a * b * c) + (27 * a * a * d));
        delta = ((delta1 * delta1) - (4 * delta0 * delta0 * delta0)) / (-27 * a * a);
        if (delta > 0) {
            System.out.println("\nEl polinomio tiene soluciones reales, si se puede hallar los maximos y minimos");
        }
        if (delta == 0) {
            System.out.println("\nEl polinomio tiene raices complejas, no se puede hacer la operacion");  
        }
        if (delta < 0) {
            System.out.println("\nEl polinomio solo tiene raices complejas, no se puede hacer la operacion");
        }
    }
}
