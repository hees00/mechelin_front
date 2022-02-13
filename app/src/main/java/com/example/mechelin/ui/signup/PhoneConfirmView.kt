package com.example.mechelin.ui.signup

interface PhoneConfirmView {
    fun onconfirmrequestSuccess(response: PhoneResult)

    fun onconfirmrequestFailure(message: String)

    fun  onCheckNumSuccess(response: ConfrimNumResult)

    fun onCheckNumFailure(message: String)
}