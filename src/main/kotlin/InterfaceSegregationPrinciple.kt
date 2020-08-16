/**
 *  儘量使用介面取代具體的實作類別建構應用程式，而介面的設計要特別注意儘量根據特定需求來定義其規格，以多個小型介面來取代單一大型介面，
 *  將特定的功能規格定義在專屬的小型介面。
 */
interface IISPUnitConversion {
    fun conversions(d: Double): Double
}

interface IISPUnitConversionUK {
    fun conversionsUK(d: Double): Double
}

class ISPOZConversion:IISPUnitConversion, IISPUnitConversionUK {
    override fun conversions(d: Double): Double {
        return d / 0.033814
    }
    override fun conversionsUK(d: Double): Double {
        return d / 0.035195
    }
}

class ISPTemperatureConversion:IISPUnitConversion {
    override fun conversions(d: Double): Double {
        return d * 9 / 5 + 32
    }
}

fun unitConversion(conversion: IISPUnitConversion, d: Double): Double {
    return conversion.conversions(d)

}

fun unitConversionUK(conversion: IISPUnitConversionUK, d: Double): Double {
    return conversion.conversionsUK(d)
}

fun ozmlISP() {
    val conversion = ISPOZConversion()
    val oz = 10.0
    val ml = unitConversionUK(conversion, oz)
    println("ozml: $ml")
}

fun ftocISP() {
    val conversion = ISPTemperatureConversion()
    val c = 100.0
    val f = unitConversion(conversion, c)
    println("ftoc: $f")
}

fun main() {
    ozmlISP()
    ftocISP()
}