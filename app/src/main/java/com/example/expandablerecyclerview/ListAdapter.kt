package com.example.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.ItemListBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyView>(){
    val data = mapOf(
        "커피" to listOf("아메리카노", "바닐라라떼", "카페라떼", "카페모카"),
        "프라페" to listOf("녹차프라페", "커피프라페", "쿠키프라페"),
        "에이드" to listOf("레몬에이드", "자몽에이드"),
        "음료" to listOf("나랑드", "토레타")
    )

    inner class MyView(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.cvCardView.setOnClickListener {
                if (binding.lyDetail.visibility == View.VISIBLE){
                    binding.lyDetail.visibility = View.GONE
                    binding.imgImageButton.animate().apply{
                        duration = 200
                        rotation(0f)
                    }
                } else {
                    binding.lyDetail.visibility = View.VISIBLE
                    binding.imgImageButton.animate().apply{
                        duration=200
                        rotation(180f)
                    }
                }
            }
        }
        fun bind(pos:Int){
            binding.tvTextView.text = data.keys.elementAt(pos)
            data.values.elementAt(pos).forEach{
                val view = TextView(binding.root.context).apply{
                    text = ".$it"
                    textSize = 20f
                    setPadding(10,10,5,10)
                }
                binding.lyDetail.addView(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}