package com.example.booklisting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import retrofit2.Call;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_book.*
import retrofit2.Callback
import retrofit2.Response

val service: QueryInterface = retrofit.create(QueryInterface::class.java)
class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        val intent: Intent = intent
        var searchName = intent.getStringExtra("SearchBook")?.replace(' ', '+')
        val URL = "https://www.googleapis.com/books/v1/volumes?q="
        val finalUri: Uri = Uri.parse(URL + searchName)
        val builder: Uri.Builder = finalUri.buildUpon()
        searchName = builder.toString()
        val recyclerView = findViewById<RecyclerView>(R.id.rvBooks)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        val adapter = BookAdapter(clickListener)
        recyclerView.adapter = adapter
        getBooksQuery(searchName, adapter)
    }
    private val clickListener = object: OnItemClickListener {
        override fun onItemClick(book: Book) {
            var url: String = book.volumeInfo.getPreviewLink()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun getBooksQuery(searchName: String, adapter: BookAdapter) {
        val call: Call<Book> = service.getBooks(searchName)
        call.enqueue(object: Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful) {
                    val book: Book? = response.body()
                    if (book != null) {
                        adapter?.setData(book.books)
                    }
                }
            }
            override fun onFailure(call: Call<Book>, t: Throwable) {
                rvBooks.visibility = View.INVISIBLE
                Toast.makeText(this@BookActivity, t.message, Toast.LENGTH_LONG).show()
            }
        });
    }
}