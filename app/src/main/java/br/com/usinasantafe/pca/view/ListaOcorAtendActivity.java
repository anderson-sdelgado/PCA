package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.LocalBean;
import br.com.usinasantafe.pca.model.bean.estaticas.OcorAtendBean;
import br.com.usinasantafe.pca.util.ConexaoWeb;

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

        ocorAtendList = pcaContext.getCirculacaoCTR().ocorAtendList();
        for(OcorAtendBean ocorAtendBean : ocorAtendList){
            itens.add(ocorAtendBean.getDescrOcorAtend());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ocorAtendListView = findViewById(R.id.listaOcorAtend);
        ocorAtendListView.setAdapter(adapterList);

        ocorAtendListView.setOnItemClickListener((l, v, position, id) -> {

            OcorAtendBean ocorAtendBean = ocorAtendList.get(position);
            pcaContext.getCirculacaoCTR().setIdOcorAtendCirculacao(ocorAtendBean.getIdOcorAtend());

            ocorAtendList.clear();

            pcaContext.setVerTela(1);
            Intent it = new Intent(ListaOcorAtendActivity.this, KilometragemActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetListaOcorAtend.setOnClickListener(v -> {
            Intent it = new Intent(ListaOcorAtendActivity.this, ListaLocalDestinoActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaOcorAtend.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOcorAtendActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaOcorAtendActivity.this)) {

                    progressBar = new ProgressDialog(ListaOcorAtendActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO OCORRÊNCIA DE ATENDIMENTO...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaOcorAtendActivity.this
                            , ListaOcorAtendActivity.class, progressBar);

                } else {

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