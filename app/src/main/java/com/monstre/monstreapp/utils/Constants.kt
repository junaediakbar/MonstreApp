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
    StressLevel("Mon", 0),
    StressLevel("Tue", 0),
    StressLevel("Wed", 0),
    StressLevel("Thu", 0),
    StressLevel("Fri", 0),
    StressLevel("Sat", 0),
    StressLevel("Sun", 0),
)

val stressListMonth: ArrayList<StressLevel> = arrayListOf(
    StressLevel("01", 0),
    StressLevel("02", 0),
    StressLevel("03", 0),
    StressLevel("04", 0),
    StressLevel("05", 0),
    StressLevel("06", 0),
    StressLevel("07", 0),
    StressLevel("08", 0),
    StressLevel("09", 0),
    StressLevel("10", 0),
    StressLevel("11", 0),
    StressLevel("12", 0),
    StressLevel("13", 0),
    StressLevel("14", 0),
    StressLevel("15", 0),
    StressLevel("16", 0),
    StressLevel("17", 0),
    StressLevel("18", 0),
    StressLevel("19", 0),
    StressLevel("20", 0),
    StressLevel("21", 0),
    StressLevel("22", 0),
    StressLevel("23", 0),
    StressLevel("24", 0),
    StressLevel("25", 0),
    StressLevel("26", 0),
    StressLevel("27", 0),
    StressLevel("28", 0),
    StressLevel("29", 0),
    StressLevel("30", 0),
    StressLevel("31", 0),
)

val stressKind: ArrayList<String> = arrayListOf(
    "Relax",
    "Calm",
    "Anxious",
    "Stressed"
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