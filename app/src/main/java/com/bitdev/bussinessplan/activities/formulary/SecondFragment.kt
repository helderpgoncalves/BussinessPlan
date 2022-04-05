package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

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

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parent = requireActivity() as FormularyActivity

        inputGroup["frais_etablissement"] = binding.fraisEtablissement
        inputGroup["frais_compteurs"] = binding.fraisCompteurs
        inputGroup["formations"] = binding.formations
        inputGroup["depot_marque"] = binding.depotMarque
        inputGroup["droits_entree"] = binding.droitsEntree
        inputGroup["commerce"] = binding.commerce
        inputGroup["droit_bail"] = binding.droitBail
        inputGroup["frais_dossier"] = binding.fraisDossier
        inputGroup["frais_notaire"] = binding.fraisNotaire
        inputGroup["elements_communication"] = binding.elementsCommunication
        inputGroup["immobilier"] = binding.immobilier
        inputGroup["travaux"] = binding.travaux
        inputGroup["materiel"] = binding.materiel
        inputGroup["materiel_bureau"] = binding.materielBureau
        inputGroup["stock_matieres"] = binding.stockMatieres
        inputGroup["tresorerie_depart"] = binding.tresorerieDepart
        inputGroup["duree_amortissement"] = binding.dureeAmortissement

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun update(){
        totalExpenses = 0.0
        for(input in inputGroup.values){
            if(input.text.isEmpty()) {
                continue
            }

            totalExpenses += input.text.toString().toDouble()
        }

        binding.total.text = totalExpenses.toString()

        if(binding.dureeAmortissement.text.toString() == ""){
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
        if(!this::parent.isInitialized) return

        for(input in inputGroup){
            parent.setData(input.key,input.value.text.toString())
        }

        findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
    }
}