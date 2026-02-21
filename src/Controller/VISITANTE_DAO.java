package Controller;

import View.CadastrarVisitante_GUI;
import static View.Inicio_GUI.login_txt;
import static View.Inicio_GUI.senha_txt;
import View.Menu_GUI;
import static View.CadastrarVisitante_GUI.*;
import View.VisualizarVisitantes_GUI;
import static View.VisualizarVisitantes_GUI.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class VISITANTE_DAO {

    static JFileChooser arquivo = new JFileChooser();
    public static boolean conf = false;
    public static String caminhoArquivo = " ";
    static long cpf;
    static String nome;
    static String empresa;
    static String motivo;
    static String colaborador;
    static String dataSaida;
    static String horaSaida;
    static String fotoEnd;

    static String url = "jdbc:mysql://localhost:3306/estacionamento"; // enderço do BD
    static String username = "root";        //nome de um usuário de seu BD
    static String password = "123";

    public static void validar() {
        //-- inicio
        String login = login_txt.getText();
        String senha = senha_txt.getText();

        login = login.toLowerCase();
        System.out.println(login + " " + senha);
        int cont = 0;

        if (cont == 2) {
            JOptionPane.showMessageDialog(null, "ERRO 001\nExcesso de tentativas\nFechando o Sistema", "ERRO", 2);
            System.exit(0);

        } else if (login.isEmpty() || senha.equals("")) {
            JOptionPane.showMessageDialog(null, "ERRO 002\nCampo em branco\nTente Novamente", "ERRO", 2);
            login_txt.setText("");
            senha_txt.setText("");
        } else if (login.equals("admin@gmail.com") && senha.equals("admin123")) {
            new Menu_GUI().setVisible(true);
            conf = true;
        } else {
            JOptionPane.showMessageDialog(null, "ERRO 003\nUsuário ou senha inválido\nTente Novamente", "ERRO", 2);
            login_txt.setText("");
            senha_txt.setText("");
        }
        //-- fim 
    }

    public static void imagem() {
        int retorno = arquivo.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
        } else {
        }
    }

    public static void cadastrarVisitante() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        nome = nome_txt.getText();
        empresa = empresa_txt.getText();
        cpf = Long.valueOf(cpf_txt.getText());
        motivo = motivo_txt.getText();
        colaborador = colaborador_txt.getText();
        fotoEnd = arquivo.getSelectedFile().getAbsolutePath();
        fotoEnd = fotoEnd.replace('\\', '/');

        Model.Conecta_DB.carregaDriver();

        try {
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarVisitante_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sql = "INSERT INTO VISITANTE VALUES ('" + nome + "', " + cpf + ", '" + empresa + "', '" + motivo + "', '" + colaborador + "', '" + fotoEnd + "', " + "NULL" + ")";
            try {
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);

                int linhasAfetadas = inserir.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "\nInserção realizada com sucesso!!!\n", "Sucesso!", 1);
                    nome_txt.setText("");
                    cpf_txt.setText("");
                    empresa_txt.setText("");
                    motivo_txt.setText("");
                    colaborador_txt.setText("");
                    caminhoArquivo = "";
                }
            } catch (Exception ex) {
                System.out.println("INSERT INTO VISITANTE VALUES ('" + nome + "', " + cpf + ", '" + empresa + "', '" + motivo + "', '" + colaborador + "', '" + fotoEnd + "', " + "NULL" + ")");
                JOptionPane.showMessageDialog(null, "\nErro na inserção! Certifique-se que o CPF já não exista no banco de dados", "ERRO NA INSERÇÃO!", 0);
            }

        } catch (NumberFormatException erro) {
            // Tratamento de erro caso o usuario não digite o cpf corretamente 
            JOptionPane.showMessageDialog(null, "Digite os dados do CPF corretamente", "ERRO", 0);
            cpf_txt.setText("");
        }
        // ---> Fim
    }

    public static void consultarVisitante() {
        // ---> Início
        try {     //Iniciando o possivel tratamento de erros
            //Declarando a variavel código
            long referencia = Long.valueOf(cpf2_txt.getText());
            Model.Conecta_DB.carregaDriver();

            try {// Tratamento de erro para a conexao
                // Declarando  a variavel de conexão con
                // e estabelendo a conexão
                Connection con = null;

                try {
                    con = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(VisualizarVisitantes_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Declarando uma string com o comando mySQL para consulta
                String sql = "SELECT cli_nome, cli_empresa, cli_motivo, cli_colaborador, cli_foto FROM VISITANTE where cli_cpf = " + referencia;
                // Criando variavel que executara o comando da string sql
                Statement stm = (Statement) con.createStatement();
                try//Tratamento de erro da consulta
                { //Criando variavel que exibira os resultados
                    //Executando o comando da string sql na variavel rs
                    ResultSet rs = stm.executeQuery(sql);

                    int i = 0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        String nomeConsulta = rs.getString("cli_nome");
                        String empresaConsulta = rs.getString("cli_empresa");
                        String motivoConsulta = rs.getString("cli_motivo");
                        String colaboradorConsulta = rs.getString("cli_colaborador");
                        String fotoConsulta = rs.getString("cli_foto");

                        i++;

                        //JOptionPane.showMessageDialog(null,"Nome: " + nome + "\nEmail: " +telefone + "\nTelefone: " +telefone, "Resultado",-1);
                        nome2_txt.setText(String.valueOf(nomeConsulta));
                        empresa2_txt.setText(String.valueOf(empresaConsulta));
                        motivo2_txt.setText(String.valueOf(motivoConsulta));
                        colaborador2_txt.setText(String.valueOf(colaboradorConsulta));
                        foto2_img.setIcon(new ImageIcon(String.valueOf(fotoConsulta)));;
                        System.out.println(fotoConsulta);
                    }

                    if (i == 0) { // Verificando se ha dados cadastrados atraves da variavel i
                        JOptionPane.showMessageDialog(null, "Usuário não cadastrado", "Resultado", -1);
                    }

                } catch (Exception ex) { // Consulta mal sucedida
                    JOptionPane.showMessageDialog(null, "\nErro ao consultar!", "ERRO", 0);
                }

            } catch (SQLException ex) {
                // Conexão com servidor mal sucedida
                JOptionPane.showMessageDialog(null, "Erro ao conectar com o servidor", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) {
            // Código fora do formato
            JOptionPane.showMessageDialog(null, "Digite o CPF do usuário corretamante", "ERRO", 0);
            cpf2_txt.setText("");
            // ---> Fim
        }

        // ---> Fim
    }

    public static void alterarVisitante() {
        // ---> Início
        String nomeAltera = nome2_txt.getText();
        String empresaAltera = empresa2_txt.getText();
        String motivoAltera = motivo2_txt.getText();
        String colaboradorAltera = colaborador2_txt.getText();

        Model.Conecta_DB.carregaDriver();

        try {
            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(VisualizarVisitantes_GUI.class.getName()).log(Level.SEVERE, null, ex);

            }
            String sql = "UPDATE visitante SET cli_nome='" + nomeAltera + "',cli_empresa='" + empresaAltera + "',cli_motivo='" + motivoAltera + "',cli_colaborador='" + colaboradorAltera + "' WHERE cli_cpf=" + cpf2_txt.getText();

            try {
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute(); // Executando a inserção

                JOptionPane.showMessageDialog(null, "\nAtualização realizada com sucesso!!!\n", "", -1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\nErro na atualização!", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) {
            // Tratamento de erro caso o usuario não digite o telefone corretamente
            JOptionPane.showMessageDialog(null, "Digite os dados corretamente", "ERRO", 0);
        }

        // ---> Fim
    }

    public static void excluirVisitante() {
        // ---> Início
        Model.Conecta_DB.carregaDriver();

        // int codigo = Integer.valueOf(cod2_txt.getText()); // Recebendo o código
        try {
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(VisualizarVisitantes_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Criando String com comando SQL para exclusão
            String sql = "DELETE FROM VISITANTE WHERE cli_cpf = " + cpf2_txt.getText();;

            try // Tratamento de erros para exclusão
            {// Criando Variavel para executar a ação
                PreparedStatement excluir = (PreparedStatement) con.prepareStatement(sql);
                excluir.execute();// Executando a exclusão
                
                JOptionPane.showMessageDialog(null, "\nExclusão realizada com sucesso!!!\n", "", -1);
                cpf2_txt.setText("");
                nome2_txt.setText("");
                empresa2_txt.setText("");
                motivo2_txt.setText("");
                colaborador2_txt.setText("");
                foto2_img.setIcon(new ImageIcon(""));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\nErro na exclusão!", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) { // Codigo digitado com caracteres não numericos
            JOptionPane.showMessageDialog(null, "Digite o CPF corretamante", "ERRO", 0);
            cpf2_txt.setText("");
        }

        // ---> Fim
    }
    
}
