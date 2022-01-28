package com.example.mechelin.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchStore(
    var storename: String = "",
    var storeaddress: String = "",
    var phone: String,                  // 전화번호
    var x: String,                      // X 좌표값 혹은 longitude
    var y: String,
//    var storeid: Int
): Parcelable
