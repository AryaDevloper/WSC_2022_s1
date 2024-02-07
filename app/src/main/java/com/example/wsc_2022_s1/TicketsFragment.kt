package com.example.wsc_2022_s1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsc_2022_s1.tools.LocalData
import org.json.JSONArray

class TicketsFragment : Fragment() {

    private val data: LocalData by lazy {
        LocalData(requireContext())
    }

    private fun initOpenRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.openRecycler)
        recyclerView.adapter = TicketsAdapter(requireContext(), data.getOpeningTickets(), findNavController())
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initCloseRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.closeRecycler)
        recyclerView.adapter = TicketsAdapter(requireContext(), data.getClosingTickets(), findNavController())
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOpenRecycler(view)
        initCloseRecycler(view)
    }

}

class TicketsAdapter(private val context: Context, private val list: JSONArray, private val navController: NavController) :
    RecyclerView.Adapter<TicketsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(context).inflate(R.layout.ticket_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list.getJSONObject(position)
        holder.itemView.findViewById<TextView>(R.id.nameText).text = item.getString("name")
        holder.itemView.findViewById<TextView>(R.id.seatText).text = item.getString("seat")

        holder.itemView.setOnClickListener {
            navController.navigate(R.id.action_ticketsFragment_to_createTicketFragment)
        }
    }

    override fun getItemCount(): Int {
        return list.length()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}