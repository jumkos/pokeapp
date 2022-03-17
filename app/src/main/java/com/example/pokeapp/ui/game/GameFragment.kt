package com.example.pokeapp.ui.game

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pokeapp.R
import com.example.pokeapp.databinding.ActivityMainBinding
import com.example.pokeapp.databinding.FragmentGameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class GameFragment : Fragment() {

    data class Question(
        val images: Int,
        val imagesTrue: Int,
        val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(images = R.drawable.clefable_black,
            imagesTrue = R.drawable.clefable,
            answers = listOf("Clefable", "Clefairy", "Sandslash", "Pikachu")),
        Question(images = R.drawable.clefairy_black,
            imagesTrue = R.drawable.clefairy,
            answers = listOf("Clefairy", "Clefable", "Sandslash", "Pikachu")),
        Question(images = R.drawable.jigglypuff_black,
            imagesTrue = R.drawable.jigglypuff,
            answers = listOf("Jigglypuff", "Clefairy", "Clefable", "Sandslash")),
        Question(images = R.drawable.ninetales_black,
            imagesTrue = R.drawable.ninetales,
            answers = listOf("Ninetales", "Clefairy", "Clefable", "Sandslash")),
        Question(images = R.drawable.persian_black,
            imagesTrue = R.drawable.persian,
            answers = listOf("Persian", "Clefairy", "Clefable", "Sandslash")),
        Question(images = R.drawable.pikachu_black,
            imagesTrue = R.drawable.pikachu,
            answers = listOf("Pikachu", "Clefairy", "Clefable", "Sandslash")),
        Question(images = R.drawable.sandslash_black,
            imagesTrue = R.drawable.sandslash,
            answers = listOf("Sandslash", "Clefairy", "Clefable", "AndroidVector")),
        Question(images = R.drawable.vulpix_black,
            imagesTrue = R.drawable.vulpix,
            answers = listOf("Vulpix", "Clefairy", "Clefable", "Sandslash")),
        Question(images = R.drawable.wigglytuff_black,
            imagesTrue = R.drawable.wigglytuff,
            answers = listOf("Wigglytuff", "Clefairy", "Clefable", "Pikachu"))
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)

        randomizeQuestions()

        binding.game = this
        binding.questionImage.setImageResource(currentQuestion.images)
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(GameFragmentDirections.actionGameToGameWon(numQuestions, questionIndex))
                    }
                } else {
                    view.findNavController().navigate(GameFragmentDirections.actionGameToGameOver())
                }
            }
        }



        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        binding.questionImage.setImageResource(currentQuestion.images)
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}