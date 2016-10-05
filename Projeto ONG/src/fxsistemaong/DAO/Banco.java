/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author giovanni.sanches
 */
public class Banco {

    private String usuario, senha = "123456", servidor, nomeBanco;
    private int porta;
    //Variavies auxiliares
    private Connection conexao;
    
    public Banco() {
    }

    public Banco(String usuario, String senha, String servidor, String nomeBanco, int porta) {
        this.usuario = usuario;
        this.senha = senha;
        this.servidor = servidor;
        this.nomeBanco = nomeBanco;
        this.porta = porta;
    }

    /**
     * Devolve a conexão estabelecida no banco de dados
     *
     * @return connection
     */
    public Connection getConexao() {
        try {
            //carrega o driver
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + getServidor()+ ":"
                    + getPorta() + "/" + getNomeBanco();
            conexao = DriverManager.getConnection(url,getUsuario(), getSenha());
            System.out.println("Conexão com BD OK:");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro SQL:"
                    + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro Class:"
                    + ex.getMessage());
        }
        return conexao;
    }


    public void fechar(Connection Conexao) {
        try {
            Conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro:"
                    + ex.getMessage());
        }
    }    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
