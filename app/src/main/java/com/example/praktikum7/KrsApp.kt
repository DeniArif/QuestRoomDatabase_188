package com.example.praktikum7

import android.app.Application
import com.example.praktikum7.dependeciesinjection.ContainerApp
import com.example.praktikum7.dependeciesinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp // Fungsi untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)// Membuat instance
        // instance adalah object yang dibuat dari class
    }
}