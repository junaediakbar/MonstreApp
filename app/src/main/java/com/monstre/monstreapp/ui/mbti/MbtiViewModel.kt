package com.monstre.monstreapp.ui.mbti

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.monstre.monstreapp.domain.Mbti
import com.monstre.monstreapp.utils.mbtiList

class MbtiViewModel : ViewModel() {

    private val _selectedMbti = MutableLiveData<String>()
    val selectedMbti :LiveData<String> = _selectedMbti

    private val _listMbti = MutableLiveData<ArrayList<Mbti>>()
    val listMbti :LiveData<ArrayList<Mbti>> = _listMbti

    init {
        _listMbti.value = mbtiList
        _selectedMbti.value =""
    }

    fun selectItem(selectedMbti : String){
        _selectedMbti.value = selectedMbti
        Log.e("apalah ","successfully changed to $selectedMbti")
    }

}