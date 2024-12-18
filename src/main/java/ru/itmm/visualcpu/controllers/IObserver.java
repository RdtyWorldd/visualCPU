package ru.itmm.visualcpu.controllers;

public interface IObserver<T> {
    void event(T model);
}
