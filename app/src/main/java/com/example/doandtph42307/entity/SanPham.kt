package com.example.doandtph42307.entity

data class SanPham(
    var id: Int = 0,
    var price: Float,
    var name: String,
    var mota: String?,
    var trangThai: Boolean?
) {
    override fun toString(): String {
        return "SanPham(id=$id, price=$price, name='$name', mota=$mota, trangThai=$trangThai)"
    }

}

fun getListSanPham(): MutableList<SanPham> {
    var list = mutableListOf<SanPham>()
    list.add(SanPham(1, 10f, "Sampham1", "", true))
    list.add(SanPham(2, 20f, "Sampham2", "", true))
    list.add(SanPham(3, 30f, "Sampham3", "", true))
    list.add(SanPham(4, 40f, "Sampham4", "", true))
    return list
}