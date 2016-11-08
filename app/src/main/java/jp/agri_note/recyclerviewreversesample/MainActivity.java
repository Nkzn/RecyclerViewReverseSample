package jp.agri_note.recyclerviewreversesample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import jp.agri_note.recyclerviewreversesample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements PeopleListPresenter {

    PeopleListViewModel peopleListViewModel;

    PeopleApplicationService applicationService;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            peopleListViewModel = new PeopleListViewModel();
        } else {
            peopleListViewModel = savedInstanceState.getParcelable("viewmodel");
        }

        peopleListViewModel.init(this);
        binding.setPeopleListViewModel(peopleListViewModel);

        CheckList checkList = new CheckList();
        applicationService = new PeopleApplicationService(this, new PeopleRepository(), checkList);
        applicationService.init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("viewmodel", peopleListViewModel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showPeople(List<Person> people) {
        peopleListViewModel.addAllPeople(people);
    }

    @Override
    public void onPersonAdded(Person person) {
        peopleListViewModel.insertPersonToHead(person);
        binding.recyclerview.smoothScrollToPosition(0);
    }

    @Override
    public void setOnMaleClickListener(OnPersonClickListener callback) {
        peopleListViewModel.setOnMaleClickListener(callback);
    }

    @Override
    public void setOnFemaleClickListener(OnPersonClickListener callback) {
        peopleListViewModel.setOnFemaleClickListener(callback);
    }

}
