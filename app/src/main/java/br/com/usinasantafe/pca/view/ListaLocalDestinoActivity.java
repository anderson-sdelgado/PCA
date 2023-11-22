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

        localDestinoList = pcaContext.getCirculacaoCTR().localDestinoList();
        for(LocalBean localBean : localDestinoList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localDestinoListView = findViewById(R.id.listaLocalDest);
        localDestinoListView.setAdapter(adapterList);

        localDestinoListView.setOnItemClickListener((l, v, position, id) -> {

            LocalBean localBean = localDestinoList.get(position);
            pcaContext.getCirculacaoCTR().setIdLocalDestinoCirculacao(localBean.getIdLocal());

            localDestinoList.clear();

            Intent it = new Intent(ListaLocalDestinoActivity.this, ListaOcorAtendActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetListaLocalDest.setOnClickListener(v -> {
            Intent it = new Intent(ListaLocalDestinoActivity.this, ListaLocalSaidaActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaLocalDest.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalDestinoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaLocalDestinoActivity.this)) {

                    progressBar = new ProgressDialog(ListaLocalDestinoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO LOCAL...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalDestinoActivity.this
                            , ListaLocalDestinoActivity.class, progressBar);

                } else {

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