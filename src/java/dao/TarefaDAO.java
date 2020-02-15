
package dao;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Tarefa;
import model.Usuario;

/**
 *
 * @author Aluno
 */
public class TarefaDAO {
    
    public static ArrayList listarTarefas(Usuario u) {
        try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM cadastro.tarefa WHERE id_usuario =?");){
            ArrayList<Tarefa> tarefas = new ArrayList();
            
            stmt.setInt(1, u.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setFinalizada(rs.getBoolean("finalizada"));
                tarefas.add(tarefa);
            } else 
                return null;
            
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setFinalizada(rs.getBoolean("finalizada"));
                tarefas.add(tarefa);
            }

            return tarefas;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int inserirTarefa(Usuario u, String titulo) {

            try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("INSERT INTO cadastro.tarefa (titulo, finalizada,id_usuario) VALUES (?,?,?)");){

            stmt.setString(1, titulo);
            stmt.setInt(2, 0);
            stmt.setInt(3, u.getId());

            int up = stmt.executeUpdate();
            return up;      

        } catch (SQLException e) {
            e.printStackTrace();

        }
            return 0;
    }
    

    public static int finalizarTarefa(Usuario u,int id) {
        
        try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("Update cadastro.tarefa SET finalizada = ? WHERE id_usuario = ? and id = ? ");){

            stmt.setInt(1, 1);
            stmt.setInt(2, u.getId());
            stmt.setInt(3, id);
            

            int up = stmt.executeUpdate();

                return up;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int removerTarefa(Usuario u, int id) {

            try (Connection conexao = Conexao.abrirConexao();PreparedStatement stmt = conexao.prepareStatement("DELETE FROM cadastro.tarefa WHERE id_usuario = ? and id = ?");){
            stmt.setInt(1, u.getId());
            stmt.setInt(2, id);

            int up = stmt.executeUpdate();

            return up;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
