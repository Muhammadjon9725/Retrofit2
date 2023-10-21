package com.example.retrofit2

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.retrofit2.adapters.MyAdapters
import com.example.retrofit2.databinding.ActivityMainBinding
import com.example.retrofit2.databinding.ItemDialogBinding
import com.example.retrofit2.models.MyRequestTodo
import com.example.retrofit2.models.MyTodo
import com.example.retrofit2.retrofit.ApiClient
import com.example.retrofit2.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapters: MyAdapters
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        binding.refreshSwipe.setOnRefreshListener {
            loadData()
            binding.refreshSwipe.isRefreshing = false
            binding.progressBar.visibility = View.GONE
        }

        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.itemBtnSave.setOnClickListener {

                val myRequestTodo = MyRequestTodo(
                    itemDialogBinding.itemHolat.text.toString(),
                    itemDialogBinding.itemMatn.text.toString(),
                    itemDialogBinding.itemMuddat.text.toString(),
                    itemDialogBinding.itemSarlavha.text.toString()
                )
                ApiClient.getApiService()

                    .addTodo(myRequestTodo)

                    .enqueue(object : Callback<MyTodo> {

                        override fun onResponse(call: Call<MyTodo>, response: Response<MyTodo>) {
                            Toast.makeText(this@MainActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                            dialog.cancel()
                        }

                        override fun onFailure(call: Call<MyTodo>, t: Throwable) {
                            Toast.makeText(
                                this@MainActivity,
                                "Xatolik yuz berdi",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }


                dialog.setView(itemDialogBinding.root)
                dialog.show()
        }







    }
    fun loadData(){
        binding.progressBar.visibility = View.VISIBLE
        val allTodo = ApiClient.getApiService().getAllTodo()
        allTodo.enqueue(object : retrofit2.Callback<List<MyTodo>>{

            override fun onResponse(call: Call<List<MyTodo>>, response: Response<List<MyTodo>>) {
                val list = response.body()
                adapters = MyAdapters(list as ArrayList<MyTodo>,this)
                binding.recy.adapter = adapters
                binding.progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<List<MyTodo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}