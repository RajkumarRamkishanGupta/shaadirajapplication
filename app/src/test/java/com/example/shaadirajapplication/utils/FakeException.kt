package com.example.shaadirajapplication.utils

//this needed as it wont throw the cached exception
class FakeException(var data: String) : IllegalStateException(data) {
}