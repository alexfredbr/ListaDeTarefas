<%-- 
    Document   : logado
    Created on : 11/01/2020, 13:18:29
    Author     : Aluno
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Lista de Tarefas</title>
    </head>
    <body>
        <h2>Bem Vindo ${Usuario.email} </h2>
        <!--Listar Todas as tarefas-->
        <!--Adicionar nova tarefa-->
        <!--Deletar uma tarefa-->    
        
        <!--Alterar Email e senha-->
        <button id="toggleBtn" onclick="toggleForm();">Alterar email/senha</button>
        <form id="altera" method="POST" action="home" style="visibility: hidden">
            
            <p>
                <label>E-mail:</label>
                <input type="email" name="emailNovo"/>
            </p>
            <p>
                <label>Senha:</label>
                <input type="password" name="senhaNova"/>
            </p>
            <input type="submit" name="alterarDados" value="Alterar">

        </form>
        
        <!--Encerrar Sessão-->
        <form  method="POST" action="logout" > 
            <input type="submit" value="Encerrar Sessão">
        </form>
        <!--JavaScript-->
        <script>
            var openForm = false;
            function toggleForm() {
                var form = document.getElementById("altera");
                var btn = document.getElementById("toggleBtn");
                
                openForm= !openForm;
                
                if (openForm){
                    btn.innerHTML = "Esconder Formulário"; 
                    form.style["visibility"] = "visible";
                }else {
                    btn.innerHTML = "Alterar Dados";
                    form.style["visibility"] = "hidden";
                }
            }
            
           
        </script>
    </body>
</html>
