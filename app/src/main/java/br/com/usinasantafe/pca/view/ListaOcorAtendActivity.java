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
import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class ListaOcorAtendActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView ocorAtendListView;
    private PCAContext pcaContext;
    private List<OcorAtendBean> ocorAtendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ocor_atend);

        pcaContext = (PCAContext) getApplication();
        Button buttonRetListaOcorAtend = findViewById(R.id.buttonRetListaOcorAtend);
        Button buttonAtualListaOcorAtend = findViewById(R.id.buttonAtualListaOcorAtend);

        ArrayList<String> itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("ocorAtendList = pcaContext.getCirculacaoCTR().ocorAtendList();\n" +
                "        for(OcorAtendBean ocorAtendBean : ocorAtendList){\n" +
                "            itens.add(ocorAtendBean.getDescrOcorAtend());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        ocorAtendListView = findViewById(R.id.listaOcorAtend);\n" +
                "        ocorAtendListView.setAdapter(adapterList);", getLocalClassName());
        ocorAtendList = pcaContext.getViagemCTR().ocorAtendList();
        for(OcorAtendBean ocorAtendBean : ocorAtendList){
            itens.add(ocorAtendBean.getDescrOcorAtend());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ocorAtendListView = findViewById(R.id.listaOcorAtend);
        ocorAtendListView.setAdapter(adapterList);
        ocorAtendListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("ocorAtendListView.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            OcorAtendBean ocorAtendBean = ocorAtendList.get(position);\n" +
                    "            pcaContext.getViagemCTR().setIdOcorAtendViagem(ocorAtendBean.getIdOcorAtend());\n" +
                    "            ocorAtendList.clear();\n" +
                    "            Intent it = new Intent(ListaOcorAtendActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
            OcorAtendBean ocorAtendBean = ocorAtendList.get(position);
            pcaContext.getViagemCTR().setIdOcorAtendViagem(ocorAtendBean.getIdOcorAtend());
            ocorAtendList.clear();
            Intent it = new Intent(ListaOcorAtendActivity.this, ListaDetalhesViagemActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetListaOcorAtend.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetListaOcorAtend.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaOcorAtendActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaOcorAtendActivity.this, ListaDetalhesViagemActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaOcorAtend.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualListaOcorAtend.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOcorAtendActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOcorAtendActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(ListaOcorAtendActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO OCORRÊNCIA DE ATENDIMENTO...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaOcorAtendActivity.this\n" +
                            "                            , ListaOcorAtendActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(ListaOcorAtendActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO OCORRÊNCIA DE ATENDIMENTO...");
                    progressBar.show();
                    pcaContext.getViagemCTR().atualDadosLocal(ListaOcorAtendActivity.this
                            , ListaOcorAtendActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaOcorAtendActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaOcorAtendActivity.this);
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
}