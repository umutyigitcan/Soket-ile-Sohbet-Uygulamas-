import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.socketchatapp.RVAdapter
import com.example.socketchatapp.RVAdapterData
import com.example.socketchatapp.databinding.FragmentAnasayfaBinding
import com.example.socketchatapp.girisYapilanVeriTabani
import com.example.socketchatapp.girisYapilanVeriTabaniDao
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Anasayfa : Fragment() {



    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var gidenListe:ArrayList<RVAdapterData>
    private lateinit var adapter:RVAdapter
    private var output:PrintWriter?=null
    private var input:BufferedReader?=null
    private var client:Socket?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentAnasayfaBinding.inflate(inflater,container,false)
        gidenListe=ArrayList()
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        var vt=girisYapilanVeriTabani(requireContext())
        var gelenData=girisYapilanVeriTabaniDao().uyeGetir(vt)
        var girisYapan=""
        for(k in gelenData){
            girisYapan=k.girisYapan
        }
        tasarim.fab.setOnClickListener {
            var mesaj=tasarim.girdi.text.toString()
            sendMessage("$girisYapan: $mesaj")
            tasarim.girdi.text.clear()
        }

        adapter= RVAdapter(requireContext(),gidenListe)
        tasarim.rv.adapter=adapter
        connectToServer()
        return tasarim.root
    }

    private fun sendMessage(message:String){
        Thread{
            output?.println(message)
        }.start()
    }

    private fun connectToServer(){
        Thread{
            client=Socket("192.168.1.37",1556)
            output= PrintWriter(client!!.getOutputStream(),true)
            input=BufferedReader(InputStreamReader(client!!.getInputStream()))
            while (true){
                val response = input?.readLine()?:break
                val sender=response.substringBefore(": ")
                val message=response.substringAfter(": ")
                requireActivity().runOnUiThread{
                    gidenListe.add(RVAdapterData(sender, message))
                    adapter.notifyItemInserted(gidenListe.size - 1)
                    tasarim.rv.scrollToPosition(gidenListe.size - 1)
                }
            }
        }.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()


        try {
            client?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}