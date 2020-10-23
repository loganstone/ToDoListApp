package com.example.to_dolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemList = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                itemList)

        add.setOnClickListener {
            itemList.add(editText.text.toString())
            todoList.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        clear.setOnClickListener {
            itemList.clear()
            adapter.notifyDataSetChanged()
        }

        todoList.setOnItemClickListener { _, _, i, _ ->
            android.widget.Toast.makeText(this,
                    "You Selected the item --> " + itemList[i],
                    android.widget.Toast.LENGTH_SHORT).show()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = todoList.checkedItemPositions
            val count = todoList.count
            var index = count - 1
            while (index >= 0) {
                if (position.get(index)) {
                    adapter.remove(itemList.get(index))
                }
                index--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        var locale = IntArray(2)
        titleText.getLocationOnScreen(locale)
        val updatedHeight = locale[1] + titleText.height
        todoList.layoutParams.height = updatedHeight
    }
}