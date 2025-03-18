package com.boilerplate_spring_boot_kotlin.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {
    // TODO : secret key 수정 필요
    private val secretKey: SecretKey = Keys.hmacShaKeyFor("mySecretKeymySecretKeymySecretKeymySecretKey".toByteArray())
    private val expirationMs = 1000 * 60 * 60

    fun generateToken(email: String): String {
        val claims: Claims = Jwts.claims().setSubject(email)
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return getClaims(token).expiration.before(Date())
    }

    fun extractUsername(token: String): String {
        return getClaims(token).subject
    }

    fun isTokenValid(token: String, email: String): Boolean {
        return extractUsername(token) == email && !isTokenExpired(token)
    }
}
