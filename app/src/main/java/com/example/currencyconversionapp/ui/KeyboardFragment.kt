package com.example.currencyconversionapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.databinding.KeyboardFragmentBinding
import kotlinx.android.synthetic.main.keyboard_fragment.*


class KeyboardFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: KeyboardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.keyboard_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.btn1.setOnClickListener { viewModel.addNumb("1") }
        binding.btn2.setOnClickListener { viewModel.addNumb("2") }
        binding.btn3.setOnClickListener { viewModel.addNumb("3") }
        binding.btn4.setOnClickListener { viewModel.addNumb("4") }
        binding.btn5.setOnClickListener { viewModel.addNumb("5") }
        binding.btn6.setOnClickListener { viewModel.addNumb("6") }
        binding.btn7.setOnClickListener { viewModel.addNumb("7") }
        binding.btn8.setOnClickListener { viewModel.addNumb("8") }
        binding.btn9.setOnClickListener { viewModel.addNumb("9") }
        binding.btn0.setOnClickListener { viewModel.addNumb("0") }
        binding.btnDel.setOnClickListener { viewModel.deleteSymbol() }
        binding.btn.setOnClickListener { viewModel.addDot() }

        return binding.root
    }



//    private val viewModel by viewModels<MainViewModel>()
//
//    private lateinit var Button1: Button
//    private lateinit var Button2: Button
//    private lateinit var Button3: Button
//    private lateinit var Button4: Button
//    private lateinit var Button5: Button
//    private lateinit var Button6: Button
//    private lateinit var Button7: Button
//    private lateinit var Button8: Button
//    private lateinit var Button9: Button
//    private lateinit var Button0: Button
//    private lateinit var ButtonPoint: Button
//    private lateinit var ButtonDel: Button


}
