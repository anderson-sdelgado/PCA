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
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.util.ConexaoWeb;

public class ListaAmbulanciaActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView ambulListView;
    private PCAContext pcaContext;
    private List<EquipBean> equipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ambulancia);

        pcaContext = (PCAContext) getApplication();
        Button buttonRetListaAmbul = findViewById(R.id.buttonRetListaAmbul);
        Button buttonAtualListaAmbul = findViewById(R.id.buttonAtualListaAmbul);

        ArrayList<String> itens = new ArrayList<>();

        equipList = pcaContext.getCirculacaoCTR().equipList();
        for(EquipBean equipBean : equipList){
            itens.add(equipBean.getNroEquip() + " - " + equipBean.getDescrClasseEquip());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ambulListView = findViewById(R.id.listaViewAmbul);
        ambulListView.setAdapter(adapterList);

        ambulListView.setOnItemClickListener((l, v, position, id) -> {

            EquipBean equipBean = equipList.get(position);
            pcaContext.getCirculacaoCTR().setIdEquipCirculacao(equipBean.getIdEquip());

            equipList.clear();

            Intent it = new Intent(ListaAmbulanciaActivity.this, ListaLocalSaidaActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetListaAmbul.setOnClickListener(v -> {
            Intent it = new Intent(ListaAmbulanciaActivity.this, LeitorColabActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaAmbul.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmbulanciaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAmbulanciaActivity.this)) {

                    progressBar = new ProgressDialog(ListaAmbulanciaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO AMBULÂNCIA...");
                    progressBar.show();

                    pcaContext.getCirculacaoCTR().atualDadosEquip(ListaAmbulanciaActivity.this
                            , ListaAmbulanciaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaAmbulanciaActivity.this);
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