package jp.mixi.android.commons.json;

/**
 * Marker interface for types supporting JSON conversion.
 * This will prevent Proguard from obfuscating or shrinking fields required for the conversion.
 * Classes being serialized to / deserialized from JSON data should implement this interface.
 * It shouldn't be necessary to implement this interface in internal classes of type which already
 * implement it.
 *
 * JSON変換対象の型がこのインターフェイスを実装するべき。
 */
public interface JsonConvertible { }
