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
        Button buttonRetListaAmbul = (Button) findViewById(R.id.buttonRetListaAmbul);
        Button buttonAtualListaAmbul = (Button) findViewById(R.id.buttonAtualListaAmbul);

        ArrayList<String> itens = new ArrayList<String>();

        equipList = pcaContext.getCirculacaoCTR().equipList();
        for(EquipBean equipBean : equipList){
            itens.add(equipBean.getNroEquip() + " - " + equipBean.getDescrClasseEquip());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ambulListView = (ListView) findViewById(R.id.listaViewAmbul);
        ambulListView.setAdapter(adapterList);

        ambulListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                EquipBean equipBean = equipList.get(position);
                pcaContext.getCirculacaoCTR().setIdEquipCirculacao(equipBean.getIdEquip());

                equipList.clear();

                Intent it = new Intent(ListaAmbulanciaActivity.this, ListaLocalSaidaActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetListaAmbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAmbulanciaActivity.this, LeitorColabActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAtualListaAmbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmbulanciaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaAmbulanciaActivity.this)) {

                            progressBar = new ProgressDialog(ListaAmbulanciaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO AMBULÂNCIA...");
                            progressBar.show();

                            pcaContext.getCirculacaoCTR().atualDadosEquip(ListaAmbulanciaActivity.this
                                    , ListaAmbulanciaActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmbulanciaActivity.this);
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