package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

public class ListaViagemActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private List<ViagemBean> viagemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagem);

        pcaContext = (PCAContext) getApplication();

        Button buttonInserirViagem = findViewById(R.id.buttonInserirViagem);
        Button buttonFinalizar = findViewById(R.id.buttonFinalizar);
        TextView textViewMotorista = findViewById(R.id.textViewMotorista);
        TextView textViewEquip = findViewById(R.id.textViewEquip);

        LogProcessoDAO.getInstance().insertLogProcesso("ColabBean colabBean = pcaContext.getViagemCTR().getColab(pcaContext.getConfigCTR().getConfig().getMatricUsuario());\n" +
                "        EquipBean equipBean = pcaContext.getViagemCTR().getEquip(pcaContext.getConfigCTR().getConfig().getIdEquip());\n" +
                "        textViewMotorista.setText(\"MOTORISTA: \" + colabBean.getMatricColab() + \" - \" + colabBean.getNomeColab());\n" +
                "        textViewEquip.setText(\"VEÍCULO: \" + equipBean.getNroEquip() + \" - \" + equipBean.getDescrClasseEquip());", getLocalClassName());
        ColabBean colabBean = pcaContext.getViagemCTR().getColab(pcaContext.getConfigCTR().getConfig().getMatricMotorista());
        EquipBean equipBean = pcaContext.getViagemCTR().getEquip(pcaContext.getConfigCTR().getConfig().getIdEquip());
        textViewMotorista.setText("MOTORISTA: " + colabBean.getMatricColab() + " - " + colabBean.getNomeColab());
        textViewEquip.setText("VEÍCULO: " + equipBean.getNroEquip() + " - " + equipBean.getDescrClasseEquip());

        LogProcessoDAO.getInstance().insertLogProcesso("circulacaoList = pcaContext.getCirculacaoCTR().circulacaoAbertoList();\n" +
                "        AdapterListViagem adapterList = new AdapterListViagem(this, circulacaoList);\n" +
                "        ListView listViewViagem = findViewById(R.id.listViewViagem);\n" +
                "        listViewViagem.setAdapter(adapterList);", getLocalClassName());
        viagemList = pcaContext.getViagemCTR().viagemAbertaList();

        AdapterListViagem adapterList = new AdapterListViagem(this, viagemList);
        ListView listViewViagem = findViewById(R.id.listViewViagem);
        listViewViagem.setAdapter(adapterList);
        listViewViagem.setOnItemClickListener((l, v, position, id) -> {
            LogProcessoDAO.getInstance().insertLogProcesso("CirculacaoBean circulacaoBean = circulacaoList.get(position);\n" +
                    "            pcaContext.getCirculacaoCTR().setIdCircSelect(circulacaoBean.getIdCirculacao());\n" +
                    "            Intent it = new Intent(ListaViagemActivity.this, ListaInforActivity.class);", getLocalClassName());
            ViagemBean viagemBean = viagemList.get(position);
            pcaContext.getViagemCTR().setIdViagemSelecionado(viagemBean.getIdViagem());
            Intent it = new Intent(ListaViagemActivity.this, ListaDetalhesViagemActivity.class);
            startActivity(it);
            finish();
        });

        buttonInserirViagem.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonInserirViagem.setOnClickListener(v -> {\n" +
                    "            pcaContext.setVerTela(1);\n" +
                    "            Intent it = new Intent(ListaViagemActivity.this, ListaLocalDestinoActivity.class);", getLocalClassName());
            pcaContext.setVerTela(1);
            Intent it = new Intent(ListaViagemActivity.this, ListaLocalDestinoActivity.class);
            startActivity(it);
            finish();
        });

        buttonFinalizar.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancViagem.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInforActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE FINALIZAR A VIAGEM?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaViagemActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE FINALIZAR TODAS AS VIAGENS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {\n" +
                        "                pcaContext.getViagemCTR().fecharViagemAberta();\n" +
                        "                Intent it = new Intent(ListaViagemActivity.this, TelaInicialActivity.class);", getLocalClassName());
                pcaContext.getViagemCTR().fecharViagemAberta();
                Intent it = new Intent(ListaViagemActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            });
            alerta.setPositiveButton("NÃO", (dialog, which) -> {});
            alerta.show();
        });

    }
}