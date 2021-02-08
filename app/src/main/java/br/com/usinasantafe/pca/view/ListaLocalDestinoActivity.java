package br.com.usinasantafe.pca.view;

import androidx.appcompat.app.AppCompatActivity;

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
        Button buttonRetListaLocalDest = (Button) findViewById(R.id.buttonRetListaLocalDest);
        Button buttonAtualListaLocalDest = (Button) findViewById(R.id.buttonAtualListaLocalDest);

        ArrayList<String> itens = new ArrayList<String>();

        localDestinoList = pcaContext.getCirculacaoCTR().localDestinoList();
        for(LocalBean localBean : localDestinoList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localDestinoListView = (ListView) findViewById(R.id.listaLocalDest);
        localDestinoListView.setAdapter(adapterList);

        localDestinoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LocalBean localBean = localDestinoList.get(position);
                pcaContext.getCirculacaoCTR().setIdLocalDestinoCirculacao(localBean.getIdLocal());

                localDestinoList.clear();

                Intent it = new Intent(ListaLocalDestinoActivity.this, ListaOcorAtendActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetListaLocalDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaLocalDestinoActivity.this, ListaLocalSaidaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAtualListaLocalDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalDestinoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaLocalDestinoActivity.this)) {

                            progressBar = new ProgressDialog(ListaLocalDestinoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO LOCAL...");
                            progressBar.show();

                            pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalDestinoActivity.this
                                    , ListaLocalDestinoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalDestinoActivity.this);
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