package br.com.usinasantafe.pca.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.bean.variaveis.CirculacaoBean;

public class ListaInformacaoActivity extends ActivityGeneric {

    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_informacao);

        pcaContext = (PCAContext) getApplication();
        Button buttonFinalizarViagem = (Button) findViewById(R.id.buttonFinalizarViagem);
        Button buttonCancViagem = (Button) findViewById(R.id.buttonCancViagem);

        CirculacaoBean circulacaoBean = pcaContext.getCirculacaoCTR().getCirculacaoAberta();

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("DATA/HORA SAÍDA:\n" + circulacaoBean.getDthrSaidaCirculacao());
        itens.add("MOTORISTA:\n" + circulacaoBean.getMatricMotoristaCirculacao() + " - "
                + pcaContext.getCirculacaoCTR().getColab(circulacaoBean.getMatricMotoristaCirculacao()).getNomeColab());
        itens.add("PACIENTE:\n" + circulacaoBean.getMatricPacienteCirculacao() + " - "
                + pcaContext.getCirculacaoCTR().getColab(circulacaoBean.getMatricPacienteCirculacao()).getNomeColab());
        itens.add("EQUIPAMENTO: " + pcaContext.getCirculacaoCTR().getEquip(circulacaoBean.getIdEquipCirculacao()).getNroEquip());
        itens.add("KM SAÍDA: " + circulacaoBean.getKmSaidaCirculacao());
        itens.add("LOCAL SAÍDA: " + pcaContext.getCirculacaoCTR().getLocal(circulacaoBean.getIdLocalSaidaCirculacao()));
        itens.add("LOCAL DESTINO: " + pcaContext.getCirculacaoCTR().getLocal(circulacaoBean.getIdLocalDestinoCirculacao()));
        itens.add("OCORRÊNCIA ATENDIMENTO:\n" + pcaContext.getCirculacaoCTR().getOcorAtend(circulacaoBean.getIdOcorAtendCirculacao()).getDescrOcorAtend());

        buttonFinalizarViagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pcaContext.setVerTela(2);
                Intent it = new Intent(ListaInformacaoActivity.this, KilometragemActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancViagem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaInformacaoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE CANCELAR A VIAGEM? ISSO FARÁ PERDER TODOS OS DADOS JÁ SALVO.");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pcaContext.getCirculacaoCTR().delCircAberto();

                        Intent it = new Intent(ListaInformacaoActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

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