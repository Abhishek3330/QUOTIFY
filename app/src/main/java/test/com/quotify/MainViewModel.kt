package test.com.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

/**
 * Created by Mukesh on 05-12-2022.
 */
class MainViewModel(val context: Context):ViewModel() {

    private var quoteList:Array<Quote> = emptyArray()
    private var index=0

    init {
        quoteList = lodeQuoteFromAssets()
    }

    private fun lodeQuoteFromAssets(): Array<Quote> {
       val inputStream =context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextquote() = quoteList[++index]

    fun previousquote() = quoteList[--index]

}