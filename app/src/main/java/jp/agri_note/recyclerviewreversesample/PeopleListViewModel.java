package jp.agri_note.recyclerviewreversesample;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class PeopleListViewModel extends BaseObservable implements Parcelable {

    LinkedList<Person> people = new LinkedList<>();
    PeopleAdapter adapter;
    private PeopleListPresenter.OnPersonClickListener onMaleClickListener;
    private PeopleListPresenter.OnPersonClickListener onFemaleClickListener;

    public PeopleListViewModel() {
    }

    public void init(Context context) {
        this.adapter = new PeopleAdapter(context, people) {
            @Override
            protected void onItemClick(int position) {
                deleteItem(position);
            }
        };
    }

    public void addAllPeople(List<Person> people) {
        adapter.addAll(people);
        adapter.notifyDataSetChanged();
    }

    public void insertPersonToHead(Person person) {
        adapter.addFirst(person);
        adapter.notifyItemInserted(0);
    }

    private void deleteItem(int position) {
        adapter.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void setOnMaleClickListener(PeopleListPresenter.OnPersonClickListener onMaleClickListener) {
        this.onMaleClickListener = onMaleClickListener;
    }

    public void onMaleClick() {
        onMaleClickListener.addPerson();
    }

    public void setOnFemaleClickListener(PeopleListPresenter.OnPersonClickListener onFemaleClickListener) {
        this.onFemaleClickListener = onFemaleClickListener;
    }

    public void onFemaleClick() {
        onFemaleClickListener.addPerson();
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.people);
    }

    protected PeopleListViewModel(Parcel in) {
        this.people = new LinkedList<>();
        in.readList(this.people, Person.class.getClassLoader());
    }

    public static final Parcelable.Creator<PeopleListViewModel> CREATOR = new Parcelable.Creator<PeopleListViewModel>() {
        @Override
        public PeopleListViewModel createFromParcel(Parcel source) {
            return new PeopleListViewModel(source);
        }

        @Override
        public PeopleListViewModel[] newArray(int size) {
            return new PeopleListViewModel[size];
        }
    };
}
