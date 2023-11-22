package br.com.usinasantafe.pca.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;
import br.com.usinasantafe.pca.util.Tempo;

public class ListaInforActivity extends ActivityGeneric {

    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_infor);

        pcaContext = (PCAContext) getApplication();
        Button buttonFinalizarViagem = findViewById(R.id.buttonFinalizarViagem);
        Button buttonCancViagem = findViewById(R.id.buttonCancViagem);

        CirculacaoBean circulacaoBean = pcaContext.getCirculacaoCTR().getCirculacaoAberta();

        ArrayList<String> itens = new ArrayList<>();

        itens.add("DATA/HORA SAÍDA:\n" + circulacaoBean.getDthrSaidaCirculacao());
        itens.add("MOTORISTA:\n" + circulacaoBean.getMatricMotoristaCirculacao() + " - "
                + pcaContext.getCirculacaoCTR().getColab(circulacaoBean.getMatricMotoristaCirculacao()).getNomeColab());
        itens.add("PACIENTE:\n" + circulacaoBean.getMatricPacienteCirculacao() + " - "
                + pcaContext.getCirculacaoCTR().getColab(circulacaoBean.getMatricPacienteCirculacao()).getNomeColab());
        itens.add("EQUIPAMENTO: " + pcaContext.getCirculacaoCTR().getEquip(circulacaoBean.getIdEquipCirculacao()).getNroEquip());
        itens.add("KM SAÍDA: " + circulacaoBean.getKmSaidaCirculacao());
        itens.add("LOCAL SAÍDA: " + pcaContext.getCirculacaoCTR().getLocal(circulacaoBean.getIdLocalSaidaCirculacao()).getDescrLocal());
        itens.add("LOCAL DESTINO: " + pcaContext.getCirculacaoCTR().getLocal(circulacaoBean.getIdLocalDestinoCirculacao()).getDescrLocal());
        itens.add("OCORRÊNCIA ATENDIMENTO:\n" + pcaContext.getCirculacaoCTR().getOcorAtend(circulacaoBean.getIdOcorAtendCirculacao()).getDescrOcorAtend());

        AdapterList adapterList = new AdapterList(this, itens);
        ListView inforListView = (ListView) findViewById(R.id.listaViewInfo);
        inforListView.setAdapter(adapterList);

        buttonFinalizarViagem.setOnClickListener(v -> {
            pcaContext.setVerTela(2);
            Intent it = new Intent(ListaInforActivity.this, KilometragemActivity.class);
            startActivity(it);
            finish();
        });

        buttonCancViagem.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInforActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE CANCELAR A VIAGEM? ISSO FARÁ PERDER TODOS OS DADOS JÁ SALVO.");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                pcaContext.getCirculacaoCTR().delCircAberto();

                Intent it = new Intent(ListaInforActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {});

            alerta.show();
        });

    }

    public void onBackPressed() {
    }

}