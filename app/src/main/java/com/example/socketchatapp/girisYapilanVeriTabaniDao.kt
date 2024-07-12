package com.example.socketchatapp

class girisYapilanVeriTabaniDao {

    fun uyeDegistir(vt:girisYapilanVeriTabani,girisYapan:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE girisYapan SET girisYapan='$girisYapan'")
        db.close()
    }

    fun uyeGetir(vt: girisYapilanVeriTabani):ArrayList<girisYapilanData>{
        var db=vt.writableDatabase
        var gelenData=ArrayList<girisYapilanData>()
        var cursor=db.rawQuery("SELECT * FROM girisYapan",null)
        while (cursor.moveToNext()){
            var data=girisYapilanData(cursor.getString(cursor.getColumnIndexOrThrow("girisYapan")))
            gelenData.add(data)
        }
        return gelenData
    }


}