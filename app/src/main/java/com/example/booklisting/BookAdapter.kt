package com.example.booklisting

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.StringBuilder

class BookAdapter(listener: OnItemClickListener) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    var mBook: ArrayList<Book> = ArrayList()
    val mListener: OnItemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = mBook[position]
        var link = currentBook.volumeInfo.imageLinks.thumbnail
        if (link != null) {
            link = link.replace("http", "https")
        }
        Picasso.with(holder.imageView.context).load(link).into(holder.imageView)
        holder.title.text = currentBook.volumeInfo.title
        var authors: String = " "
        val authorsArray: List<String> = currentBook.volumeInfo.authors
        if (authorsArray != null) {
            if (authorsArray.size > 1) {
                for (i in 0 until authorsArray.size) {
                    var obj = authorsArray.get(i)
                    if (i != authorsArray.size - 1) {
                        authors = (obj).plus(", ")
                    }
                    authors = authors.plus(obj).plus(" ")
                }
            } else if (authorsArray.size == 1) {
                var obj = authorsArray[0]
                authors = obj
            }
        } else {
            authors = "N/A"
        }
        holder.author.text = authors
        val page = " pages"
        var pages = currentBook.volumeInfo.pages
        if (pages != null) {
            holder.pages.text = currentBook.volumeInfo.pages + page
        } else {
            holder.pages.text = "N/A"
        }
        var date = currentBook.volumeInfo.date
        if (date != null) {
            holder.date.text = currentBook.volumeInfo.date
        } else {
            holder.date.text = "N/A"
        }
        var rate = currentBook.volumeInfo.rate
        if (rate != null) {
            holder.rating.text = currentBook.volumeInfo.rate
        } else {
            holder.rating.text = "N/A"
        }
        holder.bind(mBook[position], mListener)
    }

    override fun getItemCount(): Int {
        return mBook.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.cover
        val title: TextView = itemView.title
        val author: TextView = itemView.author
        val pages: TextView = itemView.pages
        val date: TextView = itemView.date
        val rating: TextView = itemView.rating

        fun bind(book: Book, listener: OnItemClickListener) {
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    listener.onItemClick(book)
                }
            })
        }
    }

    fun setData(book: ArrayList<Book>) {
        mBook.addAll(book)
        notifyDataSetChanged()
    }

}