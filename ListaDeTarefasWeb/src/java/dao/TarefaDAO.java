/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import model.Usuario;

/**
 *
 * @author Aluno
 */
public class TarefaDAO {
    
    public static void imprimirTarefas(Usuario u) {
        try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM cadastro.tarefa WHERE id_usuario =?");){
            stmt.setInt(1, u.getId());

            ResultSet rs = stmt.executeQuery();
            
//            Transformar isso para respost HTML

            if (rs.next()) {
                System.out.println("\n id: " + rs.getInt("id"));
                System.out.println("titulo: " + rs.getString("titulo"));
                System.out.println("finalizada: " + rs.getBoolean("finalizada"));
            } else {
                System.out.println("Nao ha tarefas para exibir!");
            }
            while (rs.next()) {
                System.out.println("\n id: " + rs.getInt("id"));
                System.out.println("titulo: " + rs.getString("titulo"));
                System.out.println("finalizada: " + rs.getBoolean("finalizada"));
            }

            System.out.println("pressione enter para continuar");
            Scanner scan = new Scanner(System.in);
            scan.nextLine();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void imprimirTarefas(int id, int f) {
//        try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM cadastro.tarefa WHERE id_usuario =?" /*and finalizada =?*/);){
//            stmt.setInt(1, id);
////            stmt.setInt(2, f);
//
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                System.out.println("\n id: " + rs.getInt("id"));
//                System.out.println("titulo: " + rs.getString("titulo"));
//                System.out.println("finalizada: " + rs.getBoolean("finalizada"));
//            } else {
//                System.out.println("Nao ha tarefas para exibir!");
//            }
//            while (rs.next()) {
//                System.out.println("\n id: " + rs.getInt("id"));
//                System.out.println("titulo: " + rs.getString("titulo"));
//                System.out.println("finalizada: " + rs.getBoolean("finalizada"));
//            }
//
//            System.out.println("pressione enter para continuar");
//            Scanner scan = new Scanner(System.in);
//            scan.nextLine();
//            conexao.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void inserirTarefa(Usuario u) {
        
//        TRANSFORMAR EM HTML

            Scanner scan = new Scanner(System.in);
            try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("INSERT INTO cadastro.tarefa (titulo, finalizada,id_usuario) VALUES (?,?,?)");){
            System.out.println("Digite o titulo: ");

            stmt.setString(1, scan.nextLine());
            stmt.setInt(2, 0);
            stmt.setInt(3, u.getId());

            int up = stmt.executeUpdate();

            if (up > 0) {
                System.out.println("Tarefa adicionada com sucesso!");
            } else {
                System.out.println("Algo deu errado");
            }
            System.out.println("Pressione enter para voltar para Home Page");

            scan.nextLine();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static boolean finalizarTarefa(int id) {
        
//        TRANSFORMAR EM RESPOSTA HTML
        Scanner scan = new Scanner(System.in);
        try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("Update cadastro.tarefa SET finalizada = ? WHERE id_usuario = ? and id = ? ");){

            stmt.setInt(1, 1);
            stmt.setInt(2, id);
            stmt.setInt(3, scan.nextInt());
            scan.nextLine();

            int up = stmt.executeUpdate();

            if (up > 0) {
                System.out.println("Tarefa finalizada com sucesso");
                System.out.println("pressione enter para continuar");
                scan.nextLine();
                conexao.close();
                return true;
            } else {
                System.out.println("Algo deu errado!");
                
            }
            System.out.println("pressione enter para continuar");
            scan.nextLine();

            conexao.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean removerTarefa(int id) {
        
//        TRANSFORMAR EM RESPOSTA HTML
        Scanner scan = new Scanner(System.in);
            try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("DELETE FROM cadastro.tarefa WHERE id_usuario = ? and id = ?");){
            stmt.setInt(1, id);
            stmt.setInt(2, scan.nextInt());
            scan.nextLine();
            

            int up = stmt.executeUpdate();

            if (up > 0) {
                System.out.println("Tarefa deletada com sucesso!");
                System.out.println("pressione enter para continuar");
                scan.nextLine();
                conexao.close();
                return true;
            } else {
                System.out.println("Algo deu errado! Tente outra tarefa");
            }

            System.out.println("pressione enter para continuar");
            scan.nextLine();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
