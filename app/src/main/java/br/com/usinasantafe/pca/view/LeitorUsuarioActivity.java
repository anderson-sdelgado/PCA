package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.util.ConexaoWeb;

public class LeitorUsuarioActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCAContext pcaContext;
    private TextView txtRetUsu;
    private ProgressDialog progressBar;
    private ColabBean colabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_usuario);

        pcaContext = (PCAContext) getApplication();

        txtRetUsu = findViewById(R.id.txtRetUsu);
        Button buttonOkUsu = findViewById(R.id.buttonOkUsu);
        Button buttonCancUsu = findViewById(R.id.buttonCancUsu);
        Button buttonDigUsu = findViewById(R.id.buttonDigUsu);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptMatric = findViewById(R.id.buttonCaptMatric);

        colabBean = new ColabBean();
        colabBean.setMatricColab(0L);
        colabBean.setNomeColab("");

        txtRetUsu.setText("POR FAVOR, REALIZE A LEITURA DO CARTÃO DO COLABORADOR REQUISITANDO.");

        buttonOkUsu.setOnClickListener(v -> {

            if (colabBean.getMatricColab() > 0) {

                pcaContext.getCirculacaoCTR().criarCirculacao(colabBean.getMatricColab());

                Intent it = new Intent(LeitorUsuarioActivity.this, LeitorColabActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancUsu.setOnClickListener(v -> {
            Intent it = new Intent(LeitorUsuarioActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonDigUsu.setOnClickListener(v -> {
            Intent it = new Intent(LeitorUsuarioActivity.this, DigUsuarioActivity.class);
            startActivity(it);
            finish();
        });

        buttonCaptMatric.setOnClickListener(v -> {
            Intent it = new Intent(LeitorUsuarioActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);
            startActivityForResult(it, REQUEST_CODE);
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorUsuarioActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(LeitorUsuarioActivity.this)) {

                    progressBar = new ProgressDialog(LeitorUsuarioActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosColab(LeitorUsuarioActivity.this
                            , LeitorUsuarioActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeitorUsuarioActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {});

                    alerta1.show();

                }

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {});

            alerta.show();

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            String matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                matricula = matricula.substring(0, 7);
                if (pcaContext.getCirculacaoCTR().verColab(Long.parseLong(matricula))) {
                    colabBean = pcaContext.getCirculacaoCTR().getColab(Long.parseLong(matricula));
                    txtRetUsu.setText(matricula + "\n" + colabBean.getNomeColab());
                } else {
                    txtRetUsu.setText("COLABORADOR INEXISTENTE");
                }
            }
        }

    }

    public void onBackPressed() {
        Intent it = new Intent(LeitorUsuarioActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}