import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class APSdeFATO {
    
    public static int parseSafe(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }
    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Sistema de dados sobre a Dengue nas regiões de São Paulo.");

        String[] regioes = {"Ipiranga", "São Caetano", "Santo André", "São Bernardo"};

        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new GridLayout(4, 1));    

        JRadioButton radioButton1 = new JRadioButton("Ipiranga");
        JRadioButton radioButton2 = new JRadioButton("São Caetano");
        JRadioButton radioButton3 = new JRadioButton("Santo André");
        JRadioButton radioButton4 = new JRadioButton("São Bernardo");

        ButtonGroup grupoOpcoes = new ButtonGroup();
        grupoOpcoes.add(radioButton1);
        grupoOpcoes.add(radioButton2);
        grupoOpcoes.add(radioButton3);
        grupoOpcoes.add(radioButton4);

        painelOpcoes.add(radioButton1);
        painelOpcoes.add(radioButton2);
        painelOpcoes.add(radioButton3);
        painelOpcoes.add(radioButton4);

        int result = JOptionPane.showConfirmDialog(null, painelOpcoes, 
        "Escolha uma região abaixo", JOptionPane.OK_CANCEL_OPTION);


        String regiaoEscolhida = "";
if (result == JOptionPane.OK_OPTION) {
    if (radioButton1.isSelected()) {
        regiaoEscolhida = "Ipiranga";
    } else if (radioButton2.isSelected()) {
        regiaoEscolhida = "São Caetano";
    } else if (radioButton3.isSelected()) {
        regiaoEscolhida = "Santo André";
    } else if (radioButton4.isSelected()) {
        regiaoEscolhida = "São Bernardo";
    }
} else {
    JOptionPane.showMessageDialog(null, "Nenhuma região selecionada. Encerrando.");
    return; 
}
        String casos = buscarCasosPorRegiao(regiaoEscolhida);
        String obitos = buscarObitosPorRegiao(regiaoEscolhida);

        int casosInt = Integer.parseInt(casos);
        int obitosInt = Integer.parseInt(obitos);

        double taxaDeMortalidade = calcularTaxaMortalidade(casosInt, obitosInt);

        String mensagemDados = "Dados da Região - " + regiaoEscolhida + ":\n ";
        mensagemDados += "Casos: " + casos + "\n";
        mensagemDados += "Obitos: " + obitos + "\n";
        mensagemDados += "Taxa de Mortalidade " + String.format("%.3f", taxaDeMortalidade) + "%"; 

        JOptionPane.showMessageDialog(null, mensagemDados);

        int compararResposta = JOptionPane.showOptionDialog(null,
                "Gostaria de comparar os dados das regiões?",
                "Comparar regiões.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (compararResposta == JOptionPane.YES_OPTION) {

            String regiaoescolhida1 = (String) JOptionPane.showInputDialog(null,
                    "Escolha a primeira região para comparar:",
                    "Seleções de região", JOptionPane.PLAIN_MESSAGE, null, regioes, regioes[0]);

            String regiaoEscolhida2 = "";

            do {

            regiaoEscolhida2 = (String) JOptionPane.showInputDialog(null,
                    "Escolha a segunda região para comparar:",
                    "Seleções de região", JOptionPane.PLAIN_MESSAGE, null, regioes, regioes[0]);

                    if (regiaoescolhida1.equals(regiaoEscolhida2)){
                        JOptionPane.showMessageDialog(null, "As regiões devem ser diferentes!");
                    } 
                } while (regiaoescolhida1.equals(regiaoEscolhida2));

            String casos1 = buscarCasosPorRegiao(regiaoescolhida1);
            String obitos1 = buscarObitosPorRegiao(regiaoescolhida1);
            String casos2 = buscarCasosPorRegiao(regiaoEscolhida2);
            String obitos2 = buscarObitosPorRegiao(regiaoEscolhida2);

            int casosInt1 = Integer.parseInt(casos1);
            int obitosInt1 = Integer.parseInt(obitos1);
            int casosInt2 = Integer.parseInt(casos2);
            int obitosInt2 = Integer.parseInt(obitos2); 

            double taxaDeMortalidade1 = calcularTaxaMortalidade(casosInt1, obitosInt1);
            double taxaDeMortalidade2 = calcularTaxaMortalidade(casosInt2, obitosInt2);

            String mensagemComparacao = "Comparação de dados entre as regiões " + regiaoescolhida1 + " e " + regiaoEscolhida2 + ":\n\n";
            mensagemComparacao += regiaoescolhida1 + "-" + " Casos: " + casos1 + ", Óbitos: " + obitos1 + ", Taxa de Mortalidade: " + String.format("%.3f", taxaDeMortalidade1)  + "%\n";
            mensagemComparacao += regiaoEscolhida2 + "-"  + " Casos: " + casos2 + ", Óbitos: " + obitos2 + ", Taxa de Mortalidade: " + String.format("%.3f", taxaDeMortalidade2) + "%";

            JOptionPane.showMessageDialog(null, mensagemComparacao);
        } else {
            JOptionPane.showMessageDialog(null, "Sistema finalizado !");
        }

       int verRanking = JOptionPane.showOptionDialog(null,
                " 🏆 Gostaria de ver o ranking das regiões ? 🏆 ",
                "RANKING",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (verRanking == JOptionPane.YES_OPTION) {

        RegiaoDengue[] regioesDengue = new RegiaoDengue[]{
            new RegiaoDengue("Ipiranga", parseSafe(buscarCasosPorRegiao("Ipiranga")), parseSafe(buscarObitosPorRegiao("Ipiranga"))),
            new RegiaoDengue("São Caetano", parseSafe(buscarCasosPorRegiao("São Caetano")), parseSafe(buscarObitosPorRegiao("São Caetano"))),
            new RegiaoDengue("Santo André", parseSafe(buscarCasosPorRegiao("Santo André")), parseSafe(buscarObitosPorRegiao("Santo André"))),
            new RegiaoDengue("São Bernardo", Integer.parseInt(buscarCasosPorRegiao("São Bernardo")), Integer.parseInt(buscarObitosPorRegiao("São Bernardo")))
        }; 

        java.util.Arrays.sort(regioesDengue, (a, b) -> b.getCasos() - a.getCasos());
    
        StringBuilder rankingMsg = new  StringBuilder("Ranking das Região com mais casos de Dengue: \n\n");
    
        for (int i = 0; i < regioesDengue.length; i++) {
            RegiaoDengue r = regioesDengue[i];
            rankingMsg.append((i + 1) + ". " + r.getNome() + " - Casos: " + r.getCasos() + ", Óbitos: " + r.getObitos() + ", Taxa: " + String.format("%.2f", r.getTaxaMortalidade()) + "%\n");
        }
        
        JOptionPane.showMessageDialog(null, rankingMsg.toString());
        } else {
                JOptionPane.showMessageDialog(null, "Sistema finalizado !");
            }
        }

    public static double calcularTaxaMortalidade(int casos, int obitos) {
        if (casos > 0) { 
            return (double) obitos / casos * 100;
        } else {
            return 0.0;
        }
    }

    public static String buscarCasosPorRegiao(String regiao) {
        String url = "jdbc:mysql://localhost:3306/aps";
        String usuario = "root";
        String senha = "mysql";

        String query = "SELECT casos FROM doencas WHERE regiao = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regiao);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return String.valueOf(rs.getInt("casos"));
            } else {
                return "Nenhum dado encontrado para a região";
            }

        } catch (SQLException e) {
            return "Erro de conexão: " + e.getMessage();
        }
    }

    public static String buscarObitosPorRegiao(String regiao) {
        String url = "jdbc:mysql://localhost:3306/aps";
        String usuario = "root";
        String senha = "mysql";

        String query = "SELECT obitos FROM doencas WHERE regiao = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regiao);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return String.valueOf(rs.getInt("obitos"));
            } else {
                return "Nenhum dado encontrado para a região";
            }

        } catch (SQLException e) {
            return "Erro de conexão: " + e.getMessage();
        }
    }
}

class RegiaoDengue {
    private String nome;
    private int casos;
    private int obitos;

    public RegiaoDengue(String nome, int casos, int obitos){
        this.nome = nome;
        this.casos = casos;
        this.obitos = obitos;
    }
    public String getNome(){
        return nome;
    }
    public int getCasos(){
        return casos;
    }
    public int getObitos(){
        return obitos;
    }
    public double getTaxaMortalidade(){
        if (casos > 0){
            return (double) obitos / casos * 100;
        } else {
            return 0.0;
        }
    }
}

 

    
