data class Node(
    var data: Int,
    var left: Node?,
    var right: Node?
) {
    constructor(data: Int): this(data, null, null)
}
