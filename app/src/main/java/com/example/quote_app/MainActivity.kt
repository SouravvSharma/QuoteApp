package com.example.quote_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.quote_app.ApiService.QuoteApi
import com.example.quote_app.ApiService.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var authorProgressBar: ProgressBar
    lateinit var authorName2: EditText
    lateinit var progBar: ProgressBar
    lateinit var loadQuote: Button
    lateinit var authorName: EditText
    lateinit var quote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authorProgressBar = findViewById(R.id.authorProgressBar)

        loadQuote = findViewById(R.id.refresh)
        authorName = findViewById(R.id.authorName)
        quote = findViewById(R.id.content)
        progBar = findViewById(R.id.progBar)
        authorName2 = findViewById(R.id.authorName2)



        loadQuote.setOnClickListener {
            loadQuote()
        }
    }

    private fun loadQuote() {
        val quoteApi = RetrofitHelper.getRetrofitInstance().create(QuoteApi::class.java)
        lifecycleScope.launch {
            try {
                authorProgressBar.visibility = View.VISIBLE
                progBar.visibility = View.VISIBLE
                val result = quoteApi.getQuotes() // Replace with your actual API call
                val quoteContent =
                    result.body()?.content // Replace with the actual field from your response

                withContext(Dispatchers.Main) {
                    progBar.visibility = View.GONE
                    authorName.setText(result.body()?.author.toString())
                    quote.setText(quoteContent)
                    authorName2.setText("~" + result.body()?.author.toString())

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
            progBar.visibility = View.GONE
            authorProgressBar.visibility = View.GONE

        }

    }
}
