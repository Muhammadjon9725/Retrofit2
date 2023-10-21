package com.example.retrofit2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2.databinding.ItemRvBinding
import com.example.retrofit2.models.MyTodo
import retrofit2.Callback

class MyAdapters(val list:ArrayList<MyTodo>, val click: Callback<List<MyTodo>>):RecyclerView.Adapter<MyAdapters.MyVH>() {
    inner class MyVH(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myTodo: MyTodo){
            itemRvBinding.rvHolat.text = myTodo.holat
            itemRvBinding.rvId.text = myTodo.id.toString()
            itemRvBinding.rvMatn.text = myTodo.matn
            itemRvBinding.rvOxirgi.text = myTodo.oxirgi_muddat
            itemRvBinding.rvSarlavha.text = myTodo.sarlavha

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.onBind(list[position])
    }

    interface onClick{
        fun Click()
    }
}