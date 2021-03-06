package raafat.maths.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.mariuszgromada.math.mxparser.Expression
import raafat.maths.R
import raafat.maths.TypeWriterTextView
import raafat.maths.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater,
            R.layout.fragment_question,
            container,
            false
        )

        val animationText: TypeWriterTextView = binding.questionTitle
        animationText.setCharacterDelay(90)
        animationText.displayTextWithAnimation(getString(R.string.question_title_3))
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.google)
        animationText.typeface = typeface

        val question:TextView = binding.question

        Handler().postDelayed({
            question.visibility = View.VISIBLE
        }, 7000)



        /**
         * on click listeners for all buttons of the keypad
         * */
        binding.keyPad.zero.setOnClickListener {
            if (binding.answer.text.isNotEmpty())
                binding.answer.append("0")
        }

        binding.keyPad.one.setOnClickListener {
            binding.answer.append("1")
        }

        binding.keyPad.two.setOnClickListener {
            binding.answer.append("2")
        }

        binding.keyPad.three.setOnClickListener {
            binding.answer.append("3")
        }

        binding.keyPad.four.setOnClickListener {
            binding.answer.append("4")
        }

        binding.keyPad.five.setOnClickListener {
            binding.answer.append("5")
        }

        binding.keyPad.six.setOnClickListener {
            binding.answer.append("6")
        }

        binding.keyPad.seven.setOnClickListener {
            binding.answer.append("7")
        }

        binding.keyPad.eight.setOnClickListener {
            binding.answer.append("8")
        }

        binding.keyPad.nine.setOnClickListener {
            binding.answer.append("9")
        }

        binding.keyPad.delete.setOnClickListener {
            if (binding.answer.text.isNotEmpty())
                binding.answer.text = binding.answer.text.dropLast(1)
        }

        binding.keyPad.ok.setOnClickListener {
            if (binding.answer.text.isNotEmpty()) {

                val estimated = binding.answer.text.toString()
                var questionText = binding.question.text.toString()
                questionText = questionText.replace(Regex("""[$,÷]"""), "-")
                questionText = questionText.replace(Regex("""[$,×]"""), "+")
                val expression = Expression(questionText)

                if (estimated.toLong() == expression.calculate().toLong()) {
                    Snackbar.make(
                        binding.linearLayout,
                        R.string.correct_label,
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(R.string.play_again) {
                            // Responds to click on the action
                        }
                        .show()
                } else {
                    Snackbar.make(
                        binding.linearLayout,
                        R.string.wrong_label,
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(R.string.try_again) {
                            // Responds to click on the action
                        }
                        .show()
                }
            }
        }

        return binding.root
    }

}