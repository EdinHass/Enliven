package com.example.enliven.ui.chat

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.enliven.R
import com.example.enliven.data.UserExtra
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import io.getstream.chat.android.client.ChatClient


class ChatActivity : AppCompatActivity() {

    lateinit var menu: Menu
    lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.title = "Enliven Social"
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES;

        val navController by lazy {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
            navHostFragment.navController
        }
        this.navController = navController
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.menu.get(0).setOnMenuItemClickListener {
            menu.get(0).setVisible(false)
            navController.navigate(R.id.action_profile)
            true
        }

        navView.menu.get(1).setOnMenuItemClickListener {
            menu.get(0).setVisible(false)
            navController.navigate(R.id.action_channelsFragment_to_leaderboardsStreaksFragment)
            true
        }

        navView.menu.get(2).setOnMenuItemClickListener {
            menu.get(0).setVisible(false)
            navController.navigate(R.id.action_channelsFragment_to_leaderboardsXpFragment)
            true
        }

        navView.menu.get(3).setOnMenuItemClickListener {
            finish()
            true
        }
        navView.menu.get(4).setOnMenuItemClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setCancelable(true)
            builder.setTitle("Potvrda")
            builder.setMessage("Da li ste sigurni da se želite odjaviti?")
            builder.setPositiveButton("Da",
                DialogInterface.OnClickListener { dialog, which ->
                    val prefs = this.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE)
                    ChatClient.instance().getCurrentUser()?.name = prefs.getString("loginName", null)!!
                    ChatClient.instance().getCurrentUser()?.extraData?.set(UserExtra.PHONE, prefs.getString("loginPhone", null)!!)
                    ChatClient.instance().updateUser(ChatClient.instance().getCurrentUser()!!).enqueue(){
                        if(it.isError){
                            Log.e("ERROR", it.error().message!!)
                        }
                    }
                    prefs.edit().putBoolean("Anon", false).apply()
                    FirebaseAuth.getInstance().signOut()
                    finish()
                })
            builder.setNegativeButton("Ne",
                DialogInterface.OnClickListener { dialog, which -> })

            val dialog: AlertDialog = builder.create()
            dialog.show()


            true
        }

        drawerLayout.addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                findViewById<TextView>(R.id.username).setText(ChatClient.instance().getCurrentUser()?.name)
            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })
        navController.navigate(R.id.channelsFragment)

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu_chat, menu)
        this.menu = menu!!
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_new) {
            navController.navigate(R.id.action_channelsFragment_to_newMessageFragment)
        }
        if(item.itemId == android.R.id.home && supportActionBar?.title=="Enliven Social"){
            if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.openDrawer(GravityCompat.START);
            else
                drawerLayout.closeDrawer(GravityCompat.START);
        }else if(!menu.get(0).isVisible){
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun closeOptionsMenu() {
        drawerLayout.closeDrawer(GravityCompat.START)
        drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        super.closeOptionsMenu()
    }
}

