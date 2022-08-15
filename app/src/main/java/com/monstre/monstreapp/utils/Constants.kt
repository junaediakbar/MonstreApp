package com.monstre.monstreapp.utils

import com.monstre.monstreapp.R
import com.monstre.monstreapp.domain.Device
import com.monstre.monstreapp.domain.Mbti


val deviceList : ArrayList<Device> = arrayListOf(
    Device("Smart Warch", R.drawable.img_round_watch),
    Device("Desktop", R.drawable.img_round_desktop),
    Device("Phone", R.drawable.img_round_phone),
)

val mbtiList : ArrayList<Mbti> = arrayListOf(
    Mbti("intj","https://storage.googleapis.com/neris/public/images/types/intj-architect.svg"),
    Mbti("intp","https://storage.googleapis.com/neris/public/images/types/intp-logician.svg"),
    Mbti("entj","https://storage.googleapis.com/neris/public/images/types/entj-commander.svg"),
    Mbti("entp","https://storage.googleapis.com/neris/public/images/types/entp-debater.svg"),
    Mbti("infj","https://storage.googleapis.com/neris/public/images/types/infj-advocate.svg"),
    Mbti("infp","https://storage.googleapis.com/neris/public/images/types/infp-mediator.svg"),
    Mbti("enfj","https://storage.googleapis.com/neris/public/images/types/enfj-protagonist.svg"),
    Mbti("enfp","https://storage.googleapis.com/neris/public/images/types/enfp-campaigner.svg"),
    Mbti("istj","https://storage.googleapis.com/neris/public/images/types/istj-logistician.svg"),
    Mbti("isfj","https://storage.googleapis.com/neris/public/images/types/isfj-defender.svg"),
    Mbti("estj","https://storage.googleapis.com/neris/public/images/types/estj-executive.svg"),
    Mbti("esfj","https://storage.googleapis.com/neris/public/images/types/esfj-consul.svg"),
    Mbti("istp","https://storage.googleapis.com/neris/public/images/types/istp-virtuoso.svg"),
    Mbti("isfp","https://storage.googleapis.com/neris/public/images/types/isfp-adventurer.svg"),
    Mbti("estp","https://storage.googleapis.com/neris/public/images/types/estp-entrepreneur.svg"),
    Mbti("esfp","https://storage.googleapis.com/neris/public/images/types/esfp-entertainer.svg"),
)