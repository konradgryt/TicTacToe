package com.example.konrad.tictactoe

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*

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
    var imageButtons: ArrayList<ImageButton> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        try {
            startService(Intent(this@TicTacToeActivity, SoundService::class.java))
        } catch (e: Exception) {

        }
        imageButtons.add(imgButton1)
        imageButtons.add(imgButton2)
        imageButtons.add(imgButton3)
        imageButtons.add(imgButton4)
        imageButtons.add(imgButton5)
        imageButtons.add(imgButton6)
        imageButtons.add(imgButton7)
        imageButtons.add(imgButton8)
        imageButtons.add(imgButton9)
        currentPlayer = CURRRENT_PLAYER.FIRST_PLAYER
       // txtView1.setText("Next move for: X")
        txtView1.setText("")
    }

    fun imageButtonTapped(view: View) {
        val selectedImageButton: ImageButton = view as ImageButton

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

    private fun action (optionNumber: Int, _selectedImageButton: ImageButton) {
        var selectedImageButton = _selectedImageButton
        if (currentPlayer == CURRRENT_PLAYER.FIRST_PLAYER) {
            selectedImageButton.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.x))
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            currentPlayer = CURRRENT_PLAYER.SECOND_PLAYER
            //txtView1.setText("Next move for: O")
        } //else
        if (currentPlayer == CURRRENT_PLAYER.SECOND_PLAYER) {
                //For two players
//            selectedImageButton.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.o))
//            player2Options.add(optionNumber)
//            selectedImageButton.isEnabled = false
//            allDisabledImages.add(selectedImageButton)
            var notSelectedImageNumbers = ArrayList<Int>()
            for (imageNumber in 1..9) {
                if (!player1Options.contains(imageNumber) && !player2Options.contains(imageNumber)) {
                    notSelectedImageNumbers.add(imageNumber)
                }
            }
            try {
                var randomNumber = (Math.random() * notSelectedImageNumbers.size).toInt() //from 0 to 8 indexes
                var imageNumber = notSelectedImageNumbers[randomNumber]
                when(imageNumber) {
                    1 -> selectedImageButton = imgButton1
                    2 -> selectedImageButton = imgButton2
                    3 -> selectedImageButton = imgButton3
                    4 -> selectedImageButton = imgButton4
                    5 -> selectedImageButton = imgButton5
                    6 -> selectedImageButton = imgButton6
                    7 -> selectedImageButton = imgButton7
                    8 -> selectedImageButton = imgButton8
                    9 -> selectedImageButton = imgButton9
                }
                selectedImageButton.setImageResource(R.drawable.o)
                player2Options.add(imageNumber)
                selectedImageButton.isEnabled = false
                allDisabledImages.add(selectedImageButton)
                currentPlayer = CURRRENT_PLAYER.FIRST_PLAYER
                //txtView1.setText("Next move for: X")
            } catch (e: Exception) {

            }
        }
        checkWinner()
    }

    private fun checkWinner() {
        if((player1Options.contains(1) && player1Options.contains(2) && player1Options.contains(3)) ||
            (player1Options.contains(4) && player1Options.contains(5) && player1Options.contains(6)) ||
            (player1Options.contains(7) && player1Options.contains(8) && player1Options.contains(9)) ||
            (player1Options.contains(1) && player1Options.contains(4) && player1Options.contains(7)) ||
            (player1Options.contains(2) && player1Options.contains(5) && player1Options.contains(8)) ||
            (player1Options.contains(3) && player1Options.contains(6) && player1Options.contains(9)) ||
            (player1Options.contains(3) && player1Options.contains(5) && player1Options.contains(7)) ||
            (player1Options.contains(1) && player1Options.contains(5) && player1Options.contains(9))) {
            winner = WINNER.PLAYER_ONE
        } else if((player2Options.contains(1) && player2Options.contains(2) && player2Options.contains(3)) ||
            (player2Options.contains(4) && player2Options.contains(5) && player2Options.contains(6)) ||
            (player2Options.contains(7) && player2Options.contains(8) && player2Options.contains(9)) ||
            (player2Options.contains(1) && player2Options.contains(4) && player2Options.contains(7)) ||
            (player2Options.contains(2) && player2Options.contains(5) && player2Options.contains(8)) ||
            (player2Options.contains(3) && player2Options.contains(6) && player2Options.contains(9)) ||
            (player2Options.contains(3) && player2Options.contains(5) && player2Options.contains(7)) ||
            (player2Options.contains(1) && player2Options.contains(5) && player2Options.contains(9))) {
            winner = WINNER.PLAYER_TWO
        } else if (allDisabledImages.size == 9){
            winner = WINNER.DRAW
        }

        if (winner == WINNER.PLAYER_ONE) {
            createAlert("Player One Wins", "Good job", AlertDialog.BUTTON_POSITIVE, "OK", false)
        } else if(winner == WINNER.PLAYER_TWO) {
            createAlert("Player Two Wins", "Good job", AlertDialog.BUTTON_POSITIVE, "OK", false)
        } else if(winner == WINNER.DRAW) {
            createAlert("It's a draw", "Good job", AlertDialog.BUTTON_POSITIVE, "OK", false)
        }
    }

    private fun createAlert(title: String, message: String, buttonType: Int, buttonText: String, isCancelable: Boolean) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this@TicTacToeActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(buttonType, buttonText) {
            dialog, whinch ->
            resetGame()
        }
        alertDialog.setCancelable(isCancelable)
        alertDialog.show()
    }

    private fun resetGame() {
        winner = null
        currentPlayer = CURRRENT_PLAYER.FIRST_PLAYER
        //txtView1.setText("Next move for: O")
        imageButtons.map { imageButton -> imageButton.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.empty)) }
        allDisabledImages.map { imageButton -> imageButton?.isEnabled = true  }
        allDisabledImages.clear()
        player1Options.clear()
        player2Options.clear()
    }
}