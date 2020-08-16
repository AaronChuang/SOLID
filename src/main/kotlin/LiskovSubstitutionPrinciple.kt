
/**
 *Liskov 替換原則」建議，物件在應用程式因為功能調整抽換相同型別的子類別物件時，不應對原來的功能與程式運算邏輯造成影響。
 */
//假若在設計單位轉換功能中，希望可以提供進一步的彈性，增加不同制度的單位轉換功能，調整介面規格程式碼如下：
interface ILSPUnitConversion {
    fun conversions(d: Double): Double
    // 英制盎司的單位換算
    fun conversionsUK(d: Double): Double
}

class LSPOZConversion:ILSPUnitConversion {
    override fun conversions(d: Double): Double {
        return d / 0.033814
    }
    override fun conversionsUK(d: Double): Double {
        return d / 0.035195
    }
}

class LSPTemperatureConversion:ILSPUnitConversion {
    override fun conversions(d: Double): Double {
        return d * 9 / 5 + 32
    }
    // 違反了 LSP ，在不同的狀況下，相同介面的子型別物件無法直接抽換，導致了錯誤的結果，而且這種錯誤可以通過編譯，直到程式執行的過程中才可能出現。
    // 處理違反 LSP 設計所造成的問題
    // 1. 可以透過「介面隔離原則（ISP）」來
    // 2. 合約設計 DBC (Design By Contract) : 利用前置條件與後置條件來約束子類別，確保本質不變
    // 3. 避免繼承 : 盡量利用組合
    override fun conversionsUK(d: Double): Double {
        throw RuntimeException("溫度無英制單位")
    }
}

fun unitConversion(conversion: ILSPUnitConversion, d: Double): Double {
    return conversion.conversions(d)

}

fun unitConversionUK(conversion: ILSPUnitConversion, d: Double): Double {
    return conversion.conversionsUK(d)
}

fun ozmlLSP() {
    val conversion = LSPOZConversion()
    val oz = 10.0
    val ml = unitConversionUK(conversion, oz)
    println("ozml: $ml")
}

fun ftocLSP() {
    val conversion = LSPTemperatureConversion()
    val c = 100.0
    val f = unitConversionUK(conversion, c)
    println("ftoc: $f")
}

fun main() {
    ozmlLSP()
    ftocLSP()
}