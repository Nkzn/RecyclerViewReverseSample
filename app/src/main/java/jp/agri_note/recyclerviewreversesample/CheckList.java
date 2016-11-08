package jp.agri_note.recyclerviewreversesample;

import java.util.HashSet;
import java.util.Set;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

public class CheckList implements Action1<Emitter<Set<Long>>> {
    private Set<Long> checkedIds = new HashSet<>();

    Observable<Set<Long>> observable;
    Emitter<Set<Long>> emitter;

    public Observable<Set<Long>> asObservable() {
        if (observable == null) {
            observable = Observable.fromEmitter(this, Emitter.BackpressureMode.BUFFER);
        }
        return observable;
    }

    public void add(long id) {
        checkedIds.add(id);
        emitter.onNext(checkedIds);
    }

    public boolean isChecked(long id) {
        return checkedIds.contains(id);
    }

    public void delete(long id) {
        checkedIds.remove(id);
        emitter.onNext(checkedIds);
    }

    @Override
    public void call(Emitter<Set<Long>> emitter) {
        this.emitter = emitter;
    }
}
