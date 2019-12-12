package com.example.smarty.ui.slideshow

import SlideshowAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.ListFragment
import com.example.smarty.R
import com.example.smarty.ui.slideshow.manage.Customers
import com.example.smarty.ui.slideshow.manage.Employee
import com.example.smarty.ui.slideshow.manage.Places
import com.example.smarty.ui.slideshow.manage.Sizes


class SlideshowFragment : ListFragment() {
    val catNames = arrayOf(
        "Сотрудники", "Покупатели", "Торговые Точки", "Размеры"
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val myListAdapter = SlideshowAdapter(
            activity,
            R.layout.listfragment_row, catNames
        )
        listAdapter = myListAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listfragment, null)
    }

    override fun onListItemClick(
        l: ListView?,
        v: View?,
        position: Int,
        id: Long
    ) {
        super.onListItemClick(l, v, position, id)
        if(listView.getItemAtPosition(position).toString() == "Торговые Точки"){
            val intent = Intent(context, Places::class.java)
            startActivity(intent)
        }else if(listView.getItemAtPosition(position) == "Сотрудники"){

            val intent = Intent(context, Employee::class.java)
            startActivity(intent)
        }else if(listView.getItemAtPosition(position) == "Покупатели"){
            val intent = Intent(context, Customers::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(context, Sizes::class.java)
            startActivity(intent)
        }


        Toast.makeText(
            activity,
            listView.getItemAtPosition(position).toString(),
            Toast.LENGTH_LONG
        ).show()
    }


}