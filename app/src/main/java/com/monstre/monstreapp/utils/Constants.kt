package com.monstre.monstreapp.utils

import com.monstre.monstreapp.R
import com.monstre.monstreapp.domain.model.*


val deviceList: ArrayList<Device> = arrayListOf(
    Device("Smart Warch", R.drawable.img_round_watch),
    Device("Desktop", R.drawable.img_round_desktop),
    Device("Phone", R.drawable.img_round_phone),
)

val mbtiList: ArrayList<Mbti> = arrayListOf(
    Mbti("intj", "https://storage.googleapis.com/neris/public/images/types/intj-architect.svg"),
    Mbti("intp", "https://storage.googleapis.com/neris/public/images/types/intp-logician.svg"),
    Mbti("entj", "https://storage.googleapis.com/neris/public/images/types/entj-commander.svg"),
    Mbti("entp", "https://storage.googleapis.com/neris/public/images/types/entp-debater.svg"),
    Mbti("infj", "https://storage.googleapis.com/neris/public/images/types/infj-advocate.svg"),
    Mbti("infp", "https://storage.googleapis.com/neris/public/images/types/infp-mediator.svg"),
    Mbti("enfj", "https://storage.googleapis.com/neris/public/images/types/enfj-protagonist.svg"),
    Mbti("enfp", "https://storage.googleapis.com/neris/public/images/types/enfp-campaigner.svg"),
    Mbti("istj", "https://storage.googleapis.com/neris/public/images/types/istj-logistician.svg"),
    Mbti("isfj", "https://storage.googleapis.com/neris/public/images/types/isfj-defender.svg"),
    Mbti("estj", "https://storage.googleapis.com/neris/public/images/types/estj-executive.svg"),
    Mbti("esfj", "https://storage.googleapis.com/neris/public/images/types/esfj-consul.svg"),
    Mbti("istp", "https://storage.googleapis.com/neris/public/images/types/istp-virtuoso.svg"),
    Mbti("isfp", "https://storage.googleapis.com/neris/public/images/types/isfp-adventurer.svg"),
    Mbti("estp", "https://storage.googleapis.com/neris/public/images/types/estp-entrepreneur.svg"),
    Mbti("esfp", "https://storage.googleapis.com/neris/public/images/types/esfp-entertainer.svg"),
)

val stressList: ArrayList<StressLevel> = arrayListOf(
    StressLevel("Mon", 20),
    StressLevel("Tue", 70),
    StressLevel("Wed", 60),
    StressLevel("Thu", 50),
    StressLevel("Fri", 60),
    StressLevel("Sat", 80),
    StressLevel("Sun", 90),
)

val suggestionList: ArrayList<Suggestion> = arrayListOf(
    Suggestion(
        R.drawable.img_suggestion_dance,
        "Relax your mind with  favourite music",
        "Relax your mind with  favourite music"
    ),
    Suggestion(
        R.drawable.img_suggestion_bike, "Take a break \n" +
                "to see\n" +
                "arround you", "Take a break \n" +
                "to see\n" +
                "arround you"
    ),
    Suggestion(
        R.drawable.img_suggestion_read, "Read your favourite \n" +
                "book", "Read your favourite \n" +
                "book"
    )
)

val notificationList: ArrayList<Notification> = arrayListOf(
    Notification(
        "Info",
        "Foto kamu sudah terverifikasi",
        "Foto kamu sudah kami terima, mohon tunggu konfirmasi selanjutnya"
    ),
    Notification(
        "Info",
        "Ada aktivitas login di perangkat baru",
        "Akunmu telah login melalui perangkat lenovo pada rabu, 21 Juli 2022 16:20 WIB. Kalau ini bukan kamu, amankan akunmu disini!"
    )
)