package com.example.socketchatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.socketchatapp.databinding.FragmentKodBelirlemeBinding

class KodBelirleme : Fragment() {


    private lateinit var tasarim:FragmentKodBelirlemeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentKodBelirlemeBinding.inflate(inflater,container,false)
        tasarim.tikla.setOnClickListener {
            var vt=girisYapilanVeriTabani(requireContext())
            var takmaIsim=tasarim.girdi.text.toString()
            girisYapilanVeriTabaniDao().uyeDegistir(vt,takmaIsim)
            Navigation.findNavController(it).navigate(R.id.action_kodBelirleme_to_anasayfa)
        }

        return tasarim.root
    }
}