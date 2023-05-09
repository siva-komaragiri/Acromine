package app.sivakomaragiri.acromine.data

data class LongForms (val  lf: String = "",
                      val freq: Int,
                      val since:Int,
                      val vars: List<LongForm> = mutableListOf()
)