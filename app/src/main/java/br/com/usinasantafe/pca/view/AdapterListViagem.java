package br.com.usinasantafe.pca.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pca.R;
import br.com.usinasantafe.pca.control.ViagemCTR;
import br.com.usinasantafe.pca.model.bean.variaveis.ViagemBean;

public class AdapterListViagem extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewDescrSaida;
    private TextView textViewDescrDestino;

    public AdapterListViagem(Context context, List itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_viagem, null);
        textViewDescrDestino = view.findViewById(R.id.textViewItemDescrDestino);
        textViewDescrSaida = view.findViewById(R.id.textViewItemDescrSaida);

        ViagemBean viagemBean = (ViagemBean) itens.get(position);
        ViagemCTR viagemCTR = new ViagemCTR();
        textViewDescrDestino.setText(viagemCTR.getLocal(viagemBean.getIdLocalDestinoViagem()).getDescrLocal());
        if(viagemBean.getIdLocalSaidaViagem() > 0){
            textViewDescrSaida.setText(viagemCTR.getLocal(viagemBean.getIdLocalSaidaViagem()).getDescrLocal());
        } else {
            textViewDescrSaida.setText("-");
        }

        return view;
    }

}
