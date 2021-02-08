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

public class LeitorColabActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCAContext pcaContext;
    private TextView txtRetColab;
    private ProgressDialog progressBar;
    private ColabBean colabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_colab);

        pcaContext = (PCAContext) getApplication();

        txtRetColab = (TextView) findViewById(R.id.txtRetColab);
        Button buttonOkColab = (Button) findViewById(R.id.buttonOkColab);
        Button buttonCancColab = (Button) findViewById(R.id.buttonCancColab);
        Button buttonDigColab = (Button) findViewById(R.id.buttonDigColab);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptColab = (Button) findViewById(R.id.buttonCaptColab);

        colabBean = new ColabBean();
        colabBean.setMatricColab(0L);
        colabBean.setNomeColab("");

        txtRetColab.setText("POR FAVOR, REALIZE A LEITURA DO CARTÃO DO COLABORADOR RESPONSÁVEL.");

        buttonOkColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (colabBean.getMatricColab() > 0) {

                    pcaContext.getCirculacaoCTR().setMatricPacienteCirculacao(colabBean.getMatricColab());

                    Intent it = new Intent(LeitorColabActivity.this, ListaAmbulanciaActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorColabActivity.this, LeitorUsuarioActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonDigColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorColabActivity.this, DigColabActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorColabActivity.this, br.com.usinasantafe.pca.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorColabActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(LeitorColabActivity.this)) {

                            progressBar = new ProgressDialog(LeitorColabActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO COLABORADOR...");
                            progressBar.show();

                            pcaContext.getCirculacaoCTR().atualDadosColab(LeitorColabActivity.this
                                    , LeitorColabActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorColabActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }

                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

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
                    txtRetColab.setText(matricula + "\n" + colabBean.getNomeColab());
                } else {
                    txtRetColab.setText("COLABORADOR INEXISTENTE");
                }
            }
        }

    }

    public void onBackPressed() {
        Intent it = new Intent(LeitorColabActivity.this, LeitorUsuarioActivity.class);
        startActivity(it);
        finish();
    }

}