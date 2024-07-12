package com.example.socketchatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(var mContext:Context,var disaridanGelenler:ArrayList<RVAdapterData>):RecyclerView.Adapter<RVAdapter.cardViewNesneTutucu>() {
    inner class cardViewNesneTutucu(view:View):RecyclerView.ViewHolder(view){
        var cardview:CardView
        var kullaniciadi:TextView
        var mesaj:TextView
        init{
            cardview=view.findViewById(R.id.cardview)
            kullaniciadi=view.findViewById(R.id.kullaniciadi)
            mesaj=view.findViewById(R.id.mesaj)
        }
    }

    override fun getItemCount(): Int {
        return disaridanGelenler.size
    }

    override fun onBindViewHolder(holder: cardViewNesneTutucu, position: Int) {
        var tutucu=disaridanGelenler[position]
        holder.mesaj.text=tutucu.mesaj
        holder.kullaniciadi.text=tutucu.mesajGonderen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewNesneTutucu {
        var tasarim=LayoutInflater.from(mContext).inflate(R.layout.cardview,parent,false)
        return cardViewNesneTutucu(tasarim)
    }

}