package com.nevesrafael.horas.screens.creatWorkday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nevesrafael.horas.databinding.FragmentModalSaveBinding

class CreateWorkdayFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentModalSaveBinding
//    private lateinit var presenter: CriaEventoPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModalSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//          presenter = CriaEventoPresenter(this)

    }
}