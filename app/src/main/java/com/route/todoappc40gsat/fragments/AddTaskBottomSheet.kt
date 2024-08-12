package com.route.todoappc40gsat.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc40gsat.OnTaskAddedListener
import com.route.todoappc40gsat.R
import com.route.todoappc40gsat.clearTime
import com.route.todoappc40gsat.database.TaskDatabase
import com.route.todoappc40gsat.database.model.Task
import com.route.todoappc40gsat.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    lateinit var calendar: Calendar
    var onTaskAddedListener: OnTaskAddedListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(requireContext())
        datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.selectedDateValueText.text = "$dayOfMonth/${month + 1}/$year"
        }
        datePickerDialog.show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        binding.selectDateText.setOnClickListener {
            showDatePicker()
        }
        binding.selectedDateValueText.setOnClickListener {
            showDatePicker()
        }
        binding.addTaskButton.setOnClickListener {
            if (validateFields()) {
                calendar.clearTime()
                val task = Task(
                    title = binding.titleEditText.text.toString(),
                    description = binding.descriptionEditText.text.toString(),
                    date = calendar.time,
                    isDone = false
                )

                TaskDatabase.getInstance(requireContext()).getTaskDao().insertTask(task)
                onTaskAddedListener?.onTaskAdded()
                dismiss()
            }
        }
    }

    fun validateFields(): Boolean {
        if (binding.titleEditText.text.isNullOrEmpty() || binding.titleEditText.text.isNullOrBlank()) {
            binding.titleEditText.error = getString(R.string.required)
            return false
        } else
            binding.titleEditText.error = null
        if (binding.descriptionEditText.text.isNullOrEmpty() || binding.descriptionEditText.text.isNullOrBlank()) {
            binding.descriptionEditText.error = getString(R.string.required)
            return false
        } else
            binding.descriptionEditText.error = null

        return true

        // 121312312
        // 121312315
    }
}
