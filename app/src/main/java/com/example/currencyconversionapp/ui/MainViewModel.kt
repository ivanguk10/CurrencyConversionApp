package com.example.currencyconversionapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {
    private val _valFrom = MutableLiveData<String>()
    val valFrom: LiveData<String>
        get() = _valFrom

    private val _valTo = MutableLiveData<String>()
    val valTo: LiveData<String>
        get() = _valTo

    private var percent: Float = 1.0F


    init {
        _valFrom.value = ""
    }

    fun changeData(percent: Float) {
        this.percent = percent
        if (_valFrom.value != 0.toString()) {
            convertedField()
        }
    }

    private fun clear() {
        _valFrom.value = ""
        _valTo.value = ""
    }

    fun deleteSymbol() {
        if (_valFrom.value!!.length <= 1 ) {
            clear()
        } else {
            _valFrom.value = _valFrom.value!!.substring(0, _valFrom.value!!.length - 1)
            convertedField()
        }
        Log.i("valueFrom", _valFrom.value!!)
        Log.i("valueTo", _valTo.value!!)
    }

    fun addNumb(numb: String) {
        _valFrom.value = _valFrom.value + numb
        convertedField()
    }

    fun convertedField() {
        if (_valFrom.value!!.length >= 1 ) {
            _valTo.value = ((_valFrom.value!!.toFloat() * percent).toString())
        }
    }

    fun addDot() {
        if (_valFrom.value?.contains(".")!! || _valFrom.value!!.isEmpty()) {
            //_valFrom.value.toString()
        }
        else {
            _valFrom.value =_valFrom.value + "."
        }
    }

    fun changeFields() {
        if (_valFrom.value != "") {
            _valFrom.value = _valTo.value
            convertedField()
        }
    }

    fun copy(field: Int, clipboardManager: ClipboardManager) {
        when (field) {
            1 -> {
                val clipData: ClipData = ClipData.newPlainText("text", _valFrom.value)
                clipboardManager.setPrimaryClip(clipData)
            }
            2 -> {
                val clipData: ClipData = ClipData.newPlainText("text", _valTo.value)
                clipboardManager.setPrimaryClip(clipData)
            }

        }

    }


}