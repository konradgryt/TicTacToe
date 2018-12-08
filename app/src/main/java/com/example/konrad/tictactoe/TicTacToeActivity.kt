package com.example.konrad.tictactoe

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class TicTacToeActivity : AppCompatActivity() {

    enum class CURRRENT_PLAYER {
        FIRST_PLAYER, SECOND_PLAYER
    }

    enum class WINNER {
        PLAYER_ONE, PLAYER_TWO, DRAW
    }

    var currentPlayer: CURRRENT_PLAYER? = null
    var winner: WINNER? = null

    var player1Options: ArrayList<Int> = ArrayList()
    var player2Options: ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        currentPlayer = CURRRENT_PLAYER.FIRST_PLAYER
    }

    fun imageButtonTapped(view: View) {
        val selectedImageButton: ImageButton = view as ImageButton

        val randomNumber = (Math.random() * 9 + 1).toInt()
        var optionNumber = 0
        when (selectedImageButton.id) {
            R.id.imgButton1 -> optionNumber = 1
            R.id.imgButton2 -> optionNumber = 2
            R.id.imgButton3 -> optionNumber = 3
            R.id.imgButton4 -> optionNumber = 4
            R.id.imgButton5 -> optionNumber = 5
            R.id.imgButton6 -> optionNumber = 6
            R.id.imgButton7 -> optionNumber = 7
            R.id.imgButton8 -> optionNumber = 8
            R.id.imgButton9 -> optionNumber = 9
        }
        action(optionNumber, selectedImageButton)
    }

    private fun action (optionNumber: Int, selectedImageButton: ImageButton) {
        if (currentPlayer == CURRRENT_PLAYER.FIRST_PLAYER) {
            selectedImageButton.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.x))
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            currentPlayer = CURRRENT_PLAYER.SECOND_PLAYER
        } else if (currentPlayer == CURRRENT_PLAYER.SECOND_PLAYER) {
            selectedImageButton.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
            player2Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            currentPlayer = CURRRENT_PLAYER.FIRST_PLAYER
        }
    }
}


//when (optionNumber) {
//    1 -> imgButton1.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    2 -> imgButton2.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    3 -> imgButton3.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    4 -> imgButton4.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    5 -> imgButton5.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    6 -> imgButton6.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    7 -> imgButton7.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    8 -> imgButton8.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//    9 -> imgButton9.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//}