
package com.example.posapparabicv2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posapparabicv2.databinding.ActivitySaleBinding
import com.example.posapparabicv2.print.ReceiptPrinter

class SaleActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySaleBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPay.setOnClickListener {
            val receipt = """إيصال مبيعات
------------------------
المنتج: قهوة تركي
الكمية: 2
السعر: 25.00 جنيه
------------------------
الإجمالي: 50.00 جنيه
التاريخ: 2025-11-06
شكراً لتعاملكم""".trimIndent()

            ReceiptPrinter.printText(receipt, prefer58mm=true)
        }
    }
}
