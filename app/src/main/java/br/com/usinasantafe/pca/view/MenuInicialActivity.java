package br.com.usinasantafe.pca.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.pca.BuildConfig;
import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.NetworkChangeListerner;
import br.com.usinasantafe.pca.util.EnvioDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private ListView menuInicialListView;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private TextView textViewPrincipal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pcaContext = (PCAContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);
        textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        customHandler.postDelayed(updateTimerThread, 0);

        progressBar = new ProgressDialog(this);

        if(pcaContext.getCirculacaoCTR().verCirculacaoAberta()){
            Intent it = new Intent(MenuInicialActivity.this, ListaInforActivity.class);
            startActivity(it);
            finish();
        }

        ArrayList<String> itens = new ArrayList<>();

        itens.add("INICIAR CIRCULAÇÃO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener((l, v, position, id) -> {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            if (text.equals("INICIAR CIRCULAÇÃO")) {

                if (pcaContext.getCirculacaoCTR().hasElementsColab()
                        && pcaContext.getConfigCTR().hasElements()) {

                    pcaContext.setVerTela(1);
                    Intent it = new Intent(MenuInicialActivity.this, LeitorUsuarioActivity.class);
                    startActivity(it);
                    finish();

                }

            } else if (text.equals("CONFIGURAÇÃO")) {

                Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                startActivity(it);
                finish();

            } else if (text.equals("SAIR")) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });

    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            if (pcaContext.getConfigCTR().hasElements()) {
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

}