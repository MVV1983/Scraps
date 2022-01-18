package com.example.myscraps


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myscraps.Model.Scraps
import kotlinx.android.synthetic.main.card_layout.view.*

class ScrapsAdapter(val deleteInterface: DeleteScrapInterface, val clickLisstener: ItemClickInterface) :
        RecyclerView.Adapter<ScrapsAdapter.ScrapsViewHolder>() {

    private var scrapsList = emptyList<Scraps>()

    inner class ScrapsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.item_title)
        val itemDes: TextView = itemView.findViewById(R.id.item_detail)
        var cardView: CardView = itemView.findViewById(R.id.card)
        val itemDelete: Button = itemView.findViewById(R.id.item_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrapsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ScrapsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScrapsViewHolder, position: Int) {

        val currentItem = scrapsList[position]
        holder.itemView.apply {
            holder.itemView.item_title.text = currentItem.name
            holder.itemView.item_detail.text = currentItem.description
            holder.itemView.item_date_time.text = currentItem.taskDate

            //painting card in color
            //holder.cardView.setBackgroundResource(cardViewBg[position % 4])

            holder.itemView.setOnClickListener {
                val unit = scrapsList.get(position)
                clickLisstener.onClicked(unit)

                notifyDataSetChanged()
            }

            holder.itemDelete.setOnClickListener {

                var unit = scrapsList.get(position)
                deleteInterface.onDelete(unit)
                notifyDataSetChanged()
            }
        }
    }

    fun setData(unit: List<Scraps>) {
        this.scrapsList = unit
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return scrapsList.size
    }

    interface DeleteScrapInterface {
        fun onDelete(scraps: Scraps)
    }

    interface ItemClickInterface {
        fun onClicked(scraps: Scraps)
    }

}