package ferrazfcf.catsapp.core.data.params

data class PaginationParams(
    val page: Int = 0,
    val limit: Int = 100
) {
    fun toMap(): HashMap<String, String> {
        return hashMapOf(
            "page" to page.toString(),
            "limit" to limit.toString()
        )
    }
}
