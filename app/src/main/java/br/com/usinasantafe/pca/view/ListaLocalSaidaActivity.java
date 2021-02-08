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
import br.com.usinasantafe.pca.util.ConexaoWeb;

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
        Button buttonRetListaLocalSaida = (Button) findViewById(R.id.buttonRetListaLocalSaida);
        Button buttonAtualListaLocalSaida = (Button) findViewById(R.id.buttonAtualListaLocalSaida);

        ArrayList<String> itens = new ArrayList<String>();

        localSaidaList = pcaContext.getCirculacaoCTR().localSaidaList();
        for(LocalBean localBean : localSaidaList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localSaidaListView = (ListView) findViewById(R.id.listaLocalSaida);
        localSaidaListView.setAdapter(adapterList);

        localSaidaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LocalBean localBean = localSaidaList.get(position);
                pcaContext.getCirculacaoCTR().setIdLocalSaidaCirculacao(localBean.getIdLocal());

                localSaidaList.clear();

                Intent it = new Intent(ListaLocalSaidaActivity.this, ListaLocalDestinoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetListaLocalSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaLocalSaidaActivity.this, ListaAmbulanciaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAtualListaLocalSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalSaidaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaLocalSaidaActivity.this)) {

                            progressBar = new ProgressDialog(ListaLocalSaidaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZNADO LOCAL...");
                            progressBar.show();

                            pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalSaidaActivity.this
                                    , ListaLocalSaidaActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalSaidaActivity.this);
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

    public void onBackPressed() {
    }

}