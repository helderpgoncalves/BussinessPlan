package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String, EditText> = mutableMapOf()
    private var totalExpenses = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        parent = requireActivity() as FormularyActivity

        inputGroup["apport_personnel"] = binding.apportPersonnel
        inputGroup["apports_nature"] = binding.apportsNature
        inputGroup["pret_01"] = binding.pret01
        inputGroup["pret_02"] = binding.pret02
        inputGroup["pret_03"] = binding.pret03
        inputGroup["subvention_01"] = binding.subvention01
        inputGroup["subvention_02"] = binding.subvention02
        inputGroup["autre_financement"] = binding.autreFinancement


        update()

        for(input in inputGroup.values){
            input.doAfterTextChanged {
                update()
            }
        }

        binding.nextPageBtn.setOnClickListener {
            nextPage()
        }
    }

    private fun update(){
        totalExpenses = 0.0
        for(input in inputGroup.values){
            if(input.text.isEmpty()) {
                continue
            }

            totalExpenses += input.text.toString().toDouble()
        }

    }

    private fun disableButton(){
        binding.nextPageBtn.isClickable = false
        binding.nextPageBtn.alpha = .5f
        binding.nextPageBtn.isEnabled = false
    }

    private fun enableButton(){
        binding.nextPageBtn.isClickable = true
        binding.nextPageBtn.alpha = 1f
        binding.nextPageBtn.isEnabled = true
    }

    private fun nextPage(){
        if(!this::parent.isInitialized) return

        for(input in inputGroup){
            parent.setData(input.key,input.value.text.toString())
        }

        findNavController().navigate(R.id.action_ThirdFragment_to_FourthFragment)
    }

}