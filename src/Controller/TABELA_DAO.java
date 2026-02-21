package Controller;

import static Controller.VEICULO_DAO.password;
import static Controller.VEICULO_DAO.url;
import static Controller.VEICULO_DAO.username;
import static View.VisualizarVeiculo_GUI.tabelaVeiculos;
import static View.VisualizarVeiculo_GUI.veiculo;
import static View.VisualizarVisitantes_GUI.tabelaVisitantes;
import static View.VisualizarVisitantes_GUI.visitantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class TABELA_DAO {

    public static void refreshVeiculo() {
        try {
            Model.Conecta_DB.carregaDriver(); // se você tiver uma classe "Driver" que faz isso
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT vei_status, vei_placa, vei_cor, vei_Tipo, vei_vaga, vei_modelo, vei_entrada, vei_saida, vei_cpf FROM veiculo;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            tabelaVeiculos.setModel(veiculo(rs)); // usa o método acima
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void refreshVisitante() {
        try {
            Model.Conecta_DB.carregaDriver(); // se você tiver uma classe "Driver" que faz isso
            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT cli_cpf, cli_nome, cli_foto, cli_empresa, cli_colaborador, cli_motivo, cli_placa FROM visitante;";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            tabelaVisitantes.setModel(visitantes(rs)); // usa o método acima
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela de Visitantes: " + e.getMessage(), "Erro de Refresh", JOptionPane.ERROR_MESSAGE);
        }
    }
}
