package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String,EditText> = mutableMapOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parent = requireActivity() as FormularyActivity

        inputGroup["nom"] = binding.prenomNom
        inputGroup["intitule"] = binding.intitule
        inputGroup["statut_juridique"] = binding.statutJuridique
        inputGroup["telephone"] = binding.numeroTelephone
        inputGroup["email"] = binding.adresseEmail
        inputGroup["ville"] = binding.ville
        inputGroup["type"] = binding.marchandisesOuServices


        val spinner = binding.marchandisesOuServicesSpinner

        ArrayAdapter.createFromResource(
            view.context, R.array.type, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                binding.marchandisesOuServices.setText("${p?.getItemAtPosition(pos)}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        updateButton()

        for(input in inputGroup.values){
            input.doAfterTextChanged {
                updateButton()
            }
        }

        binding.nextPageBtn.setOnClickListener {
            nextPage()
        }

    }

    private fun anyEmpty(): Boolean {
        for(input in inputGroup.values){
            if(input.text.toString().isEmpty()){
                return true
            }
        }
        return false
    }

    private fun updateButton(){
        val isEmpty = anyEmpty()

        if(isEmpty && !binding.nextPageBtn.isEnabled) return

        if(isEmpty){
            disableButton()
        }else{
            enableButton()
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
        if(!this::parent.isInitialized || anyEmpty()) return

        for(input in inputGroup){
            parent.setData(input.key,input.value.text.toString())
        }

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}