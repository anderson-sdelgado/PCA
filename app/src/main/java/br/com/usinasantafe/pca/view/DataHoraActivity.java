package br.com.usinasantafe.pca.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.os.Bundle;

import com.google.common.base.Strings;

import java.util.Calendar;

import br.com.usinasantafe.pca.PCAContext;
import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.model.dao.LogProcessoDAO;

public class DataHoraActivity extends ActivityGeneric {

    EditText editTextData, editTextHora;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private PCAContext pcaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        pcaContext = (PCAContext) getApplication();
        Button buttonSelecionarData = (Button)findViewById(R.id.buttonSelecionarData);
        Button buttonSelecionarHora = (Button)findViewById(R.id.buttonSelecionarHora);
        editTextData = (EditText)findViewById(R.id.editTextData);
        editTextHora = (EditText)findViewById(R.id.editTextHora);

        Button buttonOkDataHora = (Button)findViewById(R.id.buttonOkDataHora);
        Button buttonCancDataHora = (Button)findViewById(R.id.buttonCancDataHora);

        buttonSelecionarData.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonSelecionarData.setOnClickListener(v -> {\n" +
                    "            final Calendar c = Calendar.getInstance();\n" +
                    "            mYear = c.get(Calendar.YEAR);\n" +
                    "            mMonth = c.get(Calendar.MONTH);\n" +
                    "            mDay = c.get(Calendar.DAY_OF_MONTH);\n" +
                    "            @SuppressLint({\"SetTextI18n\", \"ResourceType\"})\n" +
                    "            DatePickerDialog datePickerDialog = new DatePickerDialog(this, 2,\n" +
                    "                    (view, year, monthOfYear, dayOfMonth) -> editTextData.setText(Strings.padStart(String.valueOf(dayOfMonth), 2, '0') + \"/\" + Strings.padStart(String.valueOf((monthOfYear + 1)), 2, '0') + \"/\" + year), mYear, mMonth, mDay);\n" +
                    "            datePickerDialog.show();", getLocalClassName());
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            @SuppressLint({"SetTextI18n", "ResourceType"})
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, 2,
                    (view, year, monthOfYear, dayOfMonth) -> editTextData.setText(Strings.padStart(String.valueOf(dayOfMonth), 2, '0') + "/" + Strings.padStart(String.valueOf((monthOfYear + 1)), 2, '0') + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        buttonSelecionarHora.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonSelecionarHora.setOnClickListener(v -> {\n" +
                    "            final Calendar c = Calendar.getInstance();\n" +
                    "            mHour = c.get(Calendar.HOUR_OF_DAY);\n" +
                    "            mMinute = c.get(Calendar.MINUTE);\n" +
                    "            TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, (view, hourOfDay, minute) -> editTextHora.setText(Strings.padStart(String.valueOf(hourOfDay), 2, '0') + \":\" + Strings.padStart(String.valueOf(minute), 2, '0')), mHour, mMinute, true);\n" +
                    "            timePickerDialog.show();", getLocalClassName());
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, (view, hourOfDay, minute) -> editTextHora.setText(Strings.padStart(String.valueOf(hourOfDay), 2, '0') + ":" + Strings.padStart(String.valueOf(minute), 2, '0')), mHour, mMinute, true);
            timePickerDialog.show();
        });

        buttonOkDataHora.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDataHora.setOnClickListener(v -> {", getLocalClassName());
            if((!editTextData.getText().toString().equals("")) && (!editTextHora.getText().toString().equals(""))){
                LogProcessoDAO.getInstance().insertLogProcesso("if((!editTextData.getText().toString().equals(\"\")) && (!editTextHora.getText().toString().equals(\"\"))){\n" +
                        "                String dthr = editTextData.getText().toString() + ' ' + editTextHora.getText().toString();\n" +
                        "                pcaContext.getViagemCTR().setDthrViagem(dthr);\n" +
                        "                Intent it = new Intent(DataHoraActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
                String dthr = editTextData.getText().toString() + ' ' + editTextHora.getText().toString();
                pcaContext.getViagemCTR().setDthrViagem(dthr);
                Intent it = new Intent(DataHoraActivity.this, ListaDetalhesViagemActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(DataHoraActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"POR FAVOR, ADICIONE A DATA E HORA PARA INSERÇÃO DAS MESMAS.\");\n" +
                        "                alerta.setPositiveButton(\"OK\", (dialog, which) -> {});\n" +
                        "                alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(DataHoraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, ADICIONE A DATA E HORA PARA INSERÇÃO DAS MESMAS.");
                alerta.setPositiveButton("OK", (dialog, which) -> {});
                alerta.show();
            }

        });

        buttonCancDataHora.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDataHora.setOnClickListener(v -> {\n" +
                    "            Intent it = new Intent(DataHoraActivity.this, ListaDetalhesViagemActivity.class);", getLocalClassName());
            Intent it = new Intent(DataHoraActivity.this, ListaDetalhesViagemActivity.class);
            startActivity(it);
            finish();
        });


    }

}