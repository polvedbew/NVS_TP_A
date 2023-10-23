package com.norden.nvs_tp_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity2 : AppCompatActivity() {

    private val mvm:MainVM = MainVM()
    private var ringOrBeep:Boolean=true;

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
        findViewById<Button>(R.id.new_ring).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            mLog.print("clicked start..")
            ringOrBeep=true;
            mvm.startSocket()
        }
        findViewById<Button>(R.id.send_btn).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            val from=Integer.parseInt(findViewById<EditText>(R.id.id_start_value).text.toString())
            val tolerance=Integer.parseInt(findViewById<EditText>(R.id.id_tolerence_value).text.toString())
            findViewById<Button>(R.id.send_btn)
            mvm.sendData(ringOrBeep,tolerance,from)
        }
        findViewById<Button>(R.id.read_ring).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            ringOrBeep=true;
            mvm.read(true);
        }
        findViewById<Button>(R.id.new_beep).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            mLog.print("clicked start..")
            ringOrBeep=false;
            mvm.startSocket()
        }
        findViewById<Button>(R.id.read_beep).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            ringOrBeep=false;
            mvm.read(false);
        }
        findViewById<ImageView>(R.id.id_pick_up).setOnClickListener {
            //it.setBackgroundColor(Color.BLACK)
            mvm.pickUp();
        }
    }
}