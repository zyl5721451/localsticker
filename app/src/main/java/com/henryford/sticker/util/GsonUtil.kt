package com.henryford.sticker.util

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object GsonUtil {
    private var gson: Gson = Gson()

    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    fun getJsonString(`object`: Any?): String? {
        if (`object` == null) {
            return null
        }
        var gsonString: String? = null
        try {
            gsonString = gson!!.toJson(`object`)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return gsonString
    }


    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    fun <T> parseObject(gsonString: String?, cls: Class<*>?): T? {
        var t: T? = null
        try {
            t = gson!!.fromJson(gsonString, cls) as T
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return t
    }

    fun <T> getFromObj(cls: Class<*>?, obj: Any?): T? {
        return if (obj != null) {
            try {
                val str = gson!!.toJson(obj)
                val bean = gson!!.fromJson(str, cls)
                if (bean != null) {
                    return bean as T
                }
            } catch (var5: Exception) {
                var5.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    fun <T> getFromJsonElement(cls: Class<*>?, obj: JsonElement?): T? {
        return if (obj != null) {
            try {
                val bean = gson!!.fromJson(obj, cls)
                if (bean != null) {
                    return bean as T
                }
            } catch (var5: Exception) {
                var5.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @return
     */
    fun <T> gsonToList(gsonString: String?): List<T>? {
        var list: List<T>? = ArrayList()
        try {
            list = gson!!.fromJson(gsonString, object : TypeToken<ArrayList<T>?>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun <T> gsonToList(gsonString: String?, t: Class<T>?): List<T>? {
        val list: MutableList<T> = ArrayList()
        try {
            val parser = JsonParser()
            val jsonarray = parser.parse(gsonString).asJsonArray
            for (element in jsonarray) {
                list.add(gson!!.fromJson(element, t))
            }
        } catch (e: Exception) {
        }
        return list
    }


    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    fun <T> gsonToListMaps(gsonString: String?): List<Map<String?, T>?>? {
        var list: List<Map<String?, T>?>? = null
        try {
            list = gson!!.fromJson(
                gsonString,
                object : TypeToken<List<Map<String?, T>?>?>() {}.type
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun <T> getGsonObj(gsonString: String?, type: Type?): T? {
        var t: T? = null
        try {
            t = gson!!.fromJson(gsonString, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return t
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    @Deprecated("") //有bug，建议用下面的gsonToMaps(String gsonString, TypeToken<Map<K, V>> type)
    fun <T> gsonToMaps(gsonString: String?): Map<String?, T>? {
        var map: Map<String?, T>? = null
        try {
            map = gson!!.fromJson(gsonString, object : TypeToken<Map<String?, T>?>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return map
    }

    fun <K, V> gsonToMaps(gsonString: String?, type: TypeToken<Map<K, V>?>): Map<K, V>? {
        var map: Map<K, V>? = null
        try {
            map = Gson().fromJson(gsonString, type.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return map
    }

    fun getStringIncludeExpose(obj: Any?): String? {
        try {
            return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(obj)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    fun getGson(): Gson? {
        return gson
    }
}