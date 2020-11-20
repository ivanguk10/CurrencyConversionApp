package com.example.currencyconversionapp.paid

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.databinding.DataFragmentBinding
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import com.example.currencyconversionapp.ui.MainViewModel
import kotlinx.android.synthetic.paid.data_fragment.*


class DataFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: DataFragmentBinding
    private lateinit var adapterTo: ArrayAdapter<*>
    lateinit var clipboardManager: ClipboardManager



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.data_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.valFrom.observe(viewLifecycleOwner, Observer { finalValFrom ->
            binding.editFrom.text = finalValFrom.toString()
        })

        viewModel.valTo.observe(viewLifecycleOwner, Observer { finalValTo ->
            binding.editTo.text = finalValTo.toString()
        })

        clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        binding.copy1Btn.setOnClickListener { viewModel.copy(1, clipboardManager) }
        binding.copy2Btn.setOnClickListener { viewModel.copy(2, clipboardManager) }
        binding.btnChange.setOnClickListener { viewModel.changeFields() }

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.measures,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerChooseUnit.adapter = adapter

        }



        binding.spinnerChooseUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                l: Long
            ) {
                    val measures: String = adapterView?.getItemAtPosition(position).toString()
                if (adapterView != null) {
                    Toast.makeText(adapterView.context, measures, Toast.LENGTH_SHORT).show()
                }
                    var mesFrom = 0
                    var mesTo = 0
                    when (measures) {
                        "CURRENCY" -> {
                            mesFrom = R.array.currencies
                            mesTo = R.array.currencies2
                        }
                        "DISTANCE" -> {
                            mesFrom = R.array.Distance
                            mesTo = R.array.Distance2
                        }
                        "WEIGHT" -> {
                            mesFrom = R.array.Weight
                            mesTo = R.array.Weight2
                        }

                    }
                    ArrayAdapter.createFromResource(
                        requireActivity(),
                        mesFrom,
                        android.R.layout.simple_spinner_item
                    ).also { adapterFrom ->
                        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spinnerFrom.adapter = adapterFrom
                    }

                    ArrayAdapter.createFromResource(
                        requireActivity(),
                        mesTo,
                        android.R.layout.simple_spinner_item
                    ).also { adapterTo ->
                        adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spinnerTo.adapter = adapterTo
                    }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val temp: String = binding.spinnerFrom.selectedItem.toString() +
                        binding.spinnerTo.selectedItem.toString()
                Log.e("test", temp)
                val strId: Int = resources.getIdentifier(temp, "string", requireActivity().packageName)
                Log.i("test1", strId.toString())
                Log.i("test2", getString(strId))
                viewModel.changeData(getString(strId).toFloat())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val temp: String = binding.spinnerFrom.selectedItem.toString() +
                        binding.spinnerTo.selectedItem.toString()
                Log.e("test", temp)
                val strId: Int = resources.getIdentifier(temp, "string", requireActivity().packageName)
                Log.i("test1", strId.toString())
                Log.i("test2", getString(strId))
                viewModel.changeData(getString(strId).toFloat())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return binding.root
    }
}


