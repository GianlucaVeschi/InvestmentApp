package com.gianlucaveschi.investmentapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

//BigDecimal is the best data structure to handle money
@Parcelize
data class Money(val amount: BigDecimal) : Parcelable  {

}