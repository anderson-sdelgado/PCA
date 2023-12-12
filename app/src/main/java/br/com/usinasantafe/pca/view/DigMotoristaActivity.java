package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class DigMotoristaActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_motorista);

        pcaContext = (PCAContext) getApplication();

        Button buttonOkFunc = findViewById(R.id.buttonOkPadrao);
        Button buttonCancFunc = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(  DigUsuarioActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(  DigMotoristaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(DigUsuarioActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO COLABORADOR...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosColab(DigUsuarioActivity.this\n" +
                            "                            , DigUsuarioActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(DigMotoristaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcaContext.getViagemCTR().atualDadosColab(DigMotoristaActivity.this
                            , DigMotoristaActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( DigUsuarioActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( DigMotoristaActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {});
                    alerta1.show();

                }

            });

            alerta.setPositiveButton("NÃO", (dialog, which) ->
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", (dialog, which) -> {", getLocalClassName())
            );
            alerta.show();

        });

        buttonOkFunc.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkFunc.setOnClickListener(v -> {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                if (pcaContext.getViagemCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcaContext.getCirculacaoCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {\n" +
                            "                    pcaContext.getConfigCTR().setMatricUsuario(Long.parseLong(editTextPadrao.getText().toString()));\n" +
                            "                    Intent it = new Intent(DigUsuarioActivity.this, ListaEquipActivity.class);", getLocalClassName());
                    pcaContext.getConfigCTR().setMatricUsuario(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(DigMotoristaActivity.this, ListaEquipActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(DigUsuarioActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"NUMERAÇÃO DO FUNCIONÁRIO INEXISTENTE! FAVOR VERIFICA A MESMA.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", (dialog, which) -> {});\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(DigMotoristaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DO FUNCIONÁRIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {});
                    alerta.show();

                }
            }

        });

        buttonCancFunc.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancFunc.setOnClickListener(v -> {\n" +
                        "            if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(DigUsuarioActivity.this, TelaInicialActivity.class);", getLocalClassName());
        Intent it = new Intent(DigMotoristaActivity.this, TelaInicialActivity.class);
        startActivity(it);
        finish();
    }

}