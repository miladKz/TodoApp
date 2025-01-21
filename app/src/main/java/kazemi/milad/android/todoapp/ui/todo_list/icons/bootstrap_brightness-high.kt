import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val BrightnessHigh: ImageVector
	get() {
		if (_BrightnessHigh != null) {
			return _BrightnessHigh!!
		}
		_BrightnessHigh = ImageVector.Builder(
            name = "BrightnessHigh",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
			path(
    			fill = SolidColor(Color(0xFF000000)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(8f, 11f)
				arcToRelative(3f, 3f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0f, -6f)
				arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 6f)
				moveToRelative(0f, 1f)
				arcToRelative(4f, 4f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -8f)
				arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 8f)
				moveTo(8f, 0f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.5f, 0.5f)
				verticalLineToRelative(2f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 0f)
				verticalLineToRelative(-2f)
				arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 0f)
				moveToRelative(0f, 13f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.5f, 0.5f)
				verticalLineToRelative(2f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 0f)
				verticalLineToRelative(-2f)
				arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 13f)
				moveToRelative(8f, -5f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.5f, 0.5f)
				horizontalLineToRelative(-2f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -1f)
				horizontalLineToRelative(2f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.5f, 0.5f)
				moveTo(3f, 8f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.5f, 0.5f)
				horizontalLineToRelative(-2f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, -1f)
				horizontalLineToRelative(2f)
				arcTo(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 8f)
				moveToRelative(10.657f, -5.657f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.707f)
				lineToRelative(-1.414f, 1.415f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -0.707f, -0.708f)
				lineToRelative(1.414f, -1.414f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.707f, 0f)
				moveToRelative(-9.193f, 9.193f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.707f)
				lineTo(3.05f, 13.657f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.707f, -0.707f)
				lineToRelative(1.414f, -1.414f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.707f, 0f)
				moveToRelative(9.193f, 2.121f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.707f, 0f)
				lineToRelative(-1.414f, -1.414f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.707f, -0.707f)
				lineToRelative(1.414f, 1.414f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.707f)
				moveTo(4.464f, 4.465f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.707f, 0f)
				lineTo(2.343f, 3.05f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 0.707f, -0.707f)
				lineToRelative(1.414f, 1.414f)
				arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 0.708f)
			}
		}.build()
		return _BrightnessHigh!!
	}

private var _BrightnessHigh: ImageVector? = null
