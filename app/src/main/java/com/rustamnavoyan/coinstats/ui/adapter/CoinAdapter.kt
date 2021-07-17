package com.rustamnavoyan.coinstats.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.rustamnavoyan.coinstats.R
import com.rustamnavoyan.coinstats.ui.model.CoinItem

class CoinAdapter : Adapter<CoinAdapter.ViewHolder>() {

    private val _items: MutableList<CoinItem> = ArrayList()
    var items: List<CoinItem>
        get() = _items
        set(values) {
            _items.clear()
            _items.addAll(values)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView by lazy { itemView.findViewById(R.id.coin_icon) }
        private val name: TextView by lazy { itemView.findViewById(R.id.coin_name) }
        private val changeIn24Hours: TextView by lazy { itemView.findViewById(R.id.coin_change_in_24_hours) }
        private val changeIn24HoursStatusIcon: ImageView by lazy { itemView.findViewById(R.id.coin_change_in_24_hours_status_icon) }
        private val price: TextView by lazy { itemView.findViewById(R.id.coin_price) }

        fun bind(item: CoinItem) {
            Glide.with(icon).load(item.iconUrl).into(icon)
            name.text = item.name
            changeIn24Hours.text = itemView.context.getString(
                R.string.coin_change_in_24_hours_template,
                item.changeIn24Hours.toString()
            )
            price.text = itemView.context.getString(
                R.string.coin_price_template,
                item.price.toString()
            )
            val colorResource = if (item.positiveChange) R.color.green_500 else R.color.red_500
            changeIn24Hours.setTextColor(
                ContextCompat.getColor(itemView.context, colorResource)
            )
            price.setTextColor(
                ContextCompat.getColor(itemView.context, colorResource)
            )
            changeIn24HoursStatusIcon.setColorFilter(
                ContextCompat.getColor(itemView.context, colorResource),
                android.graphics.PorterDuff.Mode.SRC_ATOP
            )
            changeIn24HoursStatusIcon.setImageResource(
                if (item.positiveChange) R.drawable.ic_arrow_drop_up_black_24dp
                else R.drawable.ic_arrow_drop_down_black_24dp
            )
        }
    }
}
