package com.erenalparslan.lineerlayoutsignage

import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {

    private lateinit var horizontalTop: PlayerView
    private lateinit var horizontalBottom: PlayerView
    private lateinit var squareBottom: PlayerView
    private lateinit var verticalRight: PlayerView
    private lateinit var squareTop: PlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        horizontalTop = findViewById(R.id.horizontalTop)
        horizontalBottom = findViewById(R.id.horizontal_bottom)
        squareBottom = findViewById(R.id.square_bottom)
        verticalRight = findViewById(R.id.vertical_right)
        squareTop = findViewById(R.id.square_top)

        val mediaItem = MediaItem.fromUri(Uri.parse(URL))

        val player = ExoPlayer.Builder(this).build()
        val player1 = ExoPlayer.Builder(this).build()
        val player2 = ExoPlayer.Builder(this).build()
        val player3 = ExoPlayer.Builder(this).build()
        val player4 = ExoPlayer.Builder(this).build()


        horizontalTop.player = player
        horizontalBottom.player = player1
        squareBottom.player = player2
        verticalRight.player = player3
        squareTop.player = player4


        playerSetter(player, mediaItem)
        playerSetter(player1, mediaItem)
        playerSetter(player2, mediaItem)
        playerSetter(player3, mediaItem)
        playerSetter(player4, mediaItem)


        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(URL, HashMap<String, String>())

        val width =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)?.toInt()
        val height =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)?.toInt()

        println(width)
        println(height)
        if (width != null && height != null) {
            if (width > height) {
                println("video yatay")
            } else if (height > width) {
                println("video dikey")
            } else {
                println("video kare")
            }
        }


    }

    fun playerSetter(player: ExoPlayer, mediaItem: MediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    companion object {
        const val URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    }
}