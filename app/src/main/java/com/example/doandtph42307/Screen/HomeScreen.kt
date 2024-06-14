package com.example.doandtph42307.Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.doandtph42307.entity.SanPham
import com.example.doandtph42307.entity.getListSanPham

@Composable
fun HomeScreen(navController: NavController) {
    var listSanPham by remember { mutableStateOf(getListSanPham()) }

    var sp: SanPham? by remember { mutableStateOf(null) }
    var showdialogXoaSp by remember { mutableStateOf(false) }
    var showdialogSua by remember { mutableStateOf(false) }
    var showdiallogThem by remember { mutableStateOf(false) }

    var showchitietSp by remember { mutableStateOf(false) }
    if (showchitietSp) {
        ShowDialog(title = "Chi tiet san pham",
            content = "ten san pham :${sp?.name}\n gia san pham :${sp?.price}\n mo ta san pham :${sp?.mota}\n trang thai san pham :${sp?.trangThai}",
            onDismiss = { showchitietSp = false },
            onConfirm = { showchitietSp = false }
        )

    }

    Log.d("id", "HomeScreen: ${sp?.id.toString()}")
    ////dialog xoa
    if (showdialogXoaSp) {
        ShowDialog(
            title = "Xoa san pham",
            content = "Ban co muon xoa san pham nay",
            onDismiss = { showdialogXoaSp = false },
            onConfirm = {
                val temps = listSanPham.toMutableList()
                temps.remove(sp)
                listSanPham = temps
                showdialogXoaSp = false
            }

        )
    }
    ////dialog them
    if (showdiallogThem) {
        ShowDialogThemSuaSp(
            title = "Them san pham",
            onDismiss = { showdiallogThem = false },
            onConfirm = {
                listSanPham = (listSanPham + it).toMutableList()
                showdiallogThem = false
            }
        )
    }
    ////dialog sua
    if (showdialogSua) {
        ShowDialogThemSuaSp(
            title = "Sua san pham",
            sanPham = sp,
            onDismiss = { showdialogSua = false },
            onConfirm = { update ->
                listSanPham = listSanPham.map {
                    if (it.id == update.id) update else it
                }.toMutableList()
                showdialogSua = false
                Log.d("sua", "HomeScreen: $update")
            },
            showdilog = showdialogSua
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Quan ly san pham ", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { showdiallogThem = true }) {
            Text(text = "Them san pham")
        }
        LazyColumn {
            items(listSanPham) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            sp = it
                            showchitietSp = true
                        }
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = it.name)
                        Text(text = it.price.toString())
                    }
                    Column {
                        Text(text = it.mota!!)
                        Text(text = it.trangThai.toString())
                    }
                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_edit),
                            contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable {
                                    sp = it
                                    showdialogSua = true
                                }
                        )
                        Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                            contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable {
                                    sp = it
                                    showdialogXoaSp = true
                                })
                    }
                }

            }
        }
    }
}

@Composable
fun ShowDialogThemSuaSp(
    title: String,
    sanPham: SanPham? = null,
    onDismiss: () -> Unit = {},
    onConfirm: (SanPham) -> Unit = {}, showdilog: Boolean = true
) {
    var tenSanPham by remember { mutableStateOf("") }
    var giasanPham by remember { mutableStateOf("") }
    var motaSanPham by remember { mutableStateOf("") }
    var trangThaiSanPham by remember { mutableStateOf("") }
    Log.d("idcuthe", "ShowDialogThemSuaSp: ${sanPham?.id.toString()}")
    if (sanPham != null) {
        sanPham.id = sanPham.id
        tenSanPham = sanPham.name
        giasanPham = sanPham.price.toString()
        motaSanPham = sanPham.mota!!
        trangThaiSanPham = sanPham.trangThai.toString()
    }

    var context = LocalContext.current
    Dialog(onDismissRequest = { showdilog }) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineMedium)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = tenSanPham,
                        onValueChange = { tenSanPham = it },
                        label = { Text(text = "Ten san pham") },
                        modifier = Modifier
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        value = giasanPham,
                        onValueChange = { giasanPham = it },
                        label = { Text(text = "Gia san pham") },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = motaSanPham, onValueChange = { motaSanPham = it },
                    label = { Text(text = "Mo ta san pham") },
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = trangThaiSanPham,
                    onValueChange = { trangThaiSanPham = it },
                    label = { Text(text = "Trang thai san pham") },
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = {
//

                    val newsanPham = SanPham(
                        id = sanPham?.id ?: 0,
                        name = tenSanPham,
                        price = giasanPham.toFloat(),
                        mota = motaSanPham,
                        trangThai = trangThaiSanPham.toBoolean()
                    )
                    onConfirm(newsanPham)
                }) {
                    Text(text = "Luu")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

@Composable
private fun ShowDialog(
    title: String,
    content: String,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        },
        title = { Text(text = title) },
        text = { Text(text = content) }

    )
}