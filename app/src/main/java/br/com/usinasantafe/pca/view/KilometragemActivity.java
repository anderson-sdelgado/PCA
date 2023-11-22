package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
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

        Button buttonOkKilometragem = findViewById(R.id.buttonOkPadrao);
        Button buttonCancKilometragem = findViewById(R.id.buttonCancPadrao);

        TextView textViewKilometragem = findViewById(R.id.textViewPadrao);
        if(pcaContext.getVerTela() == 1){
            textViewKilometragem.setText("KILOMETRAGEM INICIAL:");
        }
        else{
            textViewKilometragem.setText("KILOMETRAGEM FINAL:");
        }

        buttonOkKilometragem.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                String kilometragem = editTextPadrao.getText().toString();
                kilometragemNum = Double.valueOf(kilometragem.replace(",", "."));

                if (pcaContext.getVerTela() == 1) {

                    pcaContext.getCirculacaoCTR().setKmSaidaCirculacao(kilometragemNum);

                    Intent it = new Intent(KilometragemActivity.this, ListaInforActivity.class);
                    startActivity(it);

                } else {

                    pcaContext.getCirculacaoCTR().setKmRetornoCirculacao(kilometragemNum);
                    Intent it = new Intent(KilometragemActivity.this, MenuInicialActivity.class);
                    startActivity(it);

                }
                finish();

            }

        });

        buttonCancKilometragem.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it;
        if (pcaContext.getVerTela() == 1) {
            it = new Intent(KilometragemActivity.this, ListaOcorAtendActivity.class);
        } else {
            it = new Intent(KilometragemActivity.this, ListaInforActivity.class);
        }
        startActivity(it);
        finish();
    }

}