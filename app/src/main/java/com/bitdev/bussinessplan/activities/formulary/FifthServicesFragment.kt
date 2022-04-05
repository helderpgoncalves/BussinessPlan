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
import com.bitdev.bussinessplan.databinding.FragmentFifthServicesBinding


class FifthServicesFragment : Fragment() {

    private var _binding: FragmentFifthServicesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var parent: FormularyActivity
    private var inputGroup: MutableMap<String, EditText> = mutableMapOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthServicesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

        findNavController().navigate(R.id.action_ThirdFragment_to_FourthFragment)
    }
}