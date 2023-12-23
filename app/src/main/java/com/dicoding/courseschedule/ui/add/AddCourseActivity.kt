package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var courseTaskViewModel: AddCourseViewModel
    private lateinit var view: View

    private lateinit var edtCourseName: TextInputEditText
    private lateinit var dayCourse: Spinner
    private lateinit var startTime: TextView
    private lateinit var endTime: TextView
    private lateinit var edtLecturer: TextInputEditText
    private lateinit var edtNote: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.title = getString(R.string.add_course)

        edtCourseName = findViewById(R.id.ed_course_name)
        dayCourse = findViewById(R.id.spinner_day)
        startTime = findViewById(R.id.tv_start_time)
        endTime = findViewById(R.id.tv_end_time)
        edtLecturer = findViewById(R.id.ed_lecturer)
        edtNote = findViewById(R.id.ed_note)

        courseTaskViewModel = ViewModelProvider(
            this,
            AddViewModelFactory.createFactory(this)
        )[AddCourseViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {

                courseTaskViewModel.insertCourse(
                    courseName = edtCourseName.text.toString().trim(),
                    day = dayCourse.selectedItemPosition,
                    startTime = startTime.text.toString(),
                    endTime = endTime.text.toString(),
                    lecturer = edtLecturer.text.toString().trim(),
                    note = edtNote.text.toString().trim()
                )

                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showStartTimePicker(view: View) {
        TimePickerFragment().show(supportFragmentManager, "startTime")
        this.view = view
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (view.id) {
            R.id.ib_start_time -> {
                startTime.text = timeFormat.format(calendar.time)
            }

            R.id.ib_end_time -> {
                endTime.text = timeFormat.format(calendar.time)
            }
        }
    }
}