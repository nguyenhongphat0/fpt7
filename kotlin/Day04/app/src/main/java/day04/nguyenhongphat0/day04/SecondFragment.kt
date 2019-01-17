package day04.nguyenhongphat0.day04

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.fragment_second.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SecondFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SecondFragment : Fragment() {

    override fun onStart() {
        super.onStart()
        sumButton.setOnClickListener {
            val number1Edit = activity!!.findViewById<EditText>(R.id.number1Edit)
            val resultText = activity!!.findViewById<TextView>(R.id.resultText)
            val number1 = number1Edit.text.toString().toInt()
            val number2 = number2Edit.text.toString().toInt()
            resultText.text = "Result = " + (number1 + number2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
}
