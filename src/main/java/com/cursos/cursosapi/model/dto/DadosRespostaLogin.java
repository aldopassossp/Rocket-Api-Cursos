package com.cursos.cursosapi.model.dto;

import java.time.Instant;

public record DadosRespostaLogin(String token, Instant expirationDate, String role) {}

