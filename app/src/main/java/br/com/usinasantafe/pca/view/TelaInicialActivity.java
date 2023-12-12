package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

public class TelaInicialActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pcaContext = (PCAContext) getApplication();
        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.postDelayed(excluirBDThread, 0);", getLocalClassName());
        customHandler.postDelayed(excluirBDThread, 0);

    }

    private Runnable excluirBDThread = () -> {

        LogProcessoDAO.getInstance().insertLogProcesso("private Runnable excluirBDThread = () -> {\n" +
                "        pcaContext.getConfigCTR().deleteLogErro();\n" +
                "        pcaContext.getConfigCTR().deleteLogProcesso();\n" +
                "        pcaContext.getCirculacaoCTR().delCircEnviado();", getLocalClassName());
        pcaContext.getConfigCTR().deleteLogErro();
        pcaContext.getConfigCTR().deleteLogProcesso();
        pcaContext.getViagemCTR().excluirViagemEnviada();
        Intent it;
        if(pcaContext.getConfigCTR().hasElements() && (pcaContext.getConfigCTR().getStatusConfig() == 1L)){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getCirculacaoCTR().verCirculacaoAberta()){\n" +
                    "            it = new Intent(TelaInicialActivity.this, ListaViagemActivity.class);", getLocalClassName());
            it = new Intent(TelaInicialActivity.this, ListaViagemActivity.class);
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
            it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
        }
        startActivity(it);
        finish();

    };
}