package com.test.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/** JWT 工具類 */
public final class JwtUtil {

    /** 預設有效期（毫秒）— 1 小時 */
    public static final long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 建議：把祕鑰放到設定檔或環境變數。
     * 要求：Base64 編碼後的字串，解碼後長度 ≥ 32 bytes（對應 HS256）。
     * 下面只是示範值，請自行替換為隨機且足夠長度的字串。
     */
    public static final String BASE64_SECRET =
            "bXktdmVyeS1sb25nLXN1cGVyLXNlY3JldC1rZXktMzItYnl0ZXM="; // demo only

    private JwtUtil() {}

    /** 產生 JTI */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /** 建立 Token（subject 可放 userId 或 JSON 字串） */
    public static String createJWT(String subject) {
        return createJWT(getUUID(), subject, JWT_TTL);
    }

    /** 建立 Token（自訂 TTL） */
    public static String createJWT(String subject, Long ttlMillis) {
        long ttl = (ttlMillis == null ? JWT_TTL : ttlMillis);
        return createJWT(getUUID(), subject, ttl);
    }

    /** 建立 Token（自訂 jti 與 TTL） */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        SecretKey key = key();
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plusMillis(ttlMillis == null ? JWT_TTL : ttlMillis));

        return Jwts.builder()
                .id(id)                        // jti
                .subject(subject)              // 主題（可為 JSON 字串）
                .issuer("sg")                  // 簽發者
                .issuedAt(iat)                 // 簽發時間
                .expiration(exp)               // 過期時間
                .signWith(key, Jwts.SIG.HS256) // 0.12+：指定演算法常數
                .compact();
    }

    /** 驗證並解析 Claims（拋出各式簽章/過期例外時交由上層處理） */
    public static Claims parseJWT(String jwt) {
        SecretKey key = key();
        return Jwts.parser()
                .verifyWith(key)   // 0.12+ 驗簽
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    /** 產生 HMAC-SHA 金鑰（由 Base64 祕鑰還原） */
    private static SecretKey key() {
        // 若你想用純文字祕鑰（非 Base64），可改：Keys.hmacShaKeyFor("your-32+bytes-secret".getBytes(StandardCharsets.UTF_8))
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(BASE64_SECRET));
    }
}