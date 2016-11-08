package jp.agri_note.recyclerviewreversesample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by nakagawa on 2016/11/08.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private final int VIEWTYPE_MALE = 0;
    private final int VIEWTYPE_FEMALE = 1;

    final LinkedList<Person> people;
    final LayoutInflater inflater;

    protected void onItemClick(int position) {
    }

    public PeopleAdapter(Context context, LinkedList<Person> people) {
        this.people = people;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEWTYPE_MALE) {
            final View view = inflater.inflate(R.layout.male_view, parent, false);
            return new PersonViewHolder(view);
        }

        if (viewType == VIEWTYPE_FEMALE) {
            final View view = inflater.inflate(R.layout.female_view, parent, false);
            return new PersonViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        final Person item = people.get(position);

        holder.name.setText(item.name);
        holder.age.setText(String.format(Locale.JAPAN, "%1$d", item.age));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    @Override
    public int getItemViewType(int position) {

        final Person person = people.get(position);

        if (person instanceof Male) {
            return VIEWTYPE_MALE;
        }

        if (person instanceof Female) {
            return VIEWTYPE_FEMALE;
        }

        throw new IllegalArgumentException("Unknown sex!");
    }

    public void add(Person person) {
        this.people.add(person);
    }

    public void addAll(Person... people) {
        this.people.addAll(Arrays.asList(people));
    }

    public void addAll(List<Person> people) {
        this.people.addAll(people);
    }

    public void addFirst(Person person) {
        this.people.addFirst(person);
    }

    public void clear() {
        this.people.clear();
    }

    public void remove(int position) {
        this.people.remove(position);
    }
}
