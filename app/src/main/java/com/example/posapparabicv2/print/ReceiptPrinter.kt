
package com.example.posapparabicv2.print

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.dantsu.escposprinter.textparser.PrinterTextParserImg

object ReceiptPrinter {
    fun textToBitmap(text: String, paperWidthMm: Int = 58, dpi: Int = 203): Bitmap {
        val widthPx = (paperWidthMm * dpi / 25.4).toInt()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
        paint.textSize = 32f
        val lines = text.split("\n")
        val lineHeight = (paint.textSize * 1.2).toInt()
        val heightPx = lineHeight * (lines.size + 2)
        val bmp = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        canvas.drawColor(android.graphics.Color.WHITE)
        var y = paint.textSize
        for (line in lines) {
            val measured = paint.measureText(line)
            val x = (widthPx - measured - 8).toFloat()
            canvas.drawText(line, x, y, paint)
            y += lineHeight
        }
        return bmp
    }

    fun printText(text: String, prefer58mm: Boolean = true) {
        val connection = BluetoothPrintersConnections.selectFirstPaired()
        if (connection == null) {
            println("لا توجد طابعة بلوتوث مقترنة")
            return
        }
        try {
            val paperWidth = if (prefer58mm) 58 else 80
            val bmp = textToBitmap(text, paperWidth)
            val printer = EscPosPrinter(connection, 203, 48f, 32)
            val imgHex = PrinterTextParserImg.bitmapToHexadecimalString(printer, bmp)
            printer.printFormattedTextAndCut("[C]<img>$imgHex</img>\n\n")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
