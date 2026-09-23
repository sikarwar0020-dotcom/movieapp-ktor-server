package models

data class GetRemoteMoviesModelClass(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)