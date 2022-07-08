package edu.uoc.android.uocgamessuite.ui.calendar

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.uocgamessuite.R
import edu.uoc.android.uocgamessuite.databinding.CalendarEventItemViewBinding
import edu.uoc.android.uocgamessuite.models.entity.CalendarGameEntity
import edu.uoc.android.uocgamessuite.utils.ListType

class CalendarViewHolder : RecyclerView.Adapter<CalendarViewHolder.ViewHolder>() {

    val games = mutableListOf<CalendarGameEntity>()

    /**
     * ViewHolder of the view
     */
    class ViewHolder(val binding: CalendarEventItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: CalendarGameEntity) {
            binding.calendarListName.text = game.listName
            binding.calendarGameName.text = game.gameName
            binding.calendarDateText.text = game.dateGameAddedToList
            when (game.listName) {
                ListType.PLAYING -> {
                    binding.calendarListName.setBackgroundColor(this.itemView.context.resources.getColor(R.color.blue))
                    binding.calendarDateTextTitle.text = this.itemView.context.getString(R.string.calendar_date_playing_title)
                }
                ListType.COMPLETED -> {
                    binding.calendarListName.setBackgroundColor(this.itemView.context.resources.getColor(R.color.green))
                    binding.calendarDateTextTitle.text = this.itemView.context.getString(R.string.calendar_date_completed_title)
                    binding.calendarListName.setTextColor(this.itemView.context.resources.getColor(R.color.black))
                }
                ListType.ABANDONED -> {
                    binding.calendarListName.setBackgroundColor(this.itemView.context.resources.getColor(R.color.red))
                    binding.calendarDateTextTitle.text = this.itemView.context.getString(R.string.calendar_date_abandoned_title)
                }
            }
        }
    }

    /**
     * Create new views
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            CalendarEventItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
        )
    }

    /**
     * Replace the contents of a view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    /**
     * Return the size of dataSet
     */
    override fun getItemCount(): Int {
        return games.size
    }
}