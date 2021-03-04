package com.example.nut.ui.home;

import androidx.fragment.app.Fragment;

import com.example.nut.database.Task;
import com.example.nut.ui.home.model.HomeRepository;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private HomeRepository mRepository;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        mRepository = new HomeRepository(((Fragment) view).getContext());
    }

    @Override
    public void getTodoList(Callback callback) {
        Disposable subscribe = mRepository.getTaskList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onReceiveData, callback::onError);
    }

    @Override
    public void deleteTask(Task task, Callback<Integer> callback) {
        mRepository.deleteTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        callback.onReceiveData(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(e);
                    }
                });
    }

    @Override
    public void addTask(Task task, Callback<Void> callback) {
        mRepository.addTask(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        callback.onReceiveData(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(e);
                    }
                });
    }

    @Override
    public void updateTask(Task task, Callback<Void> callback) {
        mRepository.updateTask(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        callback.onReceiveData(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onError(e);
                    }
                });
    }

    public interface Callback<T> {
        void onReceiveData(T data);

        void onError(Throwable throwable);
    }
}
