package com.template.app.app.ui.test

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.template.app.app.ui.base.BaseActivity
import com.template.app.databinding.ActivityTestBinding

import com.zappstudio.databinding.ui.dialog.CustomBottomSheetDialog
import com.zappstudio.zappbase.domain.model.Listable

// TODO Borrar - Activity de prueba
class TestActivity : BaseActivity() {

    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.clickHandler = ClickHandler()

        setContentView(binding.root)
    }

    inner class ClickHandler {

        fun onFirstButtonClick() {
            showPopup(
                title = "Título",
                description = "Hola, esto es una descripción para un pop up genérico. Esto es un texto largo",
                positiveButton = "Aceptar",
                positiveListener = { _, _ ->
                },
                negativeButton = "Cancelar"
            )
        }

        fun onSecondButtonClick() {
            com.zappstudio.databinding.ui.util.showPopupList(
                activity = this@TestActivity,
                fragmentManager = supportFragmentManager,
                title = "Título lista",
                list = getListablesList(),
                icon = null,
                image = null,
                callback = null,
                selected = selected,
                hideButtons = true,
                builder = CustomBottomSheetDialog.Builder(this@TestActivity),
                cancelable = true,
                onDismiss = null
            )
        }
    }

    private val selected = MutableLiveData<List<Listable>>()

    private fun getListablesList(): List<Listable> {
        return listOf(
            CustomListable("1", "Elemento 1"),
            CustomListable("2", "Elemento 2"),
            CustomListable("3", "Elemento 3"),
            CustomListable("4", "Elemento 4"),
            CustomListable("5", "Elemento 5"),
            CustomListable("6", "Elemento 6"),
            CustomListable("7", "Elemento 7"),
            CustomListable("8", "Elemento 8"),
            CustomListable("9", "Elemento 9"),
            CustomListable("10", "Elemento 10"),
            CustomListable("11", "Elemento 11"),
        )
    }

    data class CustomListable(
        override val id: String,
        val description: String
    ) : Listable {

        override fun getString(context: Context): String = description
    }
}
