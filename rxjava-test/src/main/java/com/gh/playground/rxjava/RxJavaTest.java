package com.gh.playground.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class RxJavaTest {
    public static void main(String[] args) {
        Observable<String> hello = Observable.just("Howdy!");
        hello.subscribe(System.out::println);

        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
