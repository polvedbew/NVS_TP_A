package com.norden.nvs_tp_a

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity2 : AppCompatActivity() {

    private val mvm:MainVM = MainVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

            mvm.graph.observe(this) { img ->
                if (img != null) {
                    findViewById<ImageView>(R.id.graph_image).setImageBitmap(img)
                }
            }

            regButtons()
        }




    private fun regButtons(){
        findViewById<Button>(R.id.start_button).setOnClickListener {
            it.setBackgroundColor(Color.BLACK)
            mLog.print("clicked start..")
            mvm.startSocket()
        }
    }
}