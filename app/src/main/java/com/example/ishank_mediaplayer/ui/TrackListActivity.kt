package com.example.ishank_mediaplayer.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ishank_mediaplayer.adapter.AudioTackAdapter
import com.example.ishank_mediaplayer.databinding.ActivityTrackListBinding
import com.example.ishank_mediaplayer.model.AudioTrackData
import com.example.ishank_mediaplayer.model.CatIdBody
import com.example.ishank_mediaplayer.service.MediaPlayerService
import com.example.ishank_mediaplayer.viewmodel.TrackListActivityViewModel

class TrackListActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private lateinit var viewModel: TrackListActivityViewModel

    private val binding by lazy { ActivityTrackListBinding.inflate(layoutInflater) }

    private val audioAdapter by lazy { AudioTackAdapter(::onItemClick) }

    var mService: MediaPlayerService? = null
    var mIsBound: Boolean? = null


    companion object {
        fun callTrackListActivity(context: Context, catId: String): Intent {
            val intent = Intent(context, TrackListActivity::class.java)
            intent.putExtra("catId", catId)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TrackListActivityViewModel::class.java)

        initAudioTrack()

        binding.aviLoader.visibility = View.VISIBLE
        binding.aviLoader.show()

        val catId = intent.getStringExtra("catId")
        viewModel.getAudioTack(CatIdBody(catId))!!.observe(this, Observer { audioTrack ->
            audioAdapter.setList(audioTrack.data)
            binding.aviLoader.visibility = View.GONE


        })

        bindService()

    }

    private fun initAudioTrack() {
        binding.trackRecyclerview.adapter = audioAdapter

    }


    private fun onItemClick(audioData: AudioTrackData) {

    }


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
            Log.d(TAG, "ServiceConnection: connected to service.")
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            val binder = iBinder as MediaPlayerService.MyBinder
            mService = binder.service
            mIsBound = true
            getRandomNumberFromService() // return a random number from the service
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.d(TAG, "ServiceConnection: disconnected from service.")
            mIsBound = false
        }
    }

    private fun getRandomNumberFromService() {
        mService?.randomNumberLiveData?.observe(this, Observer {
            Log.e(TAG, "getRandomNumberFromService: $it")
            binding.text.text = "Random number from service: $it"
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService()
    }


    private fun bindService() {
        Intent(this, MediaPlayerService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }


    private fun unbindService() {
        Intent(this, MediaPlayerService::class.java).also { intent ->
            unbindService(serviceConnection)
        }
    }
}