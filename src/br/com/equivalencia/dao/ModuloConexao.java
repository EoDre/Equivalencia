
package br.com.equivalencia.dao;
import java.sql.*;
/**
 *
 * @author asilva
 */
public class ModuloConexao {
    
    public static Connection conector(){
        
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://10.145.41.252:3306/equivalencia";
        String user = "zequinha";
        String password = "root";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }
    
}
