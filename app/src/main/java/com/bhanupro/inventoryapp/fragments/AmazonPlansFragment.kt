package com.bhanupro.inventoryapp.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bhanupro.inventoryapp.R
import com.bhanupro.inventoryapp.extentions.collapse
import com.bhanupro.inventoryapp.extentions.expand
import com.bhanupro.inventoryapp.model.AmazonPlans
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_amazon_plans.*
import kotlinx.android.synthetic.main.sku_row_item_layout.*

/**
 * A simple [Fragment] subclass.
 */
class AmazonPlansFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amazon_plans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewTreeObserver(view)
        setUpRecyclerView()
    }
    private fun setViewTreeObserver(view: View){
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val bottomSheetDialog = dialog as BottomSheetDialog
           val frameLayout:FrameLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            val behavior = BottomSheetBehavior.from(frameLayout).apply {
                peekHeight = 700
            }
        }
    }
    private fun setUpRecyclerView(){

        val planList = ArrayList<AmazonPlans>().apply {
            add(AmazonPlans(false,ArrayList<AmazonPlans.Shipments>().apply {
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
            }))

            add(AmazonPlans(false,ArrayList<AmazonPlans.Shipments>().apply {
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
            }))


            add(AmazonPlans(false,ArrayList<AmazonPlans.Shipments>().apply {
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
                add(AmazonPlans.Shipments(false,ArrayList<AmazonPlans.Shipments.Sku>().apply {
                    add(AmazonPlans.Shipments.Sku(unit = "430021"))
                    add(AmazonPlans.Shipments.Sku(unit = "430022"))
                }))
            }))
        }
        val amazonAdapter = PlansAdapter(planList)
        amazon_plans_rv?.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        amazon_plans_rv.adapter = amazonAdapter
        amazon_plans_rv.isNestedScrollingEnabled = true
    }
    private inner class PlansAdapter(val list:ArrayList<AmazonPlans>):RecyclerView.Adapter<PlansAdapter.ViewHolder>(){

        val viewPool = RecyclerView.RecycledViewPool()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlansAdapter.ViewHolder {
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.amazon_plans_row_item,parent,false)
            val viewHolder =  ViewHolder(view)
            viewHolder.shipmentRv.setRecycledViewPool(viewPool)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: PlansAdapter.ViewHolder, position: Int) {

            val data = list[position]
            holder.bindData(data)
            holder.rvSeparator.isVisible = position == 0

            if (!data.isCollapse){
                holder.ceContainer.background = ContextCompat.getDrawable(requireContext(),R.drawable.collapse_bg)
                holder.collapseExpand.text = getString(R.string.expand)
                holder.collapseExpand.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                holder.ceContainer.background = ContextCompat.getDrawable(requireContext(),R.drawable.expand_bg)
                holder.collapseExpand.setTextColor(Color.parseColor("#0239bf"))
                holder.collapseExpand.text = getString(R.string.collapse)
            }

            holder.collapseExpand.setOnClickListener {
                Log.d("PlansAdapter","collapseExpand: ${data.isCollapse}")
                if (!data.isCollapse){
                    holder.ceContainer.background = ContextCompat.getDrawable(requireContext(),R.drawable.collapse_bg)
                    holder.collapseExpand.text = getString(R.string.expand)
                    holder.collapseExpand.setTextColor(Color.parseColor("#FFFFFF"))
                    Log.d("PlansAdapter","expand")
                    data.isCollapse = true
                    notifyItemChanged(position)
                    //holder.notifyAdapter()
                }else{
                    holder.ceContainer.background = ContextCompat.getDrawable(requireContext(),R.drawable.expand_bg)
                    holder.collapseExpand.setTextColor(Color.parseColor("#0239bf"))
                    holder.collapseExpand.text = getString(R.string.collapse)
                    Log.d("PlansAdapter","collapse")
                    data.isCollapse = false
                    notifyItemChanged(position)
                    //holder.notifyAdapter()
                }
            }
        }
        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val shipmentRv:RecyclerView = itemView.findViewById(R.id.shipment_rv)
            val collapseExpand:TextView = itemView.findViewById(R.id.collapse_expand)
            val ceContainer:LinearLayout = itemView.findViewById(R.id.linearLayout)
            val rvSeparator:LinearLayout = itemView.findViewById(R.id.rv_separator)
            lateinit var shipmentAdapter: ShipmentAdapter

            fun bindData(data:AmazonPlans){
                setUpRecyclerView(data.isCollapse,data.shipmentList)
            }
            private fun setUpRecyclerView(isCollapse: Boolean,list: ArrayList<AmazonPlans.Shipments>){

                shipmentAdapter = ShipmentAdapter(list,isCollapse)
                shipmentRv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                shipmentRv.adapter = shipmentAdapter
            }
            fun notifyAdapter(){
                if (::shipmentAdapter.isInitialized){
                    shipmentAdapter.notifyDataSetChanged()
                }
            }

        }

    }

    private inner class ShipmentAdapter(val list: ArrayList<AmazonPlans.Shipments>,var isCollapse:Boolean):RecyclerView.Adapter<ShipmentAdapter.ViewHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
           val view = LayoutInflater.from(requireContext()).inflate(R.layout.shipments_row_layout,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = list[position]
            holder.bindData(position,data)

            Log.d("ShipmentAdapter","isCollapse: $isCollapse")

            Log.d("Shipment adapter","shipmentRvContainer isVisible: ${holder.shipmentRvContainer.visibility}")
            if (isCollapse && (holder.shipmentRvContainer.visibility == View.GONE)){
                holder.shipmentRvContainer.expand()
                holder.addAllSelectContainer.expand()
            }else if (!isCollapse && (holder.shipmentRvContainer.visibility == View.VISIBLE)){
                holder.shipmentRvContainer.collapse()
                holder.addAllSelectContainer.collapse()
            }
            var selectedSkuTv = ""
            data.skuList.forEach {
                if (it.isSelected){
                    Log.d("Shipment adapter","selected item: ${it.unit}")
                    selectedSkuTv+=it.unit+","
                }
            }
            holder.skusTv.text = selectedSkuTv

            holder.arrowImg.setOnClickListener {
                if (!list[position].isCollapse){
                    holder.arrowImg.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_keyboard_arrow_right))
                    holder.skuRv.collapse()
                    list[position].isCollapse = true
                    notifyItemChanged(position)
                }else{
                    holder.arrowImg.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_keyboard_arrow_down))
                    holder.skuRv.expand()
                    list[position].isCollapse = false
                    notifyItemChanged(position)
                }
            }
        }
        inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView),SkuUnitListener{
            val skuRv:RecyclerView = itemView.findViewById(R.id.recyclerView)
            val arrowImg:ImageView = itemView.findViewById(R.id.imageView)
            val skusTv:TextView = itemView.findViewById(R.id.textView9)
            val shipmentRvContainer:MaterialCardView = itemView.findViewById(R.id.materialCardView)
            val addAllSelectContainer:LinearLayout = itemView.findViewById(R.id.add_all_select_container)

            fun bindData(position: Int,data: AmazonPlans.Shipments){
                setUpRv(position,data.skuList)
            }
            private fun setUpRv(position: Int,list: ArrayList<AmazonPlans.Shipments.Sku>){
                val adapter = SkuAdapter(position,list,this)
                skuRv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                skuRv.adapter = adapter
            }

            override fun onClick(position: Int) {
                Log.d("Shipment adapter","clicked position is: $position")
                notifyItemChanged(position)
            }
        }

    }
    private inner class SkuAdapter(val rvPosition:Int,val list:ArrayList<AmazonPlans.Shipments.Sku>,val listener: SkuUnitListener):RecyclerView.Adapter<SkuAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkuAdapter.ViewHolder {
           val view = LayoutInflater.from(requireContext()).inflate(R.layout.sku_row_item_layout,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: SkuAdapter.ViewHolder, position: Int) {
            val sku = list[position]

            if (sku.isSelected){
                holder.skuLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.selected_sku_unit_bg)
            }else{
                holder.skuLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.unselected_sku_unit_bg)
            }
            holder.skuLayout.setOnClickListener {
                if (sku.isSelected){
                    sku.isSelected = false
                    holder.skuLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.unselected_sku_unit_bg)
                }else{
                    sku.isSelected = true
                    holder.skuLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.selected_sku_unit_bg)
                }
                Log.d("SkuAdapter","rvPosition: $rvPosition")
                listener.onClick(rvPosition)
            }
        }

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val skuLayout:ConstraintLayout = itemView.findViewById(R.id.skuLayout)
        }

    }

    interface SkuUnitListener{
        fun onClick(position: Int)
    }

}
