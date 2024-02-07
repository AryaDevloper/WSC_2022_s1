package com.example.wsc_2022_s1

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsc_2022_s1.tools.LocalData
import com.squareup.picasso.Picasso
import org.json.JSONArray


class EventsFragment : Fragment(R.layout.fragment_events) {

    private lateinit var recycler: RecyclerView
    private val data: LocalData by lazy {
        LocalData(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler)

        recycler.adapter = EventsRecyclerAdapter(requireContext(), data, findNavController(), "all")
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        view.findViewById<View>(R.id.filterAllButton).setOnClickListener {
            recycler.adapter = EventsRecyclerAdapter(requireContext(), data, findNavController(), "all")

        }
        view.findViewById<View>(R.id.filterUnreadButton).setOnClickListener {
            recycler.adapter = EventsRecyclerAdapter(requireContext(), data, findNavController(), "unread")
        }
        view.findViewById<View>(R.id.filterReadButton).setOnClickListener {
            recycler.adapter = EventsRecyclerAdapter(requireContext(), data, findNavController(), "read")

        }

    }

}


class EventsRecyclerAdapter(
    private val context: Context,
    private val data: LocalData,
    private val navController: NavController,
    private var filter: String
) :
    RecyclerView.Adapter<EventsRecyclerAdapter.Holder>() {

    private val list = data.getEvents()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(R.layout.event_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, i: Int) {
        if (filter == "unread" && list.getJSONObject(i).getString("status") == "read") {
            holder.itemView.visibility = View.GONE
            return
        }
        if (filter == "read" && list.getJSONObject(i).getString("status") == "unread") {
            holder.itemView.visibility = View.GONE
            return
        }

        val view = holder.itemView

        Picasso.get().load(list.getJSONObject(i).getJSONArray("pictures").getString(0))
            .into(view.findViewById<ImageView>(R.id.image))

        view.findViewById<TextView>(R.id.title).text = list.getJSONObject(i).getString("title")
        view.findViewById<TextView>(R.id.text).text = list.getJSONObject(i).getString("text")
        view.findViewById<TextView>(R.id.read).text = list.getJSONObject(i).getString("status")

        view.setOnClickListener {
            list.getJSONObject(i)
                .put("view_count", list.getJSONObject(i).getInt("view_count") + 1)
            list.getJSONObject(i).put("status", "read")

            val args = Bundle()
            args.putString("title", list.getJSONObject(i).getString("title"))
            args.putInt("viewCount", list.getJSONObject(i).getInt("view_count"))
            args.putStringArray(
                "images",
                arrayOf(
                    list.getJSONObject(i).getJSONArray("pictures").getString(0),
                    list.getJSONObject(i).getJSONArray("pictures").getString(1),
                    list.getJSONObject(i).getJSONArray("pictures").getString(2)
                )
            )
            args.putString("text", list.getJSONObject(i).getString("text"))
            data.setEvents(list)
            navController.navigate(R.id.action_eventsFragment_to_eventFragment, args)
        }

    }

    override fun getItemCount(): Int {
        return list.length()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

    }
}

