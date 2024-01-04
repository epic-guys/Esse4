package org.epic_guys.esse4.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.epic_guys.esse4.R;
import org.epic_guys.esse4.models.Appello;
import org.epic_guys.esse4.models.AppelloLibretto;

import java.util.List;

public class ListAdapter extends ArrayAdapter<AppelloLibretto> {

    private List<AppelloLibretto> list;

    public ListAdapter(@NonNull Context context, @NonNull List<AppelloLibretto> list) {
        super(context, R.layout.list_item, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        AppelloLibretto item = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ((TextView)view.findViewById(R.id.name_exam)).setText(item.getDescrizioneAttivitaDidattica());
        ((TextView)view.findViewById(R.id.exam_date)).setText(item.getDataOraEsame().format(Appello.getDateTimeFormatter()));
        ((TextView)view.findViewById(R.id.exam_host)).setText(item.getPresidenteNomeCognome());

        return view;
    }

    public List<AppelloLibretto> getList() {
        return list;
    }
}
