package com.escm.lbn

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.escm.lbn.entities.RSSObject
import com.escm.recyclerview.R
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var adapter: MyAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var newsArrayList: ArrayList<News>

lateinit var imageId :Array<Int>
lateinit var heading: Array<String>
lateinit var news: Array<String>

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var rssObject: RSSObject? = null

    private val RSS_to_Json_API: String = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fanchor.fm%2Fs%2F7c5dc5ec%2Fpodcast%2Frss&api_key=nhtulqdx9ugmgyb7xc553tvnhqw3ryjoryibrofm&count=1000";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /*
        dataInitialize()
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(newsArrayList)
        recyclerView.adapter = adapter
        */
        loadRSS(view)
    }


    private fun dataInitialize(){
        newsArrayList = arrayListOf<News>()

        imageId = arrayOf(R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,
                        R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j)

        heading = arrayOf(getString(R.string.head_1),getString(R.string.head_2),getString(R.string.head_3),
            getString(R.string.head_4),getString(R.string.head_5),getString(R.string.head_6),
            getString(R.string.head_7),getString(R.string.head_8),getString(R.string.head_9),
            getString(R.string.head_10))

        news = arrayOf(getString(R.string.news_a),getString(R.string.news_b),getString(R.string.news_c),
            getString(R.string.news_d),getString(R.string.news_e),getString(R.string.news_f),
            getString(R.string.news_g),getString(R.string.news_h),getString(R.string.news_i),
            getString(R.string.news_j))

        for(i in imageId.indices){
            val news = News(imageId[i], heading[i])
            newsArrayList.add(news)
        }
    }

    private fun loadRSS(view: View) {
        val loadRSSAsync: AsyncTask<String, String, String> =
            object : AsyncTask<String, String, String>() {
                var mDialog = ProgressDialog(context)
                override fun onPreExecute() {
                    mDialog.setMessage("Por favor espere...")
                    mDialog.show()
                }

                protected override fun doInBackground(vararg params: String): String? {
                    val result: String
                    val http = HTTPDataHandler()
                    result = http.GetHTTPData(params[0])
                    return result
                }

                @SuppressLint("StaticFieldLeak")
                override fun onPostExecute(s: String?) {
                    mDialog.dismiss()
                    rssObject = Gson().fromJson(s, RSSObject::class.java)
                    /*val adapter = FeedAdapter(rssObject!!, context!!)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    */
                    val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    recyclerView = view.findViewById(R.id.recycler_view)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.setHasFixedSize(true)
                    val adapter = FeedAdapter(rssObject!!, context!!)
                    recyclerView.adapter = adapter
                }
            }
        val url_get_data: StringBuilder = StringBuilder(RSS_to_Json_API)
        //url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString())
    }
}