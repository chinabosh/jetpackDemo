package com.bosh.jboxtwod

import androidx.annotation.FloatRange
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.collision.shapes.EdgeShape
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.BodyType
import org.jbox2d.dynamics.FixtureDef
import org.jbox2d.dynamics.World
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

/**
 * @author lzq
 * @date  2022/4/26
 */

const val WIDTH = 1f  // 常量，小球在世界的宽
const val WIDTH_WORLD = 15f  // 常量，世界宽

class JboxImpl {
    public val world: World = World(Vec2(0f, 0f))

    private var width = 0f    // 宽度
    private var height = 0f  // 高度
    private var ratio = 1f   // 高宽比
    private var ratioForBox2dToScreen = 1f // 屏幕宽和世界宽的比例

    fun onSizeChanged(w: Int, h: Int) {
        width = w.toFloat()
        height = h.toFloat()
        ratio = height / width
        ratioForBox2dToScreen = width / WIDTH_WORLD
        initEdges()
    }

    fun initEdges() {
        // 创建边界

        val edgeList = listOf(
            Vec2(0f, 0f),
            Vec2(WIDTH_WORLD, 0f),
            Vec2(WIDTH_WORLD, WIDTH_WORLD * ratio),
            Vec2(0f, WIDTH_WORLD * ratio),
        )

        for (i in 0..3) {
            val bodyDef = BodyDef()
            bodyDef.type = BodyType.STATIC
            val body = world.createBody(bodyDef)
            val boxShape = EdgeShape()
            boxShape.set(edgeList[i], edgeList[(i + 1) % 4])
            val fixtureDef = FixtureDef()
            fixtureDef.shape = boxShape
            fixtureDef.density = 1f
            fixtureDef.restitution = 1f
            fixtureDef.friction = 0.5f
            body.createFixture(fixtureDef)
        }
    }

    fun createBody() {
        val bodyDef = BodyDef()
        bodyDef.type = BodyType.DYNAMIC
        bodyDef.position = Vec2((WIDTH_WORLD / 2), WIDTH_WORLD * ratio)
        //初始速度
        bodyDef.linearVelocity = Vec2(Random.nextFloat() * 10, -WIDTH_WORLD * ratio * Random.nextFloat() * 10)
        val circleShape = CircleShape()
        circleShape.radius = WIDTH / 2
        val ballFixtureDef = FixtureDef()
        ballFixtureDef.shape = circleShape
        ballFixtureDef.density = 1f
        //密度
        ballFixtureDef.density = 1f
        //摩擦系数
        ballFixtureDef.friction = 0.1f
        //弹性，1完全弹性，0无弹性
        ballFixtureDef.restitution = 0.99f

        val ballBody = world.createBody(bodyDef)
        ballBody.createFixture(ballFixtureDef)
    }

    fun invalidate() {
        world.step(16/1000f,20,20)
    }

    fun startWorld(){
        createBody()
        invalidate()
    }

}