package com.bosh.jboxtwod

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.jbox2d.dynamics.BodyType

/**
 * @author lzq
 * @date  2022/4/26
 */
class JboxView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    View(context, attributeSet) {
    private var ratioForBox2dAndScreen = 1f

    private val paint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
            color = Color.RED
        }
    }

    val jboxImpl by lazy { JboxImpl() }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w,h,oldw,oldh)
        jboxImpl.onSizeChanged(w,h)
        ratioForBox2dAndScreen = w/ WIDTH_WORLD
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var list = jboxImpl.world.bodyList
        while (list != null) {
            if (list.type == BodyType.DYNAMIC) {
                val position = list.position
                val x = (position.x- WIDTH/2)*ratioForBox2dAndScreen
                val y = (position.y- WIDTH/2)*ratioForBox2dAndScreen
                val radius = WIDTH/2*ratioForBox2dAndScreen
                canvas?.drawCircle(x,y,radius,paint)
            }
            list = list.next
        }
        jboxImpl.invalidate()
        invalidate()
    }


}