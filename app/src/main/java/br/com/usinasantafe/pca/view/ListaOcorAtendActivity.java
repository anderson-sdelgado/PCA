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
        Button buttonRetListaLocalDest = (Button) findViewById(R.id.buttonRetListaLocalDest);
        Button buttonAtualListaLocalDest = (Button) findViewById(R.id.buttonAtualListaLocalDest);

        ArrayList<String> itens = new ArrayList<String>();

        ocorAtendList = pcaContext.getCirculacaoCTR().ocorAtendList();
        for(OcorAtendBean ocorAtendBean : ocorAtendList){
            itens.add(ocorAtendBean.getDescrOcorAtend());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ocorAtendListView = (ListView) findViewById(R.id.listaOcorAtend);
        ocorAtendListView.setAdapter(adapterList);

        ocorAtendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                OcorAtendBean ocorAtendBean = ocorAtendList.get(position);
                pcaContext.getCirculacaoCTR().setIdOcorAtendCirculacao(ocorAtendBean.getIdOcorAtend());

                ocorAtendList.clear();

                pcaContext.setVerTela(1);
                Intent it = new Intent(ListaOcorAtendActivity.this, KilometragemActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetListaLocalDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaOcorAtendActivity.this, ListaLocalDestinoActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAtualListaLocalDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOcorAtendActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaOcorAtendActivity.this)) {

                            progressBar = new ProgressDialog(ListaOcorAtendActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO OCORRÊNCIA DE ATENDIMENTO...");
                            progressBar.show();

                            pcaContext.getCirculacaoCTR().atualDadosLocal(ListaOcorAtendActivity.this
                                    , ListaOcorAtendActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOcorAtendActivity.this);
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
}