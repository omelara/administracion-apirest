package com.empresa.administracion.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.123456789";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEApyOl1ia8E4uySNusmZTPe50cfi/3wHOiIQ4QP3m+LDwigtPF\r\n"
			+ "OgI178S+qK4gcurj/UuV/px6BT8rgH8JRxgjIZDpOkYRx35mHY/A9TrxPGj216q9\r\n"
			+ "LGJbqkEr+mulcqq4/k4YCEAESlEuzHU5AvPJX5f9WmiPlz4qi8pWTxgTmh1kaDd+\r\n"
			+ "wmUOJOU/CBsf4UNMD202XrXz2vDKNdrTjdO1DypTVUwsfl30pN8ccwKR1xTnlNEG\r\n"
			+ "yW/2XK36lFPb9UdTfMEXWVwvDv/w4pwObZkJy0nXRodFIe38S/mlQrI7iVaWhet/\r\n"
			+ "z1uC86H5Y9lbPOgtoZq15SpxBGXJtfFkxRA0CwIDAQABAoIBAGm6WiBy1kyC9R+z\r\n"
			+ "nqZ0hR+MDkcgrteWDjxcuhh9ObifvbEGp19/oIDw8y6fH7WyMtC7LMIoV+PTxv/8\r\n"
			+ "3HCVqzuDgJW1V2/b3Frc4dH/HDT7rRJWVl6qRZQYr+o0iC9WIbn0yq0FWfFtE26Q\r\n"
			+ "xKTCdGM9MS4ssJCa6a0OLLTR5Sx6X5EjzQpq+mIcc0yz8NfgPJtgVn8+S0GRze+S\r\n"
			+ "bTqjnqLBsL6wzHiwK+DNvY5SkrfmmHA3RDsoBMIr0KxJC5kMopwAEkbZ6ZYrvZtM\r\n"
			+ "xF8N6s8IoFReTf3BBKqBWyCuTKbRzYcV5UPXvkpaqpEIfCikUSKpGk0XPO4tcdXk\r\n"
			+ "uHgnXvECgYEA063G9mmpJt3aM15Q8Kym5UqsPLD5+WdELor8jl1k6aYzPiPO0ZCg\r\n"
			+ "479w0yrAUBPgTg1ESM2YQaNGfonj3dF93Xco7bT4q804z5Z7jFo5dtnjfWYf0i+F\r\n"
			+ "7tyK2PHxokCJOo3QnbX+EiM/KtUj7duggOyiR9C+QpJyYhe6L6E76XkCgYEAyiJ/\r\n"
			+ "6Ey6wyPzhBhBEdgbnHNsGYatoWulcrMfQHVr8KMgTF2AkkdtcOzIEDyOZsXiLc53\r\n"
			+ "VJQuG6yDaL/NFSqCmqq/qbOHYYpgTEaPmYYGLaW6T7GkfMVlcz8kwuE0A9c4pJBi\r\n"
			+ "yJj/XmlkZoi2XvEgGALkZ4ucSpHsSn6KTzln7KMCgYEAubd+l5UOZl5A4c6/Htmr\r\n"
			+ "uAJy6PCw8ONsS5V/jfxc3C4ISV2rR+wnbIn7GBorDlbR9kjnfyR6kCQLZlvegzAY\r\n"
			+ "yOSSETQ09K6iSGcT0IkcX609TTj7dDJjxLTUURC82ojanXfs72HMHueztQW5dye5\r\n"
			+ "Q0PaAT4gn1HTb0K4BK/mW6kCgYB4peDseafbw8CcrpFgIjpQxn/WOJG4UHUfIhTc\r\n"
			+ "N3nm1qb3jbOpG+MRzPKY5lwksaYPqzWEkTF4KR8CTcEOCiKMSLA/jT2YFazmuhMK\r\n"
			+ "EnnrkwjCeohwRK4J/L1naF3QkQxpBRlBHDkIVCWuIZmgz5FVY5yKxUTTKYf86syI\r\n"
			+ "/v2tuwKBgFd2hhq8hJ0+QVbTcU/whkQgMuu1gXVVY7+GfxwM0kJvzTkwcclHSs/v\r\n"
			+ "ISjf8bbtc/63Go+aQ0y2pATrPvsSFi1DKewuHtyMS6Dj93hH1/GG3GVie+SY5TnB\r\n"
			+ "8acgfB+NQmZatJNGj4534GcjxIDiMlSA+LsZy7vuwNhP9Quxok78\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApyOl1ia8E4uySNusmZTP\r\n"
			+ "e50cfi/3wHOiIQ4QP3m+LDwigtPFOgI178S+qK4gcurj/UuV/px6BT8rgH8JRxgj\r\n"
			+ "IZDpOkYRx35mHY/A9TrxPGj216q9LGJbqkEr+mulcqq4/k4YCEAESlEuzHU5AvPJ\r\n"
			+ "X5f9WmiPlz4qi8pWTxgTmh1kaDd+wmUOJOU/CBsf4UNMD202XrXz2vDKNdrTjdO1\r\n"
			+ "DypTVUwsfl30pN8ccwKR1xTnlNEGyW/2XK36lFPb9UdTfMEXWVwvDv/w4pwObZkJ\r\n"
			+ "y0nXRodFIe38S/mlQrI7iVaWhet/z1uC86H5Y9lbPOgtoZq15SpxBGXJtfFkxRA0\r\n"
			+ "CwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
 