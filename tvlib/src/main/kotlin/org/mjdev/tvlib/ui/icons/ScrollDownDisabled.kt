/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("ObjectPropertyName", "UnusedReceiverParameter")

package org.mjdev.tvlib.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Filled.ScrollDownDisabled: ImageVector
    get() {
        if (_scroll_down_disabled != null) {
            return _scroll_down_disabled!!
        }
        _scroll_down_disabled = Builder(
            name = "ScrollDownDisabled",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(4.167f, 10.9268f)
                lineTo(3.8602f, 10.5944f)
                curveTo(3.7971f, 10.5261f, 3.7405f, 10.4553f, 3.6902f, 10.3826f)
                curveTo(3.7434f, 10.3529f, 3.8047f, 10.336f, 3.87f, 10.336f)
                curveTo(4.0744f, 10.336f, 4.24f, 10.5016f, 4.24f, 10.706f)
                curveTo(4.24f, 10.7887f, 4.2129f, 10.8651f, 4.167f, 10.9268f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.5896f, 12.468f)
                lineTo(5.0881f, 11.9247f)
                curveTo(5.1551f, 11.8575f, 5.2477f, 11.816f, 5.35f, 11.816f)
                curveTo(5.5544f, 11.816f, 5.72f, 11.9816f, 5.72f, 12.186f)
                curveTo(5.72f, 12.299f, 5.6694f, 12.4001f, 5.5896f, 12.468f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(6.9993f, 13.9951f)
                lineTo(6.5155f, 13.471f)
                curveTo(6.5808f, 13.366f, 6.6972f, 13.296f, 6.83f, 13.296f)
                curveTo(7.0344f, 13.296f, 7.2f, 13.4616f, 7.2f, 13.666f)
                curveTo(7.2f, 13.8094f, 7.1185f, 13.9337f, 6.9993f, 13.9951f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.3943f, 15.5064f)
                lineTo(7.9575f, 15.0332f)
                curveTo(8.0052f, 14.884f, 8.145f, 14.776f, 8.31f, 14.776f)
                curveTo(8.5144f, 14.776f, 8.68f, 14.9416f, 8.68f, 15.146f)
                curveTo(8.68f, 15.3214f, 8.558f, 15.4682f, 8.3943f, 15.5064f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.7688f, 16.9954f)
                lineTo(9.4201f, 16.6177f)
                curveTo(9.4245f, 16.4172f, 9.5885f, 16.256f, 9.79f, 16.256f)
                curveTo(9.9944f, 16.256f, 10.16f, 16.4216f, 10.16f, 16.626f)
                curveTo(10.16f, 16.8303f, 9.9944f, 16.996f, 9.79f, 16.996f)
                curveTo(9.7829f, 16.996f, 9.7758f, 16.9958f, 9.7688f, 16.9954f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.3935f, 18.4549f)
                curveTo(11.2227f, 18.393f, 11.0594f, 18.305f, 10.9098f, 18.1908f)
                curveTo(10.9034f, 18.1635f, 10.9f, 18.1352f, 10.9f, 18.106f)
                curveTo(10.9f, 17.9016f, 11.0657f, 17.736f, 11.27f, 17.736f)
                curveTo(11.4744f, 17.736f, 11.64f, 17.9016f, 11.64f, 18.106f)
                curveTo(11.64f, 18.267f, 11.5371f, 18.4041f, 11.3935f, 18.4549f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.1054f, 18.2094f)
                curveTo(12.9597f, 18.3167f, 12.8017f, 18.3997f, 12.6369f, 18.4584f)
                curveTo(12.4879f, 18.4106f, 12.38f, 18.2709f, 12.38f, 18.106f)
                curveTo(12.38f, 17.9016f, 12.5457f, 17.736f, 12.75f, 17.736f)
                curveTo(12.9544f, 17.736f, 13.12f, 17.9016f, 13.12f, 18.106f)
                curveTo(13.12f, 18.1419f, 13.1149f, 18.1766f, 13.1054f, 18.2094f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.5998f, 16.6395f)
                lineTo(14.273f, 16.9935f)
                curveTo(14.2589f, 16.9952f, 14.2446f, 16.996f, 14.23f, 16.996f)
                curveTo(14.0257f, 16.996f, 13.86f, 16.8303f, 13.86f, 16.626f)
                curveTo(13.86f, 16.4216f, 14.0257f, 16.256f, 14.23f, 16.256f)
                curveTo(14.4344f, 16.256f, 14.6f, 16.4216f, 14.6f, 16.626f)
                curveTo(14.6f, 16.6305f, 14.5999f, 16.635f, 14.5998f, 16.6395f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.0674f, 15.0497f)
                lineTo(15.6426f, 15.5099f)
                curveTo(15.4704f, 15.4781f, 15.34f, 15.3273f, 15.34f, 15.146f)
                curveTo(15.34f, 14.9416f, 15.5057f, 14.776f, 15.71f, 14.776f)
                curveTo(15.881f, 14.776f, 16.025f, 14.892f, 16.0674f, 15.0497f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(17.5124f, 13.4842f)
                lineTo(17.0346f, 14.0018f)
                curveTo(16.9079f, 13.9431f, 16.82f, 13.8148f, 16.82f, 13.666f)
                curveTo(16.82f, 13.4616f, 16.9857f, 13.296f, 17.19f, 13.296f)
                curveTo(17.3283f, 13.296f, 17.4489f, 13.3718f, 17.5124f, 13.4842f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(18.9421f, 11.9353f)
                lineTo(18.4419f, 12.4773f)
                curveTo(18.3555f, 12.4095f, 18.3f, 12.3042f, 18.3f, 12.186f)
                curveTo(18.3f, 11.9816f, 18.4657f, 11.816f, 18.67f, 11.816f)
                curveTo(18.7776f, 11.816f, 18.8745f, 11.8619f, 18.9421f, 11.9353f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(20.344f, 10.3909f)
                curveTo(20.2952f, 10.4607f, 20.2405f, 10.5287f, 20.1799f, 10.5944f)
                lineTo(19.8622f, 10.9385f)
                curveTo(19.8108f, 10.875f, 19.78f, 10.7941f, 19.78f, 10.706f)
                curveTo(19.78f, 10.5016f, 19.9457f, 10.336f, 20.15f, 10.336f)
                curveTo(20.2212f, 10.336f, 20.2876f, 10.3561f, 20.344f, 10.3909f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(19.7812f, 7.776f)
                curveTo(19.935f, 7.8688f, 20.0718f, 7.9833f, 20.1893f, 8.1139f)
                curveTo(20.1764f, 8.1153f, 20.1633f, 8.116f, 20.15f, 8.116f)
                curveTo(19.9558f, 8.116f, 19.7965f, 7.9663f, 19.7812f, 7.776f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(18.3789f, 7.5176f)
                lineTo(18.8327f, 7.5176f)
                curveTo(18.8773f, 7.5176f, 18.9212f, 7.5191f, 18.9645f, 7.522f)
                curveTo(19.0119f, 7.5841f, 19.04f, 7.6618f, 19.04f, 7.746f)
                curveTo(19.04f, 7.9503f, 18.8744f, 8.116f, 18.67f, 8.116f)
                curveTo(18.4657f, 8.116f, 18.3f, 7.9503f, 18.3f, 7.746f)
                curveTo(18.3f, 7.6598f, 18.3295f, 7.5805f, 18.3789f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.8989f, 7.5176f)
                lineTo(17.4811f, 7.5176f)
                curveTo(17.5306f, 7.5805f, 17.56f, 7.6598f, 17.56f, 7.746f)
                curveTo(17.56f, 7.9503f, 17.3944f, 8.116f, 17.19f, 8.116f)
                curveTo(16.9857f, 8.116f, 16.82f, 7.9503f, 16.82f, 7.746f)
                curveTo(16.82f, 7.6598f, 16.8495f, 7.5805f, 16.8989f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(15.4189f, 7.5176f)
                lineTo(16.0011f, 7.5176f)
                curveTo(16.0506f, 7.5805f, 16.08f, 7.6598f, 16.08f, 7.746f)
                curveTo(16.08f, 7.9503f, 15.9144f, 8.116f, 15.71f, 8.116f)
                curveTo(15.5057f, 8.116f, 15.34f, 7.9503f, 15.34f, 7.746f)
                curveTo(15.34f, 7.6598f, 15.3695f, 7.5805f, 15.4189f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.9389f, 7.5176f)
                lineTo(14.5211f, 7.5176f)
                curveTo(14.5705f, 7.5805f, 14.6f, 7.6598f, 14.6f, 7.746f)
                curveTo(14.6f, 7.9503f, 14.4344f, 8.116f, 14.23f, 8.116f)
                curveTo(14.0257f, 8.116f, 13.86f, 7.9503f, 13.86f, 7.746f)
                curveTo(13.86f, 7.6598f, 13.8895f, 7.5805f, 13.9389f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.4589f, 7.5176f)
                lineTo(13.0411f, 7.5176f)
                curveTo(13.0905f, 7.5805f, 13.12f, 7.6598f, 13.12f, 7.746f)
                curveTo(13.12f, 7.9503f, 12.9544f, 8.116f, 12.75f, 8.116f)
                curveTo(12.5457f, 8.116f, 12.38f, 7.9503f, 12.38f, 7.746f)
                curveTo(12.38f, 7.6598f, 12.4095f, 7.5805f, 12.4589f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.9789f, 7.5176f)
                lineTo(11.5611f, 7.5176f)
                curveTo(11.6105f, 7.5805f, 11.64f, 7.6598f, 11.64f, 7.746f)
                curveTo(11.64f, 7.9503f, 11.4744f, 8.116f, 11.27f, 8.116f)
                curveTo(11.0657f, 8.116f, 10.9f, 7.9503f, 10.9f, 7.746f)
                curveTo(10.9f, 7.6598f, 10.9295f, 7.5805f, 10.9789f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.4989f, 7.5176f)
                lineTo(10.0811f, 7.5176f)
                curveTo(10.1305f, 7.5805f, 10.16f, 7.6598f, 10.16f, 7.746f)
                curveTo(10.16f, 7.9503f, 9.9944f, 8.116f, 9.79f, 8.116f)
                curveTo(9.5857f, 8.116f, 9.42f, 7.9503f, 9.42f, 7.746f)
                curveTo(9.42f, 7.6598f, 9.4495f, 7.5805f, 9.4989f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.0189f, 7.5176f)
                lineTo(8.6011f, 7.5176f)
                curveTo(8.6505f, 7.5805f, 8.68f, 7.6598f, 8.68f, 7.746f)
                curveTo(8.68f, 7.9503f, 8.5144f, 8.116f, 8.31f, 8.116f)
                curveTo(8.1057f, 8.116f, 7.94f, 7.9503f, 7.94f, 7.746f)
                curveTo(7.94f, 7.6598f, 7.9695f, 7.5805f, 8.0189f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(6.5389f, 7.5176f)
                lineTo(7.1211f, 7.5176f)
                curveTo(7.1705f, 7.5805f, 7.2f, 7.6598f, 7.2f, 7.746f)
                curveTo(7.2f, 7.9503f, 7.0344f, 8.116f, 6.83f, 8.116f)
                curveTo(6.6257f, 8.116f, 6.46f, 7.9503f, 6.46f, 7.746f)
                curveTo(6.46f, 7.6598f, 6.4895f, 7.5805f, 6.5389f, 7.5176f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.0544f, 7.5235f)
                curveTo(5.1045f, 7.5196f, 5.1555f, 7.5176f, 5.2073f, 7.5176f)
                lineTo(5.6411f, 7.5176f)
                curveTo(5.6905f, 7.5805f, 5.72f, 7.6598f, 5.72f, 7.746f)
                curveTo(5.72f, 7.9503f, 5.5544f, 8.116f, 5.35f, 8.116f)
                curveTo(5.1457f, 8.116f, 4.98f, 7.9503f, 4.98f, 7.746f)
                curveTo(4.98f, 7.6625f, 5.0077f, 7.5854f, 5.0544f, 7.5235f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(4.2375f, 7.7891f)
                curveTo(4.2162f, 7.9731f, 4.0598f, 8.116f, 3.87f, 8.116f)
                curveTo(3.8631f, 8.116f, 3.8563f, 8.1158f, 3.8494f, 8.1154f)
                curveTo(3.9618f, 7.9903f, 4.0918f, 7.8798f, 4.2375f, 7.7891f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(4.24f, 9.226f)
                curveTo(4.24f, 9.4303f, 4.0744f, 9.596f, 3.87f, 9.596f)
                curveTo(3.6657f, 9.596f, 3.5f, 9.4303f, 3.5f, 9.226f)
                curveTo(3.5f, 9.0217f, 3.6657f, 8.856f, 3.87f, 8.856f)
                curveTo(4.0744f, 8.856f, 4.24f, 9.0217f, 4.24f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.35f, 9.596f)
                curveTo(5.5544f, 9.596f, 5.72f, 9.4303f, 5.72f, 9.226f)
                curveTo(5.72f, 9.0217f, 5.5544f, 8.856f, 5.35f, 8.856f)
                curveTo(5.1457f, 8.856f, 4.98f, 9.0217f, 4.98f, 9.226f)
                curveTo(4.98f, 9.4303f, 5.1457f, 9.596f, 5.35f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.2f, 9.226f)
                curveTo(7.2f, 9.4303f, 7.0344f, 9.596f, 6.83f, 9.596f)
                curveTo(6.6257f, 9.596f, 6.46f, 9.4303f, 6.46f, 9.226f)
                curveTo(6.46f, 9.0217f, 6.6257f, 8.856f, 6.83f, 8.856f)
                curveTo(7.0344f, 8.856f, 7.2f, 9.0217f, 7.2f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.31f, 9.596f)
                curveTo(8.5144f, 9.596f, 8.68f, 9.4303f, 8.68f, 9.226f)
                curveTo(8.68f, 9.0217f, 8.5144f, 8.856f, 8.31f, 8.856f)
                curveTo(8.1057f, 8.856f, 7.94f, 9.0217f, 7.94f, 9.226f)
                curveTo(7.94f, 9.4303f, 8.1057f, 9.596f, 8.31f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.16f, 9.226f)
                curveTo(10.16f, 9.4303f, 9.9944f, 9.596f, 9.79f, 9.596f)
                curveTo(9.5857f, 9.596f, 9.42f, 9.4303f, 9.42f, 9.226f)
                curveTo(9.42f, 9.0217f, 9.5857f, 8.856f, 9.79f, 8.856f)
                curveTo(9.9944f, 8.856f, 10.16f, 9.0217f, 10.16f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 9.596f)
                curveTo(11.4744f, 9.596f, 11.64f, 9.4303f, 11.64f, 9.226f)
                curveTo(11.64f, 9.0217f, 11.4744f, 8.856f, 11.27f, 8.856f)
                curveTo(11.0657f, 8.856f, 10.9f, 9.0217f, 10.9f, 9.226f)
                curveTo(10.9f, 9.4303f, 11.0657f, 9.596f, 11.27f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 9.226f)
                curveTo(13.12f, 9.4303f, 12.9544f, 9.596f, 12.75f, 9.596f)
                curveTo(12.5457f, 9.596f, 12.38f, 9.4303f, 12.38f, 9.226f)
                curveTo(12.38f, 9.0217f, 12.5457f, 8.856f, 12.75f, 8.856f)
                curveTo(12.9544f, 8.856f, 13.12f, 9.0217f, 13.12f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.23f, 9.596f)
                curveTo(14.4344f, 9.596f, 14.6f, 9.4303f, 14.6f, 9.226f)
                curveTo(14.6f, 9.0217f, 14.4344f, 8.856f, 14.23f, 8.856f)
                curveTo(14.0257f, 8.856f, 13.86f, 9.0217f, 13.86f, 9.226f)
                curveTo(13.86f, 9.4303f, 14.0257f, 9.596f, 14.23f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.08f, 9.226f)
                curveTo(16.08f, 9.4303f, 15.9144f, 9.596f, 15.71f, 9.596f)
                curveTo(15.5057f, 9.596f, 15.34f, 9.4303f, 15.34f, 9.226f)
                curveTo(15.34f, 9.0217f, 15.5057f, 8.856f, 15.71f, 8.856f)
                curveTo(15.9144f, 8.856f, 16.08f, 9.0217f, 16.08f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(17.19f, 9.596f)
                curveTo(17.3944f, 9.596f, 17.56f, 9.4303f, 17.56f, 9.226f)
                curveTo(17.56f, 9.0217f, 17.3944f, 8.856f, 17.19f, 8.856f)
                curveTo(16.9857f, 8.856f, 16.82f, 9.0217f, 16.82f, 9.226f)
                curveTo(16.82f, 9.4303f, 16.9857f, 9.596f, 17.19f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(19.04f, 9.226f)
                curveTo(19.04f, 9.4303f, 18.8744f, 9.596f, 18.67f, 9.596f)
                curveTo(18.4657f, 9.596f, 18.3f, 9.4303f, 18.3f, 9.226f)
                curveTo(18.3f, 9.0217f, 18.4657f, 8.856f, 18.67f, 8.856f)
                curveTo(18.8744f, 8.856f, 19.04f, 9.0217f, 19.04f, 9.226f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(20.15f, 9.596f)
                curveTo(20.3544f, 9.596f, 20.52f, 9.4303f, 20.52f, 9.226f)
                curveTo(20.52f, 9.0217f, 20.3544f, 8.856f, 20.15f, 8.856f)
                curveTo(19.9457f, 8.856f, 19.78f, 9.0217f, 19.78f, 9.226f)
                curveTo(19.78f, 9.4303f, 19.9457f, 9.596f, 20.15f, 9.596f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.35f, 11.076f)
                curveTo(5.5544f, 11.076f, 5.72f, 10.9103f, 5.72f, 10.706f)
                curveTo(5.72f, 10.5016f, 5.5544f, 10.336f, 5.35f, 10.336f)
                curveTo(5.1457f, 10.336f, 4.98f, 10.5016f, 4.98f, 10.706f)
                curveTo(4.98f, 10.9103f, 5.1457f, 11.076f, 5.35f, 11.076f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.2f, 10.706f)
                curveTo(7.2f, 10.9103f, 7.0344f, 11.076f, 6.83f, 11.076f)
                curveTo(6.6257f, 11.076f, 6.46f, 10.9103f, 6.46f, 10.706f)
                curveTo(6.46f, 10.5016f, 6.6257f, 10.336f, 6.83f, 10.336f)
                curveTo(7.0344f, 10.336f, 7.2f, 10.5016f, 7.2f, 10.706f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.31f, 11.076f)
                curveTo(8.5144f, 11.076f, 8.68f, 10.9103f, 8.68f, 10.706f)
                curveTo(8.68f, 10.5016f, 8.5144f, 10.336f, 8.31f, 10.336f)
                curveTo(8.1057f, 10.336f, 7.94f, 10.5016f, 7.94f, 10.706f)
                curveTo(7.94f, 10.9103f, 8.1057f, 11.076f, 8.31f, 11.076f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.16f, 10.706f)
                curveTo(10.16f, 10.9103f, 9.9944f, 11.076f, 9.79f, 11.076f)
                curveTo(9.5857f, 11.076f, 9.42f, 10.9103f, 9.42f, 10.706f)
                curveTo(9.42f, 10.5016f, 9.5857f, 10.336f, 9.79f, 10.336f)
                curveTo(9.9944f, 10.336f, 10.16f, 10.5016f, 10.16f, 10.706f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 11.076f)
                curveTo(11.4744f, 11.076f, 11.64f, 10.9103f, 11.64f, 10.706f)
                curveTo(11.64f, 10.5016f, 11.4744f, 10.336f, 11.27f, 10.336f)
                curveTo(11.0657f, 10.336f, 10.9f, 10.5016f, 10.9f, 10.706f)
                curveTo(10.9f, 10.9103f, 11.0657f, 11.076f, 11.27f, 11.076f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 10.706f)
                curveTo(13.12f, 10.9103f, 12.9544f, 11.076f, 12.75f, 11.076f)
                curveTo(12.5457f, 11.076f, 12.38f, 10.9103f, 12.38f, 10.706f)
                curveTo(12.38f, 10.5016f, 12.5457f, 10.336f, 12.75f, 10.336f)
                curveTo(12.9544f, 10.336f, 13.12f, 10.5016f, 13.12f, 10.706f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.23f, 11.076f)
                curveTo(14.4344f, 11.076f, 14.6f, 10.9103f, 14.6f, 10.706f)
                curveTo(14.6f, 10.5016f, 14.4344f, 10.336f, 14.23f, 10.336f)
                curveTo(14.0257f, 10.336f, 13.86f, 10.5016f, 13.86f, 10.706f)
                curveTo(13.86f, 10.9103f, 14.0257f, 11.076f, 14.23f, 11.076f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.08f, 10.706f)
                curveTo(16.08f, 10.9103f, 15.9144f, 11.076f, 15.71f, 11.076f)
                curveTo(15.5057f, 11.076f, 15.34f, 10.9103f, 15.34f, 10.706f)
                curveTo(15.34f, 10.5016f, 15.5057f, 10.336f, 15.71f, 10.336f)
                curveTo(15.9144f, 10.336f, 16.08f, 10.5016f, 16.08f, 10.706f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(17.19f, 11.076f)
                curveTo(17.3944f, 11.076f, 17.56f, 10.9103f, 17.56f, 10.706f)
                curveTo(17.56f, 10.5016f, 17.3944f, 10.336f, 17.19f, 10.336f)
                curveTo(16.9857f, 10.336f, 16.82f, 10.5016f, 16.82f, 10.706f)
                curveTo(16.82f, 10.9103f, 16.9857f, 11.076f, 17.19f, 11.076f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(19.04f, 10.706f)
                curveTo(19.04f, 10.9103f, 18.8744f, 11.076f, 18.67f, 11.076f)
                curveTo(18.4657f, 11.076f, 18.3f, 10.9103f, 18.3f, 10.706f)
                curveTo(18.3f, 10.5016f, 18.4657f, 10.336f, 18.67f, 10.336f)
                curveTo(18.8744f, 10.336f, 19.04f, 10.5016f, 19.04f, 10.706f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(7.2f, 12.186f)
                curveTo(7.2f, 12.3903f, 7.0344f, 12.556f, 6.83f, 12.556f)
                curveTo(6.6257f, 12.556f, 6.46f, 12.3903f, 6.46f, 12.186f)
                curveTo(6.46f, 11.9816f, 6.6257f, 11.816f, 6.83f, 11.816f)
                curveTo(7.0344f, 11.816f, 7.2f, 11.9816f, 7.2f, 12.186f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.31f, 12.556f)
                curveTo(8.5144f, 12.556f, 8.68f, 12.3903f, 8.68f, 12.186f)
                curveTo(8.68f, 11.9816f, 8.5144f, 11.816f, 8.31f, 11.816f)
                curveTo(8.1057f, 11.816f, 7.94f, 11.9816f, 7.94f, 12.186f)
                curveTo(7.94f, 12.3903f, 8.1057f, 12.556f, 8.31f, 12.556f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.16f, 12.186f)
                curveTo(10.16f, 12.3903f, 9.9944f, 12.556f, 9.79f, 12.556f)
                curveTo(9.5857f, 12.556f, 9.42f, 12.3903f, 9.42f, 12.186f)
                curveTo(9.42f, 11.9816f, 9.5857f, 11.816f, 9.79f, 11.816f)
                curveTo(9.9944f, 11.816f, 10.16f, 11.9816f, 10.16f, 12.186f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 12.556f)
                curveTo(11.4744f, 12.556f, 11.64f, 12.3903f, 11.64f, 12.186f)
                curveTo(11.64f, 11.9816f, 11.4744f, 11.816f, 11.27f, 11.816f)
                curveTo(11.0657f, 11.816f, 10.9f, 11.9816f, 10.9f, 12.186f)
                curveTo(10.9f, 12.3903f, 11.0657f, 12.556f, 11.27f, 12.556f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 12.186f)
                curveTo(13.12f, 12.3903f, 12.9544f, 12.556f, 12.75f, 12.556f)
                curveTo(12.5457f, 12.556f, 12.38f, 12.3903f, 12.38f, 12.186f)
                curveTo(12.38f, 11.9816f, 12.5457f, 11.816f, 12.75f, 11.816f)
                curveTo(12.9544f, 11.816f, 13.12f, 11.9816f, 13.12f, 12.186f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.23f, 12.556f)
                curveTo(14.4344f, 12.556f, 14.6f, 12.3903f, 14.6f, 12.186f)
                curveTo(14.6f, 11.9816f, 14.4344f, 11.816f, 14.23f, 11.816f)
                curveTo(14.0257f, 11.816f, 13.86f, 11.9816f, 13.86f, 12.186f)
                curveTo(13.86f, 12.3903f, 14.0257f, 12.556f, 14.23f, 12.556f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.08f, 12.186f)
                curveTo(16.08f, 12.3903f, 15.9144f, 12.556f, 15.71f, 12.556f)
                curveTo(15.5057f, 12.556f, 15.34f, 12.3903f, 15.34f, 12.186f)
                curveTo(15.34f, 11.9816f, 15.5057f, 11.816f, 15.71f, 11.816f)
                curveTo(15.9144f, 11.816f, 16.08f, 11.9816f, 16.08f, 12.186f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(17.19f, 12.556f)
                curveTo(17.3944f, 12.556f, 17.56f, 12.3903f, 17.56f, 12.186f)
                curveTo(17.56f, 11.9816f, 17.3944f, 11.816f, 17.19f, 11.816f)
                curveTo(16.9857f, 11.816f, 16.82f, 11.9816f, 16.82f, 12.186f)
                curveTo(16.82f, 12.3903f, 16.9857f, 12.556f, 17.19f, 12.556f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.31f, 14.036f)
                curveTo(8.5144f, 14.036f, 8.68f, 13.8703f, 8.68f, 13.666f)
                curveTo(8.68f, 13.4616f, 8.5144f, 13.296f, 8.31f, 13.296f)
                curveTo(8.1057f, 13.296f, 7.94f, 13.4616f, 7.94f, 13.666f)
                curveTo(7.94f, 13.8703f, 8.1057f, 14.036f, 8.31f, 14.036f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.16f, 13.666f)
                curveTo(10.16f, 13.8703f, 9.9944f, 14.036f, 9.79f, 14.036f)
                curveTo(9.5857f, 14.036f, 9.42f, 13.8703f, 9.42f, 13.666f)
                curveTo(9.42f, 13.4616f, 9.5857f, 13.296f, 9.79f, 13.296f)
                curveTo(9.9944f, 13.296f, 10.16f, 13.4616f, 10.16f, 13.666f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 14.036f)
                curveTo(11.4744f, 14.036f, 11.64f, 13.8703f, 11.64f, 13.666f)
                curveTo(11.64f, 13.4616f, 11.4744f, 13.296f, 11.27f, 13.296f)
                curveTo(11.0657f, 13.296f, 10.9f, 13.4616f, 10.9f, 13.666f)
                curveTo(10.9f, 13.8703f, 11.0657f, 14.036f, 11.27f, 14.036f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 13.666f)
                curveTo(13.12f, 13.8703f, 12.9544f, 14.036f, 12.75f, 14.036f)
                curveTo(12.5457f, 14.036f, 12.38f, 13.8703f, 12.38f, 13.666f)
                curveTo(12.38f, 13.4616f, 12.5457f, 13.296f, 12.75f, 13.296f)
                curveTo(12.9544f, 13.296f, 13.12f, 13.4616f, 13.12f, 13.666f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.23f, 14.036f)
                curveTo(14.4344f, 14.036f, 14.6f, 13.8703f, 14.6f, 13.666f)
                curveTo(14.6f, 13.4616f, 14.4344f, 13.296f, 14.23f, 13.296f)
                curveTo(14.0257f, 13.296f, 13.86f, 13.4616f, 13.86f, 13.666f)
                curveTo(13.86f, 13.8703f, 14.0257f, 14.036f, 14.23f, 14.036f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.08f, 13.666f)
                curveTo(16.08f, 13.8703f, 15.9144f, 14.036f, 15.71f, 14.036f)
                curveTo(15.5057f, 14.036f, 15.34f, 13.8703f, 15.34f, 13.666f)
                curveTo(15.34f, 13.4616f, 15.5057f, 13.296f, 15.71f, 13.296f)
                curveTo(15.9144f, 13.296f, 16.08f, 13.4616f, 16.08f, 13.666f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(10.16f, 15.146f)
                curveTo(10.16f, 15.3503f, 9.9944f, 15.516f, 9.79f, 15.516f)
                curveTo(9.5857f, 15.516f, 9.42f, 15.3503f, 9.42f, 15.146f)
                curveTo(9.42f, 14.9416f, 9.5857f, 14.776f, 9.79f, 14.776f)
                curveTo(9.9944f, 14.776f, 10.16f, 14.9416f, 10.16f, 15.146f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 15.516f)
                curveTo(11.4744f, 15.516f, 11.64f, 15.3503f, 11.64f, 15.146f)
                curveTo(11.64f, 14.9416f, 11.4744f, 14.776f, 11.27f, 14.776f)
                curveTo(11.0657f, 14.776f, 10.9f, 14.9416f, 10.9f, 15.146f)
                curveTo(10.9f, 15.3503f, 11.0657f, 15.516f, 11.27f, 15.516f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 15.146f)
                curveTo(13.12f, 15.3503f, 12.9544f, 15.516f, 12.75f, 15.516f)
                curveTo(12.5457f, 15.516f, 12.38f, 15.3503f, 12.38f, 15.146f)
                curveTo(12.38f, 14.9416f, 12.5457f, 14.776f, 12.75f, 14.776f)
                curveTo(12.9544f, 14.776f, 13.12f, 14.9416f, 13.12f, 15.146f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(14.23f, 15.516f)
                curveTo(14.4344f, 15.516f, 14.6f, 15.3503f, 14.6f, 15.146f)
                curveTo(14.6f, 14.9416f, 14.4344f, 14.776f, 14.23f, 14.776f)
                curveTo(14.0257f, 14.776f, 13.86f, 14.9416f, 13.86f, 15.146f)
                curveTo(13.86f, 15.3503f, 14.0257f, 15.516f, 14.23f, 15.516f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(11.27f, 16.996f)
                curveTo(11.4744f, 16.996f, 11.64f, 16.8303f, 11.64f, 16.626f)
                curveTo(11.64f, 16.4216f, 11.4744f, 16.256f, 11.27f, 16.256f)
                curveTo(11.0657f, 16.256f, 10.9f, 16.4216f, 10.9f, 16.626f)
                curveTo(10.9f, 16.8303f, 11.0657f, 16.996f, 11.27f, 16.996f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(13.12f, 16.626f)
                curveTo(13.12f, 16.8303f, 12.9544f, 16.996f, 12.75f, 16.996f)
                curveTo(12.5457f, 16.996f, 12.38f, 16.8303f, 12.38f, 16.626f)
                curveTo(12.38f, 16.4216f, 12.5457f, 16.256f, 12.75f, 16.256f)
                curveTo(12.9544f, 16.256f, 13.12f, 16.4216f, 13.12f, 16.626f)
                close()
            }
        }.build()
        return _scroll_down_disabled!!
    }

private var _scroll_down_disabled: ImageVector? = null
