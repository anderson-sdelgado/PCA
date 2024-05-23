package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pca.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

public class ListaDetalhesViagemActivity extends ActivityGeneric {

    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_detalhes_viagem);

        pcaContext = (PCAContext) getApplication();

        Button buttonFinalizarViagem = findViewById(R.id.buttonFinalizarViagem);
        Button buttonCancViagem = findViewById(R.id.buttonCancViagem);
        Button buttonRetornarViagem = findViewById(R.id.buttonRetornarViagem);
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
        ViagemBean viagemBean = pcaContext.getViagemCTR().getViagem();

        ArrayList<String> itens = new ArrayList<>();

        if(viagemBean.getDthrSaidaViagem() != null){
            itens.add("DATA/HORA SAÍDA:\n" + viagemBean.getDthrSaidaViagem());
        } else {
            itens.add("DATA/HORA SAÍDA:");
        }
        if(viagemBean.getDthrChegadaViagem() != null){
            itens.add("DATA/HORA CHEGADA:\n" + viagemBean.getDthrChegadaViagem());
        } else {
            itens.add("DATA/HORA CHEGADA:");
        }
        if(viagemBean.getMatricPacienteViagem() != null){
            itens.add("PACIENTE:\n" + viagemBean.getMatricPacienteViagem() + " - "
                    + pcaContext.getViagemCTR().getColab(viagemBean.getMatricPacienteViagem()).getNomeColab());
        } else {
            itens.add("PACIENTE:");
        }
        if(viagemBean.getKmSaidaViagem() != null){
            itens.add("KM SAÍDA: " + viagemBean.getKmSaidaViagem());
        } else {
            itens.add("KM SAÍDA:");
        }
        if(viagemBean.getKmChegadaViagem() != null){
            itens.add("KM CHEGADA: " + viagemBean.getKmChegadaViagem());
        } else {
            itens.add("KM CHEGADA:");
        }
        if(viagemBean.getIdLocalDestinoViagem() != null){
            itens.add("LOCAL DESTINO: " + pcaContext.getViagemCTR().getLocal(viagemBean.getIdLocalDestinoViagem()).getDescrLocal());
        } else {
            itens.add("LOCAL DESTINO:");
        }
        if((viagemBean.getIdLocalSaidaViagem() != null) && (viagemBean.getIdLocalSaidaViagem() > 0L)){
            itens.add("LOCAL SAÍDA: " + pcaContext.getViagemCTR().getLocal(viagemBean.getIdLocalSaidaViagem()).getDescrLocal());
        } else {
            itens.add("LOCAL SAÍDA:");
        }
        if(viagemBean.getIdOcorAtendViagem() != null) {
            itens.add("OCORRÊNCIA ATENDIMENTO:\n" + pcaContext.getViagemCTR().getOcorAtend(viagemBean.getIdOcorAtendViagem()).getDescrOcorAtend());
        } else {
            itens.add("OCORRÊNCIA ATENDIMENTO:");
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ListView listViewDetalhes = findViewById(R.id.listViewDetalhes);
        listViewDetalhes.setAdapter(adapterList);
        listViewDetalhes.setOnItemClickListener((l, v, position, id) -> {
            switch (position) {
                case 0: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 0: {\n" +
                            "                    pcaContext.getViagemCTR().setTipoDthrSelecionado(1);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, DataHoraActivity.class);", getLocalClassName());
                    pcaContext.getViagemCTR().setTipoSelecionado(1);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, DataHoraActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 1: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 1: {\n" +
                            "                    pcaContext.getViagemCTR().setTipoDthrSelecionado(2);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, DataHoraActivity.class);", getLocalClassName());
                    pcaContext.getViagemCTR().setTipoSelecionado(2);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, DataHoraActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 2: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 2: {\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, LeitorPacienteActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, LeitorPacienteActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 3: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 3: {\n" +
                            "                    pcaContext.getViagemCTR().setTipoKilometragemSelecionado(1);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, KilometragemActivity.class);", getLocalClassName());
                    pcaContext.getViagemCTR().setTipoSelecionado(1);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, KilometragemActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 4: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 4: {\n" +
                            "                    pcaContext.getViagemCTR().setTipoKilometragemSelecionado(2);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, KilometragemActivity.class);", getLocalClassName());
                    pcaContext.getViagemCTR().setTipoSelecionado(2);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, KilometragemActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 5: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 5: {\n" +
                            "                    pcaContext.setVerTela(2);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaLocalDestinoActivity.class);", getLocalClassName());
                    pcaContext.setVerTela(2);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaLocalDestinoActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 6: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 6: {\n" +
                            "                    pcaContext.setVerTela(2);\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaLocalSaidaActivity.class);", getLocalClassName());
                    pcaContext.setVerTela(2);
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaLocalSaidaActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case 7: {
                    LogProcessoDAO.getInstance().insertLogProcesso("case 7: {\n" +
                            "                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaOcorAtendActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaOcorAtendActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
            }
        });

        buttonFinalizarViagem.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancViagem.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInforActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE FINALIZAR A VIAGEM?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaDetalhesViagemActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE FINALIZAR A VIAGEM?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {\n" +
                        "                pcaContext.getViagemCTR().delCircAberto();\n" +
                        "                Intent it = new Intent(ListaInforActivity.this, MenuInicialActivity.class);", getLocalClassName());
                pcaContext.getViagemCTR().fecharViagem();
                Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaViagemActivity.class);
                startActivity(it);
                finish();
            });
            alerta.setPositiveButton("NÃO", (dialog, which) -> {});
            alerta.show();
        });

        buttonCancViagem.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancViagem.setOnClickListener(v -> {\n" +
                    "            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInforActivity.this);\n" +
                    "            alerta.setTitle(\"ATENÇÃO\");\n" +
                    "            alerta.setMessage(\"DESEJA REALMENTE CANCELAR A VIAGEM? ISSO FARÁ PERDER TODOS OS DADOS JÁ SALVO.\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaDetalhesViagemActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE CANCELAR A VIAGEM? ISSO FARÁ PERDER TODOS OS DADOS JÁ SALVO.");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", (dialog, which) -> {\n" +
                        "                pcaContext.getViagemCTR().delCircAberto();\n" +
                        "                Intent it = new Intent(ListaInforActivity.this, MenuInicialActivity.class);", getLocalClassName());
                pcaContext.getViagemCTR().excluirViagem();
                Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaViagemActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {});
            alerta.show();
        });

        buttonRetornarViagem.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetornarViagem.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaViagemActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaDetalhesViagemActivity.this, ListaViagemActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}