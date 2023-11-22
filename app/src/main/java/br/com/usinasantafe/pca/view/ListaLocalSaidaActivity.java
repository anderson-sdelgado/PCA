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
        Button buttonRetListaLocalSaida = findViewById(R.id.buttonRetListaLocalSaida);
        Button buttonAtualListaLocalSaida = findViewById(R.id.buttonAtualListaLocalSaida);

        ArrayList<String> itens = new ArrayList<>();

        localSaidaList = pcaContext.getCirculacaoCTR().localSaidaList();
        for(LocalBean localBean : localSaidaList){
            itens.add(localBean.getDescrLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        localSaidaListView = findViewById(R.id.listaLocalSaida);
        localSaidaListView.setAdapter(adapterList);

        localSaidaListView.setOnItemClickListener((l, v, position, id) -> {

            LocalBean localBean = localSaidaList.get(position);
            pcaContext.getCirculacaoCTR().setIdLocalSaidaCirculacao(localBean.getIdLocal());

            localSaidaList.clear();

            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaLocalDestinoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetListaLocalSaida.setOnClickListener(v -> {
            Intent it = new Intent(ListaLocalSaidaActivity.this, ListaAmbulanciaActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaLocalSaida.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaLocalSaidaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaLocalSaidaActivity.this)) {

                    progressBar = new ProgressDialog(ListaLocalSaidaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZNADO LOCAL...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosLocal(ListaLocalSaidaActivity.this
                            , ListaLocalSaidaActivity.class, progressBar);

                } else {

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