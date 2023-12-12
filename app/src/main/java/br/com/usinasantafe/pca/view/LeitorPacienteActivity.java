package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class LeitorPacienteActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCAContext pcaContext;
    private TextView txtRetColab;
    private ProgressDialog progressBar;
    private ColabBean colabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_paciente);

        pcaContext = (PCAContext) getApplication();

        txtRetColab = findViewById(R.id.txtRetColab);
        Button buttonOkColab = findViewById(R.id.buttonOkColab);
        Button buttonCancColab = findViewById(R.id.buttonCancColab);
        Button buttonDigColab = findViewById(R.id.buttonDigColab);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptColab = findViewById(R.id.buttonCaptColab);

        colabBean = new ColabBean();
        colabBean.setMatricColab(0L);
        colabBean.setNomeColab("");

        txtRetColab.setText("POR FAVOR, REALIZE A LEITURA DO CARTÃO DO COLABORADOR RESPONSÁVEL.");

        buttonOkColab.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkColab.setOnClickListener(v -> {", getLocalClassName());
            if (colabBean.getMatricColab() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (colabBean.getMatricColab() > 0) {\n" +
                        "                pcaContext.getViagemCTR().setMatricPacienteViagem(colabBean.getMatricColab());\n" +
                        "                Intent it = new Intent(LeitorPacienteActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                pcaContext.getViagemCTR().setMatricPacienteViagem(colabBean.getMatricColab());
                Intent it = new Intent(LeitorPacienteActivity.this, ListaDetalhesViagemActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonCancColab.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancColab.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorPacienteActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorPacienteActivity.this, ListaDetalhesViagemActivity.class);
            startActivity(it);
            finish();
        });

        buttonDigColab.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonDigColab.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorColabActivity.this, DigColabActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorPacienteActivity.this, DigPacienteActivity.class);
            startActivity(it);
            finish();
        });

        buttonCaptColab.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCaptColab.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorColabActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);\n" +
                    "            startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
            Intent it = new Intent(LeitorPacienteActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);
            startActivityForResult(it, REQUEST_CODE);
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorColabActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorPacienteActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(LeitorColabActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO COLABORADOR...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosColab(LeitorColabActivity.this\n" +
                            "                            , LeitorColabActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(LeitorPacienteActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcaContext.getViagemCTR().atualDadosColab(LeitorPacienteActivity.this
                            , LeitorPacienteActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeitorColabActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeitorPacienteActivity.this);
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

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {\n" +
                    "            String matricula = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (matricula.length() == 8) {\n" +
                        "                matricula = matricula.substring(0, 7);", getLocalClassName());
                matricula = matricula.substring(0, 7);
                if (pcaContext.getViagemCTR().verColab(Long.parseLong(matricula))) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcaContext.getCirculacaoCTR().verColab(Long.parseLong(matricula))) {\n" +
                            "                    colabBean = pcaContext.getCirculacaoCTR().getColab(Long.parseLong(matricula));\n" +
                            "                    txtRetColab.setText(matricula + \"\\n\" + colabBean.getNomeColab());", getLocalClassName());
                    colabBean = pcaContext.getViagemCTR().getColab(Long.parseLong(matricula));
                    txtRetColab.setText(matricula + "\n" + colabBean.getNomeColab());
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    txtRetColab.setText(\"COLABORADOR INEXISTENTE\");", getLocalClassName());
                    txtRetColab.setText("COLABORADOR INEXISTENTE");
                }
            }
        }

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(LeitorColabActivity.this, LeitorUsuarioActivity.class);", getLocalClassName());
        Intent it = new Intent(LeitorPacienteActivity.this, LeitorMotoristaActivity.class);
        startActivity(it);
        finish();
    }

}