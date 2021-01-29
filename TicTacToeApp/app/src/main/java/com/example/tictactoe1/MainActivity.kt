package com.example.tictactoe1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var buttons: Array<Array<Button>>
    lateinit var textViewPlayer1: TextView
    lateinit var textViewPlayer2: TextView

    private var player1Turn: Boolean = true
    private var roundCount: Int = 0
    private var player1Points: Int = 0
    private var player2Points: Int = 0
    private var color1 = Color.GREEN
    private var color2 = Color.RED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1 = scoreTextP1
        textViewPlayer2 = scoreTextP2

        buttons = Array(3) { r ->
            Array(3) { c ->
                initButtons(r, c)
            }
        }
        val btnReset: Button = btnReset
        btnReset.setOnClickListener {
         //   Toast.makeText(
       //         applicationContext, "Reset game button clicked", Toast.LENGTH_SHORT
         //   ).show()
            player1Points = 0
            player2Points = 0
            updateScore()
            clearBoard()

        }


    }

    private fun initButtons(r: Int, c: Int): Button {
        val id = "btn$r$c"
        val btn: Button = findViewById(resources.getIdentifier(id, "id", packageName))
        btn.setOnClickListener {
            Toast.makeText(applicationContext, "Button $r$c clicked", Toast.LENGTH_LONG)
            onBtnClick(btn)
        }
        return btn
    }
    /*private fun initButtons(r: Int,c:Int) : Button{
     val btn : Button
     btn.resources.getIdentifier(("$r$c", "id", packageName))
        return btn
    }*/

    private fun onBtnClick(btn: Button) {
       if (btn.text == "1" || btn.text == "2") return
        if (player1Turn) {
            btn.text = "1"
        } else {
            btn.text = "2"
        }
        roundCount++

        if (checkForWin()){
            if (player1Turn) win(1)
            else win(2)
        }else if (roundCount == 9){
             draw()
        }
        else{
            player1Turn =!player1Turn
        }
    }

    private fun draw() {
       Toast.makeText(applicationContext,"Match Draw",Toast.LENGTH_SHORT).show()
        clearBoard()
    }

    private fun clearBoard() {
        for (i in 0..2){
            for (j in 0..2){
                buttons[i][j].text = ""
            }
        }
        roundCount =0
        player1Turn = true
    }

    private fun win(player: Int) {
        if (player == 1) player1Points++
        else player2Points++
        Toast.makeText(applicationContext,"player $player Won!",
        Toast.LENGTH_SHORT).show()

        updateScore()
        clearBoard()




    }

    private fun updateScore() {
        textViewPlayer1.text="Player 1 :$player1Points"
        textViewPlayer2.text="Player 2 :$player2Points"
    }

    private fun checkForWin(): Boolean {

        val fields = Array(3){r->
            Array(3){c->
                buttons[r][c].text
            }
        }
        for (i in 0..2) {
            if ((fields[i][0] == fields[i][1]) &&
                (fields[i][0] == fields[i][2]) &&
                (fields[i][0] != "")
            ) return true
        }

        for (i in 0..2) {
            if ((fields[0][i] == fields[1][i]) &&
                (fields[0][i] == fields[2][i]) &&
                (fields[0][i] != "")
            ) return true
        }

            if ((fields[0][0] == fields[1][1]) &&
                (fields[0][0] == fields[2][2]) &&
                (fields[0][0] != "")
            ) return true


            if ((fields[0][2] == fields[1][1]) &&
                (fields[0][2] == fields[2][0]) &&
                (fields[0][2] != "")
                ) return true
            return false
        }

    }





