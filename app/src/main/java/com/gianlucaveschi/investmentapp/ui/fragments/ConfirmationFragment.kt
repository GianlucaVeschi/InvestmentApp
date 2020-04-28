package com.gianlucaveschi.investmentapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gianlucaveschi.investmentapp.models.Money

import com.gianlucaveschi.investmentapp.R
import kotlinx.android.synthetic.main.fragment_confirmation.view.*

class ConfirmationFragment : Fragment() {

    lateinit var recipient : String
    lateinit var money: Money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = requireArguments()!!.getString("recipient").toString()
        money = requireArguments().getParcelable("amount")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amount = money!!.amount
        val confirmationMessage = "You have sent €$amount to $recipient"
        view.confirmation_message.text = confirmationMessage
    }
}
