package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;

public class KilometragemActivity extends ActivityGeneric {

    private PCAContext pcaContext;
    private Double kilometragemNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilometragem);

        pcaContext = (PCAContext) getApplication();

        Button buttonOkKilometragem = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancKilometragem = (Button) findViewById(R.id.buttonCancPadrao);

        TextView textViewKilometragem = (TextView) findViewById(R.id.textViewPadrao);
        if(pcaContext.getVerTela() == 1){
            textViewKilometragem.setText("KILOMETRAGEM INICIAL:");
        }
        else{
            textViewKilometragem.setText("KILOMETRAGEM FINAL:");
        }

        buttonOkKilometragem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String kilometragem = editTextPadrao.getText().toString();
                    kilometragemNum = Double.valueOf(kilometragem.replace(",", "."));

                    if (pcaContext.getVerTela() == 1) {

                        pcaContext.getCirculacaoCTR().setKmSaidaCirculacao(kilometragemNum);
                        Intent it = new Intent(KilometragemActivity.this, ListaInformacaoActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        pcaContext.getCirculacaoCTR().setKmRetornoCirculacao(kilometragemNum);
                        Intent it = new Intent(KilometragemActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancKilometragem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        if (pcaContext.getVerTela() == 1) {
            Intent it = new Intent(KilometragemActivity.this, ListaOcorAtendActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(KilometragemActivity.this, ListaInformacaoActivity.class);
            startActivity(it);
            finish();
        }
    }

}