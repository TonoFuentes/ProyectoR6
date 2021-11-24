package com.example.proyector6

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.Toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        //La NavigationUI usa un objeto AppBarConfiguration para administrar el comportamiento del botón Navigation en la esquina superior izquierda de la ToolBar.
        // Definimos varios destinos de nivel superior, ya que no guardan relación entre ellos.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_agentes, R.id.nav_mapas, R.id.nav_tacticas), drawerLayout)


        //El controlador de navegación posiciona el botón de navegación sobre la ToolBar y le da funcionalidad.
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    //Método para desplegar el navegador lateral deslizante al hacer click sobre el panel lateral o botón de navegación de la ToolBar
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}