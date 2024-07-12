package com.example.socketchatapp

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.CopyOnWriteArrayList

fun main(){
    val server=ServerSocket(1556)
    val clients=CopyOnWriteArrayList<Socket>()
    println("Server 1556 portunda dinlemeye başladı!...")
    while (true){
        val client=server.accept()
        clients.add(client)
        Thread{
            val input=BufferedReader(InputStreamReader(client.getInputStream()))
            while (true){
                val message=input.readLine()?:break
                println("İstemciden gelen mesaj: $message")

                for(c in clients){
                    if (!c.isClosed){
                        val output=PrintWriter(c.getOutputStream(),true)
                        output.println(message)
                    }
                }
            }
            clients.remove(client)
            client.close()
        }.start()
    }

}