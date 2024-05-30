package com.example.quote_app.Model

data class Quotes(
                  val quoteId:Int,
                  val _id: String,
                  val author: String,
                  val authorSlug: String,
                  val content: String,
                  val dateAdded: String,
                  val dateModified: String,
                  val length: Int
    )
