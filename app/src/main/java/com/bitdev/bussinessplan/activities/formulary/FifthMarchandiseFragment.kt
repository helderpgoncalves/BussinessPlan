package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.bitdev.bussinessplan.R
import com.bitdev.bussinessplan.databinding.FragmentFifthMerchandiseBinding
import com.bitdev.bussinessplan.databinding.FragmentFourthBinding
import com.bitdev.bussinessplan.databinding.FragmentThirdBinding


class FifthMarchandiseFragment : Fragment() {

    private var _binding: FragmentFifthMerchandiseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String, EditText> = mutableMapOf()

    private var totalsGroup: MutableMap<String, TextView> = mutableMapOf()

    private var totals: Array<Double> = Array(12) {0.0}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthMerchandiseBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        parent = requireActivity() as FormularyActivity

        inputGroup["jours_01"] = binding.jours01
        inputGroup["jours_02"] = binding.jours02
        inputGroup["jours_03"] = binding.jours03
        inputGroup["jours_04"] = binding.jours04
        inputGroup["jours_05"] = binding.jours05
        inputGroup["jours_06"] = binding.jours06
        inputGroup["jours_07"] = binding.jours07
        inputGroup["jours_08"] = binding.jours08
        inputGroup["jours_09"] = binding.jours09
        inputGroup["jours_010"] = binding.jours010
        inputGroup["jours_011"] = binding.jours011
        inputGroup["jours_012"] = binding.jours012
        inputGroup["affaires_01"] = binding.affaires01
        inputGroup["affaires_02"] = binding.affaires02
        inputGroup["affaires_03"] = binding.affaires03
        inputGroup["affaires_04"] = binding.affaires04
        inputGroup["affaires_05"] = binding.affaires05
        inputGroup["affaires_06"] = binding.affaires06
        inputGroup["affaires_07"] = binding.affaires07
        inputGroup["affaires_08"] = binding.affaires08
        inputGroup["affaires_09"] = binding.affaires09
        inputGroup["affaires_010"] = binding.affaires010
        inputGroup["affaires_011"] = binding.affaires011
        inputGroup["affaires_012"] = binding.affaires012

        totalsGroup["total_01"] = binding.total01
        totalsGroup["total_02"] = binding.total02
        totalsGroup["total_03"] = binding.total03
        totalsGroup["total_04"] = binding.total04
        totalsGroup["total_05"] = binding.total05
        totalsGroup["total_06"] = binding.total06
        totalsGroup["total_07"] = binding.total07
        totalsGroup["total_08"] = binding.total08
        totalsGroup["total_09"] = binding.total09
        totalsGroup["total_010"] = binding.total010
        totalsGroup["total_011"] = binding.total011
        totalsGroup["total_012"] = binding.total012

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

        var total = 0.0
        for(i in 1..12){
            val jours = inputGroup["jours_0$i"]
            val affaires = inputGroup["affaires_0$i"]

            if(jours == null || affaires == null) continue

            if(jours.text.toString() == "" || affaires.text.toString() == "") continue

            totals[i-1] = jours.text.toString().toInt() * affaires.text.toString().toDouble()
            totalsGroup["total_0$i"]?.text = totals[i-1].toString()
            total += totals[i-1]
        }


        binding.total.text = total.toString()
    }

    private fun nextPage(){
        if(!this::parent.isInitialized) return

        for(input in inputGroup){
            parent.setData(input.key,input.value.text.toString())
        }

        findNavController().navigate(R.id.action_fifthMarchandiseFragment_to_sixthFragment)
    }
}