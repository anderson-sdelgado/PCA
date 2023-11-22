package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.util.ConexaoWeb;

public class DigUsuarioActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_usuario);

        pcaContext = (PCAContext) getApplication();

        Button buttonOkFunc = findViewById(R.id.buttonOkPadrao);
        Button buttonCancFunc = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(  DigUsuarioActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(DigUsuarioActivity.this)) {

                    progressBar = new ProgressDialog(DigUsuarioActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosColab(DigUsuarioActivity.this
                            , DigUsuarioActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( DigUsuarioActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {});

                    alerta1.show();

                }

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {});

            alerta.show();

        });

        buttonOkFunc.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                if (pcaContext.getCirculacaoCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {

                    pcaContext.getCirculacaoCTR().criarCirculacao(Long.parseLong(editTextPadrao.getText().toString()));

                    Intent it = new Intent(DigUsuarioActivity.this, LeitorColabActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(DigUsuarioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DO FUNCIONÁRIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {});

                    alerta.show();

                }
            }

        });

        buttonCancFunc.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(DigUsuarioActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}