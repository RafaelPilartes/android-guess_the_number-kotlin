package com.jeciramiguel.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var guessEditText: EditText
    private lateinit var guessButton: Button
    private lateinit var messageTextView: TextView
    private lateinit var attemptsTextView: TextView

    private var randomNumber = 0
    private var numGuesses = 0
    private val maxGuesses = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessEditText = findViewById(R.id.guessEditText)
        guessButton = findViewById(R.id.guessButton)
        messageTextView = findViewById(R.id.messageTextView)
        attemptsTextView = findViewById(R.id.attemptsTextView)

        randomNumber = generateRandomNumber()

        guessButton.setOnClickListener {
            var userGuess = guessEditText.text.toString().toIntOrNull()

            if(userGuess != null && userGuess in 1..100) {
                numGuesses++

                attemptsTextView.text = "Attempts: $numGuesses"

                when {
                    userGuess == randomNumber -> {
                        showMessage("Congratulations! You guessed it right! 👏✅")
                        randomNumber = generateRandomNumber()
                        guessButton.isEnabled = false
                    }
                    userGuess < randomNumber -> showMessage("Try a higher number.")
                    else -> showMessage("Try a lower number.")
                }

                if (numGuesses == maxGuesses && userGuess != randomNumber) {
                    showMessage("Sorry, you've run out of guesses. The correct number was $randomNumber.")
                    guessButton.isEnabled = false
                }
            } else {
                showMessage("Please enter a valid number between 1 to 100.")
            }

            if (numGuesses >= maxGuesses) {
                guessButton.isEnabled = false
            }
        }
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(1, 100)
    }

    private fun showMessage(message: String){
        messageTextView.text = message
    }
}