package br.com.usinasantafe.pca;

import android.app.Application;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.control.CirculacaoCTR;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;

public class PCAContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public static String versaoAplic = "1.00";
    private CirculacaoCTR circulacaoCTR;
    private ConfigCTR configCTR;
    private int verTela;
    // 1 - Kilometragem Inicial
    // 2 - Kilometragem Final

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

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insert(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };

}
