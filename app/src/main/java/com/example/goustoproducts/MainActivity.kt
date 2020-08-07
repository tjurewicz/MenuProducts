package com.example.goustoproducts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goustoproducts.application.appModule
import com.example.goustoproducts.application.networkModule
import com.example.goustoproducts.ui.products.ProductsFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment())
                .commitNow()
        }
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule, networkModule)
        }
    }
}
