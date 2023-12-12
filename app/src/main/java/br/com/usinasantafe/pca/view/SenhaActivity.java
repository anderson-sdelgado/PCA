package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        pcaContext = (PCAContext) getApplication();

        editTextSenha = findViewById(R.id.editTextSenha);
        Button buttonOkSenha =  findViewById(R.id.buttonOkSenha);
        Button butttonCancSenha = findViewById(R.id.buttonCancSenha);

        buttonOkSenha.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkSenha.setOnClickListener(v -> {", getLocalClassName());
            if (!pcaContext.getConfigCTR().hasElements()) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!pcaContext.getConfigCTR().hasElements()) {\n" +
                        "                Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                startActivity(it);
                finish();

            } else {

                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (pcaContext.getConfigCTR().getConfig(editTextSenha.getText().toString())) {
                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        butttonCancSenha.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("butttonCancSenha.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}