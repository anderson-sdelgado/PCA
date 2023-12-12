package br.com.usinasantafe.pca;

import android.app.Application;

import br.com.usinasantafe.pca.control.ConfigCTR;
import br.com.usinasantafe.pca.control.ViagemCTR;
import br.com.usinasantafe.pca.model.dao.LogErroDAO;

public class PCAContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public static String versaoWS = "2.00";

    private ViagemCTR viagemCTR;
    private ConfigCTR configCTR;
    private int verTela; // 1 - Inserir Viagem; 2 - Atualizar Viagem

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ViagemCTR getViagemCTR() {
        if (viagemCTR == null)
            viagemCTR = new ViagemCTR();
        return viagemCTR;
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
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };

}
