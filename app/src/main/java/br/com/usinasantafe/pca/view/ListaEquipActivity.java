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
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pca.util.ConnectNetwork;

public class ListaEquipActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView ambulListView;
    private PCAContext pcaContext;
    private List<EquipBean> equipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equip);

        pcaContext = (PCAContext) getApplication();
        Button buttonRetListaAmbul = findViewById(R.id.buttonRetListaAmbul);
        Button buttonAtualListaAmbul = findViewById(R.id.buttonAtualListaAmbul);

        ArrayList<String> itens = new ArrayList<>();

        LogProcessoDAO.getInstance().insertLogProcesso("equipList = pcaContext.getCirculacaoCTR().equipList();\n" +
                "        for(EquipBean equipBean : equipList){\n" +
                "            itens.add(equipBean.getNroEquip() + \" - \" + equipBean.getDescrClasseEquip());\n" +
                "        }\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        ambulListView = findViewById(R.id.listaViewAmbul);\n" +
                "        ambulListView.setAdapter(adapterList);", getLocalClassName());
        equipList = pcaContext.getViagemCTR().equipList();
        for(EquipBean equipBean : equipList){
            itens.add(equipBean.getNroEquip() + " - " + equipBean.getDescrClasseEquip());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ambulListView = findViewById(R.id.listaViewAmbul);
        ambulListView.setAdapter(adapterList);
        ambulListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("ambulListView.setOnItemClickListener((l, v, position, id) -> {\n" +
                    "            EquipBean equipBean = equipList.get(position);\n" +
                    "            pcaContext.getConfigCTR().setIdEquip(equipBean.getIdEquip());\n" +
                    "            equipList.clear();\n" +
                    "            Intent it = new Intent(ListaEquipActivity.this, ListaViagemActivity.class);", getLocalClassName());
            EquipBean equipBean = equipList.get(position);
            pcaContext.getConfigCTR().setIdEquip(equipBean.getIdEquip());
            equipList.clear();
            Intent it = new Intent(ListaEquipActivity.this, ListaViagemActivity.class);
            startActivity(it);
            finish();
            
        });

        buttonRetListaAmbul.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetListaAmbul.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaEquipActivity.this, LeitorUsuarioActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaEquipActivity.this, LeitorMotoristaActivity.class);
            startActivity(it);
            finish();
        });

        buttonAtualListaAmbul.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualListaAmbul.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmbulanciaActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEquipActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {", getLocalClassName());
                if (ActivityGeneric.connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ActivityGeneric.connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(ListaAmbulanciaActivity.this);\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO AMBULÂNCIA...\");\n" +
                            "                    progressBar.show();\n" +
                            "                    pcaContext.getCirculacaoCTR().atualDadosEquip(ListaAmbulanciaActivity.this\n" +
                            "                            , ListaAmbulanciaActivity.class, progressBar);", getLocalClassName());
                    progressBar = new ProgressDialog(ListaEquipActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO AMBULÂNCIA...");
                    progressBar.show();
                    pcaContext.getViagemCTR().atualDadosEquip(ListaEquipActivity.this
                            , ListaEquipActivity.class, progressBar);

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaAmbulanciaActivity.this);\n" +
                            "                    alerta1.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta1.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                    alerta1.setPositiveButton(\"OK\", (dialog1, which1) -> {});\n" +
                            "                    alerta1.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ListaEquipActivity.this);
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