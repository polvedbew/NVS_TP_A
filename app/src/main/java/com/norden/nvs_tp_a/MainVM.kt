package com.norden.nvs_tp_a

import android.graphics.Bitmap
import android.os.Handler
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainVM :ViewModel(){

    private val exc:ExecutorService = Executors.newSingleThreadExecutor()
    private val _graph : MutableLiveData<Bitmap> = MutableLiveData()
    val graph          : LiveData<Bitmap> = _graph

    private val ip="192.168.4.1"

    val dataList = arrayListOf<Int>()

    @Volatile
    private var refreshFlag:Boolean = false;

    @Volatile
    private var pendingFlag:Boolean = false;

    init {
        updatePreview()

    }

    private var sob:UdpCon?=null
    private var busy:Boolean =false
    fun startSocket(){
        if(busy){
            return
        }
        Handler().postDelayed({
            busy=false
        }, 6000)
        busy=true
        mLog.print("start socket....,clearing data")
        dataList.clear()
        sob= object:UdpCon(ip){
            override fun newData(data: String?) {
                mLog.print("received $data")
                data?.let {
                    val lst=StringDecode.getIntList(data)
                    dataList.clear()
                    for(v:Int in lst){
                        dataList.add(v)
                    }
                    updatePreview()
                }
            }
        }
        val sb=sob
        exc.submit {
            sb?.startSocket()
        }


    }

    fun stopSocket(){
        sob?.stop()
    }



    private fun updatePreview() {
        if(pendingFlag){
            refreshFlag=true;
            return
        }
        CoroutineScope(Dispatchers.Default).launch {
            pendingFlag=true
            val b:GraphDraw = GraphDraw(dataList)
            _graph.postValue(b.graph)
            pendingFlag=false;
            if(refreshFlag){
                refreshFlag=false
                updatePreview()
            }
        }
    }

    fun sendData(isRing:Boolean,tolerance:Int,from:Int) {
        val sb=sob
        exc.submit {
            sb?.sendValues(isRing,tolerance,ip,dataList.toIntArray().sliceArray(from..from+30+5))
        }
    }

    fun read(isRing:Boolean) {
        val sb=sob
        exc.submit {
            sb?.readData(isRing)
        }
    }



}