package com.nevesrafael.horas.screens.createWorkday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.nevesrafael.horas.databinding.FragmentModalSaveBinding
import com.nevesrafael.horas.model.Workday

class CreateWorkdayFragment(val clickOnSave: (Workday) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentModalSaveBinding
    private lateinit var presenter: CreateWorkdayPresenter

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

        presenter = CreateWorkdayPresenter(this)

        saveButton()
        loadDataToEdit()
        configureDate()

    }

    private fun saveButton() {

        binding.saveButton.setOnClickListener {
            val projectTyped = binding.project.text.toString()
            val commentsTyped = binding.comments.text.toString()
            val dateTyped = binding.date.text.toString()
            val hoursTyped = binding.hours.text.toString()

            presenter.checksSaveOrEdit(projectTyped, commentsTyped, dateTyped, hoursTyped)
        }
    }

    fun saveOrEdit(workday: Workday) {
        clickOnSave(workday)
        dismissAllowingStateLoss()
    }

    private fun loadDataToEdit() {

        val intentData = arguments
        val loadedWorkday: Workday? = intentData?.getParcelable(EXTRA_WORKDAY_EDIT)
        presenter.isEditing(loadedWorkday)
    }

    fun showWorkdayForEditOnTheScreen(workdayForEdit: Workday?) {

        binding.project.setText(workdayForEdit?.projectName)
        binding.comments.setText(workdayForEdit?.comments)
        binding.date.setText(workdayForEdit?.date)
        binding.hours.setText(workdayForEdit?.hours)
    }

    private fun configureDate() {
        binding.date.setOnClickListener {
            presenter.checkSelectedDate()
        }
    }

    fun showSelector(defaultDate: Long) {

        val calendar = MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Select Date")
            .setSelection(defaultDate)
            .build()

        calendar.addOnPositiveButtonClickListener {
            presenter.selectDate(it)
        }
        calendar.show(parentFragmentManager, null)
    }

    fun showDateInScreen(selectedDate: String?) {
        binding.date.setText(selectedDate, TextView.BufferType.EDITABLE)
    }

    companion object {
        const val EXTRA_WORKDAY_EDIT = "extra_workday_edit"
    }
}
