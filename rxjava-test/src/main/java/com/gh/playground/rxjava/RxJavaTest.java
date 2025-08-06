package com.gh.playground.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaTest {
    public static void main(String[] args) {
        Observable<String> howdy = Observable.just("Howdy!");

        Disposable disposable = howdy.subscribe(System.out::println);
        System.out.println("disposed = <" + disposable.isDisposed() + ">");

        disposable = Flowable.just("Hello world").subscribe(System.out::println);
        System.out.println("disposed = <" + disposable.isDisposed() + ">");

        Flowable<Integer> flow = Flowable.range(1, 6)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0).flatMap(Flowable::just);

        disposable = flow.subscribe(System.out::println);
        System.out.println("disposed = <" + disposable.isDisposed() + ">");
    }
}
