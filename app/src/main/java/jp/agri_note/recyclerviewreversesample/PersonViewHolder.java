package jp.agri_note.recyclerviewreversesample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nakagawa on 2016/11/08.
 */
class PersonViewHolder extends RecyclerView.ViewHolder {

    final TextView name;
    final TextView age;

    public PersonViewHolder(View itemView) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.tv_name);
        this.age = (TextView) itemView.findViewById(R.id.tv_age);
    }
}
