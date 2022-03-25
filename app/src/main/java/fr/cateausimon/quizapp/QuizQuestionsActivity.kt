package fr.cateausimon.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    // question position (start at first question)
    private var mCurrentPosition: Int = 1

    // all questions
    private var mQuestionList: ArrayList<Question>? = null

    // which question was selected
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun setQuestion() {

        mCurrentPosition = 1
        val question: Question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()


        progressBar.progress = mCurrentPosition
        tv_progress.text = "{}/{}".format(mCurrentPosition, progressBar.max)

        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                selectedOptionsView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionsView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionsView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionsView(tv_option_four, 4)
            }
        }
    }

    private fun selectedOptionsView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )
    }
}