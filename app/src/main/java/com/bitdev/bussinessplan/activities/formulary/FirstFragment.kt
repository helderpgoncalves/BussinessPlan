package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.bitdev.bussinessplan.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private var inputGroup: MutableList<EditText> = mutableListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputGroup.add(binding.prenomNom)
        inputGroup.add(binding.intitule)
        inputGroup.add(binding.statutJuridique)
        inputGroup.add(binding.numeroTelephone)
        inputGroup.add(binding.adresseEmail)
        inputGroup.add(binding.ville)
        inputGroup.add(binding.marchandisesOuServices)

        updateButton()

        for(input in inputGroup){
            input.doAfterTextChanged {
                updateButton()
            }
        }
    }

    private fun updateButton(){
        var isEmpty = false
        for(input in inputGroup){
            if(input.text.toString().isEmpty()){
                isEmpty = true
                break;
            }
        }

        if(isEmpty && !binding.nextPageBtn.isEnabled) return

        if(isEmpty){
            binding.nextPageBtn.isClickable = false
            binding.nextPageBtn.alpha = .5f
            binding.nextPageBtn.isEnabled = false
        }else{
            binding.nextPageBtn.isClickable = true
            binding.nextPageBtn.alpha = 1f
            binding.nextPageBtn.isEnabled = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}