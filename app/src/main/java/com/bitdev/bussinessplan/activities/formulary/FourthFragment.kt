package com.bitdev.bussinessplan.activities.formulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        parent = requireActivity() as FormularyActivity

    }
}