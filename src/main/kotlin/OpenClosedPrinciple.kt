/**
 * 具有共同功能的擴充場合，透過遵循「開放－封閉原則（OCP）」，藉由衍生機制支援功能的擴充，並避免現存功能程式碼的修正
 *
 * 假設我們需要為進行轉換的數據進行轉換前的判斷，以避免小於0的數值進入轉換運算，想要完成功能的調整，必須同時修正兩個類別，
 * 除此之外還會造成重複程式碼的問題，比較好的作法抽象化實作內容，以介面或是抽象類別建立功能架構，進透過衍生類別進行功能實作。
 */
//建立一組新的介面 IUnitConversion 並且於其中定義轉換方法conversions 的參數規格，而類別 OCPOZConversion 與 OCPTemperatureConversion
//則實作介面conversions 方法，配置所需的功能程式碼。
interface IUnitConversion {
    fun conversions(d: Double): Double
}

class OCPOZConversion:IUnitConversion {
    override fun conversions(d: Double): Double {
        return d / 0.033814
    }
}

class OCPTemperatureConversion:IUnitConversion {
    override fun conversions(d: Double): Double {
        return d * 9 / 5 + 32
    }
}

fun unitConversion(conversion: IUnitConversion, d: Double): Double {
    return conversion.conversions(d)

}

fun ozml() {
    val conversion = OCPOZConversion()
    val oz = 10.0
    val ml = unitConversion(conversion, oz)
    println("ozml: $ml")
}

fun ftoc() {
    val conversion = OCPTemperatureConversion()
    val c = 100.0
    val f = unitConversion(conversion, c)
    println("ftoc: $f")
}
// OCP 的核心精神在於要求元件設計必須允許未來的擴充，稱為「open for extension」，但是不允許因此而修改現存的類別，
// 也就是所謂的「closed for modification」，遵往這個原則，當我們想要進一步擴充單位轉換功能，只需加入實作 IUnitConversion 介面的新類別，
// 不需要調整目前的任何類別實作即可達到擴充的目的，當然也不需要修正控制器中的轉換函式 unitConversion 。
fun main() {
    ozml()
    ftoc()
}