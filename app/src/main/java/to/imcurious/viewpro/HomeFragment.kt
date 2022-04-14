package to.imcurious.viewpro

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import to.imcurious.viewpro.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog
    private var meetingCalendar: Calendar? = null
    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.datetimeEdit.setOnClickListener {
            launchDatePicker()
        }

        binding.generateButton.setOnClickListener {
            // Create a dialog
            // Show loading screen
            // Wait some seconds
            // Display meeting create successfully
            // Navigate user to meeting screen
        }
    }


    private fun launchDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.show()
    }

    fun launchTimePicker() {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialog = TimePickerDialog(requireContext(), this, hourOfDay, minute, true)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (meetingCalendar != null) {
            meetingCalendar!!.set(year, month, dayOfMonth)
        }else meetingCalendar = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if (meetingCalendar != null) {
            meetingCalendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
            meetingCalendar!!.set(Calendar.MINUTE, minute)
        }else meetingCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
    }
}