package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;

public class TelaInicialActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pcaContext = (PCAContext) getApplication();
        customHandler.postDelayed(excluirBDThread, 0);

    }

    private Runnable excluirBDThread = () -> {

        pcaContext.getConfigCTR().deleteLogs();
        pcaContext.getCirculacaoCTR().delCircEnviado();
        Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();

    };
}