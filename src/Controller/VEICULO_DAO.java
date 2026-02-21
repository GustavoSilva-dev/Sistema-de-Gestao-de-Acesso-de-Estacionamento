package Controller;

import View.CadastrarVeiculo_GUI;
import static View.CadastrarVeiculo_GUI.cor_txt;
import static View.CadastrarVeiculo_GUI.cpf_txt;
import static View.CadastrarVeiculo_GUI.modelo_txt;
import static View.CadastrarVeiculo_GUI.placa_txt;
import static View.CadastrarVeiculo_GUI.tipoVaga_txt;
import static View.CadastrarVeiculo_GUI.tipoVeiculo_txt;
import static View.VisualizarVeiculo_GUI.*;
import View.VisualizarVeiculo_GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VEICULO_DAO {

    public static String url = "jdbc:mysql://localhost:3306/estacionamento"; // enderço do BD
    public static String username = "root";        //nome de um usuário de seu BD
    public static String password = "123";
    static long veiculoCPF = 0;

    public static void cadastrarVeiculo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String cpf = cpf_txt.getText();
        String placa = placa_txt.getText();
        String modelo = modelo_txt.getText();
        String cor = String.valueOf(cor_txt.getSelectedItem());
        String tipoVaga = String.valueOf(tipoVaga_txt.getSelectedItem());
        String tipoVeiculo = String.valueOf(tipoVeiculo_txt.getSelectedItem());
        String status = "ESTACIONADO";

        Model.Conecta_DB.carregaDriver();

        try {
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarVeiculo_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sql = "INSERT INTO VEICULO (vei_placa, vei_modelo, vei_cpf, vei_cor, vei_vaga, vei_tipo, vei_saida, vei_status) VALUES ('" + placa + "', '" + modelo + "', " + cpf + ", '" + cor + "', '" + tipoVaga + "', '" + tipoVeiculo + "', " + "NULL" + ", '" + status + "')";
            try {
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);

                int linhasAfetadas = inserir.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "\nInserção realizada com sucesso!!!\n", "Sucesso!", 1);

                    sql = "UPDATE visitante SET cli_placa='" + placa + "' WHERE cli_cpf= " + Long.valueOf(cpf) + "";
                    inserir = (PreparedStatement) con.prepareStatement(sql);
                    inserir.execute();

                    System.out.println(sql);

                    cpf_txt.setText("");
                    placa_txt.setText("");
                    modelo_txt.setText("");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "\nErro na inserção! Certifique-se que o CPF exista entre os usuários", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) {
            // Tratamento de erro caso o usuario não digite o cpf corretamente 
            JOptionPane.showMessageDialog(null, "Digite os dados do CPF corretamente", "ERRO", 0);
            cpf_txt.setText("");
        }
        // ---> Fim
    }

    public static void consultarVeiculo() {
        // ---> Início
        try {     //Iniciando o possivel tratamento de erros
            //Declarando a variavel código
            String referencia = placa2_txt.getText();
            Model.Conecta_DB.carregaDriver();

            try {// Tratamento de erro para a conexao
                // Declarando  a variavel de conexão con
                // e estabelendo a conexão
                Connection con = null;

                try {
                    con = (Connection) DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(VisualizarVeiculo_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Declarando uma string com o comando mySQL para consulta
                String sql = "SELECT vei_cor, vei_tipo, vei_vaga, vei_placa, vei_modelo, vei_cpf FROM VEICULO where vei_placa = '" + referencia + "'";
                // Criando variavel que executara o comando da string sql
                Statement stm = (Statement) con.createStatement();
                try//Tratamento de erro da consulta
                { //Criando variavel que exibira os resultados
                    //Executando o comando da string sql na variavel rs
                    ResultSet rs = stm.executeQuery(sql);

                    int i = 0; // Variavel utilizada para saber se ha dados cadastrados

                    while (rs.next()) {  // Criando variaveis que receberão os valores do banco de dados
                        String placaConsulta = rs.getString("vei_placa");
                        String corConsulta = rs.getString("vei_cor");
                        String tipoVeiculoConsulta = rs.getString("vei_tipo");
                        String tipoVagaConsulta = rs.getString("vei_vaga");
                        String modeloConsulta = rs.getString("vei_modelo");
                        veiculoCPF = rs.getLong("vei_cpf");

                        i++;

                        //JOptionPane.showMessageDialog(null,"Nome: " + nome + "\nEmail: " +telefone + "\nTelefone: " +telefone, "Resultado",-1);
                        placa3_txt.setText(String.valueOf(placaConsulta));
                        modelo2_txt.setText(String.valueOf(modeloConsulta));
                        cor2_txt.setSelectedItem(String.valueOf(corConsulta));
                        tipoVeiculo2_txt.setSelectedItem(String.valueOf(tipoVeiculoConsulta));
                        tipoVaga2_txt.setSelectedItem(String.valueOf(tipoVagaConsulta));
                    }

                    if (i == 0) { // Verificando se ha dados cadastrados atraves da variavel i
                        JOptionPane.showMessageDialog(null, "Veículo não cadastrado", "Resultado", -1);
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
            JOptionPane.showMessageDialog(null, "Digite a PLACA do veículo corretamante", "ERRO", 0);
            placa2_txt.setText("");
        }

        // ---> Fim
    }

    public static void alterarVeiculo() {
        // ---> Início
        String placaAltera = placa3_txt.getText();
        String modeloAltera = modelo2_txt.getText();
        String corAltera = String.valueOf(cor2_txt.getSelectedItem());
        String tipoVeiculoAltera = String.valueOf(tipoVeiculo2_txt.getSelectedItem());
        String tipoVagaAltera = String.valueOf(tipoVaga2_txt.getSelectedItem());

        Model.Conecta_DB.carregaDriver();

        try {
            Connection con = null;
            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(VisualizarVeiculo_GUI.class.getName()).log(Level.SEVERE, null, ex);

            }
            String sql = "UPDATE VEICULO SET vei_placa='" + placaAltera + "', vei_modelo='" + modeloAltera + "', vei_cor='" + corAltera + "', vei_vaga='" + tipoVagaAltera + "', vei_tipo='" + tipoVeiculoAltera + "' WHERE vei_placa= '" + placa2_txt.getText() + "'";

            try {
                PreparedStatement inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute(); // Executando a inserção

                sql = "UPDATE visitante SET cli_placa='" + placaAltera + "' WHERE cli_cpf=" + veiculoCPF + "";
                inserir = (PreparedStatement) con.prepareStatement(sql);
                inserir.execute();

                System.out.println(sql);
                JOptionPane.showMessageDialog(null, "\nAtualização realizada com sucesso!!!\n", "", -1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\nErro na atualização!", "ERRO!", 0);
                System.out.println(ex.getMessage());
            }

        } catch (NumberFormatException erro) {
            // Tratamento de erro caso o usuario não digite o telefone corretamente
            JOptionPane.showMessageDialog(null, "Digite os dados corretamente", "ERRO", 0);
        }

        // ---> Fim
    }

    public static void excluirVeiculo() {
        // ---> Início
        Model.Conecta_DB.carregaDriver();

        // int codigo = Integer.valueOf(cod2_txt.getText()); // Recebendo o código
        try {
            Connection con = null;

            try {
                con = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(VisualizarVeiculo_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Criando String com comando SQL para exclusão
            String sql = "DELETE FROM VEICULO WHERE vei_placa = '" + placa2_txt.getText() + "'";

            try // Tratamento de erros para exclusão
            {// Criando Variavel para executar a ação
                PreparedStatement excluir = (PreparedStatement) con.prepareStatement(sql);
                excluir.execute();// Executando a exclusão

                JOptionPane.showMessageDialog(null, "\nExclusão realizada com sucesso!!!\n", "", -1);
                placa2_txt.setText("");
                placa3_txt.setText("");
                modelo2_txt.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "\nErro na exclusão!", "ERRO!", 0);
            }

        } catch (NumberFormatException erro) { // Codigo digitado com caracteres não numericos
            JOptionPane.showMessageDialog(null, "Digite a PLACA do veículo corretamante", "ERRO", 0);
            placa2_txt.setText("");
        }

        // ---> Fim
    }

    public static void registrarSaida() {
        String placaSaida = placa2_txt.getText();

        String sql = "UPDATE VEICULO SET vei_saida = NOW(), vei_status = 'FORA' WHERE vei_placa = '" + placaSaida + "'";
        Model.Conecta_DB.carregaDriver();
        try (Connection con = DriverManager.getConnection(url, username, password)) {

            try (PreparedStatement update = con.prepareStatement(sql)) {
                int linhasAfetadas = update.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "Saída registrada com sucesso!\nStatus: FORA", "Sucesso", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum veículo encontrado com a placa: " + placaSaida, "Erro", 0);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(VEICULO_DAO.class.getName()).log(Level.SEVERE, "Erro ao registrar saída", ex);
            JOptionPane.showMessageDialog(null, "Erro SQL ao registrar saída: " + ex.getMessage(), "ERRO!", 0);
        }
    }
    
    public static void registrarRetorno() {
    String placaRetorno = placa2_txt.getText(); 
    
    String sql = "UPDATE VEICULO SET vei_saida = NULL, vei_entrada = NOW(), vei_status = 'ESTACIONADO' WHERE vei_placa = '" + placaRetorno + "'";

    Model.Conecta_DB.carregaDriver();

    try (Connection con = DriverManager.getConnection(url, username, password)) {
        
        try (PreparedStatement update = con.prepareStatement(sql)) {
            int linhasAfetadas = update.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Retorno  do veículo registrado com sucesso!\nStatus: ESTACIONADO", "Sucesso", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum veículo encontrado com a placa: " + placaRetorno, "Erro", 0);
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(VEICULO_DAO.class.getName()).log(Level.SEVERE, "Erro ao confirmar retorno", ex);
        JOptionPane.showMessageDialog(null, "Erro do servidor ao confirmar retorno: " + ex.getMessage(), "ERRO!", 0);
    }
}
}
