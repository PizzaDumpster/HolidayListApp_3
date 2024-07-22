import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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