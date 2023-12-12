package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class ListaLocalSaidaActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView localSaidaListView;
    private PCAContext pcaContext;
    private List<LocalBean> localSaidaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local_saida);

        pcaContext = (PCAContext) getApplication();
        Button buttonRetListaLocalSaida = findViewById(R.id.buttonRetListaLocalSaida);
        Button buttonAtualListaLocalSaida = findViewById(R.id.buttonAtualListaLocalSaida);
        Button buttonAvancarListaLocalSaida = findViewById(R.id.buttonAvancarListaLocalSaida);

        ArrayList<String> itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("localSaidaList = pcaContext.getCirculacaoCTR().localSaidaList();\n" +
                "        for(LocalBean localBean : localSaidaList){\n" +
                "            itens.add(localBean.getDescrLocal());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        localSaidaListView = findViewById(R.id.listaLocalSaida);\n" +
                "        localSaidaListView.setAdapter(adapterList);", getLocalClassName());
        localSaidaList = pcaContext.getViagemCTR().localList();
        for(LocalBean localBean : localSaidaList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localSaidaListView = findViewById(R.id.listaLocalSaida);
        localSaidaListView.setAdapter(adapterList);
        localSaidaListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("localSaidaListView.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            LocalBean localBean = localSaidaList.get(position);\n" +
                    "            pcaContext.getCirculacaoCTR().setIdLocalSaidaCirculacao(localBean.getIdLocal());\n" +
                    "            localSaidaList.clear();\n" +
                    "            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaLocalDestinoActivity.class);", getLocalClassName());
            LocalBean localBean = localSaidaList.get(position);
            pcaContext.getViagemCTR().setIdLocalSaida(localBean.getIdLocal(), pcaContext.getVerTela());
            localSaidaList.clear();

            Intent it;
            if(pcaContext.getVerTela() == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getVerTela() == 1){\n" +
                        "                it = new Intent(ListaLocalSaidaActivity.this, ListaViagemActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalSaidaActivity.this, ListaViagemActivity.class);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                it = new Intent(ListaLocalDestinoActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalSaidaActivity.this, ListaDetalhesViagemActivity.class);
            }
            startActivity(it);
            finish();

        });

        buttonAvancarListaLocalSaida.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonAvancarListaLocalSaida.setOnClickListener(v -> {\n" +
                    "            pcaContext.getViagemCTR().setIdLocalSaida(0L, pcaContext.getVerTela());\n" +
                    "            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaViagemActivity.class);", getLocalClassName());
            pcaContext.getViagemCTR().setIdLocalSaida(0L, pcaContext.getVerTela());
            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaViagemActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetListaLocalSaida.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetListaLocalSaida.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaLocalDestinoActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaLocalDestinoActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaLocalSaida.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualListaLocalSaida.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalSaidaActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalSaidaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (conexaoWeb.verificaConexao(ListaLocalSaidaActivity.this)) {\n" +
                            "                    progressBar = new ProgressDialog(ListaLocalSaidaActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZNADO LOCAL...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalSaidaActivity.this\n" +
                            "                            , ListaLocalSaidaActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(ListaLocalSaidaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZNADO LOCAL...");
                    progressBar.show();
                    pcaContext.getViagemCTR().atualDadosLocal(ListaLocalSaidaActivity.this
                            , ListaLocalSaidaActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaLocalSaidaActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaLocalSaidaActivity.this);
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

    public void onBackPressed() {
    }

}