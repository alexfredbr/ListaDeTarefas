<%-- 
    Document   : logado
    Created on : 11/01/2020, 13:18:29
    Author     : Aluno
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Lista de Tarefas</title>
    </head>
    <body>
        <h2>Bem Vindo ${Usuario.email} </h2>
        <p>${atualizado}</p>
        <form id="form" method="POST" action="home">
            <input type="hidden" name="acao" value="listar"/>
            <input type="submit" value="Listar todas tarefas"/>
        </form>
        <!--Listar Todas as tarefas-->


        <c:forEach items="${listado}" var="tarefa">
            ${tarefa.titulo} <c:if test = "${tarefa.finalizada == true}">finalizada</c:if>
            <c:if test = "${tarefa.finalizada == false}">aberta</c:if>
                <br>
        </c:forEach>

        <!--Adicionar nova tarefa-->
        <button id="toggleBtn2" onclick="toggleForm2();">Adicionar nova tarefa</button>
        <form id="adiciona" method="POST" action="home" style="visibility: hidden">
            <input type="hidden" name="acao" value="alterar" />
            <p>
                <label>Titulo:</label>
                <input  name="tituloTarefa"/>
            </p>
            <input type="submit" value="Adicionar"/>
        </form>
        <!--Deletar uma tarefa-->    


        <!--Alterar Email e senha-->
        <button id="toggleBtn" onclick="toggleForm();">Alterar email/senha</button>
        <form id="altera" method="POST" action="home" style="visibility: hidden">

            <input type="hidden" name="acao" value="alterar" />
            <p>
                <label>Novo e-mail:</label>
                <input type="email" name="novoEmail"/>
            </p>
            <p>
                <label>Nova senha:</label>
                <input type="password" name="novaSenha"/>
                ${erroSenha}
            </p>
            <input type="submit" value="Alterar dados"/>

        </form>

        <!--Encerrar Sessão-->
        <form  method="POST" action="logout" > 
            <input type="submit" value="Encerrar Sessão">
        </form>
        <!--JavaScript-->
        <script>
            var openForm = false;
            function toggleForm() {
                var altera = document.getElementById("altera");
                var btn = document.getElementById("toggleBtn");

                openForm = !openForm;

                if (openForm) {
                    btn.innerHTML = "Esconder Formulário";
                    altera.style["visibility"] = "visible";
                } else {
                    btn.innerHTML = "Alterar Dados";
                    altera.style["visibility"] = "hidden";
                }
            }
            var openForm2 = false;
            
            //depois descobrir como fazer 1 função só
            function toggleForm2() {
                var adicionar = document.getElementById("adiciona");
                var btn2 = document.getElementById("toggleBtn2");

                openForm2 = !openForm2;

                if (openForm2) {
                    btn2.innerHTML = "Esconder Formulário";
                    adicionar.style["visibility"] = "visible";
                } else {
                    btn2.innerHTML = "Adicionar nova tarefa";
                    adicionar.style["visibility"] = "hidden";
                }
            }

            var form = document.getElementById("form");
            var acao = document.getElementById("acao");
            function listar() {
                acao.value = "listar";
                form.submit();
            }


        </script>
    </body>
</html>
