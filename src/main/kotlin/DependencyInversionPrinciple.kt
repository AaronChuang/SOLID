/**
 * 依賴倒置原則 DIP (Dependency Inversion Principle)
 *
 * 1. 高階模組不應該依賴於低階模組，兩者都該依賴抽象。
 * 2. 抽象不應該依賴於具體實作方式。
 * 3. 具體實作方式則應該依賴抽象。
 *
 * 前面的例子SRP, OCP, LSP, ISP 皆滿足DIP
 */

internal class People {
    private val burger: Hamburger = Hamburger()
    fun eat() {
        burger.stuff()
    }

}
internal class Hamburger {
    fun stuff() {
        println("咔拉雞腿滿福堡")
    }
}
internal class People2 {
    private val spaghetti: Spaghetti = Spaghetti()
    fun eat() {
        spaghetti.fill();
    }

}

internal class Spaghetti {
    fun fill() {
        println("大蒜辣椒麵")
    }
}



// ******************************************************************************
// DIP Sample
internal interface Stuffer {
    fun stuff()
}
//由於 People 依賴 Stuffer 介面，就算未來替換 Stuffer 實作子類別 (食物)，
//也不用更改任何方法呼叫邏輯，確保了 統一的呼叫方式，
internal class People3(private val stuffer:Stuffer) {
    fun eat() {
        stuffer.stuff()
    }
}

internal class Hamburger2 : Stuffer {
    override fun stuff() {
        println("咔拉雞腿滿福堡")
    }
}

internal class Spaghetti2: Stuffer {
    override fun stuff() {
        println("大蒜辣椒麵")
    }
}

fun main() {
    val me = People()
    me.eat()
    val me2 = People2()
    me2.eat()
    val me3 = People3(Hamburger2())
    me3.eat()
    val me4 = People3(Spaghetti2())
    me4.eat()
}
