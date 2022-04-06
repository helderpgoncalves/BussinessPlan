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
import com.bitdev.bussinessplan.databinding.FragmentFifthServicesBinding
import com.bitdev.bussinessplan.databinding.FragmentSixthBinding


class SixthFragment : Fragment() {

    private var _binding: FragmentSixthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String, EditText> = mutableMapOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        parent = requireActivity() as FormularyActivity

        inputGroup["charges_variables"] = binding.chargesVariables
        inputGroup["duree_credits"] = binding.dureeCredits
        inputGroup["duree_dettes"] = binding.dureeDettes
        inputGroup["salaires_01"] = binding.salaires01
        inputGroup["salaires_02"] = binding.salaires02
        inputGroup["salaires_03"] = binding.salaires03
        inputGroup["remuneration_dirigeant_01"] = binding.remunerationDirigeant01
        inputGroup["remuneration_dirigeant_02"] = binding.remunerationDirigeant02
        inputGroup["remuneration_dirigeant_03"] = binding.remunerationDirigeant03
        inputGroup["dirigents_beneficient_acre"] = binding.dirigentsBeneficientAcre


        val spinner = binding.dirigentsBeneficientAcreSpinner

        ArrayAdapter.createFromResource(
            view.context, R.array.ouiNon, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                binding.dirigentsBeneficientAcre.setText("${p?.getItemAtPosition(pos)}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        update()

        for (input in inputGroup.values) {
            input.doAfterTextChanged {
                update()
            }
        }

        binding.nextPageBtn.setOnClickListener {
            nextPage()
        }
    }

    private fun update() {

    }

    private fun nextPage() {
        if (!this::parent.isInitialized) return

        for (input in inputGroup) {
            parent.setData(input.key, input.value.text.toString())
        }

        parent.finir()
    }
}