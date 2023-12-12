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

public class LeitorMotoristaActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCAContext pcaContext;
    private TextView txtRetUsu;
    private ProgressDialog progressBar;
    private ColabBean colabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_motorista);

        pcaContext = (PCAContext) getApplication();

        txtRetUsu = findViewById(R.id.txtRetUsu);
        Button buttonOkUsu = findViewById(R.id.buttonOkDataHora);
        Button buttonCancUsu = findViewById(R.id.buttonCancUsu);
        Button buttonDigUsu = findViewById(R.id.buttonDigUsu);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptMatric = findViewById(R.id.buttonCaptMatric);

        colabBean = new ColabBean();
        colabBean.setMatricColab(0L);
        colabBean.setNomeColab("");

        txtRetUsu.setText("POR FAVOR, REALIZE A LEITURA DO CARTÃO DO COLABORADOR REQUISITANDO.");

        buttonOkUsu.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkUsu.setOnClickListener(v -> {", getLocalClassName());
            if (colabBean.getMatricColab() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (colabBean.getMatricColab() > 0) {\n" +
                        "                pcaContext.getConfigCTR().setMatricUsuario(colabBean.getMatricColab());\n" +
                        "                Intent it = new Intent(LeitorUsuarioActivity.this, ListaEquipActivity.class);", getLocalClassName());
                pcaContext.getConfigCTR().setMatricUsuario(colabBean.getMatricColab());
                Intent it = new Intent(LeitorMotoristaActivity.this, ListaEquipActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancUsu.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancUsu.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorUsuarioActivity.this, TelaInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorMotoristaActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonDigUsu.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonDigUsu.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorUsuarioActivity.this, DigUsuarioActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorMotoristaActivity.this, DigMotoristaActivity.class);
            startActivity(it);
            finish();
        });

        buttonCaptMatric.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCaptMatric.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(LeitorUsuarioActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);\n" +
                    "            startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
            Intent it = new Intent(LeitorMotoristaActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);
            startActivityForResult(it, REQUEST_CODE);
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorUsuarioActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorMotoristaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(LeitorUsuarioActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO COLABORADOR...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosColab(LeitorUsuarioActivity.this\n" +
                            "                            , LeitorUsuarioActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(LeitorMotoristaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                    progressBar.show();

                    pcaContext.getViagemCTR().atualDadosColab(LeitorMotoristaActivity.this
                            , LeitorMotoristaActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeitorUsuarioActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(LeitorMotoristaActivity.this);
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
                            "                    txtRetUsu.setText(matricula + \"\\n\" + colabBean.getNomeColab());", getLocalClassName());
                    colabBean = pcaContext.getViagemCTR().getColab(Long.parseLong(matricula));
                    txtRetUsu.setText(matricula + "\n" + colabBean.getNomeColab());
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    txtRetUsu.setText(\"COLABORADOR INEXISTENTE\");", getLocalClassName());
                    txtRetUsu.setText("COLABORADOR INEXISTENTE");
                }
            }
        }

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(LeitorUsuarioActivity.this, MenuInicialActivity.class);", getLocalClassName());
        Intent it = new Intent(LeitorMotoristaActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}