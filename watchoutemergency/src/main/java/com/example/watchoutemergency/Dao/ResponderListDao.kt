package com.example.watchoutemergency.Dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponderListDao {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("responderTypes")
    @Expose
    var data: List<ResponderListData>? = null

    override fun toString(): String {
        return "ResponderListDao{" + "status='" + status + '\''.toString() + ", message='" + message + '\''.toString() + ", responderTypes=" + data + '}'.toString()
    }
}

class ResponderListData{

    @SerializedName("_id")
    @Expose
    var _id: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("color_code")
    @Expose
    var color_code: String? = null
    @SerializedName("group_name")
    @Expose
    var group_name: String? = null

    override fun toString(): String {
        return "ResponderListData{" + "_id='" + _id + '\''.toString() + ", status='" + status + '\''.toString() + ", color_code=" + color_code +  ", group_name=" + group_name + '}'.toString()
    }
}