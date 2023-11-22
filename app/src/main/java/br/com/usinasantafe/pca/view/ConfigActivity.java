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
import br.com.usinasantafe.pca.util.ConexaoWeb;

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
            editTextNroAparelhoConfig.setText(String.valueOf(pcaContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
            editTextSenhaConfig.setText(pcaContext.getConfigCTR().getConfig().getSenhaConfig());
        }

        buttonSalvarConfig.setOnClickListener(v -> {

            if(!editTextNroAparelhoConfig.getText().toString().equals("") &&
                    !editTextSenhaConfig.getText().toString().equals("")){

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Salvando dados inicial...");
                progressBar.show();
                pcaContext.getConfigCTR().salvarToken(editTextSenhaConfig.getText().toString(), BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, MenuInicialActivity.class, progressBar);

            }

        });

        buttonCancConfig.setOnClickListener(v -> {

            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();

        });

        buttonAtualizarConfig.setOnClickListener(v -> {

            if(pcaContext.getConfigCTR().hasElements()) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pcaContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {});

                    alerta.show();
                }

            } else {

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