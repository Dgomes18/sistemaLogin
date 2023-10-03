
package mapaprogii.core.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Atividade MAPA - Programação II
 * @author Danilo dos Reis Dutra Gomes
 */
public class ConexaoJDBC {
    
    private static final String URL = "jdbc:mysql://localhost:3306/mapa";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    private static Connection conn;
    
    public static Connection getConexao(){
        try{
            if(conn == null){
                conn = DriverManager.getConnection(URL,USER,PASSWORD);
                return conn;
            }else{
                return conn;
            }            
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Houve um erro ao tentar acessar o banco de dados!");
            return null;
            
        }
    }
    
    
    
        
}
