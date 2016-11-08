package jp.agri_note.recyclerviewreversesample;

import java.util.List;

public interface PeopleListPresenter {
    void showPeople(List<Person> people);
    void onPersonAdded(Person person);

    void setOnMaleClickListener(OnPersonClickListener callback);
    void setOnFemaleClickListener(OnPersonClickListener callback);

    interface OnPersonClickListener {
        void addPerson();
    }
}
