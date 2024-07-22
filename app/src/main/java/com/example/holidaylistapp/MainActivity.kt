package com.example.holidaylistapp // Make sure this matches your actual package name

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var holidayAdapter: HolidayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val holidays = getHolidays2024()
        holidayAdapter = HolidayAdapter(holidays)
        recyclerView.adapter = holidayAdapter
    }

    private fun getHolidays2024(): List<Holiday> {
        return listOf(
            Holiday("New Year's Day", "January 1, 2024"),
            Holiday("Martin Luther King Jr. Day", "January 15, 2024"),
            Holiday("Valentine's Day", "February 14, 2024"),
            Holiday("Presidents' Day", "February 19, 2024"),
            Holiday("St. Patrick's Day", "March 17, 2024"),
            Holiday("Easter", "March 31, 2024"),
            Holiday("Mother's Day", "May 12, 2024"),
            Holiday("Memorial Day", "May 27, 2024"),
            Holiday("Father's Day", "June 16, 2024"),
            Holiday("Independence Day", "July 4, 2024"),
            Holiday("Labor Day", "September 2, 2024"),
            Holiday("Columbus Day", "October 14, 2024"),
            Holiday("Halloween", "October 31, 2024"),
            Holiday("Veterans Day", "November 11, 2024"),
            Holiday("Thanksgiving Day", "November 28, 2024"),
            Holiday("Christmas Day", "December 25, 2024")
        )
    }
}

data class Holiday(val name: String, val dateString: String) {
    val date: Date = SimpleDateFormat("MMMM d, yyyy", Locale.US).parse(dateString) ?: Date()
}



class HolidayAdapter(private val holidays: List<Holiday>) :
    RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>() {

    private val currentDate = Calendar.getInstance().time
    private val upcomingHoliday = holidays.filter { it.date.after(currentDate) }.minByOrNull { it.date }

    class HolidayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.holidayName)
        val dateTextView: TextView = view.findViewById(R.id.holidayDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holiday_item, parent, false)
        return HolidayViewHolder(view)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        val holiday = holidays[position]
        holder.nameTextView.text = holiday.name
        holder.dateTextView.text = holiday.dateString

        if (holiday == upcomingHoliday) {
            holder.itemView.setBackgroundColor(Color.YELLOW)
            holder.nameTextView.setTextColor(Color.BLACK)
            holder.dateTextView.setTextColor(Color.BLACK)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
            holder.nameTextView.setTextColor(Color.BLACK)
            holder.dateTextView.setTextColor(Color.GRAY)
        }
    }

    override fun getItemCount() = holidays.size
}