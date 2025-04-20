import javax.swing.*;
import java.awt.*;

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
        // Resto do código...
    }
}
