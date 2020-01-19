package com.takanotume24.janken

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_par = findViewById<Button>(R.id.button_par)
        val button_choki = findViewById<Button>(R.id.button_choki)
        val button_guu = findViewById<Button>(R.id.button_guu)
        val textView_cpu_choise = findViewById<TextView>(R.id.cpu_choise)
        val textView_result = findViewById<TextView>(R.id.result)

        button_choki.setOnClickListener {
            val user_choise = Hand.CHOKI
            val cpu_choise = make_cpu_choise(textView_cpu_choise)

            show_result(textView_result, cpu_choise, user_choise)
        }

        button_par.setOnClickListener {
            val user_choise = Hand.PAR
            val cpu_choise = make_cpu_choise(textView_cpu_choise)

            show_result(textView_result, cpu_choise, user_choise)
        }

        button_guu.setOnClickListener {
            val user_choise = Hand.GUU
            val cpu_choise = make_cpu_choise(textView_cpu_choise)


            show_result(textView_result, cpu_choise, user_choise)
        }


    }

    fun make_cpu_choise(textView_cpu_choise: TextView): Hand {
        val hands = Hand.values()
        val hand = hands[Random.nextInt(hands.size)]
        when (hand) {
            Hand.GUU -> {
                textView_cpu_choise.text = "グー"
            }
            Hand.PAR -> {
                textView_cpu_choise.text = "パー"
            }
            Hand.CHOKI -> {
                textView_cpu_choise.text = "チョキ"
            }
        }
        return hand
    }

    fun show_result(textView_result: TextView, cpu_choise: Hand, user_choise: Hand) {
        when (isUserWin(user_choise, cpu_choise)) {
            State.LOSE -> {
                textView_result.text = "あなたの負けです"
            }
            State.WIN -> {
                textView_result.text = "あなたの勝ちです"
            }
            State.DRAW -> {
                textView_result.text = "引き分けです"
            }
        }
    }


    fun isUserWin(user_choise: Hand, cpu_choise: Hand): State {
        val result =
            when (cpu_choise) {
                Hand.CHOKI -> {
                    when (user_choise) {
                        Hand.CHOKI -> State.DRAW
                        Hand.PAR -> State.LOSE
                        Hand.GUU -> State.WIN
                    }
                }
                Hand.GUU -> {
                    when (user_choise) {
                        Hand.CHOKI -> State.LOSE
                        Hand.PAR -> State.WIN
                        Hand.GUU -> State.DRAW
                    }
                }
                Hand.PAR -> {
                    when (user_choise) {
                        Hand.CHOKI -> State.WIN
                        Hand.PAR -> State.DRAW
                        Hand.GUU -> State.LOSE
                    }
                }
            }

        return result

    }

}
