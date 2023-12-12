package br.com.usinasantafe.pca.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

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

        if(pcaContext.getViagemCTR().getTipoSelecionado() == 1){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcaContext.getVerTela() == 1){\n" +
                    "            textViewKilometragem.setText(\"KILOMETRAGEM SAÍDA:\");", getLocalClassName());
            textViewKilometragem.setText("KILOMETRAGEM SAÍDA:");
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            textViewKilometragem.setText(\"KILOMETRAGEM DESTINO:\");", getLocalClassName());
            textViewKilometragem.setText("KILOMETRAGEM CHEGADA:");
        }

        buttonOkKilometragem.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkKilometragem.setOnClickListener(v -> {", getLocalClassName());
            if (!editTextPadrao.getText().toString().equals("")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {\n" +
                        "                String kilometragem = editTextPadrao.getText().toString();\n" +
                        "                kilometragemNum = Double.valueOf(kilometragem.replace(\",\", \".\"));\n" +
                        "                pcaContext.getViagemCTR().setKilometragemViagem(kilometragemNum);\n" +
                        "                Intent it = new Intent(KilometragemActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                String kilometragem = editTextPadrao.getText().toString();
                kilometragemNum = Double.valueOf(kilometragem.replace(",", "."));
                pcaContext.getViagemCTR().setKilometragemViagem(kilometragemNum);
                Intent it = new Intent(KilometragemActivity.this, ListaDetalhesViagemActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancKilometragem.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancKilometragem.setOnClickListener(v -> {\n" +
                        "            if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));", getLocalClassName());
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "        Intent it = new Intent(KilometragemActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
        Intent it = new Intent(KilometragemActivity.this, ListaDetalhesViagemActivity.class);
        startActivity(it);
        finish();
    }

}