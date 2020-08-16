
/**
 * 這兩組方法提供不同種類的單位轉換，如果要調整其中任何一種轉換邏輯或是進一步擴充其它單位轉換功能，都必須修改 UnitConversion 類別，
 */
// 單位轉換
class SRPUnitConversion {
    // 盎司(oz)轉毫升(ml)
    fun ozToml(oz: Double): Double {
        return oz / 0.033814
    }

    // 溫度攝氏(Celsius)轉華氏(Fahrenheit)
    fun temperatureCelsiusToFahrenheit(c: Double): Double {
        return c * 9 / 5 + 32
    }
}


/**
 *  比較好的作法是以獨立的類別提供所需的功能，以下將功能移至獨立的類別：
 */
class SRPOZConversion {
    fun conversions(oz: Double): Double {
        return oz / 0.033814
    }
}

class SRPTemperatureConversion {
    fun conversions(c: Double): Double {
        return c * 9 / 5 + 32
    }
}
