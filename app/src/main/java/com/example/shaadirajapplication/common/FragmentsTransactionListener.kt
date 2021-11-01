package com.example.shaadirajapplication.common

import androidx.fragment.app.Fragment

interface FragmentsTransactionListener {
    fun addReplaceFragment(
        fragment: Fragment,
        isReplaceFragment: Boolean,
        addToBackStack: Boolean,
        tag: String
    )
}