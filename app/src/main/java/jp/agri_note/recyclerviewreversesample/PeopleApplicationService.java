package jp.agri_note.recyclerviewreversesample;

import java.util.List;

public class PeopleApplicationService {

    private PeopleListPresenter presenter;
    private PeopleRepository peopleRepository;

    CheckList checkList;

    public PeopleApplicationService(PeopleListPresenter presenter, PeopleRepository peopleRepository, CheckList checkList) {
        this.presenter = presenter;
        this.peopleRepository = peopleRepository;
        this.checkList = checkList;
    }

    public void init() {
        List<Person> people = peopleRepository.values();
        presenter.showPeople(people);

        presenter.setOnMaleClickListener(() -> {
            final Male male = peopleRepository.newMale();
            presenter.onPersonAdded(male);

            checkList.add(1);
        });

        presenter.setOnFemaleClickListener(() -> {
            final Female female = peopleRepository.newFemale();
            presenter.onPersonAdded(female);
        });

        checkList.asObservable()
                .subscribe(checkedIds -> {
                    // presenter.updateCheckState(checkedIds);
                }, throwable -> {
                    // presenter.showErrorMessage();
                });
    }

}
