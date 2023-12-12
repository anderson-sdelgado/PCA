package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pca.BuildConfig;
import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextNroAparelhoConfig;
    private EditText editTextSenhaConfig;
    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonSalvarConfig = findViewById(R.id.buttonSalvarConfig );
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        Button buttonAtualizarConfig = findViewById(R.id.buttonAtualizarBD);
        editTextNroAparelhoConfig = findViewById(R.id.editTextNroAparelhoConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        pcaContext = (PCAContext) getApplication();

        if (pcaContext.getConfigCTR().hasElements()) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (pcaContext.getConfigCTR().hasElements()) {\n" +
                    "            editTextNroAparelhoConfig.setText(String.valueOf(pcaContext.getConfigCTR().getConfig().getNroAparelhoConfig()));\n" +
                    "            editTextSenhaConfig.setText(pcaContext.getConfigCTR().getConfig().getSenhaConfig());", getLocalClassName());
            editTextNroAparelhoConfig.setText(String.valueOf(pcaContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
            editTextSenhaConfig.setText(pcaContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        buttonSalvarConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonSalvarConfig.setOnClickListener(v -> {", getLocalClassName());
            if(!editTextNroAparelhoConfig.getText().toString().equals("") &&
                    !editTextSenhaConfig.getText().toString().equals("")){

                LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextNroAparelhoConfig.getText().toString().equals(\"\") &&\n" +
                        "                    !editTextSenhaConfig.getText().toString().equals(\"\")){\n" +
                        "                progressBar = new ProgressDialog(v.getContext());\n" +
                        "                progressBar.setCancelable(true);\n" +
                        "                progressBar.setMessage(\"Salvando dados inicial...\");\n" +
                        "                progressBar.show();\n" +
                        "                pcaContext.getConfigCTR().salvarToken(editTextSenhaConfig.getText().toString(), BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, MenuInicialActivity.class, progressBar);", getLocalClassName());
                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Salvando dados inicial...");
                progressBar.show();
                pcaContext.getConfigCTR().salvarToken(editTextSenhaConfig.getText().toString(), BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, MenuInicialActivity.class, progressBar);

            }

        });

        buttonCancConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancConfig.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();

        });

        buttonAtualizarConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarConfig.setOnClickListener(v -> {", getLocalClassName());
            if(pcaContext.getConfigCTR().hasElements()) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getConfigCTR().hasElements()) {", getLocalClassName());
                if(ActivityGeneric.connectNetwork){

                    LogProcessoDAO.getInstance().insertLogProcesso("if(ActivityGeneric.connectNetwork){\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pcaContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", (dialog, which) -> {});\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {});
                    alerta.show();
                }

            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"POR FAVOR, INSIRA O NRO DO APARELHO ANTES DE ATUALIZAR OS DADDOS.\");\n" +
                        "                alerta.setPositiveButton(\"OK\", (dialog, which) -> {});\n" +
                        "                alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, INSIRA O NRO DO APARELHO ANTES DE ATUALIZAR OS DADDOS.");
                alerta.setPositiveButton("OK", (dialog, which) -> {});
                alerta.show();
            }
        });

    }

    public void onBackPressed() {
    }

}