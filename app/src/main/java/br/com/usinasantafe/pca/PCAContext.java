package br.com.usinasantafe.pca;

import android.app.Application;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.control.CirculacaoCTR;

public class PCAContext extends Application {

    public static String versaoAplic = "1.02";
    private CirculacaoCTR circulacaoCTR;
    private ConfigCTR configCTR;
    private int verTela;
    // 1 - Tela Inicial
    // 2 - Tela Passageiro
    // 3 - Verificando Motorista
    // 4 - Verificando Colaborador

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public CirculacaoCTR getCirculacaoCTR() {
        if (circulacaoCTR == null)
            circulacaoCTR = new CirculacaoCTR();
        return circulacaoCTR;
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public int getVerTela() {
        return verTela;
    }

    public void setVerTela(int verTela) {
        this.verTela = verTela;
    }
}
