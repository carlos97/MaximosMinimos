package derivar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Lopez
 */
public class Polinomio {

    public static double[] raices(double[] polinomio) {
        double[] raiz = new double[polinomio.length];
        int arrayPos = 0;
        List<Double> raices = new ArrayList<Double>();
        double ret = 0.0;
        double inc = 0.00001;
        double rad = -100.00001;
        int pot = 0;
        while (true) {
            ret = 0.0;
            pot = 0;
            for (int i = polinomio.length - 1; i >= 0; i--) {
                ret += Math.pow(rad, i) * polinomio[pot];
                pot++;
            }
            BigDecimal bd = new BigDecimal(ret);
            if (rad >= 0.76284) {
                int a = 0;
                a++;
            }
            //ret = bd.setScale(2,RoundingMode.DOWN).doubleValue();
            if (Double.valueOf(bd.toPlainString().substring(0, 5)) == 0.0) {
                if (validaRaiz(raices, rad)) {
                    //System.out.println("Raices: `"+rad);
                    raiz[arrayPos] = rad;
                    arrayPos++;
                    raices.add(rad);
                }
            }
            if (rad > 100) {
                break;
            }
            rad += inc;
        }
        for (int i = arrayPos; i < polinomio.length; i++) {
            raiz[i] = Double.NaN;
        }
        return raiz;
    }

    private static boolean validaRaiz(List<Double> raices, double valida) {
        boolean ret = true;
        for (int i = 0; i < raices.size(); i++) {
            if (String.valueOf(raices.get(i)).substring(0, 5).equals(String.valueOf(valida).substring(0, 5))) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static double resuelvePolinomio(double[] polinomio, double rad) {
        double ret = 0;
        int pot = 0;
        for (int i = polinomio.length - 1; i >= 0; i--) {
            ret += Math.pow(rad, i) * polinomio[pot];
            pot++;
        }
        return ret;
    }

}
