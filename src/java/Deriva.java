/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import derivar.Derivada;
import derivar.Graeffe;
import derivar.Polinomio;
import derivar.Verificar;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(urlPatterns = {"/Deriva"})
public class Deriva extends HttpServlet {

    static double[] funcion;
    static double[] funcion1;
    static double[] funcion2;
    static double[] raices;
    static double[] op;
    static double[] evaluar;
    static int grado;
    static Derivada d;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } finally {
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String JSONresultadosX="{";
            String JSONresultadosY="{";
            String JSONresultadosS="{";
            String JSONgrado="{";
            String JSONcoeficientes="{";
            String JSONderivada1="{";
            String JSONderivada2="{";
            //System.out.println("Digite el grado del polinomio: ");
            //grado = Integer.parseInt(s.nextLine());
            grado = Integer.parseInt(request.getParameter("grado"));
            JSONgrado = "{\"grado\":\""+request.getParameter("grado")+"\"}";
            funcion = new double[grado + 1];
            for (int i = 0; i < grado; i++) {
                //System.out.println("Digite el valor del coeficiente de x^" + (grado - i));
                //funcion[i] = Double.parseDouble(s.nextLine());
                funcion[i] = Double.parseDouble(request.getParameter("p" + i));
                JSONcoeficientes += "\"p"+i+"\":\""+request.getParameter("p" + i)+"\",";
            }
            JSONcoeficientes += "\"p"+grado+"\":\""+request.getParameter("p" + grado)+"\",";
            JSONcoeficientes = JSONcoeficientes.substring(0, JSONcoeficientes.length() - 1) + "}";
            funcion1 = funcion; // Funcion1 es para guardar la funcion original y no se pierda
            d = new Derivada(grado, funcion);
            funcion = d.Derivar(1); //Funcion derivada
            for(int i=0;i<funcion.length;i++){
                JSONderivada1 += "\"d"+i+"\":\""+funcion[i]+"\",";
            }
            JSONderivada1 = JSONderivada1.substring(0, JSONderivada1.length() - 1) + "}";
            funcion2 = funcion;
            Polinomio p = new Polinomio();
            raices = Polinomio.raices(funcion);
            funcion2 = d.Derivar(1); //Funcion 2 es la segunda derivada
            for(int i=0;i<funcion2.length;i++){
                JSONderivada2 += "\"d"+i+"\":\""+funcion2[i]+"\",";
            }
            JSONderivada2 = JSONderivada2.substring(0, JSONderivada2.length() - 1) + "}";
            //-----------------------------------------------------------------------------
            for (int j = 0; j < raices.length; j++) {
                if (Double.NaN == raices[j]) {
                    continue;
                }
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
                    raiz1[j] = String.valueOf(raices[j]) + " es un Maximo<br/>";
//                            out.println("<br/>" + raiz1[j]);
                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    JSONresultadosX+="\"r"+j+"\":\""+raices[j]+"\",";
                    JSONresultadosY+="\"r"+j+"\":\""+evaluar[j]+"\",";
                    JSONresultadosS+="\"r"+j+"\":\"Maximo\",";
//                    request.setAttribute("x" + j, String.valueOf(raices[j]));
//                    request.setAttribute("r" + j, String.valueOf(evaluar[j]));
//                    request.setAttribute("m" + j, "Maximo");
//                            out.println("<br/>Maximo (" + raices[j] + "," + evaluar[j] + ")<br/>");
                } else {
                    
                    raiz1[j] = String.valueOf(raices[j] + " es un Minimo<br/>");
//                            out.println("<br/>" + raiz1[j]);
                    evaluar[j] = Polinomio.resuelvePolinomio(funcion1, raices[j]);
                    JSONresultadosX+="\"r"+j+"\":\""+raices[j]+"\",";
                    JSONresultadosY+="\"r"+j+"\":\""+evaluar[j]+"\",";
                    JSONresultadosS+="\"r"+j+"\":\"Minimo\",";
//                    request.setAttribute("x" + j, String.valueOf(raices[j]));
//                    request.setAttribute("r" + j, String.valueOf(evaluar[j]));
//                    request.setAttribute("m" + j, "Minimo");
//                            out.println("<br/>Minimo (" + raices[j] + "," + evaluar[j] + ")<br/>");
                }
            }
            JSONresultadosX = JSONresultadosX.substring(0, JSONresultadosX.length() - 1) + "}";
            JSONresultadosY = JSONresultadosY.substring(0, JSONresultadosY.length() - 1) + "}";
            JSONresultadosS = JSONresultadosS.substring(0, JSONresultadosS.length() - 1) + "}";
            
            request.setAttribute("JSONresultadosX", JSONresultadosX);
            request.setAttribute("JSONresultadosY", JSONresultadosY);
            request.setAttribute("JSONresultadosS", JSONresultadosS);
            request.setAttribute("JSONcoeficientes", JSONcoeficientes);
            request.setAttribute("JSONderivada1", JSONderivada1);
            request.setAttribute("JSONderivada2", JSONderivada2);
            request.setAttribute("JSONgrado", JSONgrado);
            
            //---------------------------------------------------------------------------
            if (grado == 1) {
                //out.println("<br/>No se encontraron raices, no hay maximos ni minimos<br/>");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
