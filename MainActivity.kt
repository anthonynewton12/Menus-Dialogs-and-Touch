package com.example.menusdialogsandtouch
import com.example.menusdialogsandtouch.CaptchaDialogFragment.CaptchaListener
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), CaptchaListener  {

    private lateinit var imageView: ImageView
    private lateinit var fab: FloatingActionButton
    private lateinit var correctAnswerImageView: ImageView
    private lateinit var wrongAnswerImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView3)
        fab = findViewById(R.id.floatingActionButton)
        correctAnswerImageView = findViewById(R.id.correctAnswerImageView)
        wrongAnswerImageView = findViewById(R.id.wrongAnswerImageView)

        correctAnswerImageView.visibility = View.INVISIBLE
        wrongAnswerImageView.visibility = View.INVISIBLE


        fab.setOnClickListener {
            val captchaDialogFragment = CaptchaDialogFragment.newInstance()
            captchaDialogFragment.show(supportFragmentManager, "CaptchaDialog")
        }

        correctAnswerImageView.setOnTouchListener(object : OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                clearStatus()
            }
            override fun onSwipeRight() {
                clearStatus()
            }
        })
        wrongAnswerImageView.setOnTouchListener(object : OnSwipeTouchListener(this) {
            override fun onSwipeLeft() {
                clearStatus()
            }
            override fun onSwipeRight() {
                clearStatus()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_clear -> {
                clearStatus()
                return true
            }
            R.id.action_settings -> {
                Snackbar.make(findViewById(android.R.id.content), "Coming Soon", Snackbar.LENGTH_SHORT)
                    .show()
                return true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCaptchaDone(isTrafficLightClicked: Boolean) {
        if (isTrafficLightClicked) {
            correctAnswerImageView.visibility = View.VISIBLE
            wrongAnswerImageView.visibility = View.INVISIBLE
            imageView.visibility = View.INVISIBLE
        }
        else {
            correctAnswerImageView.visibility = View.INVISIBLE
            wrongAnswerImageView.visibility = View.VISIBLE
            imageView.visibility = View.INVISIBLE
        }
    }

    private fun clearStatus() {
        imageView.visibility = View.VISIBLE
        correctAnswerImageView.visibility = View.INVISIBLE
        wrongAnswerImageView.visibility = View.INVISIBLE
    }
}
