package com.nevesrafael.horas.screens.createWorkday

import com.google.android.material.datepicker.MaterialDatePicker
import com.nevesrafael.horas.formatter.DateFormatter
import com.nevesrafael.horas.model.Workday

class CreateWorkdayPresenter(private val screen: CreateWorkdayFragment) {

    private var isEdit: Boolean = false
    private var workdayForEdit: Workday? = null
    private var selectedDate: String? = null

    fun checksSaveOrEdit(projectTyped: String, commentsTyped: String, dateTyped: String, hoursTyped: String) {

        val workday: Workday

        if (isEdit == false) {
            workday = createNewWorkday(projectTyped, commentsTyped, dateTyped, hoursTyped)
        } else {
            workday = changeWorkdayforEdit(projectTyped, commentsTyped, dateTyped, hoursTyped)
        }

        screen.saveOrEdit(workday)
    }

    private fun changeWorkdayforEdit(projectTyped: String, commentsTyped: String, dateTyped: String, hoursTyped: String): Workday {

        workdayForEdit?.apply {
            projectName = projectTyped
            comments = commentsTyped
            date = dateTyped
            hours = hoursTyped
        }
        return workdayForEdit!!
    }

    private fun createNewWorkday(projectTyped: String, commentsTyped: String, dateTyped: String, hoursTyped: String): Workday {

        return Workday(
            id = 0,
            projectName = projectTyped,
            comments = commentsTyped,
            date = dateTyped,
            hours = hoursTyped
        )
    }

    fun isEditing(loadedWorkday: Workday?) {

        workdayForEdit = loadedWorkday

        if (workdayForEdit != null) {
            isEdit = true
            screen.showWorkdayForEditOnTheScreen(workdayForEdit)
        }
    }

    fun checkSelectedDate() {
        var defaultDate = MaterialDatePicker.todayInUtcMilliseconds()

        if (selectedDate != null) {
            val date = DateFormatter.stringForDate(selectedDate!!, "dd/MM/yyyy")

            if (date != null) {
                defaultDate = date.time
            }
        }
        screen.showSelector(defaultDate)
    }

    fun selectDate(dateInMilliseconds: Long) {

        selectedDate = DateFormatter.formatMilitoDate(dateInMilliseconds)
        screen.showDateInScreen(selectedDate)

    }
}