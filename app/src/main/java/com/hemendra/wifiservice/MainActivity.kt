package com.hemendra.wifiservice

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager:WifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        var wifistate:Int = wManager.wifiState

        when(wifistate)
        {
            0->wifiswitch.isChecked=false
            1->wifiswitch.isChecked=false
            2->wifiswitch.isChecked=true
            3->wifiswitch.isChecked=true

        }

        wifiswitch.setOnCheckedChangeListener{compoundButton, b ->
            wManager.isWifiEnabled = b
        }

        btn_wifidevices.setOnClickListener{
            var wlist:List<ScanResult> =wManager.scanResults
            var templist = mutableListOf<String>()
            for(item in wlist)
            {
                templist.add("SSID:${item.SSID} \n Frequency:${item.frequency}")
            }

            var listadapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,templist)
           lv.adapter = listadapter
        }

        btn_paireddevices.setOnClickListener{
            var wlist:List<WifiConfiguration> =wManager.configuredNetworks
            var templist = mutableListOf<String>()
            for(item in wlist)
            {
                templist.add("SSID:${item.SSID}")
            }

            var listadapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1,templist)
            lv.adapter = listadapter
        }

    }
}