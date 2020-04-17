package com.bhanupro.inventoryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhanupro.inventoryapp.fragments.AmazonPlansFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var amazonFragment:AmazonPlansFragment = AmazonPlansFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        check_plans?.setOnClickListener {
            openFragment()
        }
    }
    private fun openFragment(){
        amazonFragment.show(supportFragmentManager,"Amazon_plans_fragment")
    }
}
