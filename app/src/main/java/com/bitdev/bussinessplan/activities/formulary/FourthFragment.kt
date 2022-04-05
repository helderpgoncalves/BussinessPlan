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
import com.bitdev.bussinessplan.databinding.FragmentFourthBinding
import com.bitdev.bussinessplan.databinding.FragmentThirdBinding


class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String, EditText> = mutableMapOf()
    private var totalFirstYear = 0.0
    private var totalSecondYear = 0.0
    private var totalThirdYear = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        inputGroup["assurances_01"] = binding.assurances01
        inputGroup["assurances_02"] = binding.assurances02
        inputGroup["assurances_03"] = binding.assurances03

        inputGroup["internet_01"] = binding.internet01
        inputGroup["internet_02"] = binding.internet02
        inputGroup["internet_03"] = binding.internet03

        inputGroup["abonnements_01"] = binding.abonnements01
        inputGroup["abonnements_02"] = binding.abonnements02
        inputGroup["abonnements_03"] = binding.abonnements03

        inputGroup["transports_01"] = binding.transports01
        inputGroup["transports_02"] = binding.transports02
        inputGroup["transports_03"] = binding.transports03

        inputGroup["deplacement_01"] = binding.deplacement01
        inputGroup["deplacement_02"] = binding.deplacement02
        inputGroup["deplacement_03"] = binding.deplacement03

        inputGroup["eau_01"] = binding.eau01
        inputGroup["eau_02"] = binding.eau02
        inputGroup["eau_03"] = binding.eau03

        inputGroup["mutuelle_01"] = binding.mutuelle01
        inputGroup["mutuelle_02"] = binding.mutuelle02
        inputGroup["mutuelle_03"] = binding.mutuelle03

        inputGroup["fournitures_01"] = binding.fournitures01
        inputGroup["fournitures_02"] = binding.fournitures02
        inputGroup["fournitures_03"] = binding.fournitures03

        inputGroup["entretien_01"] = binding.entretien01
        inputGroup["entretien_02"] = binding.entretien02
        inputGroup["entretien_03"] = binding.entretien03

        inputGroup["nettoyage_01"] = binding.nettoyage01
        inputGroup["nettoyage_02"] = binding.nettoyage02
        inputGroup["nettoyage_03"] = binding.nettoyage03

        inputGroup["loyer_01"] = binding.loyer01
        inputGroup["loyer_02"] = binding.loyer02
        inputGroup["loyer_03"] = binding.loyer03

        inputGroup["comptable_01"] = binding.comptable01
        inputGroup["comptable_02"] = binding.comptable02
        inputGroup["comptable_03"] = binding.comptable03

        inputGroup["bancaires_01"] = binding.bancaires01
        inputGroup["bancaires_02"] = binding.bancaires02
        inputGroup["bancaires_03"] = binding.bancaires03

        inputGroup["taxes_01"] = binding.taxes01
        inputGroup["taxes_02"] = binding.taxes02
        inputGroup["taxes_03"] = binding.taxes03

        parent = requireActivity() as FormularyActivity


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
        totalFirstYear = 0.0
        totalSecondYear = 0.0
        totalThirdYear = 0.0

        for(input in inputGroup){
            if(input.value.text.isEmpty()) {
                continue
            }

            val year = input.key.subSequence(input.key.indexOf("_0"),input.key.length)


            when(year.toString()){
                "_01" -> totalFirstYear += input.value.text.toString().toDouble()
                "_02" -> totalSecondYear += input.value.text.toString().toDouble()
                "_03" -> totalThirdYear += input.value.text.toString().toDouble()
            }

        }

        binding.total01.text = totalFirstYear.toString()
        binding.total02.text = totalSecondYear.toString()
        binding.total03.text = totalThirdYear.toString()

    }

    private fun nextPage(){
        if(!this::parent.isInitialized) return

        for(input in inputGroup){
            parent.setData(input.key,input.value.text.toString())
        }

        if(parent.getBusinessType() == "Services"){
            findNavController().navigate(R.id.action_FourthFragment_to_fifthServicesFragment)
        }else{
            findNavController().navigate(R.id.action_FourthFragment_to_fifthMarchandiseFragment)
        }
    }
}