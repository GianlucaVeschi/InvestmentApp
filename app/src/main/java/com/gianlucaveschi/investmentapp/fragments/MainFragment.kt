package com.gianlucaveschi.investmentapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.gianlucaveschi.investmentapp.R
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), View.OnClickListener {

    //Properties declared as having a non-null type must be initialized in the constructor but
    // if we want to avoid null checks one can use Late Initialization.
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        //UI Binding with synthetics
        view.view_transactions_btn.setOnClickListener(this)
        view.send_money_btn.setOnClickListener(this)
        view.view_balance_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) { //The question mark allows View to be set to nulle can be set null
        //Double-bang operator !! will throw NullPointerException if the value is null.
        when( v!!.id){
            //Defining where to go through an action from the navigation Graph
            R.id.view_transactions_btn  -> navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
            R.id.send_money_btn         -> navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
            R.id.view_balance_btn       -> navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment)
        }
    }

}
