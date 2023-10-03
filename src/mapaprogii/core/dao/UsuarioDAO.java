
package mapaprogii.core.dao;

import mapaprogii.core.dao.conexao.ConexaoJDBC;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import mapaprogii.core.entity.Usuario;

/**
 * Atividade MAPA - Programação II
 * @author Danilo dos Reis Dutra Gomes
 */
public class UsuarioDAO {
    
    public void cadastrarUsuario(Usuario usuario) {
        
        String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, EMAIL) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        
        try{
            ps = ConexaoJDBC.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            
            ps.execute();
                       
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Houve um erro ao tentar inserir no banco de dados!");
        }finally {
            try{
                ps.close();
            }catch(SQLException e){}; 
            
            
        }
        
    }
    
    public Usuario autenticarUsuario(String login, String senha) {
        
        String sql = "SELECT ID, NOME, LOGIN, SENHA, EMAIL FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = ConexaoJDBC.getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
                       
            if(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("NOME"));
                usuario.setLogin(rs.getString("LOGIN"));
                usuario.setSenha(rs.getString("SENHA"));
                usuario.setEmail(rs.getString("EMAIL"));
                
                return usuario;
            }
            
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ERRO: "+ e);
        }finally {
            try{
                ps.close();
            }catch(SQLException e){}; 
            try{
                rs.close();
            }catch(SQLException e){}; 
            
        }
        
        return null;    
        
        
    } 
    
}
