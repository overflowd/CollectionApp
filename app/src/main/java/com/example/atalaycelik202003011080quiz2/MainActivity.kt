package com.example.atalaycelik202003011080quiz2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var eşya:TextView
    private lateinit var durum:TextView
    private lateinit var collectionList:ArrayList<String>
    private lateinit var collectionStatusList:ArrayList<String>
    private var counter1=0
    private var counter2=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView=findViewById(R.id.list_view)
        editText=findViewById(R.id.editTextText)
        eşya=findViewById(R.id.textView4)
        durum=findViewById(R.id.textView5)
        collectionList= ArrayList<String>()
        collectionStatusList= ArrayList<String>()

        collectionList.add("Pul")
        collectionList.add("Gazoz Kapağı")
        collectionList.add("Futbolcu Kartı")
        collectionList.add("Anahtarlık")
        collectionList.add("Antika Para")

        collectionStatusList.add("Değişime Açık")
        collectionStatusList.add("Değişime Kapalı")
        collectionStatusList.add("Gizli")
        collectionStatusList.add("Devam Ediyor")
        collectionStatusList.add("Bitmeye Yakın")

        listView.isEnabled=false

    }

    fun tipDeğiş(view:View){
        listView.isEnabled=true
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,collectionList)
        listView.adapter=adapter
        listView.onItemClickListener= AdapterView.OnItemClickListener{ parent, view, position, id ->
            val secilenEsya=collectionList[position]
            eşya.text=secilenEsya
            listView.isEnabled=false
        }

        counter1++
        if (counter1 >= collectionList.size)
            counter1 = 0

        eşya.setText(collectionList [counter1].toString())

    }

    fun değişimDeğiş(view:View){
        listView.isEnabled=true
        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,collectionStatusList)
        listView.adapter=adapter
        listView.onItemClickListener= AdapterView.OnItemClickListener{ parent, view, position, id ->
            val secilenDurum=collectionStatusList[position]
            durum.text=secilenDurum
            listView.isEnabled=false
        }

        counter2++
        if (counter2 >= collectionStatusList.size)
            counter2 = 0

        durum.setText(collectionStatusList [counter2].toString())
    }

    fun paylaş(view:View){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = "Merhaba ben ${editText.text.toString()} ${eşya.text.toString()} koleksiyonu biriktiriyorum. Koleksiyonumun durumu ${durum.text.toString()}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Metni Paylaş"))
    }
}