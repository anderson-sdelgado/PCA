package br.com.usinasantafe.pca.view;

import androidx.appcompat.app.AppCompatActivity;

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

public class LeitorUsuarioActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;
    private PCAContext pcaContext;
    private TextView txtRetFunc;
    private ProgressDialog progressBar;
    private ColabBean colabBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_usuario);

        pcaContext = (PCAContext) getApplication();

        txtRetFunc = (TextView) findViewById(R.id.txtRetFunc);
        Button buttonOkFunc = (Button) findViewById(R.id.buttonOkFunc);
        Button buttonCancFunc = (Button) findViewById(R.id.buttonCancFunc);
        Button buttonDigFunc = (Button) findViewById(R.id.buttonDigFunc);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptMatric = (Button) findViewById(R.id.buttonCaptMatric);

        colabBean = new ColabBean();
        colabBean.setMatricColab(0L);
        colabBean.setNomeColab("");

        txtRetFunc.setText("Por Favor, realize a leitura do Cartão do Colaborador Mecânico.");

        buttonOkFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (colabBean.getMatricColab() > 0) {

                    pcaContext.getConfigCTR().matricFuncConfig(colabBean.getMatricColab());

                    Intent it = new Intent(LeitorFuncActivity.this, MenuFuncaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonDigFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptMatric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(LeitorFuncActivity.this, br.com.usinasantafe.pbi.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorFuncActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(LeitorFuncActivity.this)) {

                            progressBar = new ProgressDialog(LeitorFuncActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Colaborador...");
                            progressBar.show();

                            pcaContext.getMecanicoCTR().atualDadosColab(LeitorFuncActivity.this
                                    , LeitorFuncActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorFuncActivity.this);
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

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            String matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                matricula = matricula.substring(0, 7);
                if (pcaContext.getMecanicoCTR().verMatricColab(Long.parseLong(matricula))) {
                    colabBean = pcaContext.getMecanicoCTR().getColab(Long.parseLong(matricula));
                    txtRetFunc.setText(matricula + "\n" + colabBean.getNomeColab());
                } else {
                    txtRetFunc.setText("Funcionário Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
        Intent it = new Intent(LeitorFuncActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}