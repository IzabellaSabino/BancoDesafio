package br.gov.sp.cps;

import br.gov.sp.cps.views.TelaBanco;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }
                TelaBanco tela = new TelaBanco();
                tela.setVisible(true);
            }
        });
    }
}

