<%-- 
    Document   : index
    Created on : 8/10/2018, 12:32:42 PM
    Author     : Estudiantes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Recuerda digitar todos los terminos del polinomio
            y digite 0 para los terminos </h1>
        Grado: <input type="text" id="grado" />
        <br/>
        <button onclick="generaInputs()" id="btnGeneraInputs">ok</button>
        <form action="Deriva" method="post">
            <div id='gradoP'>

            </div>
            <div id="coeficientes">
            </div>
            <br/>
            <div id='derivada1'>

            </div>
            <br/>
            <div id='derivada2'>

            </div>
            <div id="resultados">

            </div>
            <input type="hidden" name="JSONresultadosX" id="JSONresultadosX" value='${JSONresultadosX}' />
            <input type="hidden" name="JSONresultadosY" id="JSONresultadosY" value='${JSONresultadosY}' />
            <input type="hidden" name="JSONresultadosS" id="JSONresultadosS" value='${JSONresultadosS}' />
            <input type="hidden" name="JSONcoeficientes" id="JSONcoeficientes" value='${JSONcoeficientes}' />
            <input type="hidden" name="JSONderivada1" id="JSONderivada1" value='${JSONderivada1}' />
            <input type="hidden" name="JSONderivada2" id="JSONderivada2" value='${JSONderivada2}' />
            <input type="hidden" name="JSONgrado" id="JSONgrado" value='${JSONgrado}' />
        </form>
        <script>

            function generaInputs() {

                var grado = document.getElementById("grado").value;
                var htmlData = "";
                htmlData += "<input type=\"text\" name=\"grado\" value=\"" + grado + "\" hidden=\"\" />"
                for (var i = 0; i <= grado; i++) {
                    htmlData += "<input type=\"text\" name=\"p" + i + "\" />";
                }
                if (htmlData == "") {
                    document.getElementById("coeficientes").innerHTML = "grado de polinomio invalido";
                } else {
                    htmlData += "<input type=\"submit\" name=\"result\" value=\"enviar\"/>";
                    document.getElementById("coeficientes").innerHTML = htmlData;
                }
                return false;
            }

            function creaInput(padre, id, value) {
                var padre = document.getElementById(padre);
                var input = document.createElement('INPUT');

                input.value = value;
                input.id = id;
                input.type = "text"

                padre.appendChild(input);

            }

            function generaPagina() {
                var JSONresultadosX = JSON.parse(document.getElementById("JSONresultadosX").value);
                var JSONresultadosY = JSON.parse(document.getElementById("JSONresultadosY").value);
                var JSONresultadosS = JSON.parse(document.getElementById("JSONresultadosS").value);
                var JSONcoeficientes = JSON.parse(document.getElementById("JSONcoeficientes").value);
                var JSONderivada1 = JSON.parse(document.getElementById("JSONderivada1").value);
                var JSONderivada2 = JSON.parse(document.getElementById("JSONderivada2").value);
                var JSONgrado = JSON.parse(document.getElementById("JSONgrado").value);
                iteraciones(JSONcoeficientes, 'coeficientes');
                iteraciones(JSONderivada1, 'derivada1');
                iteraciones(JSONderivada2, 'derivada2');
                resultados(JSONresultadosX, JSONresultadosY, JSONresultadosS, 'resultados');
                document.getElementById('grado').value = JSONgrado['grado'];
            }
            generaPagina();

            function iteraciones(data, objetivo) {
                var e = document.createElement('span');
                e.innerHTML = objetivo + ":";
                document.getElementById(objetivo).appendChild(e);
                for (var p in data) {
                    if (data.hasOwnProperty(p)) {
                        creaInput(objetivo, p, data[p]);
                        //result += p + " , " + data[p] + "\n";
                    }
                }

            }

            function resultados(JSONresultadosX, JSONresultadosY, JSONresultadosS, objetivo) {
                for (var p in JSONresultadosX) {
                    if (JSONresultadosX.hasOwnProperty(p)) {
                        if (JSONresultadosX[p] != 'NaN') {
                            var e = document.createElement('span');
                            e.innerHTML = "<br/>";
                            document.getElementById(objetivo).appendChild(e);
                            creaInput(objetivo, p, JSONresultadosX[p]);
                            creaInput(objetivo, p, JSONresultadosY[p]);
                            creaInput(objetivo, p, JSONresultadosS[p]);

                        }
                    }
                }

            }

        </script>
    </body>
</html>
