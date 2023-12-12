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

public class ListaLocalDestinoActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView localDestinoListView;
    private PCAContext pcaContext;
    private List<LocalBean> localDestinoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local_destino);

        pcaContext = (PCAContext) getApplication();
        Button buttonRetListaLocalDest = findViewById(R.id.buttonRetListaLocalDest);
        Button buttonAtualListaLocalDest = findViewById(R.id.buttonAtualListaLocalDest);

        ArrayList<String> itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("localDestinoList = pcaContext.getCirculacaoCTR().localList();\n" +
                "        for(LocalBean localBean : localDestinoList){\n" +
                "            itens.add(localBean.getDescrLocal());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        localDestinoListView = findViewById(R.id.listaLocalDest);\n" +
                "        localDestinoListView.setAdapter(adapterList);", getLocalClassName());
        localDestinoList = pcaContext.getViagemCTR().localList();
        for(LocalBean localBean : localDestinoList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localDestinoListView = findViewById(R.id.listaLocalDest);
        localDestinoListView.setAdapter(adapterList);
        localDestinoListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("localDestinoListView.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            LocalBean localBean = localDestinoList.get(position);\n" +
                    "            pcaContext.getViagemCTR().setIdLocalDestino(localBean.getIdLocal(), pcaContext.getVerTela());\n" +
                    "            localDestinoList.clear();", getLocalClassName());
            LocalBean localBean = localDestinoList.get(position);
            pcaContext.getViagemCTR().setIdLocalDestino(localBean.getIdLocal(), pcaContext.getVerTela());
            localDestinoList.clear();

            Intent it;
            if(pcaContext.getVerTela() == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getVerTela() == 1){\n" +
                        "                it = new Intent(ListaLocalDestinoActivity.this, ListaLocalSaidaActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalDestinoActivity.this, ListaLocalSaidaActivity.class);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                it = new Intent(ListaLocalDestinoActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalDestinoActivity.this, ListaDetalhesViagemActivity.class);
            }
            startActivity(it);
            finish();

        });

        buttonRetListaLocalDest.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetListaLocalDest.setOnClickListener(v -> {", getLocalClassName());
            Intent it;
            if(pcaContext.getVerTela() == 1){
                LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getVerTela() == 1){\n" +
                        "                it = new Intent(ListaLocalDestinoActivity.this, ListaViagemActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalDestinoActivity.this, ListaViagemActivity.class);
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                it = new Intent(ListaLocalDestinoActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                it = new Intent(ListaLocalDestinoActivity.this, ListaDetalhesViagemActivity.class);
            }
            startActivity(it);
            finish();
        });

        buttonAtualListaLocalDest.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualListaLocalDest.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalDestinoActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalDestinoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(ListaLocalDestinoActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO LOCAL...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalDestinoActivity.this\n" +
                            "                            , ListaLocalDestinoActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(ListaLocalDestinoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO LOCAL...");
                    progressBar.show();
                    pcaContext.getViagemCTR().atualDadosLocal(ListaLocalDestinoActivity.this
                            , ListaLocalDestinoActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaLocalDestinoActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaLocalDestinoActivity.this);
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