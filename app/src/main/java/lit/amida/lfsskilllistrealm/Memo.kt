package lit.amida.lfsskilllistrealm

import io.realm.RealmObject

open class Memo(
    open var title: String = "",
    open var content: String = ""
): RealmObject()