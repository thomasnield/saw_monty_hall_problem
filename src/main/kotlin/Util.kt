import java.util.concurrent.ThreadLocalRandom


fun <T> List<T>.randomFirstOrNull(): T? {
    if (size == 0) return null

    val random = ThreadLocalRandom.current().nextInt(0,size)

    return this[random]
}


/**
 * Samples a single random element `T` from a `List<T>`, and throws an error if no elements exist
 */
fun <T> List<T>.randomFirst() = randomFirstOrNull()?: throw Exception("No elements found!")

/**
 * Samples a single random element `T` from a `Sequence<T>`, and throws an error if no elements exist
 */
fun <T> Sequence<T>.randomFirst() = toList().randomFirst()

/**
 * Samples a single random element `T` from a `Sequence<T>`, and throws an error if no elements exist
 */
fun <T> Iterable<T>.randomFirst() = toList().randomFirst()

/**
 * Samples `n` distinct random elements `T` from an `Iterable<T>`
 */
fun <T> List<T>.randomDistinct(sampleSize: Int): List<T> {

    val cappedSampleSize = if (sampleSize > size) size else sampleSize

    return (0..Int.MAX_VALUE).asSequence().map {
        ThreadLocalRandom.current().nextInt(0,size)
    }.distinct()
            .take(cappedSampleSize)
            .map { this[it] }
            .toList()
}


/**
 * Samples `n` random elements `T` from a `Sequence<T>`
 */
fun <T> Sequence<T>.random(sampleSize: Int) = toList().random(sampleSize)

/**
 * Samples `n` random elements `T` from an `Iterable<T>`
 */
fun <T> List<T>.random(sampleSize: Int): List<T> {

    val cappedSampleSize = if (sampleSize > size) size else sampleSize

    return (0..Int.MAX_VALUE).asSequence().map {
        ThreadLocalRandom.current().nextInt(0,size)
    }.take(cappedSampleSize)
            .map { this[it] }
            .toList()
}