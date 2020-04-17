package com.bhanupro.inventoryapp.model


/**
 * Created by Bhanu Prakash Pasupula on 04,Apr-2020.
 * Email: bhanu.prakash@loansimple.in
 */
data class AmazonPlans(
    var isCollapse:Boolean = false,
    var shipmentList:ArrayList<Shipments> = ArrayList()
){
    data class Shipments(
        var isCollapse: Boolean = false,
        val skuList:ArrayList<Sku> = ArrayList()
    ){
        data class Sku(
            var isSelected:Boolean = false,
            var unit:String = ""
        )
    }
}