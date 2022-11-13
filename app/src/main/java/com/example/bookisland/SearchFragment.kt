package com.example.bookisland
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONArray


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private lateinit var mBookInput: EditText
    private lateinit var parentRecycler: RecyclerView
    private var mutableListTotal = mutableListOf<MutableList<Book>>()
    private val categories = mutableListOf<String>("Fiction","Business", "History")
    private lateinit var adapter: ParentAdapterSearch


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        mBookInput = view.findViewById(R.id.bookInput)
        parentRecycler = view.findViewById(R.id.parent_recyclerview)
        searchBooks("Fiction")
        searchBooks("Business")
        searchBooks("History")
        adapter = ParentAdapterSearch(view.context,categories,mutableListTotal)
        parentRecycler.adapter = adapter

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            val i = Intent(activity,SearchDisplay::class.java)
            i.putExtra("Query", mBookInput.text.toString())
            startActivity(i)
        }
        return view
    }
    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private fun searchBooks(query : String)
    {
        val client = AsyncHttpClient()
        val url = "https://www.googleapis.com/books/v1/volumes?q=${query}"
        client[
                url,
                object : JsonHttpResponseHandler()
                //connect these callbacks to your API call
                {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String,
                        throwable: Throwable?
                    ) {
                        throwable?.message?.let {
                            Log.e("SearchFragment", response)
                        }
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JsonHttpResponseHandler.JSON) {
                        val resultsJSON = json.jsonObject.getJSONArray("items")
                        var listOfBooks = mutableListOf<Book>()
                        try{
                            var i = 0
                            while(i< resultsJSON.length() && i < 10){

                                val book = resultsJSON.getJSONObject(i)
                                val volumeInfo = book.getJSONObject("volumeInfo")
                                var model = Book(null,null,null,null,null)
                                model.title = volumeInfo.getString("title")
                                val authors : JSONArray = volumeInfo.getJSONArray("authors")
                                if(authors.length() <= 1)
                                {
                                    model.authors = authors[0].toString()
                                }
                                else {
                                    var completeAuthors = ""
                                    var i = 0
                                    while(i<authors.length()- 1)
                                    {
                                        completeAuthors = completeAuthors + authors[i].toString() + ", "
                                        i++
                                    }
                                    completeAuthors += authors[i].toString()
                                    model.authors = completeAuthors
                                }
                                if(volumeInfo.getJSONObject("imageLinks").getString("thumbnail") != "")
                                {
                                    model.thumbnail = volumeInfo.getJSONObject("imageLinks").getString("thumbnail")
                                }

                                if(volumeInfo.getString("description") != "")
                                {
                                    model.description = volumeInfo.getString("description")
                                }
                                model.saleability = book.getJSONObject("saleInfo").getString("saleability")
                                /**if(model.saleability != "NOT_FOR_SALE") {
                                    model.price =
                                        book.getJSONObject("saleInfo").getJSONObject("retailPrice")
                                            .getDouble("amount")
                                }**/
                                listOfBooks.add(model)
                                i++
                            }

                            mutableListTotal.add(mutableListTotal.size,listOfBooks)

                            Log.d("SearchDisplay", "response successful")
                        } catch (e : Exception)
                        {
                            e.printStackTrace()
                        }
                        adapter.notifyDataSetChanged()
                    }
                }]
    }
}



