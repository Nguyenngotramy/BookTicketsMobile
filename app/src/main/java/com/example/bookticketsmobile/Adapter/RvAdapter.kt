package com.example.bookticketsmobile.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookticketsmobile.DayData
import com.example.bookticketsmobile.databinding.DayListItemBinding

class RvAdapter(
    val context: Context,
    private val itemClickListener: OnItemClickListener // Thêm tham số này
) : RecyclerView.Adapter<RvAdapter.DateViewHolder>() {

    inner class DateViewHolder(val binding: DayListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<DayData>() {
        override fun areItemsTheSame(oldItem: DayData, newItem: DayData): Boolean {
            return oldItem.ngayChieu == newItem.ngayChieu // Assuming `day` is a unique identifier
        }

        override fun areContentsTheSame(oldItem: DayData, newItem: DayData): Boolean {
            return oldItem.ngayChieu == newItem.ngayChieu
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<DayData>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val binding = DayListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val currentDate = differ.currentList[position]
        holder.binding.apply {
            dayItem.text = currentDate.ngayChieu
        }
        holder.binding.dayItem.setOnClickListener {
            itemClickListener.onItemClick(currentDate.ngayChieu) // Gọi interface khi nhấn vào item
        }
    }

    interface OnItemClickListener {
        fun onItemClick(date: String)
    }
}
